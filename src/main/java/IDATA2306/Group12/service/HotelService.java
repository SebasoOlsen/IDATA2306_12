package IDATA2306.Group12.service;

import IDATA2306.Group12.dto.hotel.HotelCreateDTO;
import IDATA2306.Group12.dto.hotel.HotelResponseDTO;
import IDATA2306.Group12.entity.ExtraFeature;
import IDATA2306.Group12.entity.Hotel;
import IDATA2306.Group12.entity.Room;
import IDATA2306.Group12.mapper.HotelMapper;
import IDATA2306.Group12.mapper.RoomMapper;
import IDATA2306.Group12.repository.HotelRepository;
import IDATA2306.Group12.service.ExtraFeatureService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class HotelService {
    private final HotelRepository hotelRepository;
    private final HotelMapper hotelMapper;
    private final ExtraFeatureService extraFeatureService;
    private final RoomService roomService;

    public HotelService(HotelRepository hotelRepository, HotelMapper hotelMapper,
            ExtraFeatureService extraFeatureService, RoomService roomService) {
        this.hotelRepository = hotelRepository;
        this.hotelMapper = hotelMapper;
        this.extraFeatureService = extraFeatureService;
        this.roomService = roomService;
    }

    public List<HotelResponseDTO> getAllHotels() {
        List<Hotel> hotels = hotelRepository.findAll(); // verify this doesn't return null
        System.out.println("Found " + hotels.size() + " hotels");

        return hotelRepository.findAll().stream()
                .map(hotelMapper::toResponseDTO)
                .toList();
    }

    public HotelResponseDTO getHotelById(Long id) {
        Hotel hotel = hotelRepository.findById(id.intValue())
                .orElseThrow(() -> new RuntimeException("Hotel not found"));
        return hotelMapper.toResponseDTO(hotel);
    }

    public List<HotelResponseDTO> getHotelByName(String name) {
        return hotelRepository.findByName(name).stream()
                .map(hotelMapper::toResponseDTO)
                .toList();
    }

    public HotelResponseDTO createHotel(HotelCreateDTO hotelDTO) {
        System.out.println("=== DTO Received ===");
        System.out.println("Name: " + hotelDTO.getName());
        System.out.println("City: " + hotelDTO.getCity());
        System.out.println("Country: " + hotelDTO.getCountry());
        // Resolve feature names to actual entities
        Set<ExtraFeature> resolvedFeatures = hotelDTO.getExtraFeatures().stream()
                .map(extraFeatureService::getOrCreateByName)
                .collect(Collectors.toSet());

        Set<Room> resolvedRooms = hotelDTO.getRooms().stream()
                .map(roomService::getOrCreateByName)
                .collect(Collectors.toSet());

        // Pass features to mapper
        Hotel hotel = hotelMapper.toEntity(hotelDTO, resolvedFeatures, resolvedRooms);
        Hotel saved = hotelRepository.save(hotel);
        return hotelMapper.toResponseDTO(saved);
    }

    @Transactional
    public HotelResponseDTO updateHotel(Long id, HotelResponseDTO hotelResponseDTO) {
        Hotel existingHotel = hotelRepository.findById(id.intValue())
                .orElseThrow(() -> new RuntimeException("Hotel not found"));

        existingHotel.setName(hotelResponseDTO.getName());
        existingHotel.setLocationType(hotelResponseDTO.getLocationType());
        Set<ExtraFeature> resolvedFeatures = hotelResponseDTO.getExtraFeature().stream()
                .map(extraFeatureService::getOrCreateByName)
                .collect(Collectors.toSet());

        Set<Room> resolvedRooms = hotelResponseDTO.getRoom().stream()
                .map(roomService::getOrCreateByName)
                .collect(Collectors.toSet());
        existingHotel.setExtraFeatures(resolvedFeatures);
        existingHotel.setRooms(resolvedRooms);
        existingHotel.setCountry(hotelResponseDTO.getCountry());
        existingHotel.setCity(hotelResponseDTO.getCity());

        Hotel updated = hotelRepository.save(existingHotel);
        return hotelMapper.toResponseDTO(updated);
    }

    @Transactional
    public void deleteHotel(Long id) {
        hotelRepository.deleteById(id.intValue());
    }

    public List<HotelResponseDTO> getRandomHotels(int count) {
        List<HotelResponseDTO> allHotels = getAllHotels();
        Collections.shuffle(allHotels);
        return allHotels.stream().limit(count).toList();
    }

    public List<HotelResponseDTO> getHotelsBySearch(Map<String, String> params) {
        String destination = params.getOrDefault("destination", "");
        // LocalDate checkin = LocalDate.parse(params.get("checkin"));
        // LocalDate checkout = LocalDate.parse(params.get("checkout"));
        // String rooms = params.get("rooms");

        return hotelRepository.findByCityIgnoreCaseOrCountryIgnoreCase(destination, destination).stream()
                .map(hotelMapper::toResponseDTO)
                .toList();

    }

    public HotelResponseDTO updateHotelVisibility(Long id, boolean visible) {
        Hotel hotel = hotelRepository.findById(id.intValue())
                .orElseThrow(() -> new RuntimeException("Hotel not found"));

        hotel.setHidden(!visible);
        hotelRepository.save(hotel);
        return hotelMapper.toResponseDTO(hotel);
    }

}