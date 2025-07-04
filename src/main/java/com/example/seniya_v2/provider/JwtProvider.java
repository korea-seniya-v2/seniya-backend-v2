//package com.example.seniya_v2.provider;
//
//import lombok.Value;
//import org.springframework.stereotype.Component;
//
//import java.security.Key;
//import java.util.Set;
//
//@Component
//public class JwtProvider {
//    private final Key key;
//    private final int jwtExpirationMS;
//    private final long jwtEmailExpirationMs;
//
//    public int getExpiration() {return jwtExpirationMS;}
//
//    public  JwtProvider(@Value("${jwt.secret}") String secret, @Value("${jwt.expiration}") int jwtExpirationMS, @Value("${jwt.email-expiration-ms}") long jwtEmailExpirationMs){
//        this.key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret));
//        this.jwtExpirationMs = jwtExpirationMS;
//        this.jwtEmailExpirationMs = jwtEmailExpirationMs;
//    }
//
//
//
//    public String removeBearer(String authorizationHeader) {
//        return authorizationHeader;
//    }
//
//    public boolean isValidToken(String token) {
//        return false;
//    }
//
//    public String getUsenameFromJwt(String token) {
//        return token;
//    }
//
//    public String getRoleFromJwt(String token) {
//        return token;
//    }
//}
