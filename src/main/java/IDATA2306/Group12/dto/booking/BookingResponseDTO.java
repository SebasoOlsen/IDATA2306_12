package IDATA2306.Group12.dto.booking;

import java.time.LocalDate;

import IDATA2306.Group12.dto.listing.ListingDTO;
import IDATA2306.Group12.dto.user.UserDTO;

public class BookingResponseDTO {
    private int id;
    private String status;
    private LocalDate startDate;
    private LocalDate endDate;
    private UserDTO user;
    private ListingDTO listing;

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public LocalDate getStartDate() { return startDate; }
    public void setStartDate(LocalDate startDate) { this.startDate = startDate; }

    public LocalDate getEndDate() { return endDate; }
    public void setEndDate(LocalDate endDate) { this.endDate = endDate; }

    public UserDTO getUser() { return user; }
    public void setUser(UserDTO user) { this.user = user; }

    public ListingDTO getListing() { return listing; }
    public void setListing(ListingDTO listing) { this.listing = listing; }
}
