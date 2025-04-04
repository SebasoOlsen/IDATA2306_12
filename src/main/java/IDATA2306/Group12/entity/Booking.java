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
    @Column(name = "bID")
    private int id;;

    /**
     * The id of the user who made the booking.
     */
    @JsonProperty("uID")
    @Column(name = "uID")
    private int uID;

    /**
     * The listing that is being booked.
     */
    @JsonProperty("listID")
    @Column(name="listID")
    private int listID;

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

    public int getUID() {return uID;}
    public void setUID(int uID) {this.uID = uID;}

    public int getLID() {return listID;}
    public void setLID(int listID) {this.listID = listID;}

    public String getStatus() {return status;}
    public void setStatus(String status) {this.status = status;}

    public LocalDate getStartDate() {return startDate;}
    public void setStartDate(LocalDate startDate) {this.startDate = startDate;}

    public LocalDate getEndDate() {return endDate;}
    public void setEndDate(LocalDate endDate) {this.endDate = endDate;}
}
