package com.zerplabs.application.weatherforecast;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;

import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
public class WeatherForecastApplication {

	public static void main(String[] args) {
		SpringApplication.run(WeatherForecastApplication.class, args);
	}
	
	@Bean
	public RestTemplate restTemplate() 
	{
	 return new RestTemplate();
	}
	
	@Bean
	public WebClient.Builder webClientBuilder() {
		return WebClient.builder();
	}
	
//	@Bean
//	public PasswordEncoder encoder() {
//	    return new BCryptPasswordEncoder();
//	}
	
//	@Bean
//    public SecurityWebFilterChain chain(ServerHttpSecurity http, AuthenticationWebFilter webFilter) {
//        return http.authorizeExchange().anyExchange().permitAll().and()
//                .csrf().disable()
//                .build();
//	}

//	@Bean
//    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
////		http.authorizeRequests()
////        .requestMatchers("/api/register/").permitAll() // Allow unauthenticated access to the /register endpoint
////        .anyRequest().authenticated() // Require authentication for all other endpoints
////        .and()
////        .csrf().disable();
//
//        http.csrf(AbstractHttpConfigurer::disable).authorizeHttpRequests((authorize) ->
//        authorize.requestMatchers(HttpMethod.POST, "/api/user/**").permitAll()
////                        authorize.requestMatchers(HttpMethod.POST, "/api/register/").permitAll()
////                                .anyRequest().authenticated()
//
//                );        
//        
//
//        return http.build();
//    }
	
	
}
