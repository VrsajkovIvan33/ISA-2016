package ISAProject.repository;

import ISAProject.model.Cook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Nole on 11/26/2016.
 */

@Repository
public interface CookRepository extends JpaRepository<Cook, Long>{

    List<Cook> findByCId(Long cid);

    List<Cook> findByCEmail(String cemail);
}
