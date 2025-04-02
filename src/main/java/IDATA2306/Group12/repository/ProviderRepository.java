package IDATA2306.Group12.repository;

import IDATA2306.Group12.entity.Provider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProviderRepository extends JpaRepository<Provider, Integer> {
    List<Provider> findByName(String name);
}
