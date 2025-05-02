package IDATA2306.Group12.service;

import IDATA2306.Group12.dto.booking.BookingCreateDTO;
import IDATA2306.Group12.dto.booking.BookingResponseDTO;
import IDATA2306.Group12.entity.Booking;
import IDATA2306.Group12.entity.Listing;
import IDATA2306.Group12.entity.User;
import IDATA2306.Group12.mapper.BookingMapper;
import IDATA2306.Group12.repository.BookingRepository;
import IDATA2306.Group12.repository.ListingRepository;
import IDATA2306.Group12.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookingService {

    private final BookingRepository bookingRepository;
    private final ListingRepository listingRepository;
    private final UserRepository userRepository;
    private final BookingMapper bookingMapper;

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

    public List<BookingResponseDTO> getAllBookings() {
        return bookingRepository.findAll()
                .stream()
                .map(this.bookingMapper::toResponseDTO)
                .toList();
    }

    public BookingResponseDTO getBookingById(Long id) {
        Booking booking = bookingRepository.findById(id.intValue())
                .orElseThrow(() -> new RuntimeException("Booking not found"));
        return bookingMapper.toResponseDTO(booking);
    }

    public BookingResponseDTO createBooking(BookingCreateDTO dto) {
        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Listing listing = listingRepository.findById(dto.getListingId())
                .orElseThrow(() -> new RuntimeException("Listing not found"));

        Booking booking = bookingMapper.toEntity(dto, user, listing);
        Booking saved = bookingRepository.save(booking);
        return bookingMapper.toResponseDTO(saved);
    }

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

    @Transactional
    public void deleteBooking(Long id) {
        bookingRepository.deleteById(id.intValue());
    }

    private BookingResponseDTO convertToResponseDTO(Booking booking) {
        BookingResponseDTO dto = new BookingResponseDTO();
        // Map fields from booking to dto
        dto.setId(booking.getId());
        dto.setStartDate(booking.getStartDate());
        dto.setEndDate(booking.getEndDate());
        dto.setStatus(booking.getStatus());
        return dto;
    }

    public List<BookingResponseDTO> getBookingsByUser(User user) {
        System.out.println("User ID: " + user.getId());
        System.out.println("User Email: " + user.getEmail());

        List<Booking> bookings = bookingRepository.findByUserId(user.getId());
        return bookings.stream()
                .map(bookingMapper::toResponseDTO)
                .collect(Collectors.toList());
    }
}
