package IDATA2306.Group12.mapper;

import org.springframework.stereotype.Component;

import IDATA2306.Group12.dto.listing.ListingCreateDTO;
import IDATA2306.Group12.dto.listing.ListingDTO;
import IDATA2306.Group12.entity.Hotel;
import IDATA2306.Group12.entity.Listing;
import IDATA2306.Group12.entity.Provider;

/**
 * Mapper class for converting between Listings entity and ListingDTO.
 */
@Component
public class ListingMapper {

    private HotelMapper hotelMapper;
    private ProviderMapper providerMapper;

    public ListingMapper (HotelMapper hotelMapper, ProviderMapper providerMapper) {
        this.hotelMapper = hotelMapper;
        this.providerMapper = providerMapper;
    }

    public Listing toEntity(ListingCreateDTO dto, Hotel hotel, Provider provider) {
        Listing listing = new Listing();
        listing.setHotel(hotel);
        listing.setProvider(provider);
        listing.setPrice(dto.getPrice());
        listing.setCurrency(dto.getCurrency());
        listing.setLink(dto.getLink());
        return listing;
    }

    public ListingDTO toDTO(Listing listing) {
        ListingDTO dto = new ListingDTO();
        dto.setId(listing.getId());
        dto.setPrice(listing.getPrice());
        dto.setCurrency(listing.getCurrency());
        dto.setLink(listing.getLink());
        dto.setHotel(hotelMapper.toDTO(listing.getHotel()));
        dto.setProvider(providerMapper.toDTO(listing.getProvider()));
        return dto;
    }
}
