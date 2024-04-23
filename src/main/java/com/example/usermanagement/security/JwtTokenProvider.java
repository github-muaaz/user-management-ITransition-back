package com.example.usermanagement.security;

import com.example.usermanagement.entity.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.UUID;

@Component
@Slf4j
public class JwtTokenProvider {

    @Value("${jwt.access.key}")
    private String JWT_SECRET_KEY_FOR_ACCESS_TOKEN;
    @Value("${jwt.access.expiration-time}")
    private Long JWT_EXPIRED_TIME_FOR_ACCESS_TOKEN;
    @Value("${jwt.refresh.key}")
    private String JWT_SECRET_KEY_FOR_REFRESH_TOKEN;
    @Value("${jwt.refresh.expiration-time}")
    private Long JWT_EXPIRED_TIME_FOR_REFRESH_TOKEN;

    public String generateAccessToken(User user, Timestamp issuedAt) {
        Timestamp expireDate = new Timestamp(System.currentTimeMillis() + JWT_EXPIRED_TIME_FOR_ACCESS_TOKEN);
        String userId = String.valueOf(user.getId());
        String generateUuid = hideUserId(userId);
        return Jwts.builder()
                .setSubject(generateUuid)
                .setIssuedAt(issuedAt)
                .setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS256, JWT_SECRET_KEY_FOR_ACCESS_TOKEN)
                .compact();
    }

    public String generateRefreshToken(User user) {
        Timestamp issuedAt = new Timestamp(System.currentTimeMillis());
        Timestamp expireDate = new Timestamp(System.currentTimeMillis() + JWT_EXPIRED_TIME_FOR_REFRESH_TOKEN);

        return Jwts.builder()
                .setSubject(user.getId().toString())
                .setIssuedAt(issuedAt)
                .setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS512, JWT_SECRET_KEY_FOR_REFRESH_TOKEN)
                .compact();
    }

    public boolean validToken(String token, boolean accessToken) {
        try {
            checkToken(token, accessToken);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    public void checkToken(String token, boolean accessToken) {
        Jwts.parser().setSigningKey(accessToken ?
                        JWT_SECRET_KEY_FOR_ACCESS_TOKEN : JWT_SECRET_KEY_FOR_REFRESH_TOKEN)
                .parseClaimsJws(token);
    }

    public String getUserIdFromToken(String token, boolean accessToken) {
        String userId = Jwts.parser()
                .setSigningKey(accessToken ? JWT_SECRET_KEY_FOR_ACCESS_TOKEN : JWT_SECRET_KEY_FOR_REFRESH_TOKEN)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
        return showUserId(userId);

    }

    private String hideUserId(String userId) {
        String generatingUUID = String.valueOf(UUID.randomUUID());
        String substring = generatingUUID.substring(0, 18);
        String concat = substring.concat("-");
        String concat1 = concat.concat(userId);
        String substring1 = generatingUUID.substring(18);
        return concat1.concat(substring1);
    }

    private String showUserId(String concat) {
        return concat.substring(19, 55);
    }
}
