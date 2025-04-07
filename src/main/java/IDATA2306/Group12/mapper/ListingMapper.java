package IDATA2306.Group12.mapper;

import IDATA2306.Group12.entity.Listing;
import IDATA2306.Group12.dto.ListingDTO;

/**
 * Mapper class for converting between Listings entity and ListingDTO.
 */
public class ListingMapper {

    /**
     * Converts a Listings entity to a ListingDTO.
     *
     * @param listings the Listings entity to convert
     * @return the corresponding ListingDTO
     */
    public static ListingDTO toDTO(Listing listings) {
        if (listings == null) {
            throw new IllegalArgumentException("Listings cannot be null");
        }
        return new ListingDTO(
            listings.getId(),
            listings.getPID(),
            listings.getHID(),
            listings.getPrice(),
            listings.getCurrency(),
            listings.getLink()
        );
    }

    /**
     * Converts a ListingDTO to a Listings entity.
     *
     * @param listingDTO the ListingDTO to convert
     * @return the corresponding Listings entity
     */
    public static Listing toEntity(ListingDTO listingDTO) {
        if (listingDTO == null) {
            throw new IllegalArgumentException("ListingDTO cannot be null");
        }
        Listing listings = new Listing();
        listings.setId(listingDTO.getId());
        listings.setPID(listingDTO.getPID());
        listings.setHID(listingDTO.getHID());
        listings.setPrice(listingDTO.getPrice());
        listings.setCurrency(listingDTO.getCurrency());
        listings.setLink(listingDTO.getLink());
        return listings;
    }
}