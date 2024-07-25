package com.course.utils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

public class TokenUtils {
    private static final long EXPIRATION_TIME = 60 * 60 * 1000; // 1小时设置token过期时间
    private static final String SECRET_KEY = "your-secret-key";

    public static String generateToken() {
        return Jwts.builder()
                .setSubject("xsy")
                .claim("s", "123456")
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }
}
