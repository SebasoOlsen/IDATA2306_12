package IDATA2306.Group12.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import IDATA2306.Group12.dto.room.RoomResponseDTO;
import IDATA2306.Group12.entity.Room;
import IDATA2306.Group12.mapper.RoomMapper;
import IDATA2306.Group12.repository.RoomRepository;

@Service
public class RoomService {

    private final RoomRepository repository;

    private final RoomMapper roomMapper;

    public RoomService(RoomRepository repository, RoomMapper roomMapper) {
        this.repository = repository;
        this.roomMapper = roomMapper;
    }

    public Room getOrCreateByName(String name) {
        return repository.findByName(name).stream().findFirst().orElseGet(() -> {
            Room room = new Room();
            room.setName(name);
            return repository.save(room);
        });
    }

    public Room getRoomById(int id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Room not found"));
    }

    public List<RoomResponseDTO> getRoomsByHotelId(Long hotelId) {
        List<Room> rooms = repository.findByHotels_id(hotelId);
        return rooms.stream().map(roomMapper::toResponseDTO).collect(Collectors.toList());
    }

}
