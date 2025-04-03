package IDATA2306.Group12.controller;

import IDATA2306.Group12.dto.UserDTO;
import IDATA2306.Group12.entity.User;
import IDATA2306.Group12.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

/**
 * Represents a REST controller that provides endpoints for managing users.
 * It handles HTTP requests for creating, retrieving, updating, and deleting user data.
 */
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * Retrieves a list of all users.
     *
     * @return a list of UserDTO objects representing all users, or null if an exception occurs.
     */
    @GetMapping
    public List<UserDTO> getAllUsers() {
        try{
            return userService.getAllUsers();
        }catch(Exception e){
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }

    /**
     * Retrieves a user by their unique identifier.
     *
     * @param id the unique identifier of the user to retrieve
     * @return a UserDTO object containing the user's details
     */
    @GetMapping("/{id}")
    public UserDTO getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    /**
     * Creates a new user.
     *
     * @param user The user object received in the request body, containing the details of the user to be created.
     * @return A UserDTO object representing the newly created user.
     */
    @PostMapping
    public UserDTO createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    /**
     * Updates an existing user with the specified ID using the provided user details.
     *
     * @param id   the ID of the user to be updated
     * @param user the new details of the user to update
     * @return a UserDTO object representing the updated user
     */
    @PutMapping("/{id}")
    public UserDTO updateUser(@PathVariable Long id, @RequestBody User user) {
        return userService.updateUser(id, user);
    }

    /**
     * Deletes a user with the specified ID.
     *
     * @param id the ID of the user to be deleted
     */
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }
}