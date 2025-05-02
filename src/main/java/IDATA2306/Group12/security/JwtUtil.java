// File: `src/main/java/IDATA2306/Group12/security/JwtUtil.java`
package IDATA2306.Group12.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import java.time.Instant;
import java.util.Date;

public class JwtUtil {

    private static final String SECRET = "stayfinder"; // Replace with a strong secret.
    private static final String ISSUER = "NTNU";
    private static final long EXPIRATION_SECONDS = 3600; // 1 hour expiration.
    private static final long REFRESH_THRESHOLD_SECONDS = 300; // Refresh if less than 5 minutes remain.

    public static String createToken(String email) {
        return JWT.create()
                .withIssuer(ISSUER)
                .withSubject(email)
                .withIssuedAt(Date.from(Instant.now()))
                .withExpiresAt(Date.from(Instant.now().plusSeconds(EXPIRATION_SECONDS)))
                .sign(Algorithm.HMAC256(SECRET));
    }

    public static boolean validateToken(String token) {
        try {
            JWT.require(Algorithm.HMAC256(SECRET))
                    .withIssuer(ISSUER)
                    .build()
                    .verify(token);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    public static String extractUsername(String token) {
        DecodedJWT jwt = JWT.decode(token);
        return jwt.getSubject();
    }

    public static boolean isTokenExpiringSoon(String token) {
        DecodedJWT jwt = JWT.decode(token);
        Date expiresAt = jwt.getExpiresAt();
        long remainingSeconds = (expiresAt.getTime() - System.currentTimeMillis()) / 1000;
        return remainingSeconds < REFRESH_THRESHOLD_SECONDS;
    }

    public static String refreshToken(String token) {
        String username = extractUsername(token);
        return createToken(username);
    }
}