package IDATA2306.Group12.entity;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

@Entity
@Table(name = "Favourites")
public class Favourite {

    /**
     * ID of the user that has favourited a listing
     */
    @Id
    @JsonProperty("uID")
    private int uID;

    /**
     * ID of the favourited listing.
     */
    @Id
    @JsonProperty("listingID")
    private int listingID;

    /**
     * Create a new favourite using parameters.
     * @param uID id of the user
     * @param listingID id of the listing
     */
    public Favourite(int uID, int listingID) {
        this.uID = uID;
        this.listingID = listingID;
    }

    public Favourite() {}

}
