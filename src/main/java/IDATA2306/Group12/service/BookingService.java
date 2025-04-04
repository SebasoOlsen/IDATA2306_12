package IDATA2306.Group12.service;

import IDATA2306.Group12.dto.BookingDTO;
import IDATA2306.Group12.entity.Booking;
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

    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }
    public Booking getBookingById(Long id) {
        return bookingRepository.findById(id.intValue()).orElse(null);
    }
    public Booking getBookingByUserId(Long userId) {
        return bookingRepository.findByuID(userId.intValue());
    }
    public Booking createBooking(BookingDTO bookingdto) {
        if(!listingRepository.existsById(bookingdto.getListingId())){
            throw new IllegalArgumentException("Listing not found");
        }
        Booking booking = new Booking();
        booking.setLID(bookingdto.getListingId());
        booking.setStatus(bookingdto.getStatus());
        booking.setEndDate(bookingdto.getEndDate());
        booking.setUID(bookingdto.getUserId());
        return bookingRepository.save(booking);
    }
    @Transactional
    public Booking updateBooking(Long id, Booking updatedBooking) {
        Booking existingBooking = bookingRepository.findById(id.intValue())
                .orElseThrow(() -> new RuntimeException("Booking not found"));

        // Update fields except the identifier.
        existingBooking.setUID(updatedBooking.getUID());
        // Add additional field updates as appropriate (e.g. bookingDate, status, etc.)
        existingBooking.setStartDate(updatedBooking.getStartDate());
        existingBooking.setStatus(updatedBooking.getStatus());

        return bookingRepository.save(existingBooking);
    }
    @Transactional
    public void deleteBooking(Long id){
        bookingRepository.deleteById(id.intValue());
    }
}
