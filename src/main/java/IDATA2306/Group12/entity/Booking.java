package IDATA2306.Group12.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.time.LocalDate;
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
    private int id;;
    /**
     * The user who made the booking.
     */
    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    @JsonProperty("user")
    private User user;
    /**
     * The listing that is booked.
     */
    @ManyToOne
    @JoinColumn(name = "listingId", nullable = false)
    @JsonProperty("listing")
    private Listing listing;


    /**
     * The status of the booking.
     */
    @JsonProperty("status")
    @Column(name = "status")
    private String status;

    /**
     * The start date of the booking.
     */
    @JsonProperty("startDate")
    @Column(name = "startDate")
    private LocalDate startDate;

    /**
     * The end date of the booking.
     */
    @JsonProperty("endDate")
    @Column(name = "endDate")
    private LocalDate endDate;
    /**
     * Default constructor.
     */
    public Booking() {}
    /**
     * Sets the start date to the current date before persisting.
     */
    @PrePersist
    protected void onCreate(){
        this.startDate = LocalDate.now(); ;
    }
    /**
     * Returns a string representation of the booking.
     * @return string representation
     */
    @Override
    public String toString() {
        return "Booking{" +
                "id=" + id +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", status='" + status + '\'' +
                ", user=" + user +
                ", listing=" + listing +
                '}';
    }
    /**
     * Gets the booking ID.
     * @return the booking ID
     */
    public int getId() {return id;}
    /**
     * Sets the booking ID.
     * @param id the booking ID
     */
    public void setId(int id) {this.id = id;}

    /**
     * Gets the user who made the booking.
     * @return the user
     */
    public User getUser() { return user; }
    /**
     * Sets the user who made the booking.
     * @param user the user
     */
    public void setUser(User user) { this.user = user; }

    /**
     * Gets the listing that is booked.
     * @return the listing
     */
    public Listing getListing() { return listing; }
    /**
     * Sets the listing that is booked.
     * @param listing the listing
     */
    public void setListing(Listing listing) { this.listing = listing; }

    /**
     * Gets the booking status.
     * @return the status
     */
    public String getStatus() {return status;}
    /**
     * Sets the booking status.
     * @param status the status
     */
    public void setStatus(String status) {this.status = status;}

    /**
     * Gets the start date of the booking.
     * @return the start date
     */
    public LocalDate getStartDate() {return startDate;}
    /**
     * Sets the start date of the booking.
     * @param startDate the start date
     */
    public void setStartDate(LocalDate startDate) {this.startDate = startDate;}

    /**
     * Gets the end date of the booking.
     * @return the end date
     */
    public LocalDate getEndDate() {return endDate;}
    /**
     * Sets the end date of the booking.
     * @param endDate the end date
     */
    public void setEndDate(LocalDate endDate) {this.endDate = endDate;}





    
}
