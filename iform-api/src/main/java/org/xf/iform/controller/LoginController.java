package org.xf.iform.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;
import org.xf.iform.common.utils.SamlUtils;
import org.xf.iform.common.utils.JwtUtils;
import org.xf.iform.common.utils.UserDetailsImpl;
import org.xf.iform.core.common.BaseResponse;
import org.xf.iform.service.services.user.UserInfoService;
import org.xf.iform.data.dto.ChangPasswordDto;
import org.xf.iform.data.dto.JwtResponseDto;
import org.xf.iform.data.dto.LoginDto;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.IOException;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.*;
import java.security.cert.CertificateException;
import java.text.SimpleDateFormat;
import java.util.*;

@Slf4j
@RestController
@RequestMapping("/v1/login/api")
public class LoginController {
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    UserInfoService userInfoService;

    @PostMapping("/loginPassWord")
    @Operation(description = "使用者帳密登入")
    public ResponseEntity<?> loginPassWord(@Valid @RequestBody LoginDto loginDto) {

        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(loginDto.getUserId(), loginDto.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        BaseResponse<JwtResponseDto> respDto = new BaseResponse<>();
        respDto.setMessage("登入成功");
        respDto.setData(new JwtResponseDto(jwt, userDetails.getUsername()));
        return ResponseEntity.ok(respDto);
    }

    @PutMapping("/changPassWord")
    @Operation(description = "變更使用者登入密碼")
    public ResponseEntity<?> changPassWord(@Valid @RequestBody ChangPasswordDto changPasswordDto) {
        userInfoService.changePassword(changPasswordDto.getUsername(), changPasswordDto.getPassword(), changPasswordDto.getNewPassword());

        BaseResponse<String> respDto = new BaseResponse<>();
        respDto.setMessage("密碼變更成功");
        return ResponseEntity.ok(respDto);
    }

    @PostMapping("loginSaml")
    @Operation(description = "SAML登入導轉")
    public ResponseEntity<?> loginSaml() {
        return ResponseEntity.ok("");
    }


    @PostMapping("jkstest")
    @Operation(description = "jks測試")
    public ResponseEntity<?> jkstest() {
        PrivateKey key;
        try {
            key = SamlUtils.getPrivateKey();
        } catch (KeyStoreException | CertificateException | IOException |NoSuchAlgorithmException | UnrecoverableKeyException e) {
            log.error("jsk測試發生錯誤", e);
            throw new RuntimeException(e);
//        } catch (CertificateException e) {
//            throw new RuntimeException(e);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        } catch (NoSuchAlgorithmException e) {
//            throw new RuntimeException(e);
//        } catch (UnrecoverableKeyException e) {
//            throw new RuntimeException(e);
        }
        createXml();
        return ResponseEntity.ok(key);
    }

    @Value("${xfiform.app.cathaysaml.returnUrl}")
    private String defaultReturnUrl;

    @GetMapping("samlLoginUrlTest")
    @Operation(description = "samlLoginUrl測試")
    public ResponseEntity<BaseResponse<?>> samlLoginUrlTest(@RequestParam(required = false) String returnUrl) throws IOException {
        if (StringUtils.isBlank(returnUrl)) returnUrl = defaultReturnUrl;
        String samlStr = samlLoginTest();
        byte[] samlStrByte = samlStr.getBytes();
        String samlStrBase64 = Base64.getEncoder().encodeToString(samlStrByte);
        String samlUrlEncode;
        String returnUrlEncode;
        try {
            samlUrlEncode = URLEncoder.encode(samlStrBase64, "UTF-8");
            returnUrlEncode = URLEncoder.encode(returnUrl, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            log.error("URL編碼發生錯誤", e);
            throw new RuntimeException(e);
        }
        String samlLoginUrl = samlEndpoint + "?SAMLRequest=" + samlUrlEncode + "&RelayState=" + returnUrlEncode;
        BaseResponse<String> respDto = new BaseResponse<>();
        respDto.setData(samlLoginUrl);
        return ResponseEntity.ok(respDto);
    }



    @Value("${xfiform.app.cathaysaml.signingAlgorithm}")
    String signingAlgorithm;
    @Value("${xfiform.app.cathaysaml.digestMethod}")
    String digestMethod;

    @GetMapping("samlLoginTest")
    @Operation(description = "samlLogin測試")
    public String samlLoginTest() {
        log.info("signingAlgorithm=" + signingAlgorithm);
        log.info("digestMethod=" + digestMethod);
        String samlSignedXml;
        Document samlXmlDoc = createXml();
        try {
            samlSignedXml = SamlUtils.signSaml(samlXmlDoc, signingAlgorithm, digestMethod);
        } catch (Exception e) {
            log.error("SAML XML 簽署發生錯誤", e);
            throw new RuntimeException(e);
        }
        return samlSignedXml;
    }

    @Value("${xfiform.app.cathaysaml.samlEndpoint}")
    String samlEndpoint;
    @Value("${xfiform.app.cathaysaml.issuer}")
    String issuer;
    public Document createXml() {

        String id = "authid";
        id = "iform_" + UUID.randomUUID();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        String issueInstant = dateFormat.format(new Date());
//        String issueInstant = Instant.now().toString();

        try {
            // 建立XML
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document xmlDoc = docBuilder.newDocument();

            // 建立根元素<saml2p:AuthnRequest>
            Element root = xmlDoc.createElementNS("urn:oasis:names:tc:SAML:2.0:protocol", "saml2p:AuthnRequest");
            xmlDoc.appendChild(root);
            root.setAttribute("xmlns:saml2", "urn:oasis:names:tc:SAML:2.0:assertion");
            root.setAttribute("Destination", samlEndpoint);
            root.setAttribute("ForceAuthn", "false");
            root.setAttribute("ID", id);
            root.setIdAttribute("ID", true);
            root.setAttribute("IsPassive", "false");
            root.setAttribute("IssueInstant", issueInstant);
            root.setAttribute("ProtocolBinding", "urn:oasis:names:tc:SAML:2.0:bindings:HTTP-POST");
            root.setAttribute("Version", "2.0");

            // 建立<Issuer>元素
            Element element1 = xmlDoc.createElementNS("urn:oasis:names:tc:SAML:2.0:assertion", "saml2:Issuer");
            element1.setAttribute("Format", "urn:oasis:names:tc:SAML:2.0:nameid-format:entity");
            Text issuerText = xmlDoc.createTextNode(issuer);
            element1.appendChild(issuerText);
            root.appendChild(element1);

            // 建立<saml2p:NameIDPolicy>元素
            Element element2 = xmlDoc.createElementNS("urn:oasis:names:tc:SAML:2.0:protocol", "saml2p:NameIDPolicy");
            element2.setAttribute("AllowCreate", "true");
            element2.setAttribute("Format", "urn:oasis:names:tc:SAML:1.1:nameid-format:emailAddress");
            root.appendChild(element2);

            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer transformer;
            try {
                transformer = tf.newTransformer();
                StringWriter writer = new StringWriter();
                transformer.transform(new DOMSource(xmlDoc), new StreamResult(writer));
                log.info("SamlXML(" + issueInstant + "):" + writer.getBuffer().toString());
            } catch (Exception e) {
                log.error("SamlXML to Log發生錯誤", e);
            }
            return xmlDoc;
            // 輸出XML文档

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
            log.error("xmlCreate錯誤", e);
            return null;
        }
}

//    /**
//     * 產生"登入頁面"的URL
//     * @param Saml2LoginFormModel
//     * @return
//     */
//    private String CreateLoginUrl(Saml2LoginFormModel model)
//    {
//        //CheckSaml2LoginFormModel(model);//確認是否都有值
//        /*SamlEndpoint = https://tw3.cathaylife.com.tw/fsso/sps/saml20idp/saml20/login*/
//        /*Issuer = https://cathayhong.site:8080/api/login/input*/
//        var xmlDoc = _xmlDoc.CreateXmlDoc(model.SamlEndpoint, model.Issuer);
//        X509Certificate2 cert = new X509Certificate2(
//            model.CertPath/*SP Key*/,
//            Encoding.UTF8.GetString(Convert.FromBase64String(model.CertPassword)),
//            X509KeyStorageFlags.MachineKeySet | X509KeyStorageFlags.PersistKeySet);
//        var signature = Sign(new Saml2SignModel
//        {
//            XmlDocument = xmlDoc,
//                Cert = cert,
//        });
//        byte[] bytes = Encoding.Default.GetBytes(xmlDoc.OuterXml);
//        var smalRequest = HttpUtility.UrlEncode(Convert.ToBase64String(bytes));
//        //產生"前往ibm"的url
//        var q = "?SAMLRequest=" + smalRequest + "&RelayState=" + HttpUtility.UrlEncode(model.ReturnUrl);
//        var url = model.SamlEndpoint + q;
//        _log.Info("[Saml2Authentication CreateLoginUrl]", "    RelayState=" + model.ReturnUrl);
//        return url;
//    }

}
