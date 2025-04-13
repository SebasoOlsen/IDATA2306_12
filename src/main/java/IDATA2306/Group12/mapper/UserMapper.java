package IDATA2306.Group12.mapper;

import IDATA2306.Group12.dto.user.UserCreateDTO;
import IDATA2306.Group12.dto.user.UserDTO;
import IDATA2306.Group12.dto.user.UserResponseDTO;
import IDATA2306.Group12.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserMapper() {}

    /**
     * Maps a User entity to a UserResponseDTO.
     */
    public UserResponseDTO toResponseDTO(User user) {
        if (user == null) {
            throw new IllegalArgumentException("User cannot be null");
        }

        UserResponseDTO dto = new UserResponseDTO();
        dto.setId(user.getId());
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        dto.setEmail(user.getEmail());
        return dto;
    }

    public UserDTO toDTO(User user){
        if (user == null) {
            throw new IllegalArgumentException("User cannot be null");
        }

        UserDTO dto = new UserDTO();
        dto.setId(user.getId());
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        dto.setEmail(user.getEmail());
        return dto;
    }

    /**
     * Maps a UserCreateDTO to a User entity.
     */
    public User toEntity(UserCreateDTO dto) {
        if (dto == null) {
            throw new IllegalArgumentException("UserCreateDTO cannot be null");
        }

        User user = new User();
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        user.setTelephone(dto.getTelephone());
        user.setAreaCode(dto.getAreaCode());
        return user;
    }
}
