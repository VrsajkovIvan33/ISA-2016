package ISAProject.repository;

import ISAProject.model.Waiter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Nole on 11/26/2016.
 */

@Repository
public interface WaiterRepository extends JpaRepository<Waiter, Long>{

    List<Waiter> findByWId(Long wid);

    List<Waiter> findByWEmail(String wemail);
}
