package IDATA2306.Group12.api;

import IDATA2306.Group12.dto.listing.ListingCreateDTO;
import IDATA2306.Group12.dto.listing.ListingResponseDTO;
import IDATA2306.Group12.service.ListingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/listings")
@Tag(name = "Listing Management", description = "APIs for managing listings.")
public class ListingController {

    private final ListingService listingService;

    public ListingController(ListingService listingService) {
        this.listingService = listingService;
    }

    @Operation(
            summary = "Create a new listing",
            description = "Create a new listing using a ListingCreateDTO."
    )
    @ApiResponse(responseCode = "201", description = "Listing created successfully.")
    @ApiResponse(responseCode = "400", description = "Invalid input.")
    @ApiResponse(responseCode = "403", description = "Not authorized to create a listing.")
    @PostMapping("/admin/createListing")
    public ResponseEntity<?> createListing(@Valid @RequestBody ListingCreateDTO listingCreateDTO) {
        try {
            ListingResponseDTO created = listingService.createListing(listingCreateDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(created);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // @PutMapping("/{id}")
    // public ResponseEntity<ListingResponseDTO> updateListing(@PathVariable int id,
    // @Valid @RequestBody ListingCreateDTO listingCreateDTO) {
    // ListingResponseDTO updated = listingService.updateListing(id,
    // listingCreateDTO);
    // return ResponseEntity.ok(updated);
    // }

    @Operation(
            summary = "Delete a listing",
            description = "Delete a listing using the listing ID."
    )
    @ApiResponse(responseCode = "204", description = "Listing deleted successfully.")
    @ApiResponse(responseCode = "403", description = "Not authorized to delete this listing.")
    @DeleteMapping("/admin/deleteListing/{id}")
    public ResponseEntity<String> deleteListing(@PathVariable int id) {
        listingService.deleteListing(id);
        return ResponseEntity.status(204).body("Listing deleted successfully.");
    }

    /**
     * Get all rooms by hotel id
     * 
     * @param hotelId
     * @return
     */
    @Operation(
            summary = "Search for listings using hotel ID.",
            description = "Returns a list of all listings for the specified hotel ID."
    )
    @ApiResponse(responseCode = "200", description = "List of listings matching the query.")
    @GetMapping("/public/hotel/{hotelId}")
    public ResponseEntity<List<ListingResponseDTO>> getListingsByHotelId(@PathVariable int hotelId) {
        List<ListingResponseDTO> listings = listingService.getListingsByHotelId(hotelId);
        return ResponseEntity.ok(listings);
    }

}
