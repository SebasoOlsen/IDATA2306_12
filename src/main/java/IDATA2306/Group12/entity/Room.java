package IDATA2306.Group12.entity;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

@Entity
@Table(name = "Rooms")
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @JsonProperty("Name")
    @Column(name = "Name")
    private String name;

    @ManyToMany(mappedBy = "rooms")
    @JsonProperty("hotels")
    private Set<Hotel> hotels = new HashSet<>();

    public Room() {
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Hotel> getHotels() {
        return this.hotels;
    }

    public void setHotels(Set<Hotel> hotels) {
        this.hotels = hotels;
    }

    public void addHotel(Hotel hotel) {
        this.hotels.add(hotel);
        hotel.getRooms().add(this);
    }

    public void removeHotel(Hotel hotel) {
        this.hotels.remove(hotel);
        hotel.getRooms().remove(this);
    }

}
