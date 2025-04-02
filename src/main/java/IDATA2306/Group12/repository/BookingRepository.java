package IDATA2306.Group12.repository;

import IDATA2306.Group12.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking, Integer> {
    Booking findByuID(int uID);
}
