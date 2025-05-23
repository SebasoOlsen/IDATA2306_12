package IDATA2306.Group12.service;

import IDATA2306.Group12.entity.User;
import IDATA2306.Group12.repository.UserRepository;
import IDATA2306.Group12.security.JwtUtil;
import jakarta.servlet.http.Cookie;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Service for handling user login and authentication.
 */
@Service
public class LoginService {
    private final Logger log = LoggerFactory.getLogger(LoginService.class);
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    /**
     * Constructs a LoginService with required dependencies.
     *
     * @param userRepository the user repository
     * @param passwordEncoder the password encoder
     * @param jwtUtil the JWT utility
     */
    public LoginService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    /**
     * Authenticates a user and returns a JWT token if successful
     *
     * @param email the user's email
     * @param password the user's password
     * @return the JWT token
     * @throws IllegalArgumentException if email or password is invalid
     */
    public String login(String email, String password) throws IllegalArgumentException {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new IllegalArgumentException("Invalid email.");
        }
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new IllegalArgumentException("Invalid password.");
        }
        String token = jwtUtil.generateToken(user);

        Cookie cookie = new Cookie("token", token);
        cookie.setHttpOnly(true);
        cookie.setPath("/");

        return token;
    }
}
