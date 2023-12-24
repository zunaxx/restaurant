package peksoft.repo;


import peksoft.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User,Long> {

    Optional<User> getUserByEmail(String email);

    boolean existsByEmail(String email);

}
