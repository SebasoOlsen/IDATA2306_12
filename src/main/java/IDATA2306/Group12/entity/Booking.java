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

    public Booking() {}

    public int getId() {return id;}
    public void setId(int id) {this.id = id;}

    public int getUID() {return uID;}
    public void setUID(int uID) {this.uID = uID;}

    public int getLID() {return listID;}
    public void setLID(int listID) {this.listID = listID;}

    public String getStatus() {return status;}
    public void setStatus(String status) {this.status = status;}

    public Date getStartDate() {return startDate;}
    public void setStartDate(Date startDate) {this.startDate = startDate;}

    public Date getEndDate() {return endDate;}
    public void setEndDate(Date endDate) {this.endDate = endDate;}
}
