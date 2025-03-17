package IDATA2306.Group12;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Represents a listing for a hotel.
 */
@Entity
@Table(name = "listings")
public class Listing {

    /**
     * The unique identifier for the listing.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /**
     * The provider of the listing.
     */
    private Provider provider;

    /**
     * The hotel associated with the listing.
     */
    private Hotel hotel;

    /**
     * The price of the listing.
     */
    private int price;

    /**
     * The currency of the price.
     */
    private String currency;

    /**
     * The link to the listing.
     */
    private String link;

    /**
     * The bookings made for the listing.
     */
    private List<Booking> bookings;

    /**
     * The users who have favorited the listing.
     */
    private List<User> favoritedBy;
}
