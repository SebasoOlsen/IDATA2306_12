package IDATA2306.Group12.mapper;

import org.springframework.stereotype.Component;

import IDATA2306.Group12.dto.booking.BookingCreateDTO;
import IDATA2306.Group12.dto.booking.BookingResponseDTO;
import IDATA2306.Group12.entity.Booking;
import IDATA2306.Group12.entity.Listing;
import IDATA2306.Group12.entity.User;

/**
 * Mapper class for converting between Booking entity and BookingDTO.
 */
@Component
public class BookingMapper {

    private final UserMapper userMapper;
    private final ListingMapper listingMapper;

    public BookingMapper(UserMapper userMapper, ListingMapper listingMapper){
        this.userMapper = userMapper;
        this.listingMapper = listingMapper;
    }

    public Booking toEntity(BookingCreateDTO dto, User user, Listing listing) {
        Booking booking = new Booking();
        booking.setUser(user);
        booking.setListing(listing);
        booking.setStartDate(dto.getStartDate());
        booking.setEndDate(dto.getEndDate());
        booking.setStatus("PENDING");
        return booking;
    }

    public BookingResponseDTO toResponseDTO(Booking booking) {
        BookingResponseDTO dto = new BookingResponseDTO();
        dto.setId(booking.getId());
        dto.setStatus(booking.getStatus());
        dto.setStartDate(booking.getStartDate());
        dto.setEndDate(booking.getEndDate());
        dto.setUser(userMapper.toResponseDTO(booking.getUser()));
        dto.setListing(listingMapper.toResponseDTO(booking.getListing()));
        return dto;
    }
}
