package ISAProject.repository;

import ISAProject.model.VisitHistory;
import ISAProject.model.users.Guest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Verpsychoff on 2/24/2017.
 */

@Repository
public interface VisitHistoryRepository extends JpaRepository<VisitHistory, Long> {

    VisitHistory findOne(Long id);

    List<VisitHistory> findByGuest(Guest guest);

    VisitHistory save(VisitHistory visitHistory);

    void delete(Long id);

}
