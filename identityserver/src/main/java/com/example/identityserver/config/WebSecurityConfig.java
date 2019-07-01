package com.example.identityserver.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    @Override
    protected UserDetailsService userDetailsService() {

        /*
          Em produção o ideal é usar um encoder como o BCryptPasswordEncoder e não passar a senha como plain-text,
          por isso o aviso de deprecated
          */
        return new InMemoryUserDetailsManager(
                User.withDefaultPasswordEncoder().username("user")
                        .password("pass")
                        .roles("USER")
                        .build()
        );
    }

    /**
     * Precisamos para uso do Password Flow
     * */
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
