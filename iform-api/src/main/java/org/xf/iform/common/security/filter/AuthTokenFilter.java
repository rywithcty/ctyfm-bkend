package org.xf.iform.common.security.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;


import lombok.extern.slf4j.Slf4j;
import org.xf.iform.common.utils.JwtUtils;

@Slf4j
public class AuthTokenFilter extends OncePerRequestFilter {
	@Autowired
	private JwtUtils jwtUtils;

	@Autowired
	private UserDetailsService userDetailsService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		try {
			//TODO 重新簽發TOKEN，可以改在這
			String jwt = parseJwt(request);
			if (jwt != null) {
				int validJwt = jwtUtils.validateJwtToken(jwt, response);
				log.info("validJwt=" + validJwt);
				if (validJwt == 1) {
					jwt = response.getHeader("NewAuthorization");
					log.info("新Token=" + jwt);
				}
				if (validJwt != -1) {
					log.info("token驗證成功");

					String userId = jwtUtils.getUserIdFromJwtToken(jwt);
					UserDetails userDetails = userDetailsService.loadUserByUsername(userId);
					UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
						userDetails, null, userDetails.getAuthorities());
					authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

					SecurityContextHolder.getContext().setAuthentication(authentication);
				}
			}
		} catch (Exception e) {
			log.error("Cannot set user authentication: {}", e);
		}

		filterChain.doFilter(request, response);
	}

	private String parseJwt(HttpServletRequest request) {
		String headerAuth = request.getHeader("Authorization");

		// 改成不用帶開頭
//    if (StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer ")) {
//      return headerAuth.substring(7, headerAuth.length());
//    }

		return headerAuth;
	}
}
