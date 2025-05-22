package IDATA2306.Group12.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class for JWT settings.
 * Loads the JWT secret from application properties.
 */
@Configuration
public class JwtConfig {
    @Value("${jwt.secret}")
    private String jwtSecret;

    /**
     * Returns the JWT secret key.
     *
     * @return the JWT secret
     */
    public String getSecret() {
        return jwtSecret;
    }
}
