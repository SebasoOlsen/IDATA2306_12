package IDATA2306.Group12.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private static final Logger logger = LoggerFactory.getLogger(JwtAuthenticationFilter.class);

    private final JwtUtil jwtUtil;

    @Autowired
    public JwtAuthenticationFilter(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        //Extract JWT token from cookie if present
        String token = null;
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("token".equals(cookie.getName())) {
                    token = cookie.getValue();
                    break;
                }
            }
        }
        logger.debug("Extracted token from cookie: {}", token);

        if (token != null && jwtUtil.validateToken(token)) {
            String role = jwtUtil.getRoleFromToken(token);
            String username = jwtUtil.extractUsername(token);
            //TODO: Decide if username or email should be used for the user

            //Create authority based on role
            GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + role.toUpperCase());

            //Create an authentication token
            UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(username, null, Collections.singletonList(authority));

            //Set authentication token to security context
            SecurityContextHolder.getContext().setAuthentication(authentication);

            //Check if the token is expiring soon and refresh if so
            if (jwtUtil.isTokenExpiringSoon(token)) {
                refreshToken(response, token);
            }

            logger.debug("Set authentication token to security context.");
        }

        /* OLD CODE TODO: CHECK IF NEEDED
        if (token != null) {
            if (token.split("\\.").length != 3) {
                logger.debug("Token is invalid: expected JWT with 3 parts but got: {}", token);
            } else if (jwtUtil.validateToken(token)) {
                String username = jwtUtil.extractUsername(token);
                logger.debug("Token valid. Username: {}", username);
                if (username != null) {
                    UsernamePasswordAuthenticationToken authentication =
                            new UsernamePasswordAuthenticationToken(username, null, Collections.emptyList());
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
                if (jwtUtil.isTokenExpiringSoon(token)) {
                    refreshToken(response, token);
                }
            } else {
                logger.debug("Token did not validate.");
            }
        } else {
            logger.debug("No valid token provided.");
        }
         **/
        filterChain.doFilter(request, response);
    }

    private void refreshToken(HttpServletResponse response, String token) {
        String newToken = jwtUtil.refreshToken(token);
        Cookie newCookie = new Cookie("token", newToken);
        newCookie.setHttpOnly(true);
        newCookie.setPath("/");
        response.addCookie(newCookie);
        logger.debug("Token refreshed and updated in cookie");
    }
}