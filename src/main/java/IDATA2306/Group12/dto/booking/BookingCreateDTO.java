package IDATA2306.Group12.dto.booking;

import java.time.LocalDate;

public class BookingCreateDTO {
    private int userId;
    private int listingId;
    private LocalDate startDate;
    private LocalDate endDate;

    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }

    public int getListingId() { return listingId; }
    public void setListingId(int listingId) { this.listingId = listingId; }

    public LocalDate getStartDate() { return startDate; }
    public void setStartDate(LocalDate startDate) { this.startDate = startDate; }

    public LocalDate getEndDate() { return endDate; }
    public void setEndDate(LocalDate endDate) { this.endDate = endDate; }
}
