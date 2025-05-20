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

/**
 * Rest Controller for the User Management API.
 * Mapping convention:
 * Root: /api/users
 * Public access: /public/ENDPOINT_NAME
 * Logged in access: /user/ENDPOINT_NAME
 * Admin restricted access: /admin/ENDPOINT_NAME
 */
@RestController
@RequestMapping("/api/users")
@Tag(name = "User Management", description = "APIs for managing users.")
public class UserController {


    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    /**
     * Constructor for the User Controller. Automatically created by Springboot on launch
     * @param userService Dependency provided by Springboot.
     * @param passwordEncoder Dependency provided by Springboot
     */
    public UserController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @Operation(
            summary = "Get all users.",
            description = "Returns a list of all stored in the database.")
    @ApiResponse(responseCode = "200", description = "List of all users")
    @ApiResponse(responseCode = "403", description = "Not authorized to view this list of users.")
    @GetMapping("/admin/getAllUsers")
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
    @ApiResponse(responseCode = "403", description = "Not authorized to view this list of users.")
    @GetMapping("/admin/search")
    public ResponseEntity<List<UserResponseDTO>> searchUsers(@RequestParam String query) {
        return ResponseEntity.ok(userService.searchUsers(query));
    }

    @Operation(
            summary = "Get a user by their ID.",
            description = "Returns a user with the specified ID."
    )
    @GetMapping("/admin/search/{id}")
    @ApiResponse(responseCode = "200", description = "User with the specified ID.")
    @ApiResponse(responseCode = "404", description = "User not found.")
    @ApiResponse(responseCode = "403", description = "Not authorized to view this list of users.")
    public ResponseEntity<UserResponseDTO> getUserById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(userService.getUserById(id));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @Operation(
            summary = "Create a new user.",
            description = "Register a new user to the database. Request must include a valid userCreateDTO."
    )
    @PostMapping("/public/register")
    @ApiResponse(responseCode = "201", description = "User created successfully.")
    @ApiResponse(responseCode = "400", description = "Invalid input.")
    public ResponseEntity<UserResponseDTO> createUser(@Valid @RequestBody UserCreateDTO userCreateDTO) {
        try {
            UserResponseDTO created = userService.createUser(userCreateDTO);
            userCreateDTO.setPassword(passwordEncoder.encode(userCreateDTO.getPassword()));
            return ResponseEntity.status(HttpStatus.CREATED).body(created);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @Operation(
            summary = "Update an existing user",
            description = "Update a user by ID defined in PATH. RequestBody must include a valid userCreateDTO"
    )
    @PostMapping("/admin/edit/{id}")
    @ApiResponse(responseCode = "200", description = "User updated successfully.")
    @ApiResponse(responseCode = "404", description = "User not found.")
    @ApiResponse(responseCode = "403", description = "Not authorized to edit this user.")
    public ResponseEntity<UserResponseDTO> updateUser(@PathVariable Long id, @Valid @RequestBody UserCreateDTO userCreateDTO) {
        System.out.println("You are in the updateUser POST");
        System.out.println("id: " + id);
        try {
            UserResponseDTO updated = userService.updateUser(id, userCreateDTO);
            return ResponseEntity.ok(updated);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @Operation(
            summary = "Delete an existing user",
            description = "Delete a user by ID defined in PATH."
    )
    @DeleteMapping("/admin/delete/{id}")
    @ApiResponse(responseCode = "204", description = "User deleted successfully.")
    @ApiResponse(responseCode = "403", description = "Not authorized to delete this user.")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(
            summary = "Check if email is already registered",
            description = "Check if email is already registered"
    )
    @GetMapping("/public/check_email")
    @ApiResponse(responseCode = "200", description = "Email available.")
    @ApiResponse(responseCode = "400", description = "Email already registered.")
    public ResponseEntity<?> checkEmail(@RequestParam String email) {
        if (userService.emailExists(email)) {
            return ResponseEntity.badRequest().body("Email already registered.");
        }
        return ResponseEntity.ok("Email available.");
    }

    @Operation(
            summary = "Check if telephone is already registered",
            description = "Check if telephone is already registered"
    )
    @GetMapping("/public/check_telephone")
    @ApiResponse(responseCode = "200", description = "Telephone number available.")
    @ApiResponse(responseCode = "400", description = "Telephone number already registered.")
    public ResponseEntity<?> checkTelephone(@RequestParam String telephone) {
        if (userService.telephoneExists(telephone)) {
            return ResponseEntity.badRequest().body("Telephone number already registered.");
        }
        return ResponseEntity.ok("Telephone number available");
    }
}