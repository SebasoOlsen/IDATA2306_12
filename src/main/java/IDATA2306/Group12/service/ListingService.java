package IDATA2306.Group12.service;

import IDATA2306.Group12.entity.Listings;
import IDATA2306.Group12.repository.ListingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ListingService {

    @Autowired
    private ListingRepository listingRepository;

    public List<Listings> getAllListings() {
        return listingRepository.findAll();
    }
    public Listings getListingById(int id){
        return listingRepository.findById(id).orElse(null);
    }
//    public Listings getListingByHotel(Hotel hotel){
//        return listingRepository.findByHotel(hotel);
//    }
    public Listings createListing(Listings listings) {
        return listingRepository.save(listings);
    }
//    public Listings findListingByName(String name){
//        return listingRepository.findByName(name);
//    }
    @Transactional
    public Listings updateListing(int id, Listings updatedListings) {
        Listings existingListings = listingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Listings not found"));
        // Update fields except the id
        existingListings.setPID(updatedListings.getPID());
        existingListings.setHID(updatedListings.getHID());
        existingListings.setPrice(updatedListings.getPrice());
        existingListings.setCurrency(updatedListings.getCurrency());
        existingListings.setLink(updatedListings.getLink());
        return listingRepository.save(existingListings);
    }
    @Transactional
    public void deleteListing(int id) {
        listingRepository.deleteById(id);
    }
}