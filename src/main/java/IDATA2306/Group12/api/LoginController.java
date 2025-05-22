package IDATA2306.Group12.api;

import IDATA2306.Group12.security.JwtUtil;
import IDATA2306.Group12.service.LoginService;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import IDATA2306.Group12.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/login")
@Tag(name = "Login Management", description = "APIs for managing login.")
public class LoginController {

    private final LoginService loginService;
    private final UserService userService;
    private final JwtUtil jwtUtil;

    public LoginController(LoginService loginService, JwtUtil jwtUtil, UserService userService) {
        this.loginService = loginService;
        this.jwtUtil = jwtUtil;
        this.userService = userService;
    }

    @Operation(
            summary = "Process a login",
            description = "Process a login using email and password."
    )
    @ApiResponse(responseCode = "200", description = "Login successful.")
    @ApiResponse(responseCode = "400", description = "Invalid input.")
    @PostMapping("/public/process")
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

    @Operation(
            summary = "Check if a user is logged in",
            description = "Return a Map containing loggedIn status + email if the user is logged in."
    )
    @GetMapping("/public/isLoggedIn")
    @ApiResponse(responseCode = "200", description = "Logged in status")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> isLoggedIn(HttpServletRequest request) {
        Map<String, Object> responseBody = new HashMap<>();
        responseBody.put("isLoggedIn", false);

        if (request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                if ("token".equals(cookie.getName())) {
                    String token = cookie.getValue();
                    try {
                        if (token != null && jwtUtil.validateToken(token)) {
                            String email = jwtUtil.extractUsername(token);
                            responseBody.put("isLoggedIn", true);
                            responseBody.put("email", email);
                            break;
                        }
                    } catch (Exception e) {
                        // Token is invalid or expired returns loggedIn: false
                    }
                }
            }
        }
        
        return ResponseEntity.ok(responseBody);
    }

    @Operation(
            summary = "Logout",
            description = "Logout the current user by clearing the token cookie."
    )
    @ApiResponse(responseCode = "200", description = "Logout successful. Redirect to login page.")
    @ApiResponse(responseCode = "403", description = "Not authorized to logout (not logged in).")
    @PostMapping("/account/logout")
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