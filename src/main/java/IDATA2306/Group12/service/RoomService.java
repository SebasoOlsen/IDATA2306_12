package IDATA2306.Group12.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import IDATA2306.Group12.dto.room.RoomResponseDTO;
import IDATA2306.Group12.entity.Room;
import IDATA2306.Group12.mapper.RoomMapper;
import IDATA2306.Group12.repository.RoomRepository;

/**
 * Service for managing rooms.
 * Handles creation, retrieval, and listing of rooms.
 */
@Service
public class RoomService {

    private final RoomRepository repository;

    private final RoomMapper roomMapper;

    /**
     * Constructs a RoomService with required dependencies.
     *
     * @param repository the room repository
     * @param roomMapper the room mapper
     */
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

    /**
     * Retrieves a room by its ID.
     *
     * @param id the room ID
     * @return the Room
     * @throws RuntimeException if the room is not found
     */
    public Room getRoomById(int id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Room not found"));
    }

    /**
     * Retrieves all rooms for a specific hotel.
     *
     * @param hotelId the hotel ID
     * @return a list of RoomResponseDTOs for the hotel
     */
    public List<RoomResponseDTO> getRoomsByHotelId(Integer hotelId) {
        List<Room> rooms = repository.findAllByHotelId(hotelId);
        return rooms.stream().map(roomMapper::toResponseDTO).collect(Collectors.toList());
    }

}
