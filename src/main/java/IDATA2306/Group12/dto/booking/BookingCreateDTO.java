package IDATA2306.Group12.dto.booking;

import java.time.LocalDate;
/**
 * DTO for creating a booking.
 */
public class BookingCreateDTO {
    private int userId;
    private int listingId;
    private LocalDate startDate;
    private LocalDate endDate;
    /** @return user ID */
    public int getUserId() { return userId; }
    /** @param userId user ID to set */
    public void setUserId(int userId) { this.userId = userId; }

    /** @return listing ID */
    public int getListingId() { return listingId; }
    /** @param listingId listing ID to set */
    public void setListingId(int listingId) { this.listingId = listingId; }

    /** @return start date */
    public LocalDate getStartDate() { return startDate; }
    /** @param startDate start date to set */
    public void setStartDate(LocalDate startDate) { this.startDate = startDate; }

    /** @return end date */
    public LocalDate getEndDate() { return endDate; }
    /** @param endDate end date to set */
    public void setEndDate(LocalDate endDate) { this.endDate = endDate; }
}
