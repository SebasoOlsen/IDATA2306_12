package IDATA2306.Group12.repository;

import IDATA2306.Group12.entity.Listing;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ListingRepository extends JpaRepository<Listing, Integer> {
//    Listings findByHotel(Hotel hotel);

}
