package com.gabriel.cursomc.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.gabriel.cursomc.security.JWTAuthenticationFilter;
import com.gabriel.cursomc.security.JWTAuthorizationFilter;
import com.gabriel.cursomc.security.JWTUtil;


@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {
	
	@Autowired
	private JWTUtil jwtUtil;
	
	@Autowired
	private UserDetailsService userDetailsService; 
	
	
	private static final String[] PUBLIC_MATCHERS = { "/h2-console/**", "/produtos/**", "/categorias/**", "/clientes","/clientes/picture","/auth/forgot**","/estados/**"};
	
	/*private static final String[] PUBLIC_MATCHERS_POST = { "/clientes/**"};*/
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http, AuthenticationManager authenticationManager) throws Exception {
		
		http.cors(cors -> cors.configurationSource(corsConfigurationSource()));
		http.csrf(csrf -> csrf.disable())
				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.authorizeHttpRequests(auth -> auth.requestMatchers(PUBLIC_MATCHERS).permitAll().anyRequest().authenticated());
				
		       // .authorizeHttpRequests(auth -> auth.requestMatchers(PUBLIC_MATCHERS_POST).permitAll().anyRequest().authenticated());
				http.addFilter(new JWTAuthenticationFilter(authenticationManager, jwtUtil));
				http.addFilter(new JWTAuthorizationFilter(authenticationManager, jwtUtil,userDetailsService));
		http.headers(headers->headers.frameOptions(framesOptions -> framesOptions.sameOrigin()));
		
		return http.build();
	}
	
	@Bean
	public AuthenticationManager authenticationManager (HttpSecurity http,
														BCryptPasswordEncoder bCryptPasswordEncoder,
														AuthenticationConfiguration auth
														 ) throws Exception {
		AuthenticationManagerBuilder authBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
		authBuilder.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
		return authBuilder.build();
	}
	
	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		
		CorsConfiguration configuration = new CorsConfiguration().applyPermitDefaultValues();
		configuration.setAllowedMethods(Arrays.asList("POST", "GET", "DELETE", "OPTIONS"));
		final UrlBasedCorsConfigurationSource source = new  UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		
		return source;
		
	}
	
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
