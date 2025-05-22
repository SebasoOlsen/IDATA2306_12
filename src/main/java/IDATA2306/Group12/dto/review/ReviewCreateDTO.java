package IDATA2306.Group12.dto.review;
/**
 * DTO for creating a review, containing hotel ID, user ID, review text, and star rating.
 */
public class ReviewCreateDTO {

    private int hotelId;
    private int userId;
    private String review;
    private int stars;

    public ReviewCreateDTO() {
    }

    public ReviewCreateDTO(int hotelId, int userId, String review, int stars) {
        this.hotelId = hotelId;
        this.userId = userId;
        this.review = review;
        this.stars = stars;
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
}