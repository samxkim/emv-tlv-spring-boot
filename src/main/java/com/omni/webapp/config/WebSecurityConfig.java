package com.omni.webapp.config;

import com.omni.webapp.models.UserRepository;
import com.omni.webapp.service.DBUserDetailsImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserRepository userRepository;
    private final UserDetailsService userDetailsService;

    public WebSecurityConfig(UserRepository userRepository, UserDetailsService userDetailsService) {
        this.userRepository = userRepository;
        this.userDetailsService = userDetailsService;
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // Change this to change the urls that is available
        http
                .authorizeRequests()
                .antMatchers("/**")
                .permitAll()
                .and()
                .csrf()
                .ignoringAntMatchers("/users/**");
                //.antMatchers("/admin")
                //.hasAnyRole("ADMIN")
                //.antMatchers("/user*")
                //.permitAll()
                //.and()
                //.antMatchers("/admin").hasAuthority("ROLE_ADMIN")
                //.formLogin()
                //.loginPage("/login")
                //.permitAll()
                //.and()
                //.logout()
                //.permitAll()
                //.and()
                //.httpBasic();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new DBUserDetailsImpl(userRepository);
    }
}
