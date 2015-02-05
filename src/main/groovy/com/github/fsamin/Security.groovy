package com.github.fsamin

import com.github.fsamin.dao.UserRepository
import com.github.fsamin.models.User
import com.github.fsamin.utils.Utils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.core.Authentication
import org.springframework.security.core.AuthenticationException
import org.springframework.security.core.GrantedAuthority
import org.springframework.stereotype.Service


@Configuration

class CustomWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {

    @Autowired
    ElasticSearchAuthenticationProvider elasticSearchAuthenticationProvider;

    @Autowired
    UserRepository userRepository;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/secure/**").authenticated()
                .anyRequest().permitAll()
                .and().httpBasic()
                .realmName("Spring")
                .and().rememberMe()
                .tokenValiditySeconds(60)
    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring()
                .antMatchers("/public/**"); // #3
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth)
            throws Exception {
        auth.authenticationProvider(elasticSearchAuthenticationProvider);
    }

}

@Service
class ElasticSearchAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    UserRepository userRepository;

    @Override
    public Authentication authenticate(Authentication authentication)
            throws AuthenticationException {
        println("*** Starting authenticate **** " + authentication.getName() + " *** " + authentication.getCredentials());
        String username = authentication.getName();
        String password = (String) authentication.getCredentials();
        //TODO : attention s'il n'y a pas d'admin !
        User user = userRepository.findByEmail(username, Utils.getSingle()).first();
        if (user == null) {
            throw new BadCredentialsException("Username not found.");
        }

        if (!password.equals(user.getPassword())) {
            throw new BadCredentialsException("Wrong password.");
        }
        Collection<? extends GrantedAuthority> authorities = user.getAuthorities();
        authorities.each {auth -> println(auth.getAuthority())}

        return new UsernamePasswordAuthenticationToken(user, password, authorities);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        // TODO Auto-generated method stub
        return true;
    }

}