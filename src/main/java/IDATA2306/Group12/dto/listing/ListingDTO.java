package IDATA2306.Group12.dto.listing;

import IDATA2306.Group12.dto.hotel.HotelDTO;
import IDATA2306.Group12.dto.provider.ProviderDTO;

/**
 * Data Transfer Object (DTO) for Listings entity.
 */
public class ListingDTO {
    private int id;
    private int price;
    private String currency;
    private String link;
    private HotelDTO hotel;
    private ProviderDTO provider;

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getPrice() { return price; }
    public void setPrice(int price) { this.price = price; }

    public String getCurrency() { return currency; }
    public void setCurrency(String currency) { this.currency = currency; }

    public String getLink() { return link; }
    public void setLink(String link) { this.link = link; }

    public HotelDTO getHotel() { return hotel; }
    public void setHotel(HotelDTO hotel) { this.hotel = hotel; }

    public ProviderDTO getProvider() { return provider; }
    public void setProvider(ProviderDTO provider) { this.provider = provider; }
}
