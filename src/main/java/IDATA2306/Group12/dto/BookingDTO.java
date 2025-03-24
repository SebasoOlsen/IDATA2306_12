package IDATA2306.Group12.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

/**
 * Data Transfer Object (DTO) for Booking entity.
 */
public class BookingDTO {

    private int id;

    @NotNull(message = "User ID cannot be null")
    private int userId;

    @NotNull(message = "Listing ID cannot be null")
    private int listingId;

    @NotBlank(message = "Booking status cannot be empty")
    private String status;

    @NotNull(message = "Start date cannot be null")
    private LocalDate startDate;

    @NotNull(message = "End date cannot be null")
    private LocalDate endDate;

    public BookingDTO() {}

    public BookingDTO(int id, int userId, int listingId, String status, LocalDate startDate, LocalDate endDate) {
        this.id = id;
        this.userId = userId;
        this.listingId = listingId;
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

    public LocalDate getStartDate() { return startDate; }
    public void setStartDate(LocalDate startDate) { this.startDate = startDate; }

    public LocalDate getEndDate() { return endDate; }
    public void setEndDate(LocalDate endDate) { this.endDate = endDate; }
}
