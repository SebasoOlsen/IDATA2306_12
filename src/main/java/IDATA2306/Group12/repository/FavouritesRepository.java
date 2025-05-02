package IDATA2306.Group12.repository;

import IDATA2306.Group12.entity.Favourite;
import IDATA2306.Group12.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FavouritesRepository extends JpaRepository<Favourite,Integer> {
    List<Favourite> findByUserId(int userId);

}
