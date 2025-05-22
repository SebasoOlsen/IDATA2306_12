package IDATA2306.Group12.dto.room;

import IDATA2306.Group12.dto.hotel.HotelResponseDTO;

import java.util.Set;
/**
 * DTO representing room response data, including room ID, name, and associated hotels.
 */
public class RoomResponseDTO {
    private int id;
    private String name;
    private Set<HotelResponseDTO> hotels;


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

    public Set<HotelResponseDTO> getHotels(){
        return hotels;
    }

    public void setHotels(Set<HotelResponseDTO> hotels){
        this.hotels = hotels;
    }

}