package IDATA2306.Group12.repository;

import IDATA2306.Group12.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {
    
}
