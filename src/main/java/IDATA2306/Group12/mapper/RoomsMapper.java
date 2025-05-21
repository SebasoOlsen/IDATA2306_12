package IDATA2306.Group12.mapper;

import org.springframework.stereotype.Component;
import IDATA2306.Group12.dto.Rooms.RoomsCreateDTO;
import IDATA2306.Group12.dto.Rooms.RoomsResponseDTO;
import IDATA2306.Group12.entity.Rooms;

@Component
public class RoomsMapper {
    private final HotelMapper hotelMapper;

    public RoomsMapper(HotelMapper hotelMapper) {
        this.hotelMapper = hotelMapper;
    }
    public Rooms toEntity(RoomsCreateDTO dto) {
        if(dto == null) {
            return null;
        }
        Rooms room = new Rooms();
        room.setHotel(dto.getHotel());
        room.setRoomName(dto.getRoomName());
        return room;
    }

    public RoomsResponseDTO toResponseDTO(Rooms room) {
        if(room == null) {
            return null;
        }
        RoomsResponseDTO dto = new RoomsResponseDTO();
        dto.setHotel(hotelMapper.toResponseDTO(room.getHotel()));
        dto.setId(room.getId());
        dto.setRoomName(room.getRoomName());
        return dto;
    }
}