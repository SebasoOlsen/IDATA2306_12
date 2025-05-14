package IDATA2306.Group12.dto.hotel;

import com.fasterxml.jackson.annotation.JsonProperty;

public class HotelResponseDTO {

    private int id;

    private String name;

    @JsonProperty("locationType")
    private String locationType;

    @JsonProperty("roomType")
    private String roomType;

    @JsonProperty("extraFeature")
    private String extraFeature;

    @JsonProperty("country")
    private String country;

    @JsonProperty("city")
    private String city;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocationType() {
        return locationType;
    }

    public void setLocationType(String locationType) {
        this.locationType = locationType;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public String getExtraFeature() {
        return extraFeature;
    }

    public void setExtraFeature(String extraFeature) {
        this.extraFeature = extraFeature;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
