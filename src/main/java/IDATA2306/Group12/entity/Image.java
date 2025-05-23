package IDATA2306.Group12.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/**
 * Entity representing an image associated with a specific type (e.g., HOTEL or USER).
 * Stores the image URL, type, and the identifier of the associated entity.
 */
@Entity
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("id")
    private int id;

    /**
     * Type of image ex. HOTEL or USER
     */
    private String type;

    /**
     * Id of the type.
     * ex. User with id: 1 or Hotel with id: 3
     */
    private String typeId;

    private String url;

    public Image() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getOwnerId() {
        return typeId;
    }

    public void setOwnerId(String ownerId) {
        this.typeId = ownerId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
