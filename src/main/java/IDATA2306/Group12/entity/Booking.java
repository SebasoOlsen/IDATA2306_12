package IDATA2306.Group12.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
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
@Table(name = "Bookings")
public class Booking {

    /**
     * The unique identifier for the booking.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("id")
    private int id;

    /**
     * The id of the user who made the booking.
     */
    @JsonProperty("uID")
    private int uID;

    /**
     * The listing that is being booked.
     */
    @JsonProperty("listID")
    private int listID;

    /**
     * The status of the booking.
     */
    @JsonProperty("status")
    private String status;

    /**
     * The start date of the booking.
     */
    @JsonProperty("startDate")
    private Date startDate;

    /**
     * The end date of the booking.
     */
    @JsonProperty("endDate")
    private Date endDate;

    /**
     * Create a new booking using parameters.
     * @param id id of the booking
     * @param uID id of the user this booking belongs to.
     * @param status status of the booking.
     * @param startDate the first date of the booking
     * @param endDate the last day of the booking
     */
    public Booking(int id, int uID, String status, Date startDate, Date endDate) {
        this.id = id;
        this.uID = uID;
        this.status = status;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Booking() {}
}
