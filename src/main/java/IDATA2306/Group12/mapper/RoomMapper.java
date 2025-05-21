package IDATA2306.Group12.mapper;

import org.springframework.stereotype.Component;

import IDATA2306.Group12.dto.room.RoomResponseDTO;
import IDATA2306.Group12.entity.Room;

@Component
public class RoomMapper {

    public RoomMapper() {
    }

    public RoomResponseDTO toResponseDTO(Room room) {
        RoomResponseDTO dto = new RoomResponseDTO();
        dto.setId(room.getId());
        dto.setName(room.getName());
        return dto;
    }
}
