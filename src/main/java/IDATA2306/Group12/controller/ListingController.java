package IDATA2306.Group12.controller;

import IDATA2306.Group12.entity.Listing;
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
    public List<Listing> getAlllistings(){
        try{
            return listingService.getAllListings();
        }catch(Exception e){
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }
    @GetMapping("/{id}")
    public Listing getListingById(@PathVariable int id){
        return listingService.getListingById(id);
    }
    @PostMapping("/{id}")
    public Listing createListing(@RequestBody Listing listing){
        return listingService.createListing(listing);
    }
    @PutMapping("/{id}")
    public Listing updateListing(@PathVariable int id,@RequestBody Listing listing){
        return listingService.updateListing(id, listing);
    }
    @DeleteMapping("/{id}")
    public void deleteListing(@PathVariable int id){
        listingService.deleteListing(id);
    }
}
