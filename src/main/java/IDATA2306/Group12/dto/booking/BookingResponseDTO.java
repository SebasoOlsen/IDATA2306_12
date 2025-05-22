package IDATA2306.Group12.dto.booking;

import java.time.LocalDate;

import IDATA2306.Group12.dto.listing.ListingResponseDTO;
import IDATA2306.Group12.dto.user.UserResponseDTO;
/**
 * DTO for booking response data.
 */
public class BookingResponseDTO {
    private int id;
    private String status;
    private LocalDate startDate;
    private LocalDate endDate;
    private UserResponseDTO user;
    private ListingResponseDTO listing;

    /** @return booking ID */
    public int getId() { return id; }
    /** @param id booking ID to set */
    public void setId(int id) { this.id = id; }

    /** @return booking status */
    public String getStatus() { return status; }
    /** @param status booking status to set */
    public void setStatus(String status) { this.status = status; }

    /** @return start date */
    public LocalDate getStartDate() { return startDate; }
    /** @param startDate start date to set */
    public void setStartDate(LocalDate startDate) { this.startDate = startDate; }

    /** @return end date */
    public LocalDate getEndDate() { return endDate; }
    /** @param endDate end date to set */
    public void setEndDate(LocalDate endDate) { this.endDate = endDate; }

    /** @return user info */
    public UserResponseDTO getUser() { return user; }
    /** @param user user info to set */
    public void setUser(UserResponseDTO user) { this.user = user; }

    /** @return listing info */
    public ListingResponseDTO getListing() { return listing; }
    /** @param listing listing info to set */
    public void setListing(ListingResponseDTO listing) { this.listing = listing; }
}
