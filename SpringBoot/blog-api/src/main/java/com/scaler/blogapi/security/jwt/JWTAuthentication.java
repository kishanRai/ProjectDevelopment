package com.scaler.blogapi.security.jwt;

import com.scaler.blogapi.users.UserEntity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class JWTAuthentication implements Authentication {

    @Getter
    @Setter
    private String token;

    @Getter
    @Setter
    private Long userId;

    public JWTAuthentication(String token, Long userId) {
        this.token = token;
        this.userId = userId;
    }

    /**
     * @return
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    /**
     * @return
     */
    @Override
    public String getCredentials() {
        return token;
    }

    /**
     * @return
     */
    @Override
    public Object getDetails() {
        return null;
    }

    /**
     * @return
     */
    @Override
    public Long getPrincipal() {
        return userId;
    }

    /**
     * @return
     */
    @Override
    public boolean isAuthenticated() {
        return null != userId;
    }

    /**
     * @param isAuthenticated
     * @throws IllegalArgumentException
     */
    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {

    }

    /**
     * @return
     */
    @Override
    public String getName() {
        return null;
    }
}
