package com.example.apponlineshop.config.security;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Component
public class JwtErrors implements AuthenticationEntryPoint {
    public static  final Logger logger= LoggerFactory.getLogger(JwtErrors.class);
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        logger.error("Sizga kirish man qilinadi"+authException.getMessage());
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Sizda token mavjud emas: "+ authException.getMessage());
    }
}
