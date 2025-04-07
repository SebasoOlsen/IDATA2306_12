package IDATA2306.Group12.service;

import IDATA2306.Group12.dto.BookingDTO;
import IDATA2306.Group12.entity.Booking;
import IDATA2306.Group12.mapper.BookingMapper;
import IDATA2306.Group12.repository.BookingRepository;
import IDATA2306.Group12.repository.ListingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private ListingRepository listingRepository;

    public List<BookingDTO> getAllBookings() {
        return bookingRepository.findAll().stream()
                .map(booking -> BookingMapper.toDTO(booking)).toList();
    }
    public BookingDTO getBookingById(Long id) {
        return BookingMapper.toDTO(bookingRepository.findById(id.intValue()).orElse(null));
    }
    public BookingDTO getBookingByUserId(Long userId) {
        return BookingMapper.toDTO(bookingRepository.findByuID(userId.intValue()));
    }
    public BookingDTO createBooking(BookingDTO bookingDTO) {
        if(!listingRepository.existsById(bookingDTO.getListingId())){
            throw new IllegalArgumentException("Listing not found");
        }
        Booking booking = new Booking();
        booking.setLID(bookingDTO.getListingId());
        booking.setStatus(bookingDTO.getStatus());
        booking.setEndDate(bookingDTO.getEndDate());
        booking.setUID(bookingDTO.getUserId());
        bookingRepository.save(booking);
        return BookingMapper.toDTO(booking);
    }
    @Transactional
    public BookingDTO updateBooking(Long id, BookingDTO updatedBookingDTO) {
        Booking updatedBooking = BookingMapper.toEntity(updatedBookingDTO);
        Booking existingBooking = bookingRepository.findById(id.intValue())
                .orElseThrow(() -> new RuntimeException("Booking not found"));

        // Update fields except the identifier.
        existingBooking.setUID(updatedBooking.getUID());
        // Add additional field updates as appropriate (e.g. bookingDate, status, etc.)
        existingBooking.setStartDate(updatedBooking.getStartDate());
        existingBooking.setStatus(updatedBooking.getStatus());
        bookingRepository.save(existingBooking);
        return BookingMapper.toDTO(existingBooking);
    }
    @Transactional
    public void deleteBooking(Long id){
        bookingRepository.deleteById(id.intValue());
    }
}
