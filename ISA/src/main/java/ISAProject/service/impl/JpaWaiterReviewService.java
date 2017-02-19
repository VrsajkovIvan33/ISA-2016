package ISAProject.service.impl;

import ISAProject.model.Restaurant;
import ISAProject.model.WaiterReview;
import ISAProject.model.users.Waiter;
import ISAProject.repository.WaiterReviewRepository;
import ISAProject.service.WaiterReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Marko on 2/19/2017.
 */
@Service
@Transactional
public class JpaWaiterReviewService implements WaiterReviewService {
    @Autowired
    private WaiterReviewRepository waiterReviewRepository;

    @Override
    public List<WaiterReview> findAll() {
        return waiterReviewRepository.findAll();
    }

    @Override
    public WaiterReview findOne(Long id) { return waiterReviewRepository.findOne(id); }

    @Override
    public WaiterReview save(WaiterReview waiterReview) {
        return waiterReviewRepository.save(waiterReview);
    }

    @Override
    public void delete(Long id) { waiterReviewRepository.delete(id); }

    @Override
    public List<WaiterReview> findByWrRestaurant(Restaurant restaurant) {
        return waiterReviewRepository.findByWrRestaurant(restaurant);
    }

    @Override
    public List<WaiterReview> findByWrWaiter(Waiter waiter) {
        return waiterReviewRepository.findByWrWaiter(waiter);
    }
}
