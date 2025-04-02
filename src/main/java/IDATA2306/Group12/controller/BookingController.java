package IDATA2306.Group12.controller;

import IDATA2306.Group12.entity.Booking;
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
    public List<Booking> getAllbookings(){
        try{
            return bookingService.getAllBookings();
        }catch(Exception e){
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }
    @GetMapping("/{id}")
    public Booking getBookingById(@PathVariable Long id){
        return bookingService.getBookingById(id);
    }
    @PostMapping("/{id}")
    public Booking createBooking(@RequestBody Booking booking){
        return bookingService.createBooking(booking);
    }
    @PutMapping("/{id}")
    public Booking updateBooking(@PathVariable Long id,@RequestBody Booking booking){
        return bookingService.updateBooking(id, booking);
    }
    @DeleteMapping("/{id}")
    public void deleteBooking(@PathVariable Long id){
        bookingService.deleteBooking(id);
    }
}
