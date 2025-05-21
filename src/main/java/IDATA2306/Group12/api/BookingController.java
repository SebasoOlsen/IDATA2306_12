package IDATA2306.Group12.api;

import IDATA2306.Group12.dto.booking.BookingCreateDTO;
import IDATA2306.Group12.dto.booking.BookingResponseDTO;
import IDATA2306.Group12.entity.User;
import IDATA2306.Group12.repository.UserRepository;
import IDATA2306.Group12.service.BookingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/bookings")
@Tag(name = "Booking Management", description = "APIs for managing bookings.")
public class BookingController {

    private final BookingService bookingService;
    private final UserRepository userRepository;

    public BookingController(BookingService bookingService, UserRepository userRepository){
        this.userRepository = userRepository;
        this.bookingService = bookingService;
    }


    @Operation(
            summary = "Get a list of all bookings",
            description = "Get a list of all bookings."
            )
    @ApiResponse(responseCode = "200", description = "List of all bookings.")
    @GetMapping("/admin/allBookings")
    public ResponseEntity<List<BookingResponseDTO>> getAllBookings() {
        return ResponseEntity.ok(bookingService.getAllBookings());
    }

    @Operation(
            summary = "Search for a booking using ID",
            description = "Search for a booking using ID"
    )
    @ApiResponse(responseCode = "200", description = "Booking with the matching ID.")
    @ApiResponse(responseCode = "404", description = "Booking not found.")
    @GetMapping("/admin/search/{id}")
    public ResponseEntity<BookingResponseDTO> getBookingById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(bookingService.getBookingById(id));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @Operation(
            summary = "Create a new booking",
            description = "Create a new booking."
    )
    @ApiResponse(responseCode = "201", description = "Booking created successfully.")
    @ApiResponse(responseCode = "404", description = "Listing not found.")
    @ApiResponse(responseCode = "500", description = "Internal server error.")
    @PostMapping("/account/createBooking")
    public ResponseEntity<BookingResponseDTO> createBooking(@Valid @RequestBody BookingCreateDTO bookingCreateDTO) {
        try {
            BookingResponseDTO created = bookingService.createBooking(bookingCreateDTO);
            return ResponseEntity.status(201).body(created);
        } catch (RuntimeException e) {
            String message = e.getMessage();
            if (message.contains("not found")) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
        }
    }


    //TODO: Can users update their own bookings? If so: implement a new controller with auth check to filter editable bookings.
    @Operation(
            summary = "Update an existing booking",
            description = "Update an existing booking."
    )
    @ApiResponse(responseCode = "200", description = "Booking updated successfully.")
    @ApiResponse(responseCode = "404", description = "Booking not found.")
    @ApiResponse(responseCode = "400", description = "Invalid input.")
    @PutMapping("/admin/editBooking/{id}")
    public ResponseEntity<BookingResponseDTO> updateBooking(@PathVariable Long id, 
                                                            @Valid @RequestBody BookingCreateDTO bookingCreateDTO) {
        BookingResponseDTO updated = bookingService.updateBooking(id, bookingCreateDTO);
        if (updated == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updated);
    }

    @Operation(
            summary = "Delete an existing booking",
            description = "Delete an existing booking."
    )
    @ApiResponse(responseCode = "204", description = "Booking deleted successfully.")
    @ApiResponse(responseCode = "403", description = "Not authorized to delete this booking.")
    @DeleteMapping("/admin/deleteBooking/{id}")
    public ResponseEntity<String> deleteBooking(@PathVariable Long id) {
        bookingService.deleteBooking(id);
        return ResponseEntity.status(204).body("Booking deleted successfully.");
    }

    @Operation(
            summary = "Get a list of bookings for a user",
            description = "Get a list of bookings for a user."
            )
    @ApiResponse(responseCode = "200", description = "List of bookings for the user.")
    @ApiResponse(responseCode = "404", description = "User not found.")
    @ApiResponse(responseCode = "401", description = "User is not authenticated.")
    @ApiResponse(responseCode = "403", description = "User is not authorized to view this list of bookings.")
    @GetMapping("/account/user")
    public ResponseEntity<?> getUserBookings() {

        //TODO: Move this logic to service. Throw IllegalArgumentExceptions if user is not auth or not found.

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
