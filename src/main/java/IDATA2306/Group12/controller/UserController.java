package IDATA2306.Group12.controller;

import IDATA2306.Group12.dto.user.UserCreateDTO;
import IDATA2306.Group12.dto.user.UserDTO;
import IDATA2306.Group12.dto.user.UserResponseDTO;
import IDATA2306.Group12.service.UserService;
import jakarta.validation.Valid;

import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.autoconfigure.observation.ObservationProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @PostMapping
    public ResponseEntity<UserResponseDTO> createUser(@Valid @RequestBody UserCreateDTO userCreateDTO) {
        UserResponseDTO created = userService.createUser(userCreateDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDTO> updateUser(@PathVariable Long id, @Valid @RequestBody UserCreateDTO userCreateDTO) {
        UserResponseDTO updated = userService.updateUser(id, userCreateDTO);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }



//    @Autowired
//    private UserService userService;
//
//    @GetMapping
//    public List<UserDTO> getAllUsers() {
//        try{
//            return userService.getAllUsers();
//        }catch(Exception e){
//            System.out.println("Error: " + e.getMessage());
//            return null;
//        }
//
//    }
//
//    @GetMapping("/{id}")
//    public UserDTO getUserById(@PathVariable Long id) {
//        return userService.getUserById(id);
//    }
//
//    @PostMapping
//    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO) {
//        UserDTO created = userService.createUser(userDTO);
//        return ResponseEntity.status(HttpStatus.CREATED).body(created);
//    }
//
//    @PutMapping("/{id}")
//    public UserDTO updateUser(@PathVariable Long id, @RequestBody UserDTO userDTO) {
//        return userService.updateUser(id, userDTO);
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
//        try {
//            userService.deleteUser(id);
//            return ResponseEntity.status(HttpStatus.OK).build();
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
//        }
//    }
}