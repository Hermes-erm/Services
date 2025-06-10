package com.service.user_management.Service;

import com.service.user_management.DTO.UserDTO;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JWTService {
    @Value("${secret}")
    private String jwtSecret;

    public String generateToken(UserDTO user) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("user", user.getName());
        return Jwts
                .builder()
                .claims(claims) // payload | subject
                .subject(user.getName())
                .issuedAt(new Date(System.currentTimeMillis()))
                .signWith(getSignInKey()).compact();
    }

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(jwtSecret); // decoding layer into ->  bytes or array
        return Keys.hmacShaKeyFor(keyBytes); // convert it into HMAC key to sign the JWT
    }
}
