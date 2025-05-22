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
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * REST controller for managing bookings.
 * Provides endpoints for creating, retrieving, updating, and deleting bookings.
 */
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

    /**
     * Constructs a new BookingController.
     *
     * @param bookingService the booking service
     * @param userRepository the user repository
     */

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

    /**
     * Retrieves a list of all bookings.
     *
     * @return a ResponseEntity containing the list of all bookings
     */

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

    /**
     * Creates a new booking.
     *
     * @param bookingCreateDTO the booking creation data
     * @return a ResponseEntity containing the created booking, or error status
     */

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
    /**
     * Updates an existing booking.
     *
     * @param id the booking ID
     * @param bookingCreateDTO the updated booking data
     * @return a ResponseEntity containing the updated booking, or 404 if not found
     */
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
    /**
     * Deletes a booking by its ID.
     *
     * @param id the booking ID
     * @return a ResponseEntity with status 204 if deleted
     */
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
    /**
     * Retrieves bookings for the authenticated user.
     *
     * @return a ResponseEntity containing the user's bookings, or error status
     */
    @GetMapping("/account/user")
    public ResponseEntity<?> getUserBookings() {

        //TODO: Move this logic to service. Throw IllegalArgumentExceptions if user is not auth or not found.

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || !auth.isAuthenticated()) {
            return ResponseEntity.status(401).build();
        }
        // Assuming the principal contains the user's email or username
        String email = auth.getName();
        System.out.println("Email: " + email);
        User user = userRepository.findByEmail(email);
        if (user == null) {
            System.out.println("User not found");
            return ResponseEntity.status(404).build();
        }

        return ResponseEntity.ok(bookingService.getBookingsByUser(user));
    }

    @Operation(
            summary = "Get a list of booked dates for a listing",
            description = "Get a list of booked dates for a listing."
    )
    @ApiResponse(responseCode = "200", description = "List of booked dates for the listing.")
    @ApiResponse(responseCode = "404", description = "Listing not found.")
    /**
     * Retrieves booked dates for a specific listing.
     *
     * @param id the listing ID
     * @return a ResponseEntity containing the list of booked dates, or 404 if not found
     */
    @GetMapping("/public/bookedDatesForListing/{id}")
    public ResponseEntity<?> getBookedDatesForListing(@PathVariable long id) {
        try {
            return ResponseEntity.ok(bookingService.getBookedDatesByListingId(id));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
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
