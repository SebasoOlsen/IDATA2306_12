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
    @Column(name = "bookingId")
    private int id;;

    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    @JsonProperty("user")
    private User user;

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

    public Booking() {}

    @PrePersist
    protected void onCreate(){
        this.startDate = LocalDate.now(); ;
    }

    public int getId() {return id;}
    public void setId(int id) {this.id = id;}

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public Listing getListing() { return listing; }
    public void setListing(Listing listing) { this.listing = listing; }

    public String getStatus() {return status;}
    public void setStatus(String status) {this.status = status;}

    public LocalDate getStartDate() {return startDate;}
    public void setStartDate(LocalDate startDate) {this.startDate = startDate;}

    public LocalDate getEndDate() {return endDate;}
    public void setEndDate(LocalDate endDate) {this.endDate = endDate;}





    
}
