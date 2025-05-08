// package IDATA2306.Group12.mapper;
//
// import IDATA2306.Group12.dto.booking.BookingCreateDTO;
// import IDATA2306.Group12.dto.booking.BookingResponseDTO;
// import IDATA2306.Group12.dto.listing.ListingResponseDTO;
// import IDATA2306.Group12.dto.user.UserResponseDTO;
// import IDATA2306.Group12.entity.Booking;
// import IDATA2306.Group12.entity.Listing;
// import IDATA2306.Group12.entity.User;
// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import java.time.LocalDate;
// import static org.junit.jupiter.api.Assertions.*;
// import static org.mockito.Mockito.*;
//
// class BookingMapperTest {
//
// private BookingMapper bookingMapper;
// private UserMapper userMapper;
// private ListingMapper listingMapper;
//
// @BeforeEach
// void setup() {
// userMapper = mock(UserMapper.class);
// listingMapper = mock(ListingMapper.class);
// bookingMapper = new BookingMapper(userMapper, listingMapper);
// }
//
// @Test
// void toEntity_shouldMapCorrectly() {
// BookingCreateDTO dto = new BookingCreateDTO();
// dto.setUserId(1);
// dto.setListingId(2);
// dto.setStartDate(LocalDate.now());
// dto.setEndDate(LocalDate.now().plusDays(2));
//
// User user = new User();
// Listing listing = new Listing();
//
// Booking booking = bookingMapper.toEntity(dto, user, listing);
//
// assertEquals(user, booking.getUser());
// assertEquals(listing, booking.getListing());
// assertEquals("PENDING", booking.getStatus());
// }
//
// @Test
// void toResponseDTO_shouldMapCorrectly() {
// Booking booking = new Booking();
// booking.setId(1);
// booking.setStatus("CONFIRMED");
// booking.setStartDate(LocalDate.now());
// booking.setEndDate(LocalDate.now().plusDays(3));
// booking.setUser(new User());
// booking.setListing(new Listing());
//
// when(userMapper.toResponseDTO(any())).thenReturn(new UserResponseDTO());
// when(listingMapper.toResponseDTO(any())).thenReturn(new
// ListingResponseDTO());
//
// BookingResponseDTO dto = bookingMapper.toResponseDTO(booking);
// assertNotNull(dto);
// assertEquals("CONFIRMED", dto.getStatus());
// }
// }