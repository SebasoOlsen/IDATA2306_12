package IDATA2306.Group12.dto.favourite;

public class FavouriteCreateDTO {
    private int userId;
    private int listingId;

    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }

    public int getListingId() { return listingId; }
    public void setListingId(int listingId) { this.listingId = listingId; }
}