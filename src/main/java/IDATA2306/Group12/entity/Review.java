package IDATA2306.Group12.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name ="Reviews")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "hotelId", nullable = false)
    @JsonProperty("hotel")
    private Hotel hotel;

    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    @JsonProperty("user")
    private User user;

    @JsonProperty("review")
    @Column(name= "review")
    private String review;

    @JsonProperty("stars")
    @Column(name="stars")
    private int stars;

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
