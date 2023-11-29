package com.social.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class AppConfig {
	//step2 made a custom user detail services for not generating the default password
	
	//step 1
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http)throws Exception{
		
		http.sessionManagement(management->management.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
		.authorizeHttpRequests(authorize->authorize
				.requestMatchers("/api/**").authenticated().anyRequest().permitAll())

				.addFilterBefore(new jwtValidator(),BasicAuthenticationFilter.class)
				.csrf(csrf->csrf.disable());
		
		return http.build();
	}
	
	private static final String[] AUTH_WHITELI_STRINGS = {
		
			"/api/v1/auth/**",
			"/v3/api-docs/**",
			"/v3/api-docs.yaml",
			"/swagger-ui/**",
			"/swagger-ui.html"
			
			
	};
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
