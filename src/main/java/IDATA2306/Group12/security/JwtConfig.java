package IDATA2306.Group12.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JwtConfig {
    @Value("${jwt.secret}")
    private String jwtSecret;

    public String getSecret() {
        return jwtSecret;
    }
}
