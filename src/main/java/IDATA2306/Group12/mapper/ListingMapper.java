package IDATA2306.Group12.mapper;

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
    private HotelMapper hotelMapper;

    public ListingMapper (RoomsMapper roomsMapper, ProviderMapper providerMapper, HotelMapper hotelMapper) {
        this.roomsMapper = roomsMapper;
        this.providerMapper = providerMapper;
        this.hotelMapper = hotelMapper;
    }

    public Listing toEntity(ListingCreateDTO dto, Hotel hotel, Provider provider) {
        Listing listing = new Listing();
        listing.setProvider(provider);
        listing.setPrice(dto.getPrice());
        listing.setCurrency(dto.getCurrency());
        listing.setLink(dto.getLink());
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
