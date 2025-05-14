package IDATA2306.Group12.service;

import IDATA2306.Group12.dto.hotel.HotelResponseDTO;
import IDATA2306.Group12.entity.Hotel;
import IDATA2306.Group12.mapper.HotelMapper;
import IDATA2306.Group12.repository.HotelRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Service
public class HotelService {

    private final HotelRepository hotelRepository;
    private final HotelMapper hotelMapper;

    public HotelService(HotelRepository hotelRepository, HotelMapper hotelMapper) {
        this.hotelRepository = hotelRepository;
        this.hotelMapper = hotelMapper;
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

    public HotelResponseDTO createHotel(HotelResponseDTO hotelDTO) {
        System.out.println("=== DTO Received ===");
        System.out.println("Name: " + hotelDTO.getName());
        System.out.println("City: " + hotelDTO.getCity());
        System.out.println("Country: " + hotelDTO.getCountry());
        Hotel hotel = hotelMapper.toEntity(hotelDTO);
        Hotel saved = hotelRepository.save(hotel);
        return hotelMapper.toResponseDTO(saved);
    }

    @Transactional
    public HotelResponseDTO updateHotel(Long id, HotelResponseDTO hotelResponseDTO) {
        Hotel existingHotel = hotelRepository.findById(id.intValue())
                .orElseThrow(() -> new RuntimeException("Hotel not found"));

        existingHotel.setName(hotelResponseDTO.getName());
        existingHotel.setLocationType(hotelResponseDTO.getLocationType());
        existingHotel.setRoomTypes(hotelResponseDTO.getRoomType());
        existingHotel.setExtraFeatures(hotelResponseDTO.getExtraFeature());
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

}
