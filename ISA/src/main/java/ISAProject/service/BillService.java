package ISAProject.service;

import ISAProject.model.Bill;
import ISAProject.model.Restaurant;
import ISAProject.model.users.Waiter;
import java.util.List;

/**
 * Created by Verpsychoff on 2/24/2017.
 */

public interface BillService {

    Bill findOne(Long id);

    List<Bill> findByRestaurant(Restaurant restaurant);

    List<Bill> findByWaiter(Waiter waiter);

    Bill save(Bill bill);

    void delete(Long id);
}
