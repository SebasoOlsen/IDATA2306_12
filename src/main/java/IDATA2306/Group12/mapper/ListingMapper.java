package IDATA2306.Group12.mapper;

import org.springframework.stereotype.Component;

import IDATA2306.Group12.dto.listing.ListingCreateDTO;
import IDATA2306.Group12.dto.listing.ListingResponseDTO;
import IDATA2306.Group12.entity.Hotel;
import IDATA2306.Group12.entity.Listing;
import IDATA2306.Group12.entity.Provider;
import IDATA2306.Group12.entity.Room;

/**
 * Mapper class for converting between Listings entity and ListingDTO.
 */
@Component
public class ListingMapper {

    private RoomMapper roomMapper;
    private ProviderMapper providerMapper;
    private HotelMapper hotelMapper;

    /**
     * Constructs a ListingMapper with required dependencies.
     *
     * @param roomMapper the RoomMapper to use
     * @param providerMapper the ProviderMapper to use
     * @param hotelMapper the HotelMapper to use
     */
    public ListingMapper(RoomMapper roomMapper, ProviderMapper providerMapper, HotelMapper hotelMapper) {
        this.roomMapper = roomMapper;
        this.providerMapper = providerMapper;
        this.hotelMapper = hotelMapper;
    }

    /**
     * Converts a ListingCreateDTO, Hotel, Room, and Provider to a Listing entity.
     *
     * @param dto the ListingCreateDTO containing listing details
     * @param hotel the Hotel associated with the listing
     * @param room the Room associated with the listing
     * @param provider the Provider associated with the listing
     * @return the Listing entity
     */
    public Listing toEntity(ListingCreateDTO dto, Hotel hotel, Room room, Provider provider) {
        Listing listing = new Listing();
        listing.setProvider(provider);
        listing.setPrice(dto.getPrice());
        listing.setCurrency(dto.getCurrency());
        listing.setRoom(room);
        listing.setHotel(hotel);
        return listing;
    }

    /**
     * Converts a Listing entity to a ListingResponseDTO.
     *
     * @param listing the Listing entity to convert
     * @return the ListingResponseDTO
     */
    public ListingResponseDTO toResponseDTO(Listing listing) {
        ListingResponseDTO dto = new ListingResponseDTO();
        dto.setId(listing.getId());
        dto.setPrice(listing.getPrice());
        dto.setCurrency(listing.getCurrency());
        dto.setRoom(roomMapper.toResponseDTO(listing.getRoom()));
        dto.setHotel(hotelMapper.toResponseDTO(listing.getHotel()));
        dto.setProvider(providerMapper.toResponseDTO(listing.getProvider()));
        return dto;
    }
}