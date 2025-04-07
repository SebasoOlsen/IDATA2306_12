package IDATA2306.Group12.service;

import IDATA2306.Group12.dto.ListingDTO;
import IDATA2306.Group12.entity.Listing;
import IDATA2306.Group12.mapper.ListingMapper;
import IDATA2306.Group12.repository.ListingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ListingService {

    @Autowired
    private ListingRepository listingRepository;

    public List<ListingDTO> getAllListings() {
        return listingRepository.findAll().stream()
            .map(listing -> ListingMapper.toDTO(listing)).toList();
    }
    public ListingDTO getListingById(int id){
        return ListingMapper.toDTO(listingRepository.findById(id).orElse(null));
    }
//    public Listings getListingByHotel(Hotel hotel){
//        return listingRepository.findByHotel(hotel);
//    }
    public ListingDTO createListing(ListingDTO listingDTO) {
        listingRepository.save(ListingMapper.toEntity(listingDTO));
        return listingDTO;
    }
//    public Listings findListingByName(String name){
//        return listingRepository.findByName(name);
//    }
    @Transactional
    public ListingDTO updateListing(int id, ListingDTO updatedListingsDTO) {
        Listing updatedListings = ListingMapper.toEntity(updatedListingsDTO);
        Listing existingListings = listingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Listings not found"));
        // Update fields except the id
        existingListings.setPID(updatedListings.getPID());
        existingListings.setHID(updatedListings.getHID());
        existingListings.setPrice(updatedListings.getPrice());
        existingListings.setCurrency(updatedListings.getCurrency());
        existingListings.setLink(updatedListings.getLink());
        listingRepository.save(existingListings);
        return ListingMapper.toDTO(existingListings);
    }
    @Transactional
    public void deleteListing(int id) {
        listingRepository.deleteById(id);
    }
}