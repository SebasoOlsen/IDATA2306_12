package IDATA2306.Group12.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
/**
 * Entity representing a user's favourite listing.
 * Each favourite links a user to a specific listing they have marked as favourite.
 */
@Entity
@Table(name = "Favourites")
public class Favourite {
    /**
     * The unique identifier for the favourite entry.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * The user who marked the listing as favourite.
     */
    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    @JsonProperty("user")
    private User user;
    /**
     * The listing that is marked as favourite.
     */
    @ManyToOne
    @JoinColumn(name = "listingId", nullable = false)
    @JsonProperty("listing")
    private Listing listing;

    /**
     * Constructs a Favourite with the specified user and listing.
     * @param user the user who favourited the listing
     * @param listing the listing marked as favourite
     */
    public Favourite(User user, Listing listing) {
        this.user = user;
        this.listing = listing;
    }
    /**
     * Default constructor.
     */
    public Favourite() {
    }
    /**
     * Gets the user who favourited the listing.
     * @return the user
     */
    public User getUser() {
        return this.user;
    }
    /**
     * Sets the user who favourited the listing.
     * @param user the user to set
     */
    public void setUser(User user) {
        this.user = user;
    }
    /**
     * Gets the listing marked as favourite.
     * @return the listing
     */
    public Listing getListing() {
        return this.listing;
    }
    /**
     * Sets the listing marked as favourite.
     * @param listing the listing to set
     */
    public void setListingId(Listing listing) {
        this.listing = listing;
    }
}
