package ISAProject.service.impl;

import ISAProject.model.users.Guest;
import ISAProject.repository.GuestRepository;
import ISAProject.service.GuestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Nole on 11/20/2016.
 */

@Service
@Transactional
public class JpaGuestService implements GuestService{

    @Autowired
    private GuestRepository guestRepository;

    @Override
    public List<Guest> findAll() {
        return guestRepository.findAll();
    }

    @Override
    public Guest findOne(Long id) {
        return guestRepository.findOne(id);
    }

    @Override
    public Guest save(Guest guest) {
        return guestRepository.save(guest);
    }

    @Override
    public Guest delete(Long id) {
        Guest guest = guestRepository.findOne(id);
        if(guest == null){
            try {
                throw new Exception("Guest cannot be found");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        guestRepository.delete(guest);
        return guest;

    }
}
