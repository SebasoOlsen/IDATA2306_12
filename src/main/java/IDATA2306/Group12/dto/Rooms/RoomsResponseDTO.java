package IDATA2306.Group12.dto.Rooms;

import IDATA2306.Group12.dto.hotel.HotelResponseDTO;

public class RoomsResponseDTO {
    private int id;
    private String roomName;
    private int capacity;
    private HotelResponseDTO hotel;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public HotelResponseDTO getHotel() {
        return hotel;
    }
    public void setHotel(HotelResponseDTO hotel) {
        this.hotel = hotel;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}