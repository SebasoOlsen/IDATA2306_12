package IDATA2306.Group12.repository;

import IDATA2306.Group12.entity.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface HotelRepository extends JpaRepository<Hotel, Integer> {

    @Query("SELECT h FROM Hotel h WHERE h.isHidden = false")
    List<Hotel> findAllByIsHiddenFalse();

    List<Hotel> findByName(String name);

    @Query("SELECT h FROM Hotel h WHERE (LOWER(h.city) = LOWER(?1) OR LOWER(h.country) = LOWER(?2)) AND h.isHidden = false")
    List<Hotel> findByCityIgnoreCaseOrCountryIgnoreCase(String city, String country);
}
