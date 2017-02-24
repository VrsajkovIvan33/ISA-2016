package ISAProject.service.impl;

import ISAProject.model.VisitHistory;
import ISAProject.model.users.Guest;
import ISAProject.repository.VisitHistoryRepository;
import ISAProject.service.VisitHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Verpsychoff on 2/24/2017.
 */

@Service
@Transactional
public class JpaVisitHistoryService implements VisitHistoryService {

    @Autowired
    private VisitHistoryRepository visitHistoryRepository;

    @Override
    public VisitHistory findOne(Long id) {
        return visitHistoryRepository.findOne(id);
    }

    @Override
    public List<VisitHistory> findByGuest(Guest guest) {
        return visitHistoryRepository.findByGuest(guest);
    }

    @Override
    public VisitHistory save(VisitHistory visitHistory) {
        return visitHistoryRepository.save(visitHistory);
    }

    @Override
    public void delete(Long id) {
        visitHistoryRepository.delete(id);
    }
}
