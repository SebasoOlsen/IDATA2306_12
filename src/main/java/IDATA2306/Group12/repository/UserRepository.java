package IDATA2306.Group12.repository;

import IDATA2306.Group12.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

    boolean existsByEmail(String email);
    boolean existsByTelephone(String telephone);
    User findByEmail(String email);
}
