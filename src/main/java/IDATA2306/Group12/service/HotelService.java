package IDATA2306.Group12.service;
import IDATA2306.Group12.dto.hotel.HotelResponseDTO;
import IDATA2306.Group12.entity.Hotel;
import IDATA2306.Group12.mapper.HotelMapper;
import IDATA2306.Group12.repository.HotelRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        Hotel hotel = hotelMapper.toEntity(hotelDTO);
        Hotel saved = hotelRepository.save(hotel);
        return hotelMapper.toResponseDTO(saved);
    }

    @Transactional
    public HotelResponseDTO updateHotel(Long id, HotelResponseDTO hotelResponseDTO) {
        Hotel existingHotel = hotelRepository.findById(id.intValue())
                .orElseThrow(() -> new RuntimeException("Hotel not found"));

        existingHotel.setName(hotelResponseDTO.getName());
        existingHotel.setLocationType(hotelResponseDTO.getLocationTypes());
        existingHotel.setRoomTypes(hotelResponseDTO.getRoomTypes());
        existingHotel.setExtraFeatures(hotelResponseDTO.getExtraFeatures());
        existingHotel.setCountry(hotelResponseDTO.getCountry());
        existingHotel.setCity(hotelResponseDTO.getCity());

        Hotel updated = hotelRepository.save(existingHotel);
        return hotelMapper.toResponseDTO(updated);
    }

    @Transactional
    public void deleteHotel(Long id) {
        hotelRepository.deleteById(id.intValue());
    }




    //@Autowired
    //private HotelRepository hotelRepository;
//
    //public List<HotelDTO> getAllHotels(){
    //    return hotelRepository.findAll().stream()
    //            .map(hotel -> HotelMapper.toDTO(hotel)).toList();
    //}
    //public HotelDTO getHotelById(Long id){
    //    return HotelMapper.toDTO(hotelRepository.findById(id.intValue()).orElse(null));
    //}
    //public List<HotelDTO> getHotelByName(String name){
    //    return hotelRepository.findByName(name).stream()
    //            .map(hotel -> HotelMapper.toDTO(hotel)).toList();
    //}
    //public HotelDTO createHotel(HotelDTO hotel){
    //    hotelRepository.save(HotelMapper.toEntity(hotel));
    //    return hotel;
    //}
//
    //@Transactional
    //public HotelDTO updateHotel(Long id, HotelDTO hotel){
    //    Hotel existingHotel = hotelRepository.findById(id.intValue()).orElseThrow(()
    //            -> new RuntimeException("Hotel not found"));
    //            existingHotel.setId(hotel.getId());
    //            existingHotel.setName(hotel.getName());
    //            existingHotel.setLocationType(hotel.getLocationType());
    //            existingHotel.setRoomTypes(hotel.getRoomTypes());
    //            existingHotel.setExtraFeatures(hotel.getExtraFeatures());
    //            hotelRepository.save(existingHotel);
    //            return HotelMapper.toDTO(existingHotel);
    //}
    //@Transactional
    //public void deleteHotel(Long id){
    //    hotelRepository.deleteById(id.intValue());
    //}
}
