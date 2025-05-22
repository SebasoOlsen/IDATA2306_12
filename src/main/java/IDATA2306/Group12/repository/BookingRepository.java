package IDATA2306.Group12.repository;

import IDATA2306.Group12.entity.Booking;
import IDATA2306.Group12.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Arrays;
import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Integer> {
    List<Booking> findByUserId(int userId);

    @Query("SELECT b FROM Booking b WHERE b.listing.id = ?1")
    List<Booking> findByListingId(long listingId);
}
