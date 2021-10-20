package com.omni.webapp.config;

import com.omni.webapp.service.DBUserDetailsImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity(debug = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final DBUserDetailsImpl userDetailsService;
    private final JwtRequestFilter jwtRequestFilter;
    private final JwtAuthenticationEntrypoint jwtAuthenticationEndpoint;

    public WebSecurityConfig(DBUserDetailsImpl userDetailsService,
                             @Lazy JwtRequestFilter jwtRequestFilter, JwtAuthenticationEntrypoint jwtAuthenticationEndpoint) {
        this.userDetailsService = userDetailsService;
        this.jwtRequestFilter = jwtRequestFilter;
        this.jwtAuthenticationEndpoint = jwtAuthenticationEndpoint;
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .eraseCredentials(true)
                .userDetailsService(userDetailsService)
                .passwordEncoder(bCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // Change this to change the urls that is available
        http.cors().and().csrf().disable();
        http
                .exceptionHandling().authenticationEntryPoint(jwtAuthenticationEndpoint).and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
                // Maximum session tokens
                //.sessionManagement().maximumSessions(3);
        http
                .authorizeRequests()
                // To generate jwt token with authenticated user/pass
                .mvcMatchers(HttpMethod.GET, "/api/v1/users/login")
                .permitAll()
                // end of permit all
                .anyRequest()
                .authenticated()
                .and()
                .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
                //.ignoringAntMatchers("/users/**");
                //.cors().disable()
                //.headers().frameOptions().disable()
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

//    //
//    @Bean
//    public UserDetailsService userDetailsService() {
//        return new DBUserDetailsImpl(userRepository);
//    }
}
