package IDATA2306.Group12.repository;

import IDATA2306.Group12.entity.Listing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface ListingRepository extends JpaRepository<Listing, Integer> {
}
