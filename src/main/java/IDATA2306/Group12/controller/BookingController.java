package IDATA2306.Group12.controller;

import IDATA2306.Group12.dto.BookingDTO;
import IDATA2306.Group12.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bookings")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @GetMapping
    public List<BookingDTO> getAllbookings(){
        try{
            return bookingService.getAllBookings();
        }catch(Exception e){
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }
    @GetMapping("/{id}")
    public BookingDTO getBookingById(@PathVariable Long id){
        return bookingService.getBookingById(id);
    }
    @PostMapping
    public BookingDTO createBooking(@RequestBody BookingDTO bookingDTO){
        return bookingService.createBooking(bookingDTO);
    }
    @PutMapping("/{id}")
    public BookingDTO updateBooking(@PathVariable Long id,@RequestBody BookingDTO bookingDTO){
        return bookingService.updateBooking(id, bookingDTO);
    }
    @DeleteMapping("/{id}")
    public void deleteBooking(@PathVariable Long id){
        bookingService.deleteBooking(id);
    }
}
