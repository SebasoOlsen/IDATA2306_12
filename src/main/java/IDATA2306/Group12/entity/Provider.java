package IDATA2306.Group12.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

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
    @Column(name="providerId")
    private int id;

    /**
     * The name of the provider.
     */
    @JsonProperty("name")
    @Column(name="name")
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

    public int getId() {return id;}
    public void setId(int id) {this.id = id;}

    public String getName() {return name;}
    public void setName(String name) {this.name = name;}
}
