package IDATA2306.Group12.entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Represents a provider of listings.
 */
@Entity
@Table(name = "providers")
public class Provider {

    /**
     * The unique identifier for the provider.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /**
     * The name of the provider.
     */
    private String name;

    /**
     * The listings associated with the provider.
     */
    private List<Listing> listings;
}
