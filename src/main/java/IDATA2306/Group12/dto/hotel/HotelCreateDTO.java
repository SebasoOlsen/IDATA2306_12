package IDATA2306.Group12.dto.hotel;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class HotelCreateDTO {

    @JsonProperty("name")
    private String name;

    @JsonProperty("locationType")
    private String locationType;

    @JsonProperty("roomType")
    private String roomType;

    @JsonProperty("extraFeatures")
    private List<String> extraFeatures;

    @JsonProperty("country")
    private String country;

    @JsonProperty("city")
    private String city;

    public HotelCreateDTO() {
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

    public List<String> getExtraFeatures() {
        return extraFeatures;
    }

    public void setExtraFeatures(List<String> extraFeatures) {
        this.extraFeatures = extraFeatures;
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
