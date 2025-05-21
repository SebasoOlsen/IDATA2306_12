// File: src/main/java/IDATA2306/Group12/service/ListingService.java
package IDATA2306.Group12.service;

import IDATA2306.Group12.dto.listing.ListingCreateDTO;
import IDATA2306.Group12.dto.listing.ListingResponseDTO;
import IDATA2306.Group12.entity.Listing;
import IDATA2306.Group12.entity.Provider;
import IDATA2306.Group12.entity.Rooms;
import IDATA2306.Group12.mapper.ListingMapper;
import IDATA2306.Group12.repository.ListingRepository;
import IDATA2306.Group12.repository.ProviderRepository;
import IDATA2306.Group12.repository.RoomsRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ListingService {

    private final ListingRepository listingRepository;
    private final ListingMapper listingMapper;
    private final ProviderRepository providerRepository;
    private final RoomsRepository roomsRepository;

    public ListingService(
            ListingRepository listingRepository,
            ListingMapper listingMapper,
            ProviderRepository providerRepository,
            RoomsRepository roomsRepository
    ) {
        this.listingRepository = listingRepository;
        this.listingMapper = listingMapper;
        this.providerRepository = providerRepository;
        this.roomsRepository = roomsRepository;
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
        Provider provider = providerRepository.findById(createDTO.getProviderId())
                .orElseThrow(() -> new RuntimeException("Provider not found"));

        Rooms rooms = roomsRepository.findById(createDTO.getRooms().getId())
                .orElseThrow(() -> new RuntimeException("Room not found"));

        Listing listing = listingMapper.toEntity(createDTO, provider, rooms);
        Listing saved = listingRepository.save(listing);

        return listingMapper.toResponseDTO(saved);
    }

    @Transactional
    public ListingResponseDTO updateListing(int id, ListingCreateDTO updateDTO) {
        Listing existingListing = listingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Listing not found"));

        Provider provider = providerRepository.findById(updateDTO.getProviderId())
                .orElseThrow(() -> new RuntimeException("Provider not found"));

        // Optionally update the room if provided (assuming updateDTO has room information)
        if (updateDTO.getRooms() != null) {
            Rooms rooms = roomsRepository.findById(updateDTO.getRooms().getId())
                    .orElseThrow(() -> new RuntimeException("Room not found"));
            existingListing.setRooms(rooms);
        }
        existingListing.setProvider(provider);
        existingListing.setPrice(updateDTO.getPrice());
        existingListing.setCurrency(updateDTO.getCurrency());
        existingListing.setLink(updateDTO.getLink());

        return listingMapper.toResponseDTO(existingListing);
    }

    @Transactional
    public void deleteListing(int id) {
        listingRepository.deleteById(id);
    }
}