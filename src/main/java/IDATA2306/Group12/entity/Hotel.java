package IDATA2306.Group12.entity;

import java.util.HashSet;
import java.util.Set;

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
     * The country where the hotel is located.
     */
    @JsonProperty("country")
    @Column(name = "country")
    private String country;

    /**
     * The city where the hotel is located.
     */
    @JsonProperty("city")
    @Column(name = "city")
    private String city;

    /**
     * The set of rooms associated with the hotel.
     */
    @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JoinTable(name = "hotel_rooms", joinColumns = @JoinColumn(name = "hotel_id"), inverseJoinColumns = @JoinColumn(name = "room_id"))
    @JsonProperty("hotelRooms")
    private Set<Room> rooms = new HashSet<>();

    /**
     * The set of extra features associated with the hotel.
     */
    @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JoinTable(name = "hotel_extra_features", joinColumns = @JoinColumn(name = "hotel_id"), inverseJoinColumns = @JoinColumn(name = "feature_id"))
    private Set<ExtraFeature> extraFeatures = new HashSet<>();

    /**
     * The average review score for the hotel
     */
    private float averageReview;

    /**
     * Indicates if the hotel is hidden.
     */
    private boolean isHidden;

    /**
     * Default constructor for Hotel class.
     */
    public Hotel() {
    }

    /**
     * Gets the unique identifier for the hotel.
     * @return the hotel ID
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the unique identifier for the hotel.
     * @param id the hotel ID
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the name of the hotel.
     * @return the hotel name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the hotel.
     * @param name the hotel name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the type of location where the hotel is situated.
     * @return the location type
     */
    public String getLocationType() {
        return locationType;
    }

    /**
     * Sets the type of location for the hotel.
     * @param locationType the location type
     */
    public void setLocationType(String locationType) {
        this.locationType = locationType;
    }

    /**
     * Adds a room to the hotel.
     * @param room the room to add
     */
    public void addRoom(Room room) {
        this.rooms.add(room);
        room.getHotels().add(this);
    }

    /**
     * Removes a room from the hotel.
     * @param room the room to remove
     */
    public void removeRoom(Room room) {
        this.rooms.remove(room);
        room.getHotels().remove(this);
    }

    /**
     * Gets the set of rooms associated with the hotel.
     * @return the set of rooms
     */
    public Set<Room> getRooms() {
        return this.rooms;
    }

    /**
     * Sets the set of rooms associated with the hotel.
     * @param rooms the set of rooms
     */
    public void setRooms(Set<Room> rooms) {
        this.rooms = rooms;
    }

    /**
     * Adds an extra feature to the hotel.
     * @param feature the extra feature to add
     */
    public void addFeature(ExtraFeature feature) {
        this.extraFeatures.add(feature);
        feature.getHotels().add(this);
    }

    /**
     * Removes an extra feature from the hotel.
     * @param feature the extra feature to remove
     */
    public void removeFeature(ExtraFeature feature) {
        this.extraFeatures.remove(feature);
        feature.getHotels().remove(this);
    }

    /**
     * Gets the set of extra features associated with the hotel.
     * @return the set of extra features
     */
    public Set<ExtraFeature> getExtraFeatures() {
        return extraFeatures;
    }

    /**
     * Sets the set of extra features associated with the hotel.
     * @param extraFeatures the set of extra features
     */
    public void setExtraFeatures(Set<ExtraFeature> extraFeatures) {
        this.extraFeatures = extraFeatures;
    }

    /**
     * Gets the country where the hotel is located
     * @return the country
     */
    public String getCountry() {
        return country;
    }

    /**
     * Sets the country where the hotel is located.
     * @param country the country
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * Gets the city where the hotel is located.
     * @return the city
     */
    public String getCity() {
        return city;
    }

    /**
     * Sets the city where the hotel is located.
     * @param city the city
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * Gets the average review score for the hotel.
     * @return the average review
     */
    public float getAverageReview() {
        return this.averageReview;
    }

    /**
     * Sets the averege review score for the hotel.
     * @param averageReview the average review
     */
    public void setAverageReview(float averageReview) {
        this.averageReview = averageReview;
    }

    /**
     * Checks if the hotel is hidden.
     * @return true if hidden, false otherwise
     */
    public boolean isHidden() {
        return this.isHidden;
    }

    /**
     * Sets the hidden status of the hotel.
     * @param isHidden true to hide, false to show
     */
    public void setHidden(boolean isHidden) {
        this.isHidden = isHidden;
    }
}
