package ISAProject.service;

import ISAProject.model.Reservation;
import ISAProject.model.Restaurant;
import ISAProject.model.users.Guest;

import java.util.Date;
import java.util.List;

/**
 * Created by Nole on 2/23/2017.
 */
public interface ReservationService {

    List<Reservation> findAll();

    Reservation findOne(Long id);

    List<Reservation> findByDate(Date date);

    List<Reservation> findLaterThanDateByRestaurant(Date date, Restaurant restaurant);

    List<Reservation> findByHost(Guest guest);

    Reservation save(Reservation reservation);

    void delete(Long id);
}
