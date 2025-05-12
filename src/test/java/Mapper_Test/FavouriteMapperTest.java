// package IDATA2306.Group12.mapper;
//
// import IDATA2306.Group12.dto.favourite.FavouriteCreateDTO;
// import IDATA2306.Group12.dto.favourite.FavouriteResponseDTO;
// import IDATA2306.Group12.dto.listing.ListingResponseDTO;
// import IDATA2306.Group12.dto.user.UserResponseDTO;
// import IDATA2306.Group12.entity.Favourite;
// import IDATA2306.Group12.entity.Listing;
// import IDATA2306.Group12.entity.User;
// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import static org.junit.jupiter.api.Assertions.*;
// import static org.mockito.Mockito.*;
//
// class FavouriteMapperTest {
//
// private FavouriteMapper favouriteMapper;
// private UserMapper userMapper;
// private ListingMapper listingMapper;
//
// @BeforeEach
// void setup() {
// userMapper = mock(UserMapper.class);
// listingMapper = mock(ListingMapper.class);
// favouriteMapper = new FavouriteMapper(userMapper, listingMapper);
// }
//
// @Test
// void toEntity_shouldMapCorrectly() {
// FavouriteCreateDTO dto = new FavouriteCreateDTO();
// User user = new User();
// Listing listing = new Listing();
//
// Favourite favourite = favouriteMapper.toEntity(dto, user, listing);
// assertEquals(user, favourite.getUser());
// assertEquals(listing, favourite.getListing());
// }
//
// @Test
// void toResponseDTO_shouldMapCorrectly() {
// Favourite favourite = new Favourite(new User(), new Listing());
//
// when(userMapper.toResponseDTO(any())).thenReturn(new UserResponseDTO());
// when(listingMapper.toResponseDTO(any())).thenReturn(new
// ListingResponseDTO());
//
// FavouriteResponseDTO response = favouriteMapper.toResponseDTO(favourite);
// assertNotNull(response.getUser());
// assertNotNull(response.getListing());
// }
// }