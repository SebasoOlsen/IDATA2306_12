// File: `src/main/java/IDATA2306/Group12/security/JwtAuthenticationFilter.java`
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
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private static final Logger logger = LoggerFactory.getLogger(JwtAuthenticationFilter.class);

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {
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

        if (token != null) {
            if (token.split("\\.").length != 3) {
                logger.debug("Token is invalid: expected JWT with 3 parts but got: {}", token);
            } else if (JwtUtil.validateToken(token)) {
                String username = JwtUtil.extractUsername(token);
                logger.debug("Token valid. Username: {}", username);
                if (username != null) {
                    UsernamePasswordAuthenticationToken authentication =
                            new UsernamePasswordAuthenticationToken(username, null, Collections.emptyList());
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
                // Check if token is nearing expiry; if yes, refresh it.
                if (JwtUtil.isTokenExpiringSoon(token)) {
                    String newToken = JwtUtil.refreshToken(token);
                    Cookie newCookie = new Cookie("token", newToken);
                    newCookie.setHttpOnly(true);
                    newCookie.setPath("/");
                    response.addCookie(newCookie);
                    logger.debug("Token refreshed and updated in cookie");
                }
            } else {
                logger.debug("Token did not validate.");
            }
        } else {
            logger.debug("No valid token provided.");
        }
        filterChain.doFilter(request, response);
    }
}