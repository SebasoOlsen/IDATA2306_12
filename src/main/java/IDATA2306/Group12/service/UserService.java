package IDATA2306.Group12.service;

import IDATA2306.Group12.entity.User;
import IDATA2306.Group12.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        return userRepository.findById(id.intValue()).orElse(null);
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }
    @Transactional
    public User updateUser(Long id, User user) {
        User existingUser = userRepository.findById(id.intValue()).orElseThrow(()
                -> new RuntimeException("User not found"));
                existingUser.setFirstName(user.getFirstName());
                existingUser.setLastName(user.getLastName());
                existingUser.setEmail(user.getEmail());
                return userRepository.save(existingUser);
    }
    @Transactional
    public void deleteUser(Long id) {
        userRepository.deleteById(id.intValue());
    }
}
