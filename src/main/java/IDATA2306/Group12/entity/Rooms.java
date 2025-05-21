package IDATA2306.Group12.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

@Entity
@Table(name="Rooms")
public class Rooms {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "hotelId", nullable = false)
    @JsonProperty("hotel")
    private Hotel hotel;

    @JsonProperty("roomName")
    @Column(name="roomName")
    private String roomName;

    public Rooms(){}

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public Hotel getHotel() {
        return hotel;
    }
    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }
    public String getRoomName() {
        return roomName;
    }
    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }
}
