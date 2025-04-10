package IDATA2306.Group12.service;

import IDATA2306.Group12.dto.listing.ListingCreateDTO;
import IDATA2306.Group12.dto.listing.ListingDTO;
import IDATA2306.Group12.entity.Hotel;
import IDATA2306.Group12.entity.Listing;
import IDATA2306.Group12.entity.Provider;
import IDATA2306.Group12.mapper.ListingMapper;
import IDATA2306.Group12.repository.HotelRepository;
import IDATA2306.Group12.repository.ListingRepository;
import IDATA2306.Group12.repository.ProviderRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ListingService {


    private final ListingRepository listingRepository;
    private final ListingMapper listingMapper;
    private final HotelRepository hotelRepository;
    private final ProviderRepository providerRepository;

    public ListingService(
        ListingRepository listingRepository,
        ListingMapper listingMapper,
        HotelRepository hotelRepository,
        ProviderRepository providerRepository
    ) {
        this.listingRepository = listingRepository;
        this.listingMapper = listingMapper;
        this.hotelRepository = hotelRepository;
        this.providerRepository = providerRepository;
    }

    public List<ListingDTO> getAllListings() {
        return listingRepository.findAll().stream()
                .map(listingMapper::toDTO)
                .toList();
    }

    public ListingDTO getListingById(int id) {
        Listing listing = listingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Listing not found"));
        return listingMapper.toDTO(listing);
    }

    public ListingDTO createListing(ListingCreateDTO createDTO) {
        Hotel hotel = hotelRepository.findById(createDTO.getHotelId())
                .orElseThrow(() -> new RuntimeException("Hotel not found"));
        Provider provider = providerRepository.findById(createDTO.getProviderId())
                .orElseThrow(() -> new RuntimeException("Provider not found"));

        Listing listing = listingMapper.toEntity(createDTO, hotel, provider);
        Listing saved = listingRepository.save(listing);

        return listingMapper.toDTO(saved);
    }

    @Transactional
    public ListingDTO updateListing(int id, ListingCreateDTO updateDTO) {
        Listing existingListing = listingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Listing not found"));

        Hotel hotel = hotelRepository.findById(updateDTO.getHotelId())
                .orElseThrow(() -> new RuntimeException("Hotel not found"));
        Provider provider = providerRepository.findById(updateDTO.getProviderId())
                .orElseThrow(() -> new RuntimeException("Provider not found"));

        // Update the fields
        existingListing.setHotel(hotel);
        existingListing.setProvider(provider);
        existingListing.setPrice(updateDTO.getPrice());
        existingListing.setCurrency(updateDTO.getCurrency());
        existingListing.setLink(updateDTO.getLink());

        return listingMapper.toDTO(existingListing);
    }

    @Transactional
    public void deleteListing(int id) {
        listingRepository.deleteById(id);
    }


//    @Autowired
//    private ListingRepository listingRepository;
//
//    public List<ListingDTO> getAllListings() {
//        return listingRepository.findAll().stream()
//            .map(listing -> ListingMapper.toDTO(listing)).toList();
//    }
//    public ListingDTO getListingById(int id){
//        return ListingMapper.toDTO(listingRepository.findById(id).orElse(null));
//    }
////    public Listings getListingByHotel(Hotel hotel){
////        return listingRepository.findByHotel(hotel);
////    }
//    public ListingDTO createListing(ListingDTO listingDTO) {
//        listingRepository.save(ListingMapper.toEntity(listingDTO));
//        return listingDTO;
//    }
////    public Listings findListingByName(String name){
////        return listingRepository.findByName(name);
////    }
//    @Transactional
//    public ListingDTO updateListing(int id, ListingDTO updatedListingsDTO) {
//        Listing updatedListings = ListingMapper.toEntity(updatedListingsDTO);
//        Listing existingListings = listingRepository.findById(id)
//                .orElseThrow(() -> new RuntimeException("Listings not found"));
//        // Update fields except the id
//        existingListings.setPID(updatedListings.getPID());
//        existingListings.setHID(updatedListings.getHID());
//        existingListings.setPrice(updatedListings.getPrice());
//        existingListings.setCurrency(updatedListings.getCurrency());
//        existingListings.setLink(updatedListings.getLink());
//        listingRepository.save(existingListings);
//        return ListingMapper.toDTO(existingListings);
//    }
//    @Transactional
//    public void deleteListing(int id) {
//        listingRepository.deleteById(id);
//    }
}