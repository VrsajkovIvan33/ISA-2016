package ISAProject.service;

import ISAProject.model.VisitHistory;
import ISAProject.model.users.Guest;

import java.util.List;

/**
 * Created by Verpsychoff on 2/24/2017.
 */

public interface VisitHistoryService {

    VisitHistory findOne(Long id);

    List<VisitHistory> findByGuest(Guest guest);

    VisitHistory save(VisitHistory visitHistory);

    void delete(Long id);

}
