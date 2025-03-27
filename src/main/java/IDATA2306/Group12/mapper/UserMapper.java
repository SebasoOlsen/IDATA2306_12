package IDATA2306.Group12.mapper;

import IDATA2306.Group12.entity.User;
import IDATA2306.Group12.dto.UserDTO;

/**
 * Mapper class for converting between User entity and UserDTO.
 */
public class UserMapper {

    /**
     * Converts a User entity to a UserDTO.
     *
     * @param user the User entity to convert
     * @return the corresponding UserDTO
     */
    public static UserDTO toDTO(User user) {
        if (user == null) {
            throw new IllegalArgumentException("User cannot be null");
        }
        return new UserDTO(
            user.getId(),
            user.getFirstName(),
            user.getLastName(),
            user.getEmail(),
            user.getTelephone(),
            user.getAreaCode()
        );
    }

    /**
     * Converts a UserDTO to a User entity.
     *
     * @param userDTO the UserDTO to convert
     * @return the corresponding User entity
     */
    public static User toEntity(UserDTO userDTO) {
        if (userDTO == null) {
            throw new IllegalArgumentException("UserDTO cannot be null");
        }
        User user = new User();
        user.setId(userDTO.getId());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setEmail(userDTO.getEmail());
        user.setTelephone(userDTO.getTelephone());
        user.setAreaCode(userDTO.getAreaCode());
        return user;
    }
}