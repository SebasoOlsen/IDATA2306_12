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

    @JsonProperty("country")
    @Column(name = "country")
    private String country;

    @JsonProperty("city")
    @Column(name = "city")
    private String city;

    @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JoinTable(name = "hotel_rooms", joinColumns = @JoinColumn(name = "hotel_id"), inverseJoinColumns = @JoinColumn(name = "room_id"))
    @JsonProperty("hotelRooms")
    private Set<Room> rooms = new HashSet<>();

    @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JoinTable(name = "hotel_extra_features", joinColumns = @JoinColumn(name = "hotel_id"), inverseJoinColumns = @JoinColumn(name = "feature_id"))
    private Set<ExtraFeature> extraFeatures = new HashSet<>();

    private float averageReview;

    private boolean isHidden;

    public Hotel() {
    }

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

    public void addRoom(Room room) {
        this.rooms.add(room);
        room.getHotels().add(this);
    }

    public void removeRoom(Room room) {
        this.rooms.remove(room);
        room.getHotels().remove(this);
    }

    public Set<Room> getRooms() {
        return this.rooms;
    }

    public void setRooms(Set<Room> rooms) {
        this.rooms = rooms;
    }

    public void addFeature(ExtraFeature feature) {
        this.extraFeatures.add(feature);
        feature.getHotels().add(this);
    }

    public void removeFeature(ExtraFeature feature) {
        this.extraFeatures.remove(feature);
        feature.getHotels().remove(this);
    }

    public Set<ExtraFeature> getExtraFeatures() {
        return extraFeatures;
    }

    public void setExtraFeatures(Set<ExtraFeature> extraFeatures) {
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
