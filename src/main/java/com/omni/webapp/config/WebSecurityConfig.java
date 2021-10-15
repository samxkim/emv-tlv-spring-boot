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
@EnableWebSecurity
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
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and();
        http
                .authorizeRequests()
                .mvcMatchers(HttpMethod.GET, "/**")
                .permitAll()
                .mvcMatchers(HttpMethod.PUT, "/**").hasRole("ADMIN")
                .mvcMatchers(HttpMethod.DELETE, "/**").hasRole("ADMIN")
                .mvcMatchers(HttpMethod.PATCH, "/**").hasRole("ADMIN")
                .mvcMatchers(HttpMethod.HEAD, "/**").hasRole("ADMIN")
                .mvcMatchers(HttpMethod.OPTIONS, "/**").hasRole("ADMIN")
                .mvcMatchers(HttpMethod.TRACE, "/**").hasRole("ADMIN")
                //.mvcMatchers(HttpMethod.POST, "/users/").hasRole("ADMIN")

                .mvcMatchers(HttpMethod.POST, "/users/register/").permitAll()
                // When admin page is made
                // .mvcMatchers(HttpMethod.POST, "/**").hasRole("ADMIN")
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
