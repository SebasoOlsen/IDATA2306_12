package IDATA2306.Group12.controller;

import IDATA2306.Group12.dto.booking.BookingCreateDTO;
import IDATA2306.Group12.dto.booking.BookingResponseDTO;
import IDATA2306.Group12.entity.User;
import IDATA2306.Group12.repository.BookingRepository;
import IDATA2306.Group12.repository.UserRepository;
import IDATA2306.Group12.service.BookingService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/bookings")
public class BookingController {

    @Autowired
    private final BookingService bookingService;

    private final BookingRepository bookingRepository;
    @Autowired
    private UserRepository userRepository;


    @Autowired
    public BookingController(BookingService bookingService, BookingRepository bookingRepository){
        this.bookingService = bookingService;
        this.bookingRepository = bookingRepository;
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
    @GetMapping("/user")
    public ResponseEntity<?> getUserBookings() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || !auth.isAuthenticated()) {
            return ResponseEntity.status(401).build();
        }
        // Assuming the principal contains the user's email or username
        String username = auth.getName();
        System.out.println("Username: " + username);
        User user = userRepository.findByEmail(username);
        if (user == null) {
            System.out.println("User not found");
            return ResponseEntity.status(404).build();
        }

        return ResponseEntity.ok(bookingService.getBookingsByUser(user));
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
