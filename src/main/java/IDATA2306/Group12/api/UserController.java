package IDATA2306.Group12.api;

import IDATA2306.Group12.dto.user.UserCreateDTO;
import IDATA2306.Group12.dto.user.UserResponseDTO;
import IDATA2306.Group12.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@Tag(name = "User Management", description = "APIs for managing users.")
public class UserController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public UserController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @Operation(
            summary = "Get all users.",
            description = "Returns a list of all stored in the database.")
    @ApiResponse(responseCode = "200", description = "List of all users")
    @GetMapping("")
    @ResponseBody
    public ResponseEntity<List<UserResponseDTO>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @Operation(
            summary = "Search for a user by first name, last name, email, telephone or role.",
            description = "Searches for users based on a query string. " +
                    "The search is performed on the first name, last name, email, telephone, and role fields of the user. " +
                    "The search is case-insensitive for string fields and exact match for telephone."
    )
    @ApiResponse(responseCode = "200", description = "List of users matching the query.")
    @GetMapping("/search")
    public ResponseEntity<List<UserResponseDTO>> searchUsers(@RequestParam String query) {
        return ResponseEntity.ok(userService.searchUsers(query));
    }

    @Operation(
            summary = "Get a user by their ID.",
            description = "Returns a user with the specified ID."
    )
    @GetMapping("/{id}")
    @ApiResponse(responseCode = "200", description = "User with the specified ID.")
    public ResponseEntity<UserResponseDTO> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @Operation(
            summary = "Create a new user.",
            description = ""
    )
    @PostMapping
    @ApiResponse(responseCode = "201", description = "User created successfully.")
    public ResponseEntity<UserResponseDTO> createUser(@Valid @RequestBody UserCreateDTO userCreateDTO) {
        UserResponseDTO created = userService.createUser(userCreateDTO);
        userCreateDTO.setPassword(passwordEncoder.encode(userCreateDTO.getPassword()));
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PostMapping("/{id}")
    @ApiResponse(responseCode = "200", description = "User updated successfully.")
    public ResponseEntity<UserResponseDTO> updateUser(@PathVariable Long id, @Valid @RequestBody UserCreateDTO userCreateDTO) {
        System.out.println("You are in the updateUser POST");
        System.out.println("id: " + id);
        UserResponseDTO updated = userService.updateUser(id, userCreateDTO);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "204", description = "User deleted successfully.")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/check_email")
    @ApiResponse(responseCode = "200", description = "Email available.")
    @ApiResponse(responseCode = "400", description = "Email already registered.")
    public ResponseEntity<?> checkEmail(@RequestParam String email) {
        if (userService.emailExists(email)) {
            return ResponseEntity.badRequest().body("Email already registered.");
        }
        return ResponseEntity.ok("Email available.");
    }

    @GetMapping("/check_telephone")
    @ApiResponse(responseCode = "200", description = "Telephone number available.")
    @ApiResponse(responseCode = "400", description = "Telephone number already registered.")
    public ResponseEntity<?> checkTelephone(@RequestParam String telephone) {
        if (userService.telephoneExists(telephone)) {
            return ResponseEntity.badRequest().body("Telephone number already registered.");
        }
        return ResponseEntity.ok("Telephone number available");
    }
}