package IDATA2306.Group12.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.List;

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
    private int id;

    /**
     * The name of the hotel.
     */
    @JsonProperty("name")
    private String name;

    /**
     * The type of location where the hotel is situated.
     */
    @JsonProperty("locationType")
    private String locationType;

    /**
     * The types of rooms available in the hotel.
     */
    @JsonProperty("roomTypes")
    private String roomTypes;

    /**
     * The extra features offered by the hotel.
     */
    @JsonProperty("extraFeatures")
    private String extraFeatures;

    /**
     * Create a Hotel using parameters.
     * @param id id of the hotel
     * @param name name of the hotel
     * @param locationType location type
     * @param roomTypes room types
     * @param extraFeatures extra features
     */
    public Hotel(int id, String name, String locationType,
                 String roomTypes, String extraFeatures) {
        this.id = id;
        this.name = name;
        this.locationType = locationType;
        this.roomTypes = roomTypes;
        this.extraFeatures = extraFeatures;
    }

    public Hotel() {}
}
