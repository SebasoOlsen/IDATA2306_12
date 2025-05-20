package IDATA2306.Group12.service;

import IDATA2306.Group12.dto.listing.ListingCreateDTO;
import IDATA2306.Group12.dto.listing.ListingResponseDTO;
import IDATA2306.Group12.entity.Hotel;
import IDATA2306.Group12.entity.Listing;
import IDATA2306.Group12.entity.Provider;
import IDATA2306.Group12.entity.Room;
import IDATA2306.Group12.mapper.ListingMapper;
import IDATA2306.Group12.repository.HotelRepository;
import IDATA2306.Group12.repository.ListingRepository;
import IDATA2306.Group12.repository.ProviderRepository;
import IDATA2306.Group12.repository.RoomRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ListingService {

    private final ListingRepository listingRepository;
    private final ListingMapper listingMapper;
    private final HotelRepository hotelRepository;
    private final ProviderRepository providerRepository;
    private final RoomRepository roomRepository;

    public ListingService(
            ListingRepository listingRepository,
            ListingMapper listingMapper,
            HotelRepository hotelRepository,
            ProviderRepository providerRepository,
            RoomRepository roomRepository) {
        this.listingRepository = listingRepository;
        this.listingMapper = listingMapper;
        this.hotelRepository = hotelRepository;
        this.providerRepository = providerRepository;
        this.roomRepository = roomRepository;
    }

    public List<ListingResponseDTO> getAllListings() {
        return listingRepository.findAll().stream()
                .map(listingMapper::toResponseDTO)
                .toList();
    }

    public ListingResponseDTO getListingById(int id) {
        Listing listing = listingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Listing not found"));
        return listingMapper.toResponseDTO(listing);
    }

    public ListingResponseDTO createListing(ListingCreateDTO createDTO) {
        Hotel hotel = hotelRepository.findById(createDTO.getHotelId())
                .orElseThrow(() -> new RuntimeException("Hotel not found"));
        Provider provider = providerRepository.findById(createDTO.getProviderId())
                .orElseThrow(() -> new RuntimeException("Provider not found"));
        Room room = roomRepository.findById(createDTO.getRoomId())
                .orElseThrow(() -> new RuntimeException("Room not found"));

        Listing listing = listingMapper.toEntity(createDTO, hotel, room, provider);
        Listing saved = listingRepository.save(listing);

        return listingMapper.toResponseDTO(saved);
    }

    // @Transactional
    // public ListingResponseDTO updateListing(int id, ListingCreateDTO updateDTO) {
    // Listing existingListing = listingRepository.findById(id)
    // .orElseThrow(() -> new RuntimeException("Listing not found"));
    //
    // Hotel hotel = hotelRepository.findById(updateDTO.getHotel().getId())
    // .orElseThrow(() -> new RuntimeException("Hotel not found"));
    // Provider provider = providerRepository.findById(updateDTO.getProviderId())
    // .orElseThrow(() -> new RuntimeException("Provider not found"));
    //
    // // Update the fields
    // existingListing.setProvider(provider);
    // existingListing.setPrice(updateDTO.getPrice());
    // existingListing.setCurrency(updateDTO.getCurrency());
    // existingListing.setLink(updateDTO.getLink());
    //
    // return listingMapper.toResponseDTO(existingListing);
    // }

    @Transactional
    public void deleteListing(int id) {
        listingRepository.deleteById(id);
    }

    /**
     * Get all listings by a specific hotel id
     *
     * @param hotelId the id of the hotel
     * @return a list of ListingResponseDTO
     */
    public List<ListingResponseDTO> getListingsByHotelId(int hotelId) {
        return listingRepository.findByHotel_Id(hotelId).stream()
                .map(listingMapper::toResponseDTO)
                .toList();
    }
}