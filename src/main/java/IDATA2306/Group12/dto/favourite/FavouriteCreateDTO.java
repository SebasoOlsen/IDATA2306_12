package IDATA2306.Group12.dto.favourite;
/**
 * DTO for creating a favourite.
 */
public class FavouriteCreateDTO {
    private int userId;
    private int listingId;

    /** @return user ID */
    public int getUserId() { return userId; }
    /** @param userId user ID to set */
    public void setUserId(int userId) { this.userId = userId; }

    /** @return listing ID */
    public int getListingId() { return listingId; }
    /** @param listingId listing ID to set */
    public void setListingId(int listingId) { this.listingId = listingId; }
}