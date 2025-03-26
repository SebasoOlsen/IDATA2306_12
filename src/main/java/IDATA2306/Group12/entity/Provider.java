package IDATA2306.Group12.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Represents a provider of listings.
 */
@Entity
@Table(name = "Providers")
public class Provider {

    /**
     * The unique identifier for the provider.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("id")
    private int id;

    /**
     * The name of the provider.
     */
    @JsonProperty("name")
    private String name;

    /**
     * Create a Provider using parameters.
     * @param id id of the provider
     * @param name name of the provider
     */
    public Provider(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Provider() {}
}
