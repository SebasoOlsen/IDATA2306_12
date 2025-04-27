package IDATA2306.Group12.service;

import IDATA2306.Group12.dto.user.UserCreateDTO;
import IDATA2306.Group12.dto.user.UserResponseDTO;
import IDATA2306.Group12.entity.User;
import IDATA2306.Group12.exception.UserExistsException;
import IDATA2306.Group12.mapper.UserMapper;
import IDATA2306.Group12.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * The UserService class provides methods for managing user data in the system.
 * It interacts with the UserRepository to perform CRUD operations and maps
 * User entities to UserDTO objects for external use.
 */
@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public List<UserResponseDTO> getAllUsers() {
        return userRepository.findAll().stream()
                .map(userMapper::toResponseDTO)
                .toList();
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
