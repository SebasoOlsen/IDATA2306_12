package IDATA2306.Group12.dto.hotel;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
/**
 * DTO for creating a hotel.
 */
public class HotelCreateDTO {

    @JsonProperty("name")
    private String name;

    @JsonProperty("locationType")
    private String locationType;

    @JsonProperty("roomTypes")
    private List<String> rooms;

    @JsonProperty("extraFeatures")
    private List<String> extraFeatures;

    @JsonProperty("country")
    private String country;

    @JsonProperty("city")
    private String city;

    @JsonProperty("averageReview")
    private float averageReview;

    @JsonProperty("visibility")
    private boolean isHidden;

    public HotelCreateDTO() {
    }

    /** @return hotel name */
    public String getName() {
        return this.name;
    }

    /** @param name hotel name to set */
    public void setName(String name) {
        this.name = name;
    }

    /** @return location type */
    public String getLocationType() {
        return this.locationType;
    }

    /** @param locationType set location */
    public void setLocationType(String locationType) {
        this.locationType = locationType;
    }

    /** @return list of room types */
    public List<String> getRooms() {
        return this.rooms;
    }

    /** @param rooms list of room types to set */
    public void setRooms(List<String> rooms) {
        this.rooms = rooms;
    }

    /** @return list of extra features */
    public List<String> getExtraFeatures() {
        return this.extraFeatures;
    }

    /** @param extraFeatures list of extra features to set */
    public void setExtraFeatures(List<String> extraFeatures) {
        this.extraFeatures = extraFeatures;
    }

    /** @return country */
    public String getCountry() {
        return this.country;
    }

    /** @param country country to set */
    public void setCountry(String country) {
        this.country = country;
    }

    /** @return city */
    public String getCity() {
        return this.city;
    }

    /** @param city city to set */
    public void setCity(String city) {
        this.city = city;
    }

    /** @return average review score */

    public float getAverageReview() {
        return this.averageReview;
    }

    /** @param averageReview average review score to set */
    public void setAverageReview(float averageReview) {
        this.averageReview = averageReview;
    }

    /** @return true if hotel is hidden */
    public boolean isHidden() {
        return this.isHidden;
    }

    /** @param isHidden hotel visibility status to set */
    public void setHidden(boolean isHidden) {
        this.isHidden = isHidden;
    }
}
