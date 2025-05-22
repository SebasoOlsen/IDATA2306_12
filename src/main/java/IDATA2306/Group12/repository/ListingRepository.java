package IDATA2306.Group12.repository;

import IDATA2306.Group12.entity.Listing;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ListingRepository extends JpaRepository<Listing, Integer> {
    //TODO find out if its needed

    List<Listing> findByHotel_Id(Integer hotelId);
//
//    List<Listing> findByHotelId(int hotelId);
}
