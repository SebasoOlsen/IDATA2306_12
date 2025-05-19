// File: `src/main/java/IDATA2306/Group12/controller/LoginController.java`
package IDATA2306.Group12.controller;

import IDATA2306.Group12.entity.User;
import IDATA2306.Group12.repository.UserRepository;
import IDATA2306.Group12.security.JwtUtil;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LoginController {

    private static final Logger log = LoggerFactory.getLogger(LoginController.class);

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @Autowired
    public LoginController(UserRepository userRepository,
                           PasswordEncoder passwordEncoder,
                           JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    @GetMapping("/login")
    public String showLoginPage(HttpServletRequest request) {
        System.out.println("GET /login requested.");
        if (request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                System.out.println("Found cookie: \\{{}\\}" + cookie.getName());
                if ("token".equals(cookie.getName())) {
                    String token = cookie.getValue();
                    System.out.println("Token from cookie found: \\{{}\\}" + token);
                    if (token != null && token.split("\\.").length == 3 && jwtUtil.validateToken(token)) {
                        System.out.println("Valid token detected, redirecting to /myPage.");
                        return "redirect:/myPage";
                    }
                }
            }
        }
        System.out.println("No valid token found, returning login page.");
        return "login";
    }

    @PostMapping("/login")
    @ResponseBody
    public ResponseEntity<Map<String, String>> processLogin(@RequestParam String email,
                                                            @RequestParam String password,
                                                            HttpServletResponse response) throws IOException {
        log.info("POST /login called with email: \\{{}\\}" + email);
        User user = userRepository.findByEmail(email);
        if (user == null || !passwordEncoder.matches(password, user.getPassword())) {
            log.warn("Authentication failed for email: \\{{}\\}", email);
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid email or password");
            return null;
        }
        String token = jwtUtil.generateToken(email);
        log.info("Token created successfully for email: \\{{}\\}", email);
        Cookie cookie = new Cookie("token", token);
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        response.addCookie(cookie);
        log.info("Cookie added. Sending JSON response with redirect URL.");

        Map<String, String> body = new HashMap<>();
        body.put("redirect", "/myPage");
        return ResponseEntity.ok(body);
    }
}