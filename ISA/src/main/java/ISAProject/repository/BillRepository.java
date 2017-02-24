package ISAProject.repository;

import ISAProject.model.Bill;
import ISAProject.model.Restaurant;
import ISAProject.model.users.Waiter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Verpsychoff on 2/24/2017.
 */

@Repository
public interface BillRepository extends JpaRepository<Bill, Long> {

    Bill findOne(Long id);

    @Query("select bill from Bill bill where bill.waiter.restaurant = ?1")
    List<Bill> findByRestaurant(Restaurant restaurant);

    List<Bill> findByWaiter(Waiter waiter);

    Bill save(Bill bill);

    void delete(Long id);

}
