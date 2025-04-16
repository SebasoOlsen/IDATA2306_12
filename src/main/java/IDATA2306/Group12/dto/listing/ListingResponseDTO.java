package IDATA2306.Group12.dto.listing;

import IDATA2306.Group12.dto.hotel.HotelResponseDTO;
import IDATA2306.Group12.dto.provider.ProviderResponseDTO;

public class ListingResponseDTO {
    private int id;
    private ProviderResponseDTO provider;
    private HotelResponseDTO hotel;
    private int price;
    private String currency;
    private String link;

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public ProviderResponseDTO getProvider() { return provider; }
    public void setProvider(ProviderResponseDTO provider) { this.provider = provider; }

    public HotelResponseDTO getHotel() { return hotel; }
    public void setHotel(HotelResponseDTO hotel) { this.hotel = hotel; }

    public int getPrice() { return price; }
    public void setPrice(int price) { this.price = price; }

    public String getCurrency() { return currency; }
    public void setCurrency(String currency) { this.currency = currency; }

    public String getLink() { return link; }
    public void setLink(String link) { this.link = link; }
    
}
