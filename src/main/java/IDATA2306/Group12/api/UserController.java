package IDATA2306.Group12.api;

import IDATA2306.Group12.dto.user.UserCreateDTO;
import IDATA2306.Group12.dto.user.UserResponseDTO;
import IDATA2306.Group12.service.UserService;
import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public UserController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("")
    @ResponseBody
    public ResponseEntity<List<UserResponseDTO>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/search")
    public ResponseEntity<List<UserResponseDTO>> searchUsers(@RequestParam String query) {
        return ResponseEntity.ok(userService.searchUsers(query));
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @PostMapping
    public ResponseEntity<UserResponseDTO> createUser(@Valid @RequestBody UserCreateDTO userCreateDTO) {
        UserResponseDTO created = userService.createUser(userCreateDTO);
        userCreateDTO.setPassword(passwordEncoder.encode(userCreateDTO.getPassword()));
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PostMapping("/{id}")
    public ResponseEntity<UserResponseDTO> updateUser(@PathVariable Long id, @Valid @RequestBody UserCreateDTO userCreateDTO) {
        System.out.println("You are in the updateUser POST");
        System.out.println("id: " + id);
        UserResponseDTO updated = userService.updateUser(id, userCreateDTO);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/check_email")
    public ResponseEntity<?> checkEmail(@RequestParam String email) {
        if (userService.emailExists(email)) {
            return ResponseEntity.badRequest().body("Email already registered.");
        }
        return ResponseEntity.ok("Email available.");
    }

    @GetMapping("/check_telephone")
    public ResponseEntity<?> checkTelephone(@RequestParam String telephone) {
        if (userService.telephoneExists(telephone)) {
            return ResponseEntity.badRequest().body("Telephone number already registered.");
        }
        return ResponseEntity.ok("Telephone number available");
    }
}