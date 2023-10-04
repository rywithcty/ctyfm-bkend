package org.xf.iform;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@EnableConfigurationProperties
@SpringBootApplication
public class IformApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(IformApiApplication.class, args);
    }

	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}
}
