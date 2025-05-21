package IDATA2306.Group12.api;

import IDATA2306.Group12.security.JwtUtil;
import IDATA2306.Group12.service.LoginService;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/login")
public class LoginController {

    private final LoginService loginService;
    private final JwtUtil jwtUtil;

    public LoginController(LoginService loginService, JwtUtil jwtUtil) {
        this.loginService = loginService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/")
    @ResponseBody
    public ResponseEntity<String> processLogin(@RequestParam String email,
                                               @RequestParam String password) {
        System.out.println("Login attempt with email: " + email);
        System.out.println("Login attempt with password: " + password);
        try {
            String token = loginService.login(email, password);

            ResponseCookie cookie = ResponseCookie.from("token", token)
                    .httpOnly(true)
                    .secure(true)
                    .path("/")
                    .maxAge(Duration.ofHours(24))
                    .sameSite("Lax")
                    .build();

            return ResponseEntity.ok()
                    .header(HttpHeaders.SET_COOKIE, cookie.toString())
                    .body("Login successful.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    @GetMapping("/isLoggedIn")
    @ResponseBody
    public ResponseEntity<Map<String, Boolean>> isLoggedIn(HttpServletRequest request) {
        boolean loggedIn = false;
        //log.info("isLoggedIn endpoint called.");
        if (request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                if ("token".equals(cookie.getName())) {
                    String token = cookie.getValue();
                    if (token != null && token.split("\\.").length == 3 && jwtUtil.validateToken(token)) {
                        loggedIn = true;
                        break;
                    }
                }
            }
        }
        //log.info("isLoggedIn called. User logged in: \\{{}\\}", loggedIn);
        Map<String, Boolean> responseBody = new HashMap<>();
        responseBody.put("loggedIn", loggedIn);
        return ResponseEntity.ok(responseBody);
    }

    @PostMapping("/logout")
    @ResponseBody
    public ResponseEntity<Map<String, String>> logout(HttpServletResponse response) {
        //log.info("Logout endpoint called.");

        // Clear the token cookie - match properties if needed
        Cookie cookie = new Cookie("token", null);
        cookie.setPath("/");
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        //log.info("Token cookie cleared and added to response.");

        Map<String, String> body = new HashMap<>();
        body.put("redirect", "/login");
        //log.info("Responding with redirect to /login.");

        return ResponseEntity.ok(body);
    }
}