package com.shrmusic.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebMvcSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)   /* enables for example: @Secured("ROLE_USER") on class or method annotation */
public class SecurityConfig extends WebSecurityConfigurerAdapter{
    @Autowired
    @Qualifier("restAuthEntryPoint")
    private AuthenticationEntryPoint authenticationEntryPoint;
    @Autowired
    @Qualifier(value = "userAuthenticationService")
    private UserDetailsService userDetailsService;
    @Autowired
    @Qualifier("successHandler")
    private RestAuthenticationSuccessHandler successHandler;
    @Autowired
    @Qualifier("logoutSuccessHandler")
    private RestLogoutSuccessHandler logoutSuccessHandler;
    @Autowired
    private AuthenticationTokenProcessingFilter authenticationTokenProcessingFilter;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.addFilterBefore(authenticationTokenProcessingFilter, UsernamePasswordAuthenticationFilter.class);
        http.authorizeRequests()
                .antMatchers("/admin**").access("hasRole('ROLE_ADMIN')")
                .antMatchers("/account*//**").access("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
                .antMatchers("/user**").permitAll()
                .and().exceptionHandling().authenticationEntryPoint(authenticationEntryPoint)
                .and().formLogin().successHandler(successHandler).loginPage("/authtoken")
                .and().logout().logoutSuccessHandler(logoutSuccessHandler)
                .and().csrf().disable();
    }
}