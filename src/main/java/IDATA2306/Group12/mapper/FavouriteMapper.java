package IDATA2306.Group12.mapper;

import org.springframework.stereotype.Component;

import IDATA2306.Group12.dto.favourite.FavouriteCreateDTO;
import IDATA2306.Group12.dto.favourite.FavouriteResponseDTO;
import IDATA2306.Group12.entity.Favourite;
import IDATA2306.Group12.entity.Listing;
import IDATA2306.Group12.entity.User;

/**
 * Mapper class for converting between Favourite entity and Favourite DTOs.
 */
@Component
public class FavouriteMapper {

    private final UserMapper userMapper;
    private final ListingMapper listingMapper;

    /**
     * Constructs a FavouriteMapper with required dependencies.
     *
     * @param userMapper the UserMapper to use
     * @param listingMapper the ListingMapper to use
     */
    public FavouriteMapper (UserMapper userMapper, ListingMapper listingMapper) {
        this.userMapper = userMapper;
        this.listingMapper = listingMapper;
    }
    /**
     * Converts a FavouriteCreateDTO, User, and Listing to a Favourite entity.
     *
     * @param dto the FavouriteCreateDTO containing favourite details
     * @param user the User who favourites the listing
     * @param listing the Listing being favorited
     * @return the Favourite entity
     */
    public Favourite toEntity(FavouriteCreateDTO dto, User user, Listing listing) {
        return new Favourite(user, listing);
    }

    /**
     * Converts a Favourite entity to a FavouriteResponseDTO.
     *
     * @param favourite the Favourite entity to convert
     * @return the FavouriteResponseDTO
     */
    public FavouriteResponseDTO toResponseDTO(Favourite favourite) {
        FavouriteResponseDTO dto = new FavouriteResponseDTO();
        dto.setUser(userMapper.toResponseDTO(favourite.getUser()));
        dto.setListing(listingMapper.toResponseDTO(favourite.getListing()));
        return dto;
    }
}

