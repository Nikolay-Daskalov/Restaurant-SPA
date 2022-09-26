package com.restaurant.server.exception;

import lombok.NoArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@NoArgsConstructor
public class CustomRestAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private final static String ALL_ORIGINS = "*";

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        response.setHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, ALL_ORIGINS);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.sendError(HttpStatus.UNAUTHORIZED.value());
    }
}
