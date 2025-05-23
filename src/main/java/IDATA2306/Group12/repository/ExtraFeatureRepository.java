package IDATA2306.Group12.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import IDATA2306.Group12.entity.ExtraFeature;

public interface ExtraFeatureRepository extends JpaRepository<ExtraFeature, Long> {
    List<ExtraFeature> findByName(String name);
}
