package IDATA2306.Group12.dto.hotel;

public class HotelResponseDTO {
    private int id;
    private String name;
    private String locationType;
    private String roomTypes;
    private String extraFeatures;

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getLocationTypes() { return this.locationType; }
    public void setLocationTypes(String locationTypes) { this.locationType = locationTypes;}

    public String getRoomTypes() { return this.roomTypes; }
    public void setRoomTypes(String roomtypes) { this.roomTypes = roomtypes;}

    public String getExtraFeatures() { return this.extraFeatures; }
    public void setExtraFeatures(String extraFeatures) { this.extraFeatures = extraFeatures;}
}
