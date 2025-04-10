package IDATA2306.Group12.dto.listing;

public class ListingCreateDTO {
    private int hotelId;
    private int providerId;
    private int price;
    private String currency;
    private String link;

    public int getHotelId() { return hotelId; }
    public void setHotelId(int hotelId) { this.hotelId = hotelId; }

    public int getProviderId() { return providerId; }
    public void setProviderId(int providerId) { this.providerId = providerId; }

    public int getPrice() { return price; }
    public void setPrice(int price) { this.price = price; }

    public String getCurrency() { return currency; }
    public void setCurrency(String currency) { this.currency = currency; }

    public String getLink() { return link; }
    public void setLink(String link) { this.link = link; }
}
