package IDATA2306.Group12.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.time.LocalDate;

/**
 * Entity representing an image associated with a specific type (e.g., HOTEL or USER).
 * Stores the image URL, type, and the identifier of the associated entity.
 */
@Entity
@Table(name ="Reviews")
public class Review {

    /**
     * The unique identifier for the review.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("id")
    private int id;

    /**
     * The hotel being reviewed.
     */
    @ManyToOne
    @JoinColumn(name = "hotelId", nullable = false)
    @JsonProperty("hotel")
    private Hotel hotel;

    /**
     * The user who wrote the review.
     */
    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    @JsonProperty("user")
    private User user;

    /**
     * The review text.
     */
    @JsonProperty("review")
    @Column(name= "review")
    private String review;

    /**
     * The star rating given in the review.
     */
    @JsonProperty("stars")
    @Column(name="stars")
    private int stars;

    /**
     * The date the review was posted.
     */
    @JsonProperty("postDate")
    @Column(name="postDate")
    private LocalDate postDate;

    public Review() {}

    @PrePersist
    protected void onCreate() {
        this.postDate = LocalDate.now();
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public Hotel getHotel() {
        return hotel;
    }
    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
    public String getReview() {
        return review;
    }
    public void setReview(String review) {
        this.review = review;
    }
    public int getStars() {
        return stars;
    }
    public void setStars(int stars) {
        this.stars = stars;
    }
    public LocalDate getPostDate() {
        return postDate;
    }
    public void setPostDate(LocalDate postDate) {
        this.postDate = postDate;
    }
}
