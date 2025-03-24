package IDATA2306.Group12.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * Data Transfer Object (DTO) for Listing entity.
 */
public class ListingDTO {

    private int id;

    @NotNull(message = "Provider ID cannot be null")
    private int providerId;

    @NotNull(message = "Hotel ID cannot be null")
    private int hotelId;

    @Min(value = 0, message = "Price must be positive")
    private int price;

    @NotBlank(message = "Currency cannot be empty")
    private String currency;

    @NotBlank(message = "Link cannot be empty")
    private String link;

    public ListingDTO() {}

    public ListingDTO(int id, int providerId, int hotelId, int price, String currency, String link) {
        this.id = id;
        this.providerId = providerId;
        this.hotelId = hotelId;
        this.price = price;
        this.currency = currency;
        this.link = link;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getProviderId() { return providerId; }
    public void setProviderId(int providerId) { this.providerId = providerId; }

    public int getHotelId() { return hotelId; }
    public void setHotelId(int hotelId) { this.hotelId = hotelId; }

    public int getPrice() { return price; }
    public void setPrice(int price) { this.price = price; }

    public String getCurrency() { return currency; }
    public void setCurrency(String currency) { this.currency = currency; }

    public String getLink() { return link; }
    public void setLink(String link) { this.link = link; }
}
