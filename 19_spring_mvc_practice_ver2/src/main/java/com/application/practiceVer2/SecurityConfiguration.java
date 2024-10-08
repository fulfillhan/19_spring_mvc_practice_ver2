package com.application.practiceVer2;

import org.springframework.context.annotation.Bean;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
	
	//PasswordEncoder 인터페이스형으로 내보내는게 낫다.
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	 @Bean
		public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
			http.cors().disable()			//cors 방지
				.csrf().disable()			//csrf 방지
				.formLogin().disable()		//기본 로그인페이지 없애기
				.headers().frameOptions().disable();
	 
			return http.build();
		}

}
