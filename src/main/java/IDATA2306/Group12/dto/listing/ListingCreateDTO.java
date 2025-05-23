package IDATA2306.Group12.dto.listing;
/**
 * DTO for creating a listing, containing room, provider, price, currency, and hotel identifiers.
 */
public class ListingCreateDTO {
    private int roomId;
    private int providerId;
    private int price;
    private String currency;
    private int hotelId;

    public int getRoomId() {
        return this.roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public int getProviderId() {
        return this.providerId;
    }

    public void setProviderId(int providerId) {
        this.providerId = providerId;
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

    public int getHotelId() {
        return this.hotelId;
    }

    public void setHotelId(int hotelId) {
        this.hotelId = hotelId;
    }
}