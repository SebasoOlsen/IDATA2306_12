package IDATA2306.Group12.mapper;

import IDATA2306.Group12.entity.Hotel;
import IDATA2306.Group12.dto.HotelDTO;

/**
 * Mapper class for converting between Hotel entity and HotelDTO.
 */
public class HotelMapper {

    /**
     * Converts a Hotel entity to a HotelDTO.
     *
     * @param hotel the Hotel entity to convert
     * @return the corresponding HotelDTO
     */
    public static HotelDTO toDTO(Hotel hotel) {
        if (hotel == null) {
            throw new IllegalArgumentException("Hotel cannot be null");
        }
        return new HotelDTO(
            hotel.getId(),
            hotel.getName(),
            hotel.getLocationType(),
            hotel.getRoomTypes(),
            hotel.getExtraFeatures()
        );
    }

    /**
     * Converts a HotelDTO to a Hotel entity.
     *
     * @param hotelDTO the HotelDTO to convert
     * @return the corresponding Hotel entity
     */
    public static Hotel toEntity(HotelDTO hotelDTO) {
        if (hotelDTO == null) {
            throw new IllegalArgumentException("HotelDTO cannot be null");
        }
        Hotel hotel = new Hotel();
        hotel.setId(hotelDTO.getId());
        hotel.setName(hotelDTO.getName());
        hotel.setLocationType(hotelDTO.getLocationType());
        hotel.setRoomTypes(hotelDTO.getRoomTypes());
        hotel.setExtraFeatures(hotelDTO.getExtraFeatures());
        return hotel;
    }
}