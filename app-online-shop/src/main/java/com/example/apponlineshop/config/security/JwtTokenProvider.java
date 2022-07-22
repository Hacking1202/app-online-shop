package com.example.apponlineshop.config.security;


import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.UUID;

@Component
public class JwtTokenProvider {

    @Value("${app.jwtSecretKey}")
    String key;

    @Value("${app.jwtExpireInMilSec}")
    Long expireDate;

    public String generateToken(UUID userId){
        Date expireTime=new Date(new Date().getTime()+expireDate);
        return Jwts
                .builder()
                .setIssuedAt(new Date())
                .setExpiration(expireTime)
                .setSubject(userId.toString())
                .signWith(SignatureAlgorithm.HS512, key)
                .compact();
    }



}
