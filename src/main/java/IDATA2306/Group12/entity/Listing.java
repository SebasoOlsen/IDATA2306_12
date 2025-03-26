package IDATA2306.Group12.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Represents a listing for a hotel.
 */
@Entity
@Table(name = "Listings")
public class Listing {

    /**
     * The unique identifier for the listing.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("id")
    private int id;

    /**
     * The provider of the listing.
     */
    @JsonProperty("pID")
    private int pID;

    /**
     * The hotel associated with the listing.
     */
    @JsonProperty("hID")
    private int hID;

    /**
     * The price of the listing.
     */
    @JsonProperty("price")
    private int price;

    /**
     * The currency of the price.
     */
    @JsonProperty("currency")
    private String currency;

    /**
     * The link to the listing.
     */
    @JsonProperty("link")
    private String link;

}
