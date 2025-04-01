package IDATA2306.Group12.service;
import IDATA2306.Group12.entity.Hotel;
import IDATA2306.Group12.repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class HotelService {

    @Autowired
    private HotelRepository hotelRepository;

    public List<Hotel> getAllHotels(){
        return StreamSupport.stream(hotelRepository.findAll().spliterator(),false)
                .collect(Collectors.toList());
    }
    public Hotel getHotelById(Long id){
        return hotelRepository.findById(id.intValue()).orElse(null);
    }
    public List<Hotel> getHotelByName(String name){
        return hotelRepository.findByName(name);
    }
    public Hotel createHotel(Hotel hotel){
        return hotelRepository.save(hotel);
    }
    @Transactional
    public Hotel updateHotel(Long id, Hotel hotel){
        Hotel existingHotel = hotelRepository.findById(id.intValue()).orElseThrow(()
                -> new RuntimeException("Hotel not found"));
                existingHotel.setId(hotel.getId());
                existingHotel.setName(hotel.getName());
                existingHotel.setLocationType(hotel.getLocationType());
                existingHotel.setRoomTypes(hotel.getRoomTypes());
                existingHotel.setExtraFeatures(hotel.getExtraFeatures());
                return hotelRepository.save(existingHotel);
    }
    @Transactional
    public void deleteUser(Long id){
        hotelRepository.deleteById(id.intValue());
    }
}
