package com.codegym.casemodule4be.service;


import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.util.Date;


@Service
public class JwtService {
    // key để mã hóa token.
    private static final String SECRET_KEY = "12345678999";
    // thời gian để token sống.
    private static final long EXPIRE_TIME = 86400000000L;

    // hàm tạo ra token
    public String createToken(Authentication authentication) {
        // lấy đối tượng đang đăng nhập.
        User user = (User) authentication.getPrincipal();

        return Jwts.builder()
                .setSubject((user.getUsername()))
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + EXPIRE_TIME * 1000))
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                .compact();
    }

    // lấy username từ token
    public String getUserNameFromJwtToken(String token) {
        String userName = Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody().getSubject();
        return userName;
    }
}