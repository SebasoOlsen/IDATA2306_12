package IDATA2306.Group12.entity;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
/**
 * Entity representing a room that can be associated with one or more hotels.
 * Stores the room's name and its hotel associations.
 */
@Entity
@Table(name = "Rooms")
public class Room {

    /**
     * The unique identifier for the room.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    /**
     * The name of the room.
     */
    @JsonProperty("Name")
    @Column(name = "Name")
    private String name;

    /**
     * The set of hotels associated with this room.
     */
    @ManyToMany(mappedBy = "rooms")
    @JsonProperty("hotels")
    private Set<Hotel> hotels = new HashSet<>();

    /**
     * Default constructor.
     */
    public Room() {
    }

    /**
     * Gets the unique identifier for the room.
     * @return the room ID
     */
    public int getId() {
        return this.id;
    }

    /**
     * Sets the unique identifier for the room.
     * @param id the room ID
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the name of the room
     * @return the room name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Sets the name of the room.
     * @param name the room name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the set of hotels associated with this room.
     * @return the set of hotels
     */
    public Set<Hotel> getHotels() {
        return this.hotels;
    }

    /**
     * Sets the set of hotels associated with this room.
     * @param hotels the set of hotels
     */
    public void setHotels(Set<Hotel> hotels) {
        this.hotels = hotels;
    }

    /**
     * Adds a hotel to the set of associated hotels.
     * @param hotel the hotel to add
     */
    public void addHotel(Hotel hotel) {
        this.hotels.add(hotel);
        hotel.getRooms().add(this);
    }

    /**
     * Removes a hotel from the set of associated hotels.
     * @param hotel the hotel to remove
     */
    public void removeHotel(Hotel hotel) {
        this.hotels.remove(hotel);
        hotel.getRooms().remove(this);
    }

}
