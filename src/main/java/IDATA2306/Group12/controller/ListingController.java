package IDATA2306.Group12.controller;

import IDATA2306.Group12.dto.ListingDTO;
import IDATA2306.Group12.service.ListingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/listings")
public class ListingController {

    @Autowired
    private ListingService listingService;

    @GetMapping
    public List<ListingDTO> getAlllistings(){
        try{
            return listingService.getAllListings();
        }catch(Exception e){
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }
    @GetMapping("/{id}")
    public ListingDTO getListingById(@PathVariable int id){
        return listingService.getListingById(id);
    }
    @PostMapping
    public ListingDTO createListing(@RequestBody ListingDTO listingDTO){
        return listingService.createListing(listingDTO);
    }
    @PutMapping("/{id}")
    public ListingDTO updateListing(@PathVariable int id, @RequestBody ListingDTO listingDTO){
        return listingService.updateListing(id, listingDTO);
    }
    @DeleteMapping("/{id}")
    public void deleteListing(@PathVariable int id){
        listingService.deleteListing(id);
    }
}
