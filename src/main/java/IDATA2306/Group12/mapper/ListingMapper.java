package IDATA2306.Group12.mapper;

import IDATA2306.Group12.entity.Rooms;
import org.springframework.stereotype.Component;

import IDATA2306.Group12.dto.listing.ListingCreateDTO;
import IDATA2306.Group12.dto.listing.ListingResponseDTO;
import IDATA2306.Group12.entity.Hotel;
import IDATA2306.Group12.entity.Listing;
import IDATA2306.Group12.entity.Provider;

/**
 * Mapper class for converting between Listings entity and ListingDTO.
 */
@Component
public class ListingMapper {

    private RoomsMapper roomsMapper;
    private ProviderMapper providerMapper;

    public ListingMapper (RoomsMapper roomsMapper, ProviderMapper providerMapper) {
        this.roomsMapper = roomsMapper;
        this.providerMapper = providerMapper;
    }

    public Listing toEntity(ListingCreateDTO dto, Provider provider, Rooms rooms) {
        Listing listing = new Listing();
        listing.setProvider(provider);
        listing.setPrice(dto.getPrice());
        listing.setCurrency(dto.getCurrency());
        listing.setLink(dto.getLink());
        listing.setRooms(rooms);
        return listing;
    }

    public ListingResponseDTO toResponseDTO(Listing listing) {
        ListingResponseDTO dto = new ListingResponseDTO();
        dto.setId(listing.getId());
        dto.setPrice(listing.getPrice());
        dto.setCurrency(listing.getCurrency());
        dto.setLink(listing.getLink());
        dto.setRooms(roomsMapper.toResponseDTO(listing.getRooms()));
        dto.setProvider(providerMapper.toResponseDTO(listing.getProvider()));
        return dto;
    }
}
