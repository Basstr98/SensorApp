package com.ticarum.apirest.config;

import org.modelmapper.ModelMapper;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
/*
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
/*
 * 
 */
@Configuration
//@EnableWebSecurity
public class AppConfig {
	/*
	@Autowired
	UserDetailsService usuarioService;
     
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
     
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(usuarioService);
        authProvider.setPasswordEncoder(passwordEncoder());
         
        return authProvider;
    }
    
 
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }
 
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(requests -> requests
	                .anyRequest().authenticated()
	                .requestMatchers("/","/ticarumapirest/h2-console/**").permitAll())
                .formLogin(login -> login.permitAll())
                .logout(logout -> logout.permitAll())
                .csrf(csrf -> csrf.ignoringRequestMatchers(AntPathRequestMatcher.antMatcher("/ticarumapirest/h2-console/**")));
    }
    
*/
    @Bean
    ModelMapper modelMapper() {
		return new ModelMapper();
	}

}
