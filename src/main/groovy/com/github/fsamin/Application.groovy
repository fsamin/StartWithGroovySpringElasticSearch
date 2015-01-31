package com.github.fsamin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.github.fsamin.controllers.MainController;

@Configuration
@EnableAutoConfiguration
@EnableWebMvc
class Application {
	static main(args) {
		SpringApplication.run(MainController, args);
	}
}

@Configuration
@EnableWebMvcSecurity
class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// The "/" and "/home" paths are configured to not require any authentication. 
		// All other paths must be authenticated.
		
		// When a user successfully logs in, they will be redirected to the previously 
		// requested page that requires authentication. 
		// There is a custom "/login" page specified by loginPage(), and everyone is allowed to view it.
		http
         .authorizeRequests()
             .antMatchers("/", "/home").permitAll()
             .anyRequest().authenticated()
             .and()
         .formLogin()
             .loginPage("/login")
             .permitAll()
             .and()
         .logout()
             .permitAll();
	}
	@Override
	protected void configure(AuthenticationManagerBuilder auth)
			throws Exception {
		// It sets up an in-memory user store with a single user. 
		// That user is given a username of "user", a password of "password", and a role of "USER".
		auth
         .inMemoryAuthentication()
            .withUser("user").password("password").roles("USER");
	}
}