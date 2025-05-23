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

/**
 * Service class for managing hotels.
 * Handles creation, retrieval, updating, deletion, and search operations for hotels.
 */
@Service
public class HotelService {
    private final HotelRepository hotelRepository;
    private final HotelMapper hotelMapper;
    private final ExtraFeatureService extraFeatureService;
    private final RoomService roomService;

    /**
     * Constructs a HotelService with required dependencies.
     *
     * @param hotelRepository the hotel repository
     * @param hotelMapper the hotel mapper
     * @param extraFeatureService the extra feature service
     * @param roomService the room service
     */
    public HotelService(HotelRepository hotelRepository, HotelMapper hotelMapper,
            ExtraFeatureService extraFeatureService, RoomService roomService) {
        this.hotelRepository = hotelRepository;
        this.hotelMapper = hotelMapper;
        this.extraFeatureService = extraFeatureService;
        this.roomService = roomService;
    }

    /**
     * Retrieves all hotels.
     *
     * @return a list of HotelResponseDTOs
     */
    public List<HotelResponseDTO> getAllHotels() {
        List<Hotel> hotels = hotelRepository.findAll(); // TODO verify this doesn't return null

        return hotelRepository.findAll().stream()
                .map(hotelMapper::toResponseDTO)
                .toList();
    }

    /**
     * Retrieves all visible (not hidden) hotels.
     *
     * @return a list of HotelResponseDTOs for visible hotels
     */
    public List<HotelResponseDTO> getAllVisibleHotels() {
        List<Hotel> hotels = hotelRepository.findAllByIsHiddenFalse();
        return hotels.stream()
                .map(hotelMapper::toResponseDTO)
                .toList();
    }

    /**
     * Retrieves a hotel by its ID.
     *
     * @param id the hotel ID
     * @return the HotelResponseDTO
     * @throws RuntimeException if the hotel is not found
     */
    public HotelResponseDTO getHotelById(Long id) {
        Hotel hotel = hotelRepository.findById(id.intValue())
                .orElseThrow(() -> new RuntimeException("Hotel not found"));
        return hotelMapper.toResponseDTO(hotel);
    }

    /**
     * Retrieves hotels by name.
     *
     * @param name the hotel name
     * @return a list of HotelResponseDTOs matching the name
     */
    public List<HotelResponseDTO> getHotelByName(String name) {
        return hotelRepository.findByName(name).stream()
                .map(hotelMapper::toResponseDTO)
                .toList();
    }

    /**
     * Creates a new hotel.
     *
     * @param hotelDTO the hotel creation DTO
     * @return the created HotelResponseDTO
     */
    public HotelResponseDTO createHotel(HotelCreateDTO hotelDTO) {
        Set<ExtraFeature> resolvedFeatures = hotelDTO.getExtraFeatures().stream()
                .map(extraFeatureService::getOrCreateByName)
                .collect(Collectors.toSet());

        Set<Room> resolvedRooms = hotelDTO.getRooms().stream()
                .map(roomService::getOrCreateByName)
                .collect(Collectors.toSet());

        Hotel hotel = hotelMapper.toEntity(hotelDTO, resolvedFeatures, resolvedRooms);
        Hotel saved = hotelRepository.save(hotel);
        return hotelMapper.toResponseDTO(saved);
    }

    /**
     * Updates an existing hotel.
     *
     * @param id the hotel ID
     * @param hotelResponseDTO the hotel response DTO with updated data
     * @return the updated HotelResponseDTO
     * @throws RuntimeException if the hotel is not found
     */
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

    /**
     * Deletes a hotel by its ID.
     *
     * @param id the hotel ID
     */
    @Transactional
    public void deleteHotel(Long id) {
        hotelRepository.deleteById(id.intValue());
    }

    public List<HotelResponseDTO> getRandomHotels(int count) {
        List<HotelResponseDTO> allHotels = getAllHotels();
        Collections.shuffle(allHotels);
        return allHotels.stream().limit(count).toList();
    }

    /**
     * Retrieves a random selection of hotels.
     *
     * @param count the number of hotels to return
     * @return a list of randomly selected HotelResponseDTOs
     */
    public List<HotelResponseDTO> getHotelsBySearch(Map<String, String> params) {
        String destination = params.getOrDefault("destination", "");
        // LocalDate checkin = LocalDate.parse(params.get("checkin"));
        // LocalDate checkout = LocalDate.parse(params.get("checkout"));
        // String rooms = params.get("rooms");

        return hotelRepository.findByCityIgnoreCaseOrCountryIgnoreCase(destination, destination).stream()
                .map(hotelMapper::toResponseDTO)
                .toList();

    }

    /**
     * Updates the visibility (hidden status) of a hotel.
     *
     * @param id the hotel ID
     * @param visible true to make the hotel visible, false to hide it
     * @return the updated HotelResponseDTO
     * @throws RuntimeException if the hotel is not found
     */
    public HotelResponseDTO updateHotelVisibility(Long id, boolean visible) {
        Hotel hotel = hotelRepository.findById(id.intValue())
                .orElseThrow(() -> new RuntimeException("Hotel not found"));

        hotel.setHidden(!visible);
        hotelRepository.save(hotel);
        return hotelMapper.toResponseDTO(hotel);
    }

}