package IDATA2306.Group12.api;

import IDATA2306.Group12.dto.booking.BookingCreateDTO;
import IDATA2306.Group12.dto.booking.BookingResponseDTO;
import IDATA2306.Group12.service.BookingService;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bookings")
public class BookingController {

    private final BookingService bookingService;

    @Autowired
    public BookingController(BookingService bookingService){
        this.bookingService = bookingService;
    }


    @GetMapping
    public ResponseEntity<List<BookingResponseDTO>> getAllBookings() {
        return ResponseEntity.ok(bookingService.getAllBookings());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookingResponseDTO> getBookingById(@PathVariable Long id) {
        return ResponseEntity.ok(bookingService.getBookingById(id));
    }

    @PostMapping
    public ResponseEntity<BookingResponseDTO> createBooking(@Valid @RequestBody BookingCreateDTO bookingCreateDTO) {
        BookingResponseDTO created = bookingService.createBooking(bookingCreateDTO);
        return ResponseEntity.status(201).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookingResponseDTO> updateBooking(@PathVariable Long id, 
                                                            @Valid @RequestBody BookingCreateDTO bookingCreateDTO) {
        BookingResponseDTO updated = bookingService.updateBooking(id, bookingCreateDTO);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBooking(@PathVariable Long id) {
        bookingService.deleteBooking(id);
        return ResponseEntity.noContent().build();
    }


    // OLD METHODS:
    //@GetMapping
    //public List<BookingDTO> getAllbookings(){
    //    try{
    //        return bookingService.getAllBookings();
    //    }catch(Exception e){
    //        System.out.println("Error: " + e.getMessage());
    //        return null;
    //    }
    //}
    //@GetMapping("/{id}")
    //public BookingDTO getBookingById(@PathVariable Long id){
    //    return bookingService.getBookingById(id);
    //}
    //@PostMapping
    //public BookingDTO createBooking(@RequestBody BookingDTO bookingDTO){
    //    return bookingService.createBooking(bookingDTO);
    //}
    //@PutMapping("/{id}")
    //public BookingDTO updateBooking(@PathVariable Long id,@RequestBody BookingDTO bookingDTO){
    //    return bookingService.updateBooking(id, bookingDTO);
    //}
    //@DeleteMapping("/{id}")
    //public void deleteBooking(@PathVariable Long id){
    //    bookingService.deleteBooking(id);
    //}
}
