package IDATA2306.Group12.dto.hotel;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

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

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocationType() {
        return this.locationType;
    }

    public void setLocationType(String locationType) {
        this.locationType = locationType;
    }

    public List<String> getRooms() {
        return this.rooms;
    }

    public void setRooms(List<String> rooms) {
        this.rooms = rooms;
    }

    public List<String> getExtraFeatures() {
        return this.extraFeatures;
    }

    public void setExtraFeatures(List<String> extraFeatures) {
        this.extraFeatures = extraFeatures;
    }

    public String getCountry() {
        return this.country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public float getAverageReview() {
        return this.averageReview;
    }

    public void setAverageReview(float averageReview) {
        this.averageReview = averageReview;
    }

    public boolean isHidden() {
        return this.isHidden;
    }

    public void setHidden(boolean isHidden) {
        this.isHidden = isHidden;
    }
}
