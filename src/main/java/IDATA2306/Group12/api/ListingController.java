package IDATA2306.Group12.api;

import IDATA2306.Group12.dto.listing.ListingCreateDTO;
import IDATA2306.Group12.dto.listing.ListingResponseDTO;
import IDATA2306.Group12.service.ListingService;
import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/listings")
public class ListingController {

    private final ListingService listingService;

    public ListingController(ListingService listingService) {
        this.listingService = listingService;
    }

    @GetMapping
    public ResponseEntity<List<ListingResponseDTO>> getAllListings() {
        return ResponseEntity.ok(listingService.getAllListings());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ListingResponseDTO> getListingById(@PathVariable int id) {
        return ResponseEntity.ok(listingService.getListingById(id));
    }

    @PostMapping
    public ResponseEntity<ListingResponseDTO> createListing(@Valid @RequestBody ListingCreateDTO listingCreateDTO) {
        ListingResponseDTO created = listingService.createListing(listingCreateDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    // @PutMapping("/{id}")
    // public ResponseEntity<ListingResponseDTO> updateListing(@PathVariable int id,
    // @Valid @RequestBody ListingCreateDTO listingCreateDTO) {
    // ListingResponseDTO updated = listingService.updateListing(id,
    // listingCreateDTO);
    // return ResponseEntity.ok(updated);
    // }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteListing(@PathVariable int id) {
        listingService.deleteListing(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Get all rooms by hotel id
     * 
     * @param hotelId
     * @return
     */
    @GetMapping("/public/hotel/{hotelId}")
    public ResponseEntity<List<ListingResponseDTO>> getListingsByHotelId(@PathVariable int hotelId) {
        List<ListingResponseDTO> listings = listingService.getListingsByHotelId(hotelId);
        return ResponseEntity.ok(listings);
    }

}
