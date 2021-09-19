package com.jobayed.bloggingapp.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

public class JwtUtils
{
    //@Value("${jwt.access_validity}")
    private long JWT_ACCESS_TOKEN_VALIDITY = 5*60;

    //@Value("${jwt.refresh_validity}")
    private long JWT_REFRESH_TOKEN_VALIDITY = 60*60*24;

    //@Value("${jwt.secret}")
    private String SECRET = "JWTSECRET";

    private final Algorithm algorithm = Algorithm.HMAC256(SECRET.getBytes());

    public String getAccessToken(User user, List<?> claims, String issuer)
    {
        String access_token = JWT.create()
                .withSubject(user.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis()+JWT_ACCESS_TOKEN_VALIDITY*1000))
                .withIssuer(issuer)
                .withClaim("Roles",claims)
                .sign(algorithm);
        return access_token;
    }
    public String getRefreshToken(User user, String issuer)
    {
        String refresh_token = JWT.create()
                .withSubject(user.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis()+JWT_REFRESH_TOKEN_VALIDITY*1000))
                .withIssuer(issuer)
                .sign(algorithm);
        return refresh_token;
    }

    public DecodedJWT getTokenDecoder(String token){
        JWTVerifier jwtVerifier = JWT.require(algorithm).build();
        DecodedJWT decodedJWT = jwtVerifier.verify(token);
        return  decodedJWT;
    }
}
