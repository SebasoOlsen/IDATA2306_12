package IDATA2306.Group12.mapper;

import IDATA2306.Group12.entity.Booking;
import IDATA2306.Group12.dto.BookingDTO;

/**
 * Mapper class for converting between Booking entity and BookingDTO.
 */
public class BookingMapper {

    /**
     * Converts a Booking entity to a BookingDTO.
     *
     * @param booking the Booking entity to convert
     * @return the corresponding BookingDTO
     */
    public static BookingDTO toDTO(Booking booking) {
        if (booking == null) {
            throw new IllegalArgumentException("Booking cannot be null");
        }
        return new BookingDTO(
            booking.getId(),
            booking.getUID(),
            booking.getStatus(),
            booking.getStartDate(),
            booking.getEndDate()
        );
    }

    /**
     * Converts a BookingDTO to a Booking entity.
     *
     * @param bookingDTO the BookingDTO to convert
     * @return the corresponding Booking entity
     */
    public static Booking toEntity(BookingDTO bookingDTO) {
        if (bookingDTO == null) {
            throw new IllegalArgumentException("BookingDTO cannot be null");
        }
        Booking booking = new Booking();
        booking.setId(bookingDTO.getId());
        booking.setStatus(bookingDTO.getStatus());
        booking.setStartDate(bookingDTO.getStartDate());
        booking.setEndDate(bookingDTO.getEndDate());
        return booking;
    }
}