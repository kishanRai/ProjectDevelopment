package com.scaler.blogapi.security.jwt;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.Instant;

import static org.junit.jupiter.api.Assertions.*;

class JWTServiceTest {

    private JWTService jwtService = new JWTService();

    @Test
    void createJWT_Test() {
        var jwt = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxMTIyIiwiZXhwIjoxNjc3OTk3Mjc5LCJpYXQiOjE2NzczOTI0Nzl9.f3v_VFPN-zxIpByAUfVJX2pfvQKA0U_8Y7dBovxvgbk";
        var userId = jwtService.getUserIdFromJWT(jwt);
        Assertions.assertEquals(1122, userId);
    }


    /**
     * Current below JUnit method is failing because everytime test runs Instant.now() will be different
     */
    @Test
    void getUserIdFromJWT_Test() {
        var userId = 1122;
        var jwt = jwtService.createJWT(userId, Instant.ofEpochMilli(1677997279),Instant.ofEpochMilli(1677392479));
        Assertions.assertEquals("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxMTIyIiwiZXhwIjoxNjc3MzkyLCJpYXQiOjE2Nzc5OTd9.l3q57XJb-z4tgEKna5c4gkollR1fdpHpaw8qZAxHqcc", jwt);
    }
}