package IDATA2306.Group12.repository;

import IDATA2306.Group12.entity.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface HotelRepository extends JpaRepository<Hotel, Integer> {
    List<Hotel> findByName(String name);

    List<Hotel> findByCityIgnoreCaseOrCountryIgnoreCase(String city, String country);
}
