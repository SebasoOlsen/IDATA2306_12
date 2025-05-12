package IDATA2306.Group12.repository;

import IDATA2306.Group12.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ImageRepository extends JpaRepository<Image, Integer> {

    List<Image> findByTypeAndTypeId(String type, String typeId);

}
