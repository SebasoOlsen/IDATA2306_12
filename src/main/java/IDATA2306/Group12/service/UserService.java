package IDATA2306.Group12.service;

import IDATA2306.Group12.dto.UserDTO;
import IDATA2306.Group12.entity.User;
import IDATA2306.Group12.mapper.UserMapper;
import IDATA2306.Group12.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * The UserService class provides methods for managing user data in the system.
 * It interacts with the UserRepository to perform CRUD operations and maps
 * User entities to UserDTO objects for external use.
 */
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    /**
     * Retrieves a list of all users from the repository and converts them into UserDTO objects.
     *
     * @return a list of UserDTO objects representing all users in the system.
     */
    public List<UserDTO> getAllUsers() {
        List <User> users = userRepository.findAll();
        return users.stream()
                .map(user -> new UserDTO(user.getId(), 
                                        user.getFirstName(), 
                                        user.getLastName(), 
                                        user.getEmail(), 
                                        user.getTelephone(), 
                                        user.getAreaCode())) 
                .collect(Collectors.toList());
    }

    /**
     * Retrieves a user by their unique identifier.
     *
     * @param id the unique identifier of the user to retrieve
     * @return a UserDTO object representing the user if found, or null if no user exists with the given ID
     */
    public UserDTO getUserById(Long id) {
        return UserMapper.toDTO(userRepository.findById(id.intValue()).orElse(null));
    }

    /**
     * Creates a new user by saving it to the repository and mapping it to a UserDTO.
     *
     * @param user the User entity to be created and saved
     * @return a UserDTO representation of the saved User entity
     */
    public UserDTO createUser(User user) {
        userRepository.save(user);
        return UserMapper.toDTO(user);
    }

    /**
     * Updates an existing user's information based on the provided user ID and user details.
     * 
     * @param id   The ID of the user to be updated.
     * @param user The user object containing the updated information.
     * @return A UserDTO object representing the updated user.
     * @throws RuntimeException if the user with the specified ID is not found.
     */
    @Transactional
    public UserDTO updateUser(Long id, User user) {
        User existingUser = userRepository.findById(id.intValue()).orElseThrow(()
                -> new RuntimeException("User not found"));
                existingUser.setFirstName(user.getFirstName());
                existingUser.setLastName(user.getLastName());
                existingUser.setEmail(user.getEmail());
                userRepository.save(existingUser);
                return UserMapper.toDTO(existingUser);
    }

    /**
     * Deletes a user from the repository based on the provided user ID.
     *
     * @param id the ID of the user to be deleted. It is expected to be a Long value,
     *           which will be converted to an integer for deletion.
     * @throws IllegalArgumentException if the provided ID is null or invalid.
//     * @throws EmptyResultDataAccessException if no user with the given ID exists in the repository.
     */
    @Transactional
    public void deleteUser(Long id) {
        userRepository.deleteById(id.intValue());
    }
}
