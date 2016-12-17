package ISAProject.repository;

import ISAProject.model.users.Cook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Nole on 11/26/2016.
 */

@Repository
public interface CookRepository extends JpaRepository<Cook, Long>{

    List<Cook> findById(Long cid);

}
