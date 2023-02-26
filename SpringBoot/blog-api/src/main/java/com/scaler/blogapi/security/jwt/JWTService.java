package com.scaler.blogapi.security.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;

@Service
public class JWTService {

    /**
     * Secret key shouldn't part of Source code(HARD CODE). Instead, it should be AWS key manager or save in config file
     */
    private Algorithm algorithm = Algorithm.HMAC256("SECRET SIGNING KEY (should be in env or config)");

    public String createJWT(Long userId) {
        return JWT.create()
                .withSubject(userId.toString())
                .withIssuedAt(Instant.now())
                .withExpiresAt(Instant.now().plusMillis(1000 * 60 * 60 * 24 * 7)) /* 7 days addition */
                .sign(algorithm);
    }

    /**
     * Created another protected createdJWT method so that it not available to other packages.
     * Main expose is for JUnit Method usage i.e. same package
     *
     * @param userId
     * @param issuedAt
     * @param expiresAt
     * @return
     */
    protected String createJWT(Long userId, Instant issuedAt, Instant expiresAt) {
        return JWT.create()
                .withSubject(userId.toString())
                .withIssuedAt(issuedAt)
                .withExpiresAt(expiresAt)
                .sign(algorithm);
    }

    public Integer getUserIdFromJWT(String jwt) {
        try {
            var verifier = JWT.require(algorithm).build();
            var decodedJWT = verifier.verify(jwt);
            var subject = decodedJWT.getSubject();
            return Integer.parseInt(subject);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return null;
    }

}
