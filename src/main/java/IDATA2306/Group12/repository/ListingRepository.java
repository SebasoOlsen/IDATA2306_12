package IDATA2306.Group12.repository;

import IDATA2306.Group12.entity.Listings;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ListingRepository extends JpaRepository<Listings, Integer> {
//    Listings findByHotel(Hotel hotel);

}
