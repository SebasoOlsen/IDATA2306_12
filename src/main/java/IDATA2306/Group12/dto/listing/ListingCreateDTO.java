package IDATA2306.Group12.dto.listing;

import IDATA2306.Group12.entity.Hotel;
import IDATA2306.Group12.entity.Rooms;

public class ListingCreateDTO {
    private Rooms rooms;
    private int providerId;
    private int price;
    private String currency;
    private String link;
    private Hotel hotel;

    public Rooms getRooms() { return rooms; }
    public void setRooms(Rooms rooms) { this.rooms = rooms; }

    public int getProviderId() { return providerId; }
    public void setProviderId(int providerId) { this.providerId = providerId; }

    public int getPrice() { return price; }
    public void setPrice(int price) { this.price = price; }

    public String getCurrency() { return currency; }
    public void setCurrency(String currency) { this.currency = currency; }

    public String getLink() { return link; }
    public void setLink(String link) { this.link = link; }
}
