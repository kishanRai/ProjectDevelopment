package com.scaler.blogapi.security.authtokens;

import com.scaler.blogapi.users.UserEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AuthTokenService {

    private final AuthTokenRepository authTokenRepository;

    public AuthTokenService(AuthTokenRepository authTokenRepository) {
        this.authTokenRepository = authTokenRepository;
    }

    public UUID createAuthToken(UserEntity userEntity) {
        AuthTokenEntity authTokenEntity = new AuthTokenEntity();
        authTokenEntity.setUserEntity(userEntity);
        var savedAuthToken = authTokenRepository.save(authTokenEntity);
        return savedAuthToken.getId();
    }

    public UserEntity getUserEntityFromAuthToken(UUID authtoken) {
        var fetchedUserEntity = authTokenRepository.findById(authtoken)
                .orElseThrow(() -> new BadCredentialsException("Invalid Auth Token"));
        return fetchedUserEntity.getUserEntity();
    }
}
