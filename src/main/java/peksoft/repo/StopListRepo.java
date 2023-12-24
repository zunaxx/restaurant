package peksoft.repo;

import peksoft.entities.StopList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StopListRepo extends JpaRepository<StopList,Long> {

}
