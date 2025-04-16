package IDATA2306.Group12.mapper;

import org.springframework.stereotype.Component;

import IDATA2306.Group12.dto.favourite.FavouriteCreateDTO;
import IDATA2306.Group12.dto.favourite.FavouriteResponseDTO;
import IDATA2306.Group12.entity.Favourite;
import IDATA2306.Group12.entity.Listing;
import IDATA2306.Group12.entity.User;

@Component
public class FavouriteMapper {

    private final UserMapper userMapper;
    private final ListingMapper listingMapper;

    public FavouriteMapper (UserMapper userMapper, ListingMapper listingMapper) {
        this.userMapper = userMapper;
        this.listingMapper = listingMapper;
    }

    public Favourite toEntity(FavouriteCreateDTO dto, User user, Listing listing) {
        return new Favourite(user, listing);
    }

    public FavouriteResponseDTO toResponseDTO(Favourite favourite) {
        FavouriteResponseDTO dto = new FavouriteResponseDTO();
        dto.setUser(userMapper.toResponseDTO(favourite.getUser()));
        dto.setListing(listingMapper.toResponseDTO(favourite.getListing()));
        return dto;
    }
}

