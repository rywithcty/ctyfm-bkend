package org.xf.iform.common.utils;

import com.sun.org.apache.xml.internal.security.c14n.CanonicalizationException;
import com.sun.org.apache.xml.internal.security.c14n.Canonicalizer;
import com.sun.org.apache.xml.internal.security.c14n.InvalidCanonicalizerException;
import com.sun.org.apache.xml.internal.security.exceptions.XMLSecurityException;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.crypto.MarshalException;
import javax.xml.crypto.dsig.*;
import javax.xml.crypto.dsig.dom.DOMSignContext;
import javax.xml.crypto.dsig.keyinfo.KeyInfo;
import javax.xml.crypto.dsig.keyinfo.KeyInfoFactory;
import javax.xml.crypto.dsig.keyinfo.X509Data;
import javax.xml.crypto.dsig.spec.C14NMethodParameterSpec;
import javax.xml.crypto.dsig.spec.TransformParameterSpec;
import java.io.IOException;
import java.security.*;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Collections;

@Slf4j
@Component
public class SamlUtils {

    private static String jksSource;
    private static String jksPd;

    @Value("${xfiform.app.jks-utils.jksSource}")
    public void setJksSource(String JKS_SOURCE) {
        jksSource = JKS_SOURCE;
    }

    @Value("${xfiform.app.jks-utils.jksPd}")
    public void setJksPd(String JKS_PD) {
        jksPd = JKS_PD;
    }

    public static PrivateKey getPrivateKey() throws KeyStoreException, CertificateException, IOException, NoSuchAlgorithmException, UnrecoverableKeyException {

//        String path = System.getProperty("java.class.path");
//        log.info("classpath= " + path);
        KeyStore keyStore = KeyStore.getInstance("JKS");
//        ClassPathResource resource = new ClassPathResource("credentials/cathay/cathayhong.site.jks");
        ClassPathResource resource = new ClassPathResource(jksSource);
//        log.info("jksSource=" + jksSource);
        //FileInputStream fis = new FileInputStream("path/to/your/keystore.jks");
        keyStore.load(resource.getInputStream(), jksPd.toCharArray());
//        String privateKeyAlias = keyStore.aliases().nextElement();
        PrivateKey key = (PrivateKey) keyStore.getKey("cathayhong.site", jksPd.toCharArray());
//        String encodedKey = new Base64Encoder().encode();
//        Base64.getEncoder();
//        log.info(privateKeyAlias);
        return key;
    }
    public static X509Certificate getX509Cert() throws KeyStoreException, CertificateException, IOException, NoSuchAlgorithmException, UnrecoverableKeyException {

        KeyStore keyStore = KeyStore.getInstance("JKS");
        ClassPathResource resource = new ClassPathResource(jksSource);
        keyStore.load(resource.getInputStream(), jksPd.toCharArray());
        // 2. 使用 KeyStore 的 getCertificate 方法获取 X.509 证书
        X509Certificate cert = (X509Certificate) keyStore.getCertificate("cathayhong.site");
        return cert;
    }

    @SneakyThrows
    public static String signSaml(Document document, String signingAlgorithm, String digestMethod)
        throws XMLSignatureException, XMLSecurityException, KeyException, InvalidAlgorithmParameterException, NoSuchAlgorithmException, MarshalException {
        PrivateKey privateKey = getPrivateKey();
        X509Certificate cert = getX509Cert();
        // Create a DOMSignContext
        DOMSignContext dsc = new DOMSignContext(privateKey, document.getDocumentElement());

        // Create a XMLSignatureFactory
        XMLSignatureFactory fac = XMLSignatureFactory.getInstance("DOM");

        // Create a Reference
        Reference ref = fac.newReference("#" + getAttribute(document.getDocumentElement(), "ID"), fac.newDigestMethod(digestMethod, null),
            Collections.singletonList(fac.newTransform(Transform.ENVELOPED, (TransformParameterSpec) null)), null, null);

        // Create a SignedInfo
        SignedInfo si = fac.newSignedInfo(fac.newCanonicalizationMethod(CanonicalizationMethod.EXCLUSIVE, (C14NMethodParameterSpec) null),
            fac.newSignatureMethod(signingAlgorithm, null), Collections.singletonList(ref));

        KeyInfoFactory kif = fac.getKeyInfoFactory();
        X509Data x509Data = kif.newX509Data(Collections.singletonList(cert));
        KeyInfo ki = kif.newKeyInfo(Collections.singletonList(x509Data));

        // Create a XMLSignature
        XMLSignature signature = fac.newXMLSignature(si, ki);
        // Sign the XML
        signature.sign(dsc);

        // Return the signed XML
        return serializeDocument(document);
    }

    private static String getAttribute(Element element, String attributeName) {
        return element.getAttribute(attributeName);
    }

    private static String serializeDocument(Document document) throws CanonicalizationException, InvalidCanonicalizerException {
        Canonicalizer canon = Canonicalizer.getInstance(CanonicalizationMethod.EXCLUSIVE);
        byte[] canonBytes = canon.canonicalizeSubtree(document.getDocumentElement());
        return new String(canonBytes);
    }

    //第二版
//    public static String signSamlAssertion(Document samlAssertion) throws Exception {
//        X509Certificate cert = getX509Cert();
//        PrivateKey privateKey = getPrivateKey();
//
//        // 创建一个 XML 数字签名工厂
//        XMLSignatureFactory factory = XMLSignatureFactory.getInstance("DOM");
//
//
//        // 创建一个 Reference 对象
//        Reference reference = factory.newReference("#" + samlAssertion.getDocumentElement().getAttribute("ID"),
//            factory.newDigestMethod(DigestMethod.SHA1, null),
//            Collections.singletonList(factory.newTransform(Transform.ENVELOPED, (TransformParameterSpec) null)), null, null);
//
//        // 创建 SignedInfo 对象
//        SignedInfo signedInfo = factory.newSignedInfo(factory.newCanonicalizationMethod(CanonicalizationMethod.EXCLUSIVE, (C14NMethodParameterSpec) null),
//            factory.newSignatureMethod("http://www.w3.org/2001/04/xmldsig-more#rsa-sha256", null),
//            Collections.singletonList(reference));
//
//        // 创建 KeyInfo 对象，包含 X.509 证书
//        KeyInfoFactory keyInfoFactory = factory.getKeyInfoFactory();
//        X509Data x509Data = keyInfoFactory.newX509Data(Collections.singletonList(cert));
//        KeyInfo keyInfo = keyInfoFactory.newKeyInfo(Collections.singletonList(x509Data));
//
//        // 创建 XML 签名对象
//        KeyPair keyPair = new KeyPair(cert.getPublicKey(), privateKey);
//        Key signingKey = keyPair.getPrivate();
//        DOMSignContext signContext = new DOMSignContext(signingKey, samlAssertion.getDocumentElement());
//
//        XMLSignature signature = factory.newXMLSignature(signedInfo, keyInfo);
//        signature.sign(signContext);
//
//        // 将签名后的断言转换为字符串
//        Element signedElement = (Element) signContext.getParent();
//        samlAssertion.getDocumentElement().appendChild(signedElement);
//
//        return elementToString(signedElement);
//    }
//
//    private static String elementToString(Element element) {
//        // 将 DOM 元素转换为字符串
//        try {
//            DOMImplementationLS domImplementation = (DOMImplementationLS) element.getOwnerDocument().getImplementation().getFeature("LS", "3.0");
//            LSSerializer lsSerializer = domImplementation.createLSSerializer();
//            return lsSerializer.writeToString(element);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
}
