package IDATA2306.Group12.dto.favourite;

import IDATA2306.Group12.dto.listing.ListingResponseDTO;
import IDATA2306.Group12.dto.user.UserResponseDTO;

public class FavouriteResponseDTO {
    private UserResponseDTO user;
    private ListingResponseDTO listing;

    public UserResponseDTO getUser() { return user; }
    public void setUser(UserResponseDTO user) { this.user = user; }

    public ListingResponseDTO getListing() { return listing; }
    public void setListing(ListingResponseDTO listing) { this.listing = listing; }
}