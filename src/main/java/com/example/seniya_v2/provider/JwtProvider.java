package com.example.seniya_v2.provider;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtProvider {
    private final Key key;
    private final int jwtExpirationMS;
    private final long jwtEmailExpirationMs;

    public int getExpiration() {return jwtExpirationMS;}

    public  JwtProvider(@Value("${jwt.secret}") String secret, @Value("${jwt.expiration}") int jwtExpirationMS, @Value("${jwt.email-expiration-ms}") long jwtEmailExpirationMs){
        this.key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret));
        this.jwtExpirationMS = jwtExpirationMS;
        this.jwtEmailExpirationMs = jwtEmailExpirationMs;
    }

    public String generateToken(String username, String role) {
        return Jwts.builder()
                .claim("username", username)
                .claim("role", role)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpirationMS))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public String generateEmailToken(String email) {
        return Jwts.builder()
                .claim("email", email)
                .setIssuedAt(new Date())
                .setExpiration(new Date())
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }


    public boolean isValidToken(String token) {
        try {
            getClaims(token);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    private Claims getClaims(String token) {
        JwtParser jwtParser = Jwts.parserBuilder()
                .setSigningKey(key)
                .build();
        return jwtParser.parseClaimsJws(token).getBody();
    }

    public String getUsenameFromJwt(String token) {
        Claims claims = getClaims(token);
        return claims.get("username",  String.class);
    }

    public String getRoleFromJwt(String token) {
        Claims claims = getClaims(token);
        return claims.get("role",String.class);
    }

    public String getEmailFromJwt(String token) {
        Claims claims = getClaims(token);
        return claims.get("email",String.class);
    }

    public String removeBearer(String bearerToken) {
        if (bearerToken == null || !bearerToken.startsWith("Bearer ")) {
            throw new RuntimeException("Invalid JWT token format");
        }
        return bearerToken.substring("Bearer ".length());
    }
}
