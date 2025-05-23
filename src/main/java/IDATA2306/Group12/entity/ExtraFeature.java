package IDATA2306.Group12.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
/**
 * Entity representing an extra feature that can be associated with multiple hotels.
 * Each feature has a unique name and can be linked to several hotels.
 */
@Entity
public class ExtraFeature {
    /**
     * The unique identifier for the extra feature.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * The unique name of the extra feature.
     */
    @Column(unique = true)
    private String name;
    /**
     * The set of hotels associated with this extra feature.
     */
    @ManyToMany(mappedBy = "extraFeatures")
    private Set<Hotel> hotels = new HashSet<>();
    /**
     * Default constructor.
     */
    public ExtraFeature() {
    }
    /**
     * Gets the unique identifier of the extra feature.
     * @return the id of the extra feature
     */
    public Long getId() {
        return id;
    }
    /**
     * Sets the unique identifier of the extra feature.
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }
    /**
     * Gets the name of the extra feature.
     * @return the name of the extra feature
     */
    public String getName() {
        return name;
    }
    /**
     * Sets the name of the extra feature.
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * Gets the set of hotels associated with this extra feature.
     * @return the set of hotels
     */
    public Set<Hotel> getHotels() {
        return hotels;
    }
    /**
     * Sets the set of hotels associated with this extra feature.
     * @param hotels the set of hotels to associate
     */
    public void setHotels(Set<Hotel> hotels) {
        this.hotels = hotels;
    }
    /**
     * Associates a hotel with this extra feature.
     * @param hotel the hotel to add
     */
    public void addHotel(Hotel hotel) {
        this.hotels.add(hotel);
        hotel.getExtraFeatures().add(this);
    }
    /**
     * Removes the association of a hotel from this extra feature.
     * @param hotel the hotel to remove
     */
    public void removeHotel(Hotel hotel) {
        this.hotels.remove(hotel);
        hotel.getExtraFeatures().remove(this);
    }
}
