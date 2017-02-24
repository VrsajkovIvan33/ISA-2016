package ISAProject.service.impl;

import ISAProject.model.Bill;
import ISAProject.model.Restaurant;
import ISAProject.model.users.Waiter;
import ISAProject.repository.BillRepository;
import ISAProject.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Verpsychoff on 2/24/2017.
 */

@Service
@Transactional
public class JpaBillService implements BillService {

    @Autowired
    private BillRepository billRepository;

    @Override
    public Bill findOne(Long id) {
        return billRepository.findOne(id);
    }

    @Override
    public List<Bill> findByRestaurant(Restaurant restaurant) {
        return billRepository.findByRestaurant(restaurant);
    }

    @Override
    public List<Bill> findByWaiter(Waiter waiter) {
        return billRepository.findByWaiter(waiter);
    }

    @Override
    public Bill save(Bill bill) {
        return billRepository.save(bill);
    }

    @Override
    public void delete(Long id) {
        billRepository.delete(id);
    }
}
