package com.jobayed.bloggingapp.filter;

import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jobayed.bloggingapp.service.UserServiceImpl;
import com.jobayed.bloggingapp.utils.JwtUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Slf4j
@RequiredArgsConstructor
public class CustomAuthorizationFilter extends OncePerRequestFilter {
    private final JwtUtils jwtUtils;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        if(request.getServletPath().equals("/login")){
            filterChain.doFilter(request,response);
        }
        else {
            final String AUTHORIZATION_HEADER = request.getHeader(AUTHORIZATION);
            String token = null;
            String username = null;
            Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();

            if (AUTHORIZATION_HEADER!=null && AUTHORIZATION_HEADER.startsWith("Bearer "))
            {
                token = AUTHORIZATION_HEADER.substring("Bearer ".length());

                try {

                    DecodedJWT decodedJWT = jwtUtils.getTokenDecoder(token);
                    username = decodedJWT.getSubject();
                    String[] roles = decodedJWT.getClaim("roles").asArray(String.class);
                    Arrays.stream(roles).forEach(role->authorities.add(new SimpleGrantedAuthority(role)));

                } catch (IllegalArgumentException e) {
                    log.debug("Unable to get JWT Token");
                    throw new IllegalArgumentException("Unable to get JWT Token");
                } catch (Exception e){
                    log.debug("Error Logging In: {}",e.getMessage());

                    response.setStatus(FORBIDDEN.value());
                    Map<String,String> errors = new HashMap<>();
                    errors.put("error_messages",e.getMessage());
                    response.setContentType(APPLICATION_JSON_VALUE);
                    new ObjectMapper().writeValue(response.getOutputStream(),errors);
                }

            } else {
                log.debug("JWT Token does not begin with Bearer String");
            }

            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

                try {
                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                            new UsernamePasswordAuthenticationToken(
                                    username, null, authorities
                            );
                    usernamePasswordAuthenticationToken
                            .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                } catch (Exception e) {
                    log.debug(e.getMessage());
                }
                filterChain.doFilter(request, response);
            }
            else {
                filterChain.doFilter(request, response);
            }

        }
    }
}
