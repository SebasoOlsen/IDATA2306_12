package IDATA2306.Group12.dto.Rooms;

import IDATA2306.Group12.entity.Hotel;

public class RoomsCreateDTO {
    private String roomName;
    private int capacity;
    private Hotel hotel;

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
    public Hotel getHotel() {
        return hotel;
    }
    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }
}