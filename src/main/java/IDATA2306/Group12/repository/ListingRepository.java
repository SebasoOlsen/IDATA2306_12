package IDATA2306.Group12.repository;

import IDATA2306.Group12.entity.Listing;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ListingRepository extends JpaRepository<Listing, Integer> {

    // Find all listings whith rooms that has the given hotel id
    List<Listing> findByRooms_Hotel_Id(int hotelId);
}
