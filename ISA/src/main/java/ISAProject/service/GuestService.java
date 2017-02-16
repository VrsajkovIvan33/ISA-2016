package ISAProject.service;

import ISAProject.model.users.Guest;

import java.util.List;

/**
 * Created by Nole on 11/20/2016.
 */
public interface GuestService {

    List<Guest> findAll();

    List<Guest> findByName(String name);

    List<Guest> findBySurname(String surname);

    List<Guest> findByNameAndSurname(String name, String surname);

    Guest findOne(Long id);

    Guest save(Guest guest);

    Guest delete(Long id);

}
