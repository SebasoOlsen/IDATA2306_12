package IDATA2306.Group12.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class FavouriteId implements Serializable {

    @Column(name = "userId")
    private int userId;

    @Column(name = "listingId")
    private int listingId;

    public FavouriteId() {}

    public FavouriteId(int userId, int listingId) {
        this.userId = userId;
        this.listingId = listingId;
    }

    // Required by JPA to compare composite keys
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FavouriteId)) return false;
        FavouriteId that = (FavouriteId) o;
        return userId == that.userId && listingId == that.listingId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, listingId);
    }

    // Getters and setters
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getListingId() {
        return listingId;
    }

    public void setListingId(int listingId) {
        this.listingId = listingId;
    }
}
