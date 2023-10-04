package org.xf.iform.common.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletResponse;

@Slf4j
@Component
public class JwtUtils {

	@Value("${xfiform.app.jwtSecret}")
	private String jwtSecret;

	@Value("${xfiform.app.jwtExpirationMs}")
	private int jwtExpirationMs;
	@Value("${xfiform.app.jwtExpirationAllowMs}")
	private int jwtExpirationAllowMs;

	public String generateJwtToken(Authentication authentication) {

		UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();
		String pwdModtime = userPrincipal.getPwdModtime() == null ? "" : new SimpleDateFormat("yyyyMMddhhmmss").format(userPrincipal.getPwdModtime());
		return Jwts.builder()
			.setId(userPrincipal.getUsername())
			.setSubject(pwdModtime)
			.setIssuedAt(new Date())
			.setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
			.signWith(SignatureAlgorithm.HS512, jwtSecret).compact();
	}

	public String getUserIdFromJwtToken(String token) {
		Date expDate = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getExpiration();

		log.info(expDate.toString());
		log.info(new Date(expDate.getTime() + jwtExpirationAllowMs).toString());
		return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getId();
	}

	public int validateJwtToken(String authToken, HttpServletResponse resp) {
		try {
			Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
			return 0;
		} catch (SignatureException e) {
			log.error("Invalid JWT signature: {}", e.getMessage());
		} catch (MalformedJwtException e) {
			log.error("Invalid JWT token: {}", e.getMessage());
		} catch (ExpiredJwtException e) {
			log.error("JWT token is expired: user={} - {}", e.getClaims().getId(), e.getMessage());
			Date expirationAllow = new Date(e.getClaims().getExpiration().getTime() + jwtExpirationAllowMs);
			if (new Date().before(expirationAllow)) {
				log.info("will refresh token");
				String newJwt = refreshJwtToken(e.getClaims().getId(), e.getClaims().getSubject());
				if (StringUtils.isNotBlank(newJwt)) {
					log.info("new token = " + newJwt);
					resp.setHeader("NewAuthorization", newJwt);
					return 1;
				}
			}
//			Jwts.parser().setSigningKey(Base64.getDecoder().decode(getBase64Security())).parseClaimsJws(authToken).getBody();
		} catch (UnsupportedJwtException e) {
			log.error("JWT token is unsupported: {}", e.getMessage());
		} catch (IllegalArgumentException e) {
			log.error("JWT claims string is empty: {}", e.getMessage());
		}

		return -1;
	}

	public String refreshJwtToken(String id, String subject) {

		return Jwts.builder()
			.setId(id)
			.setSubject(subject)
			.setIssuedAt(new Date())
			.setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
			.signWith(SignatureAlgorithm.HS512, jwtSecret).compact();
	}
}
