package org.xf.iform.common.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(info = @Info(title = "iform API", version = "1.0.0"))
@Configuration
public class OpenApiConfig {

	private static final String SECURITY_SCHEME_NAME = "Authorization";

	@Bean
	public OpenAPI IformOpenAPI() {
		return new OpenAPI().addSecurityItem(new SecurityRequirement().addList(SECURITY_SCHEME_NAME))
				.components(new Components().addSecuritySchemes(SECURITY_SCHEME_NAME,
						new SecurityScheme().name(SECURITY_SCHEME_NAME).type(SecurityScheme.Type.APIKEY)
								.in(SecurityScheme.In.HEADER).scheme("Bearer").bearerFormat("JWT")));

	}

	@Bean
	public GroupedOpenApi apiTestApi() {
		return ToGroupApi("rileyTest", new String[] { "/api/rileytest/**", "/api/auth/**" }); }
//	@Bean
//	public GroupedOpenApi v1Api() {
//		return ToGroupApi("warroom", new String[] { "/v1/api/**" });
//	}
	@Bean
	public GroupedOpenApi v1LoginApi() {
		return ToGroupApi("0.v1.login", new String[] { "/v1/login/api/**" });
	}
	@Bean
	public GroupedOpenApi v1AdmApi() {
		return ToGroupApi("1.adm", new String[] { "/api/adm/**" });
	}
	@Bean
	public GroupedOpenApi v1CommApi() {
		return ToGroupApi("2.comm", new String[] { "/api/comm/**" });
	}
	@Bean
	public GroupedOpenApi v1ReportApi() {
		return ToGroupApi("3.report", new String[] { "/api/report/**" });
	}
	@Bean
	public GroupedOpenApi v1IformApi() {
		return ToGroupApi("0.iformNew", new String[] { "/api/iform/**" });
	}

	private GroupedOpenApi ToGroupApi(String title, String[] pathsToMatch) {
		return GroupedOpenApi.builder().group(title).pathsToMatch(pathsToMatch).build();
	}
}