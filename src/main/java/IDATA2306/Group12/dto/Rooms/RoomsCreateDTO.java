package IDATA2306.Group12.dto.Rooms;

public class RoomsCreateDTO {
    private String roomName;
    private int capacity;

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