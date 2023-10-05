package org.xf.iform.common.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.security.web.session.HttpSessionEventPublisher;
import org.xf.iform.common.security.filter.AuthTokenFilter;
import org.xf.iform.common.security.jwt.AuthEntryPointJwt;
import org.xf.iform.common.security.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//import org.xf.warroom.security.service.UserDetailsServiceImpl;


@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig { // extends WebSecurityConfigurerAdapter {
	@Autowired
	UserDetailsServiceImpl userDetailsService;

//	@Autowired
	private AuthEntryPointJwt unauthorizedHandler;

	@Bean
	public AuthTokenFilter authenticationJwtTokenFilter() {
		return new AuthTokenFilter();
	}

//	public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
//		authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
//	}

	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

		authProvider.setUserDetailsService(userDetailsService);
		authProvider.setPasswordEncoder(passwordEncoder());

		return authProvider;
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
		return authConfig.getAuthenticationManager();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.cors().and()
//			.csrf().disable()
			.exceptionHandling().authenticationEntryPoint(unauthorizedHandler)
			.and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
			.and().authorizeRequests()
			.antMatchers("/api/auth/**").permitAll()
			.antMatchers("/v1/login/api/**").permitAll()
			.antMatchers("/swagger-ui/**", "/swagger-resources/**", "/api-docs/**").permitAll()
			.antMatchers("/**").permitAll().anyRequest()
			.authenticated();
		http.authenticationProvider(authenticationProvider());
//		http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);

		return http.build();
	}

//	@Bean
//	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
//		return httpSecurity
//			.csrf().disable()
//			.sessionManagement() // 设置 session 配置管理
//			.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED) // 设置session创建策略
//			.maximumSessions(1) // 设置最大会话数
////				.expiredUrl("/login") // session 被挤下线之后, 跳转的位置
//			.expiredSessionStrategy(event -> { // 在前后端分离的情况下, 则不能使用上面的 expiredUrl, 但可以使用 expiredSessionStrategy
//				HttpServletResponse response = event.getResponse();
//				response.setContentType(MediaType.APPLICATION_JSON_VALUE);
//				HashMap<String, Object> result = new HashMap<>();
//				result.put("status", 500);
//				result.put("msg", "当前会话失效, 请重新登录");
//				String s = objectMapper().writeValueAsString(result);
//				response.getWriter().write(s); // 写入响应缓存
//				response.flushBuffer(); // 刷新缓存
//			})
//			.and()
//			.and()
//			.authorizeRequests()
//			.anyRequest()
//			.authenticated()
//			.and()
//			.formLogin()
//			.and().build();
//	}

	@Bean
	public ObjectMapper objectMapper() {
		return new ObjectMapper();
	}

	@Bean
	public HttpSessionEventPublisher httpSessionEventPublisher() {
		return new HttpSessionEventPublisher();
	}
}
