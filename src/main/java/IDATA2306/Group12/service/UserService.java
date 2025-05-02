package IDATA2306.Group12.service;

import IDATA2306.Group12.dto.user.UserCreateDTO;
import IDATA2306.Group12.dto.user.UserResponseDTO;
import IDATA2306.Group12.entity.User;
import IDATA2306.Group12.exception.UserExistsException;
import IDATA2306.Group12.mapper.UserMapper;
import IDATA2306.Group12.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * The UserService class provides methods for managing user data in the system.
 * It interacts with the UserRepository to perform CRUD operations and maps
 * User entities to UserDTO objects for external use.
 */
@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, UserMapper userMapper,
                       PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
    }

    public List<UserResponseDTO> getAllUsers() {
        return userRepository.findAll().stream()
                .map(userMapper::toResponseDTO)
                .toList();
    }

    /**
     * Searches for users based on a query string. The search is performed on
     * the first name, last name, email, telephone, and role fields of the user.
     * The search is case-insensitive for string fields and exact match for telephone.
     * 
     * @param query the search query string
     * @return a list of UserResponseDTO objects that match the search criteria
     */
    public List<UserResponseDTO> searchUsers(String query) {
    return getAllUsers().stream()
        .filter(user ->
            (user.getFirstName() != null && user.getFirstName().toLowerCase().contains(query.toLowerCase())) ||
            (user.getLastName() != null && user.getLastName().toLowerCase().contains(query.toLowerCase())) ||
            (user.getEmail() != null && user.getEmail().toLowerCase().contains(query.toLowerCase())) ||
            (user.getTelephone() != null && user.getTelephone().contains(query)) ||
            (user.getRole() != null && user.getRole().toLowerCase().contains(query.toLowerCase()))
        )
        .collect(Collectors.toList());
    }

    public UserResponseDTO getUserById(Long id) {
        User user = userRepository.findById(id.intValue())
                .orElseThrow(() -> new RuntimeException("User not found"));
        return userMapper.toResponseDTO(user);
    }

    public UserResponseDTO createUser(UserCreateDTO userCreateDTO) {

        if (userRepository.existsByEmail(userCreateDTO.getEmail())) {
            throw new UserExistsException("This email is already registered.");
        }
        if (userRepository.existsByTelephone(userCreateDTO.getTelephone())) {
            throw new UserExistsException("This telephone number is already registered.");
        }
        User user = userMapper.toEntity(userCreateDTO);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User saved = userRepository.save(user);
        return userMapper.toResponseDTO(saved);
    }

    @Transactional
    public UserResponseDTO updateUser(Long id, UserCreateDTO userCreateDTO) {
        User existingUser = userRepository.findById(id.intValue())
                .orElseThrow(() -> new RuntimeException("User not found"));

        existingUser.setFirstName(userCreateDTO.getFirstName());
        existingUser.setLastName(userCreateDTO.getLastName());
        existingUser.setEmail(userCreateDTO.getEmail());
        existingUser.setRole(userCreateDTO.getRole());
        existingUser.setTelephone(userCreateDTO.getTelephone());

        User saved = userRepository.save(existingUser);
        return userMapper.toResponseDTO(saved);
    }

    @Transactional
    public void deleteUser(Long id) {
        userRepository.deleteById(id.intValue());
    }

    public boolean emailExists(String email) {
        return this.userRepository.existsByEmail(email);
    }

    public boolean telephoneExists(String telephone) {
        return this.userRepository.existsByTelephone(telephone);
    }

}
