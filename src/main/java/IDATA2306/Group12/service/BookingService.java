package IDATA2306.Group12.service;

import IDATA2306.Group12.dto.booking.BookedDatesDTO;
import IDATA2306.Group12.dto.booking.BookingCreateDTO;
import IDATA2306.Group12.dto.booking.BookingResponseDTO;
import IDATA2306.Group12.entity.Booking;
import IDATA2306.Group12.entity.Listing;
import IDATA2306.Group12.entity.User;
import IDATA2306.Group12.mapper.BookingMapper;
import IDATA2306.Group12.repository.BookingRepository;
import IDATA2306.Group12.repository.ListingRepository;
import IDATA2306.Group12.repository.UserRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Service class for managing bookings.
 * Handles creation, retrieval, updating, and deletion of bookings,
 * as well as fetching bookings by user and booked dates by listing.
 */
@Service
public class BookingService {

    private static final Logger log = LoggerFactory.getLogger(BookingService.class);

    private final BookingRepository bookingRepository;
    private final ListingRepository listingRepository;
    private final UserRepository userRepository;
    private final BookingMapper bookingMapper;

    /**
     * Constructs a BookingService with required dependencies.
     *
     * @param bookingRepository the booking repository
     * @param listingRepository the listing repository
     * @param userRepository the user repository
     * @param bookingMapper the booking mapper
     */
    public BookingService(
        BookingRepository bookingRepository,
        ListingRepository listingRepository,
        UserRepository userRepository,
        BookingMapper bookingMapper) {
        this.bookingRepository = bookingRepository;
        this.listingRepository = listingRepository;
        this.userRepository = userRepository;
        this.bookingMapper = bookingMapper;
    }

    /**
     * Retrieves all bookings.
     *
     * @return a list of BookingResponseDTOs
     */
    public List<BookingResponseDTO> getAllBookings() {
        return bookingRepository.findAll()
                .stream()
                .map(this.bookingMapper::toResponseDTO)
                .toList();
    }

    /**
     * Retrieves a booking by its ID.
     *
     * @param id the booking ID
     * @return the BookingResponseDTO
     * @throws RuntimeException if the booking is not found
     */
    public BookingResponseDTO getBookingById(Long id) throws RuntimeException {
        Booking booking = bookingRepository.findById(id.intValue())
                .orElseThrow(() -> new RuntimeException("Booking not found"));
        return bookingMapper.toResponseDTO(booking);
    }

    /**
     * Creates a new booking.
     *
     * @param dto the booking creation DTO
     * @return the created BookingResponseDTO
     * @throws RuntimeException if the user or listing is not found
     */
    public BookingResponseDTO createBooking(BookingCreateDTO dto) throws RuntimeException {
        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Listing listing = listingRepository.findById(dto.getListingId())
                .orElseThrow(() -> new RuntimeException("Listing not found"));

        Booking booking = bookingMapper.toEntity(dto, user, listing);
        Booking saved = bookingRepository.save(booking);
        return bookingMapper.toResponseDTO(saved);
    }
    /**
     * Updates an existing booking.
     *
     * @param id the booking ID
     * @param dto the booking creation DTO with updated data
     * @return the updated BookingResponseDTO
     * @throws RuntimeException if the booking, user, or listing is not found
     */
    @Transactional
    public BookingResponseDTO updateBooking(Long id, BookingCreateDTO dto) {
        Booking existingBooking = bookingRepository.findById(id.intValue())
                .orElseThrow(() -> new RuntimeException("Booking not found"));

        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Listing listing = listingRepository.findById(dto.getListingId())
                .orElseThrow(() -> new RuntimeException("Listing not found"));

        // Update fields
        existingBooking.setUser(user);
        existingBooking.setListing(listing);
        existingBooking.setStartDate(dto.getStartDate());
        existingBooking.setEndDate(dto.getEndDate());

        return bookingMapper.toResponseDTO(existingBooking);
    }

    /**
     * Deletes a booking by its ID.
     *
     * @param id the booking ID
     */
    @Transactional
    public void deleteBooking(Long id) {
        bookingRepository.deleteById(id.intValue());
    }

    private BookingResponseDTO convertToResponseDTO(Booking booking) {
        BookingResponseDTO dto = new BookingResponseDTO();
        dto.setId(booking.getId());
        dto.setStartDate(booking.getStartDate());
        dto.setEndDate(booking.getEndDate());
        dto.setStatus(booking.getStatus());
        return dto;
    }

    /**
     * Converts a Booking entity to a BookingResponseDTO.
     *
     * @param booking the Booking entity
     * @return the BookingResponseDTO
     */
    public List<BookingResponseDTO> getBookingsByUser(User user) {
        log.info("Fetching bookings for user: {} (id: {})", user.getEmail(), user.getId());
        List<Booking> bookings = bookingRepository.findByUserId(user.getId());
        log.info("Found {} bookings for user id: {}", bookings.size(), user.getId());
        return bookings.stream()
                .map(bookingMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    /**
     * Retrieves all bookings for a the select user,
     *
     * @param user the user
     * @return a list of BookingResponseDTOs for the user
     */

    public List<BookedDatesDTO> getBookedDatesByListingId(long listingId) {
        List<Booking> bookings = bookingRepository.findByListingId(listingId);
        return bookings.stream()
                .map(bookingMapper::toBookedDatesDTO)
                .toList();
    }
}
