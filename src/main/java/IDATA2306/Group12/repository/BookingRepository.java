package IDATA2306.Group12.repository;

import IDATA2306.Group12.entity.Booking;
import IDATA2306.Group12.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Arrays;
import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Integer> {
    List<Booking> findByUserId(int userId);
}
