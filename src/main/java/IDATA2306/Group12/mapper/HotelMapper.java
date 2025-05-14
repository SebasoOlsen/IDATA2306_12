package IDATA2306.Group12.mapper;

import org.springframework.stereotype.Component;

import IDATA2306.Group12.dto.hotel.HotelResponseDTO;
import IDATA2306.Group12.entity.Hotel;

/**
 * Mapper class for converting between Hotel entity and HotelDTO.
 */
@Component
public class HotelMapper {

    public HotelMapper() {
    }

    public HotelResponseDTO toResponseDTO(Hotel hotel) {
        if (hotel == null)
            return null;

        HotelResponseDTO dto = new HotelResponseDTO();
        dto.setId(hotel.getId());

        dto.setName(hotel.getName() != null ? hotel.getName() : "Unnamed Hotel");

        dto.setLocationType(
                hotel.getLocationType() != null ? hotel.getLocationType() : "");

        dto.setExtraFeature(
                hotel.getExtraFeatures() != null ? hotel.getExtraFeatures() : "");

        dto.setRoomType(
                hotel.getRoomTypes() != null ? hotel.getRoomTypes() : "");

        dto.setCountry(hotel.getCountry() != null ? hotel.getCountry() : "Unknown");
        dto.setCity(hotel.getCity() != null ? hotel.getCity() : "Unknown");

        return dto;
    }

    public Hotel toEntity(HotelResponseDTO hotelDTO) {
        Hotel hotel = new Hotel();
        hotel.setId(hotelDTO.getId());
        hotel.setName(hotelDTO.getName());
        hotel.setLocationType(hotelDTO.getLocationType());
        hotel.setExtraFeatures(hotelDTO.getExtraFeature());
        hotel.setRoomTypes(hotelDTO.getRoomType());
        hotel.setCity(hotelDTO.getCity());
        hotel.setCountry(hotelDTO.getCountry());
        return hotel;
    }
}
