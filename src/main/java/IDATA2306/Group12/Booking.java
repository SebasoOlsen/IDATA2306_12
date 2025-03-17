package IDATA2306.Group12;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.Date;

/**
 * Represents a booking made by a user for a listing.
 */
@Entity
@Table(name = "bookings")
public class Booking {

    /**
     * The unique identifier for the booking.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /**
     * The user who made the booking.
     */
    private User user;

    /**
     * The listing that is being booked.
     */
    private Listing listing;

    /**
     * The status of the booking.
     */
    private String status;

    /**
     * The start date of the booking.
     */
    private Date startDate;

    /**
     * The end date of the booking.
     */
    private Date endDate;

}
