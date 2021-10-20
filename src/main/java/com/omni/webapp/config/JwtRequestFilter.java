package com.omni.webapp.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.omni.webapp.models.APIErrorResponse;
import com.omni.webapp.models.JwtFilterErrorResponse;
import com.omni.webapp.utils.JwtUtils;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.SignatureException;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    private final UserDetailsService userDetailsService;

    private final JwtUtils jwtUtils;

    public JwtRequestFilter(UserDetailsService userDetailsService, JwtUtils jwtUtils) {
        this.userDetailsService = userDetailsService;
        this.jwtUtils = jwtUtils;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, @NotNull HttpServletResponse response, @NotNull FilterChain chain)
            throws ServletException, IOException, IllegalArgumentException, ExpiredJwtException, MalformedJwtException {

        final String requestTokenHeader = request.getHeader("Authorization");

        try {
            String username = null;
            String jwtToken = null;
            if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
                jwtToken = requestTokenHeader.substring(7);
                username = jwtUtils.getUsernameFromToken(jwtToken);
            }
            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

                UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);

                if (jwtUtils.validateToken(jwtToken, userDetails)) {

                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                            userDetails, null, userDetails.getAuthorities());
                    usernamePasswordAuthenticationToken
                            .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                }
            }
        } catch (MalformedJwtException | UnsupportedJwtException | IllegalArgumentException | SignatureException e) {
            JwtFilterErrorResponse errorResponse = new JwtFilterErrorResponse(HttpStatus.BAD_REQUEST, e.getClass().getSimpleName(),
                    "Please verify that the Jwt token is correct.");
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            response.getWriter().write(convertObjectToJson(errorResponse));
        } catch (ExpiredJwtException e) {
            JwtFilterErrorResponse errorResponse = new JwtFilterErrorResponse(HttpStatus.BAD_REQUEST, e.getClass().getSimpleName(),
                    "Jwt token is expired.");
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            response.getWriter().write(convertObjectToJson(errorResponse));
        }
        chain.doFilter(request, response);
    }

    private String convertObjectToJson(Object object) throws JsonProcessingException {
        if (object == null) {
            return null;
        }
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(object);
    }
}