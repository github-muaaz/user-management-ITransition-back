package com.example.usermanagement.security;

import com.example.usermanagement.entity.User;
import com.example.usermanagement.exceptions.RestException;
import com.example.usermanagement.service.auth.AuthService;
import com.example.usermanagement.utils.MessageConstants;
import com.example.usermanagement.utils.RestConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

@Slf4j
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtTokenProvider jwtTokenProvider;
    private final AuthService authService;

    public JwtAuthenticationFilter(JwtTokenProvider jwtTokenProvider,
                                   @Lazy AuthService authService) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.authService = authService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest,
                                    HttpServletResponse httpServletResponse,
                                    FilterChain filterChain) throws ServletException, IOException {

        try {
            setUserPrincipalIfAllOk(httpServletRequest);
        } catch (Exception e) {
            log.error("Error in JwtAuthenticationFilter setUserPrincipalIfAllOk method: ", e);
        }

        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

    private void setUserPrincipalIfAllOk(HttpServletRequest request) {
        String authorization = request.getHeader(RestConstants.AUTHENTICATION_HEADER);

        if (authorization != null) {
            User user = getUserFromBearerToken(authorization);
            if (user != null) {
                if (!user.isEnabled())
                    throw RestException
                            .restThrow(MessageConstants.USER_DISABLED, HttpStatus.FORBIDDEN);

                UsernamePasswordAuthenticationToken authenticationToken =
                        new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }
    }


    public User getUserFromBearerToken(String token) {
        try {
            token = token.substring("Bearer".length()).trim();
            if (jwtTokenProvider.validToken(token, true)) {
                String userId = jwtTokenProvider.getUserIdFromToken(token, true);
                return authService.getUserById(UUID.fromString(userId)).orElseThrow(RuntimeException::new);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
