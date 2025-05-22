package IDATA2306.Group12.dto.listing;

import IDATA2306.Group12.dto.hotel.HotelResponseDTO;
import IDATA2306.Group12.dto.provider.ProviderResponseDTO;
import IDATA2306.Group12.dto.room.RoomResponseDTO;
import IDATA2306.Group12.entity.Hotel;
/**
 * DTO for creating a listing, containing room, provider, price, currency, and hotel identifiers.
 */
public class ListingResponseDTO {
    private int id;
    private ProviderResponseDTO provider;
    private RoomResponseDTO room;
    private int price;
    private String currency;
    private HotelResponseDTO hotel;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ProviderResponseDTO getProvider() {
        return provider;
    }

    public void setProvider(ProviderResponseDTO provider) {
        this.provider = provider;
    }

    public RoomResponseDTO getRoom() {
        return this.room;
    }

    public void setRoom(RoomResponseDTO room) {
        this.room = room;
    }

    public int getPrice() {
        return this.price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getCurrency() {
        return this.currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public HotelResponseDTO getHotel() {
        return this.hotel;
    }

    public void setHotel(HotelResponseDTO hotel) {
        this.hotel = hotel;
    }
}