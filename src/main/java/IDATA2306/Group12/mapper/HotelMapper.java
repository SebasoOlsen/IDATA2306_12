package IDATA2306.Group12.mapper;

import org.springframework.stereotype.Component;

import IDATA2306.Group12.dto.hotel.HotelDTO;
import IDATA2306.Group12.entity.Hotel;

/**
 * Mapper class for converting between Hotel entity and HotelDTO.
 */
@Component
public class HotelMapper {

    public HotelMapper () {}

    public HotelDTO toDTO(Hotel hotel) {
        HotelDTO dto = new HotelDTO();
        dto.setId(hotel.getId());
        dto.setName(hotel.getName());
        dto.setLocationTypes(hotel.getLocationType());
        dto.setExtraFeatures(hotel.getExtraFeatures());
        dto.setRoomTypes(hotel.getRoomTypes());
        return dto;
    }

    public Hotel toEntity(HotelDTO hotelDTO){
        Hotel hotel = new Hotel();
        hotel.setId(hotelDTO.getId());
        hotel.setName(hotelDTO.getName());
        hotel.setLocationType(hotelDTO.getLocationTypes());
        hotel.setExtraFeatures(hotelDTO.getExtraFeatures());
        hotel.setRoomTypes(hotelDTO.getRoomTypes());
        return hotel;
    }
}
