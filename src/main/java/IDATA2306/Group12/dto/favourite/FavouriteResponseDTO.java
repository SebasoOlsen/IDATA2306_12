package IDATA2306.Group12.dto.favourite;

import IDATA2306.Group12.dto.listing.ListingDTO;
import IDATA2306.Group12.dto.user.UserDTO;

public class FavouriteResponseDTO {
    private UserDTO user;
    private ListingDTO listing;

    public UserDTO getUser() { return user; }
    public void setUser(UserDTO user) { this.user = user; }

    public ListingDTO getListing() { return listing; }
    public void setListing(ListingDTO listing) { this.listing = listing; }
}