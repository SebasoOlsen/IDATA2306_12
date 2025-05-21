package IDATA2306.Group12.repository;

import IDATA2306.Group12.entity.Room;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomsRepository extends JpaRepository<Room, Integer> {
    List<Room> findByName(String name);

    List<Room> findByHotels_id(Long hotelId);
}
