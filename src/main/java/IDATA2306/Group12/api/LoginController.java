package IDATA2306.Group12.api;

import IDATA2306.Group12.security.JwtUtil;
import IDATA2306.Group12.service.LoginService;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

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
    private final JwtUtil jwtUtil;

    public LoginController(LoginService loginService, JwtUtil jwtUtil) {
        this.loginService = loginService;
        this.jwtUtil = jwtUtil;
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
            summary = "Check if user is logged in",
            description = "Check if user is logged in by checking if a token cookie is present."
    )
    @ApiResponse(responseCode = "200", description = "Boolean: loggedIn true/false")
    @GetMapping("/public/isLoggedIn")
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