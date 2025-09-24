package com.jvitorsaugusto.vetclinic_api.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.security.Key;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Component
public class TokenService {


    private final Key key;

    Instant timeNow = Instant.now();
    Instant expiration = timeNow.plus(24, ChronoUnit.HOURS);

    public TokenService(@Value("${jwt.secret}") String secret) {
        byte[] secretKey = Decoders.BASE64.decode(secret);
        this.key = Keys.hmacShaKeyFor(secretKey);
    }

    public String generateToken(String username) {
        return Jwts.builder()
                .issuer("vetclinic-api")
                .subject(username)
                .issuedAt(Date.from(timeNow))
                .expiration(Date.from(expiration))
                .signWith(key)
                .compact();
    }

    public Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith((SecretKey) key)
                .requireIssuer("vetclinic-api")
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public String extractUsername(String token) {
        return extractAllClaims(token).getSubject();
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {

        try {
            String usernameFromToken = extractUsername(token);
            return usernameFromToken.equals(userDetails.getUsername()) && !isTokenExpired(token);
        } catch (JwtException e) {
            return false;
        }
    }

    public boolean isTokenExpired(String token) {
        return extractAllClaims(token).getExpiration().before(Date.from(timeNow));
    }
}
