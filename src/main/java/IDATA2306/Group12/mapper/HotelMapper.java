package IDATA2306.Group12.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import IDATA2306.Group12.dto.hotel.HotelCreateDTO;
import IDATA2306.Group12.dto.hotel.HotelResponseDTO;
import IDATA2306.Group12.entity.ExtraFeature;
import IDATA2306.Group12.entity.Hotel;
import IDATA2306.Group12.entity.Room;

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

                List<String> extraFeatureNames = hotel.getExtraFeatures() != null
                                ? hotel.getExtraFeatures().stream()
                                                .map(ExtraFeature::getName)
                                                .collect(Collectors.toList())
                                : new ArrayList<>();
                dto.setExtraFeature(extraFeatureNames);

                List<String> roomNames = hotel.getRooms() != null
                                ? hotel.getRooms().stream()
                                                .map(Room::getName)
                                                .collect(Collectors.toList())
                                : new ArrayList<>();
                dto.setRoom(roomNames);

                dto.setCountry(hotel.getCountry() != null ? hotel.getCountry() : "Unknown");
                dto.setCity(hotel.getCity() != null ? hotel.getCity() : "Unknown");
                dto.setAverageReview(hotel.getAverageReview());

                return dto;
        }

        public Hotel toEntity(HotelCreateDTO hotelDTO, Set<ExtraFeature> featuresFromDb, Set<Room> roomsFromDb) {
                Hotel hotel = new Hotel();
                hotel.setName(hotelDTO.getName());
                hotel.setLocationType(hotelDTO.getLocationType());
                hotel.setExtraFeatures(featuresFromDb);
                hotel.setRooms(roomsFromDb);
                hotel.setCity(hotelDTO.getCity());
                hotel.setCountry(hotelDTO.getCountry());
                hotel.setAverageReview(hotelDTO.getAverageReview());
                return hotel;
        }
}
