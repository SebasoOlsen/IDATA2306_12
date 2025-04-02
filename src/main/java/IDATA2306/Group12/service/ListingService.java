package IDATA2306.Group12.service;

import IDATA2306.Group12.entity.Hotel;
import IDATA2306.Group12.entity.Listing;
import IDATA2306.Group12.repository.ListingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ListingService {

    @Autowired
    private ListingRepository listingRepository;

    public List<Listing> getAllListings() {
        return listingRepository.findAll();
    }
    public Listing getListingById(int id){
        return listingRepository.findById(id).orElse(null);
    }
//    public Listing getListingByHotel(Hotel hotel){
//        return listingRepository.findByHotel(hotel);
//    }
    public Listing createListing(Listing listing) {
        return listingRepository.save(listing);
    }
//    public Listing findListingByName(String name){
//        return listingRepository.findByName(name);
//    }
    @Transactional
    public Listing updateListing(int id, Listing updatedListing) {
        Listing existingListing = listingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Listing not found"));
        // Update fields except the id
        existingListing.setPID(updatedListing.getPID());
        existingListing.setHID(updatedListing.getHID());
        existingListing.setPrice(updatedListing.getPrice());
        existingListing.setCurrency(updatedListing.getCurrency());
        existingListing.setLink(updatedListing.getLink());
        return listingRepository.save(existingListing);
    }
    @Transactional
    public void deleteListing(int id) {
        listingRepository.deleteById(id);
    }
}