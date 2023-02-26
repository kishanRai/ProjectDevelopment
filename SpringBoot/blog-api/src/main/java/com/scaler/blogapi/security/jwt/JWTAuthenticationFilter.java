package com.scaler.blogapi.security.jwt;

import com.scaler.blogapi.users.UserEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationManagerResolver;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationConverter;
import org.springframework.security.web.authentication.AuthenticationFilter;

import javax.servlet.http.HttpServletRequest;

/**
 * The purpose of JWTAuthenticationFilter is to authenticate Request which contains JWT Token
 */
public class JWTAuthenticationFilter extends AuthenticationFilter {

    // INFO : Two different parameterised Constructor

   /* public JWTAuthenticationFilter(AuthenticationManager authenticationManager, AuthenticationConverter authenticationConverter) {
        super(authenticationManager, authenticationConverter);
    }

    public JWTAuthenticationFilter(AuthenticationManagerResolver<HttpServletRequest> authenticationManagerResolver, AuthenticationConverter authenticationConverter) {
        super(authenticationManagerResolver, authenticationConverter);
    }*/

    public JWTAuthenticationFilter() {
        super(new JWTAuthenticationManager(), new JWTAuthenticationConverter());

        //TODO : To understand
        setSuccessHandler((request, response, authentication) -> SecurityContextHolder.getContext().setAuthentication(authentication));

    }

    static class JWTAuthenticationConverter implements AuthenticationConverter {

        /**
         * @param request
         * @return
         */
        @Override
        public Authentication convert(HttpServletRequest request) {
            if (null != request.getHeader("Authorization")) {
                String token = request.getHeader("Authorization").split(" ")[1];
                return new JWTAuthentication(token, null);
            }
            return null;
        }
    }

    static class JWTAuthenticationManager implements AuthenticationManager {

        //TODO : Autowire service
        private JWTService jwtService = new JWTService();

        /**
         * @param authentication
         * @return
         * @throws AuthenticationException
         */
        @Override
        public Authentication authenticate(Authentication authentication) throws AuthenticationException {
            if (authentication instanceof JWTAuthentication) {
                JWTAuthentication jwtAuthentication = (JWTAuthentication) authentication;
                String token = jwtAuthentication.getCredentials();

                if (null != token) {
                    var userId = jwtService.getUserIdFromJWT(token);
                    if (null != userId) {
                        jwtAuthentication.setUserId(userId);
                        SecurityContextHolder.getContext().setAuthentication(authentication);
                        return jwtAuthentication;
                    }
                }
            }
            return null;
        }
    }


}
