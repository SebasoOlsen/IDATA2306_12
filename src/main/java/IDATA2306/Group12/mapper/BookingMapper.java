package IDATA2306.Group12.mapper;

import IDATA2306.Group12.dto.booking.BookedDatesDTO;
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

    /**
     * Constructs a BookingMapper with required dependencies.
     *
     * @param userMapper the UserMapper to use
     * @param listingMapper the ListingMapper to use
     */
    public BookingMapper(UserMapper userMapper, ListingMapper listingMapper){
        this.userMapper = userMapper;
        this.listingMapper = listingMapper;
    }
    /**
     * Converts a BookingCreateDTO, User and Listing to a booking entity
     *
     * @param dto the BookingCreateDTO containing booking details
     * @param user the User making the booking
     * @param listing the Listing being booked
     * @return the Booking entity
     */
    public Booking toEntity(BookingCreateDTO dto, User user, Listing listing) {
        Booking booking = new Booking();
        booking.setUser(user);
        booking.setListing(listing);
        booking.setStartDate(dto.getStartDate());
        booking.setEndDate(dto.getEndDate());
        booking.setStatus("PENDING");
        return booking;
    }

    /**
     * Converts a Booking entity to a BookingResponseDTO.
     *
     * @param booking the Booking entity to convert
     * @return the BookingResponseDTO
     */
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

    /**
     * Converts a Booking entity to a BookedDatesDTO.
     *
     * @param booking the Booking entity
     * @return the BookedDatesDTO containing start and end dates
     */
    public BookedDatesDTO toBookedDatesDTO(Booking booking) {
        BookedDatesDTO dto = new BookedDatesDTO();
        dto.setStartDate(booking.getStartDate());
        dto.setEndDate(booking.getEndDate());
        return dto;
    }
}
