package IDATA2306.Group12.dto.booking;

import java.time.LocalDate;

import IDATA2306.Group12.dto.listing.ListingResponseDTO;
import IDATA2306.Group12.dto.user.UserResponseDTO;

public class BookingResponseDTO {
    private int id;
    private String status;
    private LocalDate startDate;
    private LocalDate endDate;
    private UserResponseDTO user;
    private ListingResponseDTO listing;

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public LocalDate getStartDate() { return startDate; }
    public void setStartDate(LocalDate startDate) { this.startDate = startDate; }

    public LocalDate getEndDate() { return endDate; }
    public void setEndDate(LocalDate endDate) { this.endDate = endDate; }

    public UserResponseDTO getUser() { return user; }
    public void setUser(UserResponseDTO user) { this.user = user; }

    public ListingResponseDTO getListing() { return listing; }
    public void setListing(ListingResponseDTO listing) { this.listing = listing; }
}
