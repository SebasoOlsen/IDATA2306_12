package IDATA2306.Group12.security;

import IDATA2306.Group12.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import IDATA2306.Group12.security.JwtConfig;

/**
 * Utility class for handling JWT operations such as token generation, validation,
 * extraction of claims, and refreshing tokens.
 */
@Component
public class JwtUtil {

    private final JwtConfig jwtConfig;

    /**
     * Constructs a JwtUtil with the required JwtConfig dependency.
     *
     * @param jwtConfig the configuration containing the JWT secret
     */
    @Autowired
    public JwtUtil(JwtConfig jwtConfig) {
        this.jwtConfig = jwtConfig;
    }

    /**
     * Validates the given JWT token.
     *
     * @param token the JWT token to validate
     * @return true if the token is valid, false otherwise
     */
    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(jwtConfig.getSecret()).parseClaimsJws(token);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    /**
     * Extracts the username (subject) from the JWT token.
     *
     * @param token the JWT token
     * @return the username (subject) from the token
     */
    public String extractUsername(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(jwtConfig.getSecret())
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }

    /**
     * Checks if the JWT token is expiring within the next 15 minutes.
     *
     * @param token the JWT token
     * @return true if the token is expiring soon, false otherwise
     */
    public boolean isTokenExpiringSoon(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(jwtConfig.getSecret())
                .parseClaimsJws(token)
                .getBody();
        Date expiration = claims.getExpiration();
        long now = System.currentTimeMillis();
        return expiration.getTime() - now < 15 * 60 * 1000;
    }

    /**
     * Refreshes the JWT token by updating its issued and expiration dates.
     *
     * @param token the current JWT token
     * @return the refreshed JWT token
     */
    public String refreshToken(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(jwtConfig.getSecret())
                .parseClaimsJws(token)
                .getBody();
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(claims.getSubject())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 60 * 60 * 1000))
                .signWith(SignatureAlgorithm.HS256, jwtConfig.getSecret())
                .compact();
    }

    /**
     * Generates a new JWT token for the given user.
     *
     * @param user the user for whom to generate the token
     * @return the generated JWT token
     */
    public String generateToken(User user) {
        return Jwts.builder()
                .setSubject(user.getEmail())
                .claim("role", user.getRole())
                .claim("id", user.getId())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 60 * 60 * 1000))
                .signWith(SignatureAlgorithm.HS256, jwtConfig.getSecret())
                .compact();
    }

    /**
     * Get the role from the token.
     * @param token The token to extract the role from
     * @return The users role as a string. If the token is invalid, returns null.
     */
    public String getRoleFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(jwtConfig.getSecret())
                .parseClaimsJws(token)
                .getBody()
                .get("role", String.class);
    }

}