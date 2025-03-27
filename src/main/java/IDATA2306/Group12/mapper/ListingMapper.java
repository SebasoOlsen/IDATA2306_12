package IDATA2306.Group12.mapper;

import IDATA2306.Group12.entity.Listing;
import IDATA2306.Group12.dto.ListingDTO;

/**
 * Mapper class for converting between Listing entity and ListingDTO.
 */
public class ListingMapper {

    /**
     * Converts a Listing entity to a ListingDTO.
     *
     * @param listing the Listing entity to convert
     * @return the corresponding ListingDTO
     */
    public static ListingDTO toDTO(Listing listing) {
        if (listing == null) {
            throw new IllegalArgumentException("Listing cannot be null");
        }
        return new ListingDTO(
            listing.getId(),
            listing.getPID(),
            listing.getHID(),
            listing.getPrice(),
            listing.getCurrency(),
            listing.getLink()
        );
    }

    /**
     * Converts a ListingDTO to a Listing entity.
     *
     * @param listingDTO the ListingDTO to convert
     * @return the corresponding Listing entity
     */
    public static Listing toEntity(ListingDTO listingDTO) {
        if (listingDTO == null) {
            throw new IllegalArgumentException("ListingDTO cannot be null");
        }
        Listing listing = new Listing();
        listing.setId(listingDTO.getId());
        listing.setPID(listingDTO.getPID());
        listing.setHID(listingDTO.getHID());
        listing.setPrice(listingDTO.getPrice());
        listing.setCurrency(listingDTO.getCurrency());
        listing.setLink(listingDTO.getLink());
        return listing;
    }
}