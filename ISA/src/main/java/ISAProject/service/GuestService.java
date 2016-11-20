package ISAProject.service;

import ISAProject.model.Guest;

import java.util.List;

/**
 * Created by Nole on 11/20/2016.
 */
public interface GuestService {

    List<Guest> findAll();

    Guest findOne(Long id);

    Guest save(Guest guest);

    Guest delete(Long id);

    Guest findByEmail(String email);
}
