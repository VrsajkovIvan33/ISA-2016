package ISAProject.service;

import ISAProject.model.Restaurant;
import ISAProject.model.WaiterReview;
import ISAProject.model.users.Waiter;

import java.util.List;

/**
 * Created by Marko on 2/19/2017.
 */
public interface WaiterReviewService {
    List<WaiterReview> findAll();

    WaiterReview findOne(Long id);

    WaiterReview save(WaiterReview waiterReview);

    void delete(Long id);

    List<WaiterReview> findByWrRestaurant(Restaurant restaurant);

    List<WaiterReview> findByWrWaiter(Waiter waiter);
}
