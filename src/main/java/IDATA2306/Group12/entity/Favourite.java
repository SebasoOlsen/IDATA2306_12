package IDATA2306.Group12.entity;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

@Entity
@Table(name = "Favourites")
public class Favourite {

    @EmbeddedId
    private FavouriteId id;

    @MapsId("userId")
    @ManyToOne
    @JsonProperty("user")
    @JoinColumn(name = "userId")
    private User user;

    @MapsId("listingId")
    @ManyToOne
    @JsonProperty("listing")
    @JoinColumn(name = "listingId")
    private Listing listing;

    public Favourite(User user, Listing listing){
        this.user = user;
        this.listing = listing;
    }

    public Favourite() {}

    public User getUser() {return this.user;}
    public void setUser(User user) {this.user = user;}

    public Listing getListing() {return this.listing;}
    public void setListingId(Listing listing) {this.listing = listing;}
}
