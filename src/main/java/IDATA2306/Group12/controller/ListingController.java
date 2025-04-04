package IDATA2306.Group12.controller;

import IDATA2306.Group12.entity.Listings;
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
    public List<Listings> getAlllistings(){
        try{
            return listingService.getAllListings();
        }catch(Exception e){
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }
    @GetMapping("/{id}")
    public Listings getListingById(@PathVariable int id){
        return listingService.getListingById(id);
    }
    @PostMapping
    public Listings createListing(@RequestBody Listings listings){
        return listingService.createListing(listings);
    }
    @PutMapping("/{id}")
    public Listings updateListing(@PathVariable int id, @RequestBody Listings listings){
        return listingService.updateListing(id, listings);
    }
    @DeleteMapping("/{id}")
    public void deleteListing(@PathVariable int id){
        listingService.deleteListing(id);
    }
}
