package ISAProject.repository;

import ISAProject.model.Bartender;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Nole on 11/26/2016.
 */

@Repository
public interface BartenderRepository extends JpaRepository<Bartender, Long>{

    List<Bartender> findByBId(Long bid);

    List<Bartender> findByBEmail(String bemail);
}
