package IDATA2306.Group12.dto.favourite;

import IDATA2306.Group12.dto.listing.ListingResponseDTO;
import IDATA2306.Group12.dto.user.UserResponseDTO;
/**
 * DTO for favourite response data.
 */
public class FavouriteResponseDTO {
    private UserResponseDTO user;
    private ListingResponseDTO listing;

    /** @return user info */
    public UserResponseDTO getUser() { return user; }
    /** @param user user info to set */
    public void setUser(UserResponseDTO user) { this.user = user; }

    /** @return listing info */
    public ListingResponseDTO getListing() { return listing; }
    /** @param listing listing info to set */
    public void setListing(ListingResponseDTO listing) { this.listing = listing; }
}