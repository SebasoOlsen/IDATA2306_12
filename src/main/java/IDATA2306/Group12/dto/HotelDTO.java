package IDATA2306.Group12.dto;

import jakarta.validation.constraints.NotBlank;
import java.util.List;

/**
 * Data Transfer Object (DTO) for Hotel entity.
 */
public class HotelDTO {

    private int id;

    @NotBlank(message = "Hotel name cannot be empty")
    private String name;

    @NotBlank(message = "Location type cannot be empty")
    private String locationType;

    private String roomTypes;
    private String extraFeatures;

    public HotelDTO() {}

    public HotelDTO(int id, String name, String locationType, String roomTypes, String extraFeatures) {
        this.id = id;
        this.name = name;
        this.locationType = locationType;
        this.roomTypes = roomTypes;
        this.extraFeatures = extraFeatures;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getLocationType() { return locationType; }
    public void setLocationType(String locationType) { this.locationType = locationType; }

    public String getRoomTypes() { return roomTypes; }
    public void setRoomTypes(String roomTypes) { this.roomTypes = roomTypes; }

    public String getExtraFeatures() { return extraFeatures; }
    public void setExtraFeatures(String extraFeatures) { this.extraFeatures = extraFeatures; }
}
