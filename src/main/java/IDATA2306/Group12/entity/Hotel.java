package IDATA2306.Group12.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.List;

/**
 * Represents a hotel with various listings.
 */
@Entity
@Table(name = "hotels")
public class Hotel {

    /**
     * The unique identifier for the hotel.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /**
     * The name of the hotel.
     */
    private String name;

    /**
     * The type of location where the hotel is situated.
     */
    private String locationType;

    /**
     * The types of rooms available in the hotel.
     */
    private List<String> roomTypes;

    /**
     * The extra features offered by the hotel.
     */
    private List<String> extraFeatures;

    /**
     * The listings associated with the hotel.
     */
    private List<Listing> listings;

}
