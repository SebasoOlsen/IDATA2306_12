package IDATA2306.Group12.controller;

import IDATA2306.Group12.dto.listing.ListingCreateDTO;
import IDATA2306.Group12.dto.listing.ListingDTO;
import IDATA2306.Group12.service.ListingService;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/listings")
public class ListingController {

    private final ListingService listingService;

    public ListingController(ListingService listingService) {
        this.listingService = listingService;
    }

    @GetMapping
    public ResponseEntity<List<ListingDTO>> getAllListings() {
        return ResponseEntity.ok(listingService.getAllListings());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ListingDTO> getListingById(@PathVariable int id) {
        return ResponseEntity.ok(listingService.getListingById(id));
    }

    @PostMapping
    public ResponseEntity<ListingDTO> createListing(@Valid @RequestBody ListingCreateDTO listingCreateDTO) {
        ListingDTO created = listingService.createListing(listingCreateDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ListingDTO> updateListing(@PathVariable int id, @Valid @RequestBody ListingCreateDTO listingCreateDTO) {
        ListingDTO updated = listingService.updateListing(id, listingCreateDTO);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteListing(@PathVariable int id) {
        listingService.deleteListing(id);
        return ResponseEntity.noContent().build();
    }

//    @Autowired
//    private ListingService listingService;
//
//    @GetMapping
//    public List<ListingDTO> getAlllistings(){
//        try{
//            return listingService.getAllListings();
//        }catch(Exception e){
//            System.out.println("Error: " + e.getMessage());
//            return null;
//        }
//    }
//    @GetMapping("/{id}")
//    public ListingDTO getListingById(@PathVariable int id){
//        return listingService.getListingById(id);
//    }
//    @PostMapping
//    public ListingDTO createListing(@RequestBody ListingDTO listingDTO){
//        return listingService.createListing(listingDTO);
//    }
//    @PutMapping("/{id}")
//    public ListingDTO updateListing(@PathVariable int id, @RequestBody ListingDTO listingDTO){
//        return listingService.updateListing(id, listingDTO);
//    }
//    @DeleteMapping("/{id}")
//    public void deleteListing(@PathVariable int id){
//        listingService.deleteListing(id);
//    }
}
