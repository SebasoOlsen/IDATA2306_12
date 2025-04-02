package IDATA2306.Group12.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

/**
 * Represents a hotel with various listings.
 */
@Entity
@Table(name = "Hotels")
public class Hotel {

    /**
     * The unique identifier for the hotel.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("id")
    @Column(name = "hID")
    private int id;

    /**
     * The name of the hotel.
     */
    @JsonProperty("name")
    @Column(name = "name")
    private String name;

    /**
     * The type of location where the hotel is situated.
     */
    @JsonProperty("locationType")
    @Column(name = "locationType")
    private String locationType;

    /**
     * The types of rooms available in the hotel.
     */
    @JsonProperty("roomTypes")
    @Column(name = "roomTypes")
    private String roomTypes;

    /**
     * The extra features offered by the hotel.
     */
    @JsonProperty("extraFeatures")
    @Column(name = "extraFeatures")
    private String extraFeatures;

    public Hotel() {}

    public int getId() {return id;}
    public void setId(int id) {this.id = id;}

    public String getName() {return name;}
    public void setName(String name) {this.name = name;}

    public String getLocationType() {return locationType;}
    public void setLocationType(String locationType) {this.locationType = locationType;}

    public String getRoomTypes() {return roomTypes;}
    public void setRoomTypes(String roomTypes) {this.roomTypes = roomTypes;}

    public String getExtraFeatures() {return extraFeatures;}
    public void setExtraFeatures(String extraFeatures) {this.extraFeatures = extraFeatures;}
}
