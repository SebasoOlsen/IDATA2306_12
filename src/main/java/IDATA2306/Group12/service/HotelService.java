package IDATA2306.Group12.service;
import IDATA2306.Group12.dto.HotelDTO;
import IDATA2306.Group12.entity.Hotel;
import IDATA2306.Group12.mapper.HotelMapper;
import IDATA2306.Group12.repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class HotelService {

    @Autowired
    private HotelRepository hotelRepository;

    public List<HotelDTO> getAllHotels(){
        return hotelRepository.findAll().stream()
                .map(hotel -> HotelMapper.toDTO(hotel)).toList();
    }
    public HotelDTO getHotelById(Long id){
        return HotelMapper.toDTO(hotelRepository.findById(id.intValue()).orElse(null));
    }
    public List<HotelDTO> getHotelByName(String name){
        return hotelRepository.findByName(name).stream()
                .map(hotel -> HotelMapper.toDTO(hotel)).toList();
    }
    public HotelDTO createHotel(HotelDTO hotel){
        hotelRepository.save(HotelMapper.toEntity(hotel));
        return hotel;
    }

    @Transactional
    public HotelDTO updateHotel(Long id, HotelDTO hotel){
        Hotel existingHotel = hotelRepository.findById(id.intValue()).orElseThrow(()
                -> new RuntimeException("Hotel not found"));
                existingHotel.setId(hotel.getId());
                existingHotel.setName(hotel.getName());
                existingHotel.setLocationType(hotel.getLocationType());
                existingHotel.setRoomTypes(hotel.getRoomTypes());
                existingHotel.setExtraFeatures(hotel.getExtraFeatures());
                hotelRepository.save(existingHotel);
                return HotelMapper.toDTO(existingHotel);
    }
    @Transactional
    public void deleteHotel(Long id){
        hotelRepository.deleteById(id.intValue());
    }
}
