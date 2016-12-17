package ISAProject.service.impl;

import ISAProject.model.users.Waiter;
import ISAProject.repository.WaiterRepository;
import ISAProject.service.WaiterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Nole on 11/26/2016.
 */

@Service
@Transactional
public class JpaWaiterService implements WaiterService{

    @Autowired
    private WaiterRepository waiterRepository;

    @Override
    public List<Waiter> findAll() {
        return waiterRepository.findAll();
    }

    @Override
    public Waiter findOne(Long id) {
        return waiterRepository.findOne(id);
    }

    @Override
    public Waiter save(Waiter waiter) {
        return waiterRepository.save(waiter);
    }

    @Override
    public Waiter delete(Long id) {
        Waiter waiter = waiterRepository.findOne(id);
        if(waiter == null){
            try{
                throw new Exception("Waiter cannot be found");
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        waiterRepository.delete(waiter);
        return waiter;
    }
}
