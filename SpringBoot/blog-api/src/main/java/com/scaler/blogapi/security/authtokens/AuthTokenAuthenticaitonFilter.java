package com.scaler.blogapi.security.authtokens;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationConverter;
import org.springframework.security.web.authentication.AuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

public class AuthTokenAuthenticaitonFilter extends AuthenticationFilter {
    public AuthTokenAuthenticaitonFilter(AuthTokenService authTokenService) {
        super(new AuthTokenManager(authTokenService), new AuthTokenConverter());
        setSuccessHandler((request, response, authentication) -> {
            SecurityContextHolder.getContext().setAuthentication(authentication);
        });
    }

    static class AuthTokenConverter implements AuthenticationConverter {

        /**
         * @param request
         * @return
         */
        @Override
        public Authentication convert(HttpServletRequest request) {
            if(null != request.getHeader("X-Auth-Token")){
                String token = request.getHeader("X-Auth-Token");
                return  new AuthTokenAuthentication(token,null);
            }
            return null;
        }
    }

    static class AuthTokenManager implements AuthenticationManager {

        private AuthTokenService authTokenService;
        public AuthTokenManager(AuthTokenService authTokenService) {
            this.authTokenService = authTokenService;
        }

        /**
         * @param authentication the authentication request object
         * @return
         * @throws AuthenticationException
         */
        @Override
        public Authentication authenticate(Authentication authentication) throws AuthenticationException {
            if(authentication instanceof AuthTokenAuthentication){
                AuthTokenAuthentication authTokenAuthentication = (AuthTokenAuthentication) authentication;
                UUID authToken = UUID.fromString(authTokenAuthentication.getCredentials());
                var userId =authTokenService.getUserEntityFromAuthToken(authToken);
                authTokenAuthentication.setUserId(userId.getId());
                return authTokenAuthentication;
            }
            return null;
        }
    }
}
