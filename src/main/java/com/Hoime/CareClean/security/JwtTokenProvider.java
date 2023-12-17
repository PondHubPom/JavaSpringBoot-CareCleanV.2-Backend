package com.Hoime.CareClean.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;

@Component
public class JwtTokenProvider {
//    @Value("${jwt.secret}")
    private String SECRET_KEY = "]4Vg=vR2EtN9NH1=yj{aUxJ18y7u6>rn8bZ>/aPF:w8~kVF1th&kpzHQ+}>;*,+";

    public String generateToken(Map<String, Object> claims) {
        final long EXPIRATION_TIME =  15L*(1000*60*60*24); // 15 วัน
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                .compact();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser()
                    .setSigningKey(SECRET_KEY)
                    .parseClaimsJws(token);
            return true;
        } catch (Exception ignored) {
        }
        return false;
    }

    public Claims readToken(String token) {
        if (validateToken(token)) {
            return Jwts.parser()
                    .setSigningKey(SECRET_KEY)
                    .parseClaimsJws(token)
                    .getBody();
        }
        return null;
    }

    public String getIdToken(String token) {
        Object ob = readToken(token).get("id");
        return ob.toString();
    }

}