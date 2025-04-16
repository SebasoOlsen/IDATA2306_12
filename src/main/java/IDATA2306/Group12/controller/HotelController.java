package IDATA2306.Group12.controller;

import IDATA2306.Group12.dto.hotel.HotelResponseDTO;
import IDATA2306.Group12.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hotels")
public class HotelController {

    @Autowired
    private HotelService hotelService;

    @GetMapping
    public List<HotelResponseDTO> getAllhotels(){
        try{
            return hotelService.getAllHotels();
        }catch(Exception e){
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }
    @GetMapping("/{id}")
    public HotelResponseDTO getHotelById(@PathVariable Long id){
        return hotelService.getHotelById(id);
    }
    @PostMapping
    public HotelResponseDTO createHotel(@RequestBody HotelResponseDTO hotel){
        return hotelService.createHotel(hotel);
    }
    @PutMapping("/{id}")
    public HotelResponseDTO updateHotel(@PathVariable Long id,@RequestBody HotelResponseDTO hotel){
        return hotelService.updateHotel(id, hotel);
    }
    @DeleteMapping("/{id}")
    public void deleteHotel(@PathVariable Long id){
        hotelService.deleteHotel(id);
    }
}