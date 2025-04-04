package IDATA2306.Group12.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.Date;

/**
 * Data Transfer Object (DTO) for Booking entity.
 */
public class BookingDTO {

    private int id;

    @NotNull(message = "User ID cannot be null")
    private int userId;

    @NotNull(message = "Listings ID cannot be null")
    private int listingId;

    @NotBlank(message = "Booking status cannot be empty")
    private String status;

    @NotNull(message = "Start date cannot be null")
    private Date startDate;

    @NotNull(message = "End date cannot be null")
    private Date endDate;

    public BookingDTO() {}

    public BookingDTO(int id, int userId, String status, Date startDate, Date endDate) {
        this.id = id;
        this.userId = userId;
        this.status = status;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }

    public int getListingId() { return listingId; }
    public void setListingId(int listingId) { this.listingId = listingId; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public Date getStartDate() { return startDate; }
    public void setStartDate(Date startDate) { this.startDate = startDate; }

    public Date getEndDate() { return endDate; }
    public void setEndDate(Date endDate) { this.endDate = endDate; }
}
