package IDATA2306.Group12.api;

import IDATA2306.Group12.dto.hotel.HotelResponseDTO;
import IDATA2306.Group12.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/hotels")
public class HotelController {

    @Autowired
    private HotelService hotelService;

    @GetMapping
    public ResponseEntity<List<HotelResponseDTO>> getAllHotels() {
        try {
            List<HotelResponseDTO> hotels = hotelService.getAllHotels();
            return ResponseEntity.ok(hotels);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<HotelResponseDTO> getHotelById(@PathVariable Long id) {
        try {
            HotelResponseDTO hotel = hotelService.getHotelById(id);
            return hotel != null ? ResponseEntity.ok(hotel) : ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping
    public ResponseEntity<HotelResponseDTO> createHotel(@RequestBody HotelResponseDTO hotel) {
        System.out.println("✅ RECEIVED: city=" + hotel.getCity() + ", country=" + hotel.getCountry());
        try {
            HotelResponseDTO created = hotelService.createHotel(hotel);
            return ResponseEntity.ok(created);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<HotelResponseDTO> updateHotel(@PathVariable Long id, @RequestBody HotelResponseDTO hotel) {
        try {
            HotelResponseDTO updated = hotelService.updateHotel(id, hotel);
            return updated != null ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHotel(@PathVariable Long id) {
        try {
            hotelService.deleteHotel(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/random")
    public ResponseEntity<List<HotelResponseDTO>> getRandomHotels(
            @RequestParam(name = "count", defaultValue = "3") int count) {
        try {
            List<HotelResponseDTO> allHotels = hotelService.getAllHotels();
            if (allHotels == null || allHotels.isEmpty()) {
                return ResponseEntity.ok(List.of());
            }
            List<HotelResponseDTO> modifiableList = new ArrayList<>(allHotels);
            Collections.shuffle(modifiableList);
            List<HotelResponseDTO> randomHotels = modifiableList.stream()
                    .limit(Math.min(count, modifiableList.size()))
                    .toList();
            return ResponseEntity.ok(randomHotels);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
