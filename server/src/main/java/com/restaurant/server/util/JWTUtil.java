package com.restaurant.server.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.time.Instant;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

public class JWTUtil {

    private static final String SECRET = "secret_token";
    private static final Integer INSPIRATION_TIME = 60 * 60;
    private static final Algorithm ALGORITHM = Algorithm.HMAC256(SECRET);

    public static String creatToken(String username, Collection<? extends GrantedAuthority> authorities, String issuer) {
        return JWT.create()
                .withSubject(username)
                .withArrayClaim("roles", authorities.stream().map(GrantedAuthority::getAuthority).toArray(String[]::new))
                .withIssuer(issuer)
                .withExpiresAt(Instant.now().plusSeconds(INSPIRATION_TIME))
                .sign(ALGORITHM);
    }

    public static UsernamePasswordAuthenticationToken verifyToken(String token) {
        JWTVerifier jwtVerifier = JWT.require(ALGORITHM).build();

        try {
            DecodedJWT decodedJWT = jwtVerifier.verify(token);
            String username = decodedJWT.getSubject();
            Collection<? extends SimpleGrantedAuthority> roles = Arrays.stream(decodedJWT.getClaim("roles").asArray(String.class))
                    .map(SimpleGrantedAuthority::new).collect(Collectors.toSet());
            return new UsernamePasswordAuthenticationToken(username, null, roles);
        } catch (JWTVerificationException e) {
            return null;
        }
    }
}
