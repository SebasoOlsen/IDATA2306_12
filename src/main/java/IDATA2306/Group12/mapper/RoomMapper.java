package IDATA2306.Group12.mapper;

import IDATA2306.Group12.dto.hotel.HotelResponseDTO;
import org.springframework.stereotype.Component;

import IDATA2306.Group12.dto.room.RoomResponseDTO;
import IDATA2306.Group12.entity.Room;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * Mapper class for converting between Room entity and RoomResponseDTO.
 */
@Component
public class RoomMapper {

    private HotelMapper hotelMapper;

    /**
     * Constructs a RoomMapper with the required HotelMapper dependency.
     *
     * @param hotelMapper the HotelMapper to use for mapping hotels
     */
    public RoomMapper(HotelMapper hotelMapper) {
        this.hotelMapper = hotelMapper;
    }

    /**
     * Converts a Room entity to a RoomResponseDTO.
     *
     * @param room the Room entity to convert
     * @return the corresponding RoomResponseDTO
     */
    public RoomResponseDTO toResponseDTO(Room room) {
        RoomResponseDTO dto = new RoomResponseDTO();
        dto.setId(room.getId());
        dto.setName(room.getName());
        Set<HotelResponseDTO> hotelsDto = room.getHotels().stream()
                .map(hotelMapper::toResponseDTO)
                .collect(Collectors.toSet());
        dto.setHotels(hotelsDto);
        return dto;
    }
}
