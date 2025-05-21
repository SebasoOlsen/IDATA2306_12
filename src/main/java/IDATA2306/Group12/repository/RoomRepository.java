package IDATA2306.Group12.repository;

import IDATA2306.Group12.entity.Room;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RoomRepository extends JpaRepository<Room, Integer> {
    List<Room> findByName(String name);

    @Query("SELECT r FROM Room r JOIN r.hotels h WHERE h.id = :hotelId")
    List<Room> findAllByHotelId(@Param("hotelId") int hotelId);
}
