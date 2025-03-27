package IDATA2306.Group12.entity;

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

    public Listing () {}

    public int getId() {return id;}
    public void setId(int id) {this.id = id;}

    public int getPID() {return pID;}
    public void setPID(int pID) {this.pID = pID;}

    public int getHID() {return hID;}
    public void setHID(int hID) {this.hID = hID;}

    public int getPrice() {return price;}
    public void setPrice(int price) {this.price = price;}

    public String getCurrency() {return currency;}
    public void setCurrency(String currency) {this.currency = currency;}

    public String getLink() {return link;}
    public void setLink(String link) {this.link = link;}
}
