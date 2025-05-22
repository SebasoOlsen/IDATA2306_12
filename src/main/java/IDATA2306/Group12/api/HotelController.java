package IDATA2306.Group12.api;

import IDATA2306.Group12.dto.hotel.HotelCreateDTO;
import IDATA2306.Group12.dto.hotel.HotelResponseDTO;
import IDATA2306.Group12.dto.room.RoomResponseDTO;
import IDATA2306.Group12.service.HotelService;
import IDATA2306.Group12.service.RoomService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/hotels")
@Tag(name = "Hotel Management", description = "APIs for managing hotels.")
public class HotelController {

    private final HotelService hotelService;
    private final RoomService roomService;

    public HotelController(HotelService hotelService, RoomService roomService) {
        this.hotelService = hotelService;
        this.roomService = roomService;
    }

    @Operation(summary = "Get all hotels", description = "Get a list of all hotels")
    @ApiResponse(responseCode = "200", description = "List of all hotels.")
    @ApiResponse(responseCode = "500", description = "Internal server error.")
    @ApiResponse(responseCode = "403", description = "Not authorized to view this list of hotels.")
    @GetMapping("/admin/allHotels")
    public ResponseEntity<List<HotelResponseDTO>> getAllHotels() {
        try {
            List<HotelResponseDTO> hotels = hotelService.getAllHotels();
            return ResponseEntity.ok(hotels);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }

    @Operation(summary = "Search for a hotel", description = "Search for a hotel using the hotel ID.")
    @GetMapping("public/searchById/{id}")
    @ApiResponse(responseCode = "200", description = "Hotel with the matching ID.")
    @ApiResponse(responseCode = "404", description = "Hotel not found.")
    @ApiResponse(responseCode = "500", description = "Internal server error.")
    public ResponseEntity<HotelResponseDTO> getHotelById(@PathVariable Long id) {
        try {
            HotelResponseDTO hotel = hotelService.getHotelById(id);
            return hotel != null ? ResponseEntity.ok(hotel) : ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @Operation(summary = "Create a new hotel", description = "Create a new hotel using a HotelCreateDTO.")
    @ApiResponse(responseCode = "201", description = "Hotel created successfully.")
    @ApiResponse(responseCode = "400", description = "Invalid input.")
    @ApiResponse(responseCode = "403", description = "Not authorized to create a hotel.")
    @PostMapping("/admin/createHotel")
    public ResponseEntity<HotelResponseDTO> createHotel(@RequestBody HotelCreateDTO hotel) {
        try {
            HotelResponseDTO created = hotelService.createHotel(hotel);
            return ResponseEntity.ok(created);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @Operation(summary = "Update a hotel", description = "Update a hotel using a HotelCreateDTO.")
    @ApiResponse(responseCode = "200", description = "Hotel updated successfully.")
    @ApiResponse(responseCode = "404", description = "Hotel not found.")
    @ApiResponse(responseCode = "400", description = "Invalid input.")
    @ApiResponse(responseCode = "403", description = "Not authorized to update this hotel.")
    @PutMapping("/admin/updateHotel/{id}")
    public ResponseEntity<HotelResponseDTO> updateHotel(@PathVariable Long id, @RequestBody HotelResponseDTO hotel) {
        try {
            HotelResponseDTO updated = hotelService.updateHotel(id, hotel);
            return updated != null ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @Operation(summary = "Get a list of random hotels", description = "Get a list of random hotels.")
    @ApiResponse(responseCode = "200", description = "List of random hotels.")
    @ApiResponse(responseCode = "500", description = "Internal server error.")
    @GetMapping("/public/randomHotels")
    public ResponseEntity<List<HotelResponseDTO>> getRandomHotels(
            @RequestParam(name = "count", defaultValue = "3") int count) {
        try {
            List<HotelResponseDTO> allHotels = hotelService.getAllVisibleHotels();
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

    @Operation(summary = "Get hotels by search", description = "Get hotels by search using the search parameters.")
    @ApiResponse(responseCode = "200", description = "List of hotels matching the search parameters.")
    @ApiResponse(responseCode = "500", description = "Internal server error.")
    @ApiResponse(responseCode = "404", description = "Hotel not found.")
    @GetMapping("/public/search")
    public ResponseEntity<List<HotelResponseDTO>> getHotelsBySearch(@RequestParam Map<String, String> params) {
        try {
            List<HotelResponseDTO> searchResults = hotelService.getHotelsBySearch(params);
            if (searchResults == null || searchResults.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(searchResults);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @Operation(summary = "Get a list of rooms by hotel ID", description = "Get a list of rooms by hotel ID.")
    @ApiResponse(responseCode = "200", description = "List of rooms for the hotel.")
    @ApiResponse(responseCode = "500", description = "Internal server error.")
    @ApiResponse(responseCode = "404", description = "Hotel not found.")
    @GetMapping("/public/{id}/rooms")
    public ResponseEntity<List<RoomResponseDTO>> getRoomsByHotelId(@PathVariable Integer id) {
        try {
            List<RoomResponseDTO> rooms = roomService.getRoomsByHotelId(id);
            return ResponseEntity.ok(rooms);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @Operation(summary = "Update hotel visibility", description = "Update the visibility of a hotel.")
    @ApiResponse(responseCode = "200", description = "Hotel visibility updated successfully.")
    @ApiResponse(responseCode = "404", description = "Hotel not found.")
    @ApiResponse(responseCode = "400", description = "Invalid input.")
    @ApiResponse(responseCode = "403", description = "Not authorized to update this hotel.")
    @PostMapping("/admin/updateHotelVisibility/{id}")
    public ResponseEntity<HotelResponseDTO> updateHotelVisibility(@PathVariable Long id,
            @RequestParam boolean visible) {
        try {
            HotelResponseDTO updated = hotelService.updateHotelVisibility(id, visible);
            return updated != null ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
