package ISAProject.repository;

import ISAProject.model.Restaurant;
import ISAProject.model.WaiterReview;
import ISAProject.model.users.Waiter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Marko on 2/19/2017.
 */
@Repository
public interface WaiterReviewRepository extends JpaRepository<WaiterReview, Long> {
    List<WaiterReview> findByWrId(Long id);

    List<WaiterReview> findAll();

    void delete(Long id);

    WaiterReview save(WaiterReview waiterReview);

    List<WaiterReview> findByWrRestaurant(Restaurant restaurant);

    List<WaiterReview> findByWrWaiter(Waiter waiter);
}
