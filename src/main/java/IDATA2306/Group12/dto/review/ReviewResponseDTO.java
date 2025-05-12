package IDATA2306.Group12.dto.review;

import java.time.LocalDate;

public class ReviewResponseDTO {

    private int id;
    private int hotelId;
    private int userId;
    private String review;
    private int stars;
    private LocalDate postDate;

    public ReviewResponseDTO() {
    }

    public ReviewResponseDTO(int id, int hotelId, int userId, String review, int stars, LocalDate postDate) {
        this.id = id;
        this.hotelId = hotelId;
        this.userId = userId;
        this.review = review;
        this.stars = stars;
        this.postDate = postDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getHotelId() {
        return hotelId;
    }

    public void setHotelId(int hotelId) {
        this.hotelId = hotelId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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