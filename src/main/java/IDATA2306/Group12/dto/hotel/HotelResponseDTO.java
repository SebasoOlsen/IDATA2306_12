package IDATA2306.Group12.dto.hotel;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class HotelResponseDTO {

    private int id;

    private String name;

    @JsonProperty("locationType")
    private String locationType;

    @JsonProperty("room")
    private List<String> room;

    @JsonProperty("extraFeature")
    private List<String> extraFeature;

    @JsonProperty("country")
    private String country;

    @JsonProperty("city")
    private String city;

    @JsonProperty("averageReview")
    private float averageReview;

    public HotelResponseDTO() {
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
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

    public List<String> getRoom() {
        return this.room;
    }

    public void setRoom(List<String> room) {
        this.room = room;
    }

    public List<String> getExtraFeature() {
        return this.extraFeature;
    }

    public void setExtraFeature(List<String> extraFeature) {
        this.extraFeature = extraFeature;
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
}
