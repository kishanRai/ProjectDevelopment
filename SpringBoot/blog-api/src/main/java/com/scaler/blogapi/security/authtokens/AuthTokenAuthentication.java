package com.scaler.blogapi.security.authtokens;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class AuthTokenAuthentication implements Authentication {

    private String token;

    @Getter
    @Setter
    private Long userId;

    public AuthTokenAuthentication(String token, Long userId) {
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
     * @param isAuthenticated <code>true</code> if the token should be trusted (which may
     *                        result in an exception) or <code>false</code> if the token should not be trusted
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
