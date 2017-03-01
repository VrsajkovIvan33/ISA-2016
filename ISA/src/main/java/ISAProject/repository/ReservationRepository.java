package ISAProject.repository;

import ISAProject.model.Reservation;
import ISAProject.model.Restaurant;
import ISAProject.model.users.Guest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * Created by Nole on 2/23/2017.
 */

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long>{

    List<Reservation> findById(Long id);

    List<Reservation> findByDate(Date date);

    @Query("select res from Reservation res where res.date >= ?1 and res.restaurant = ?2")
    List<Reservation> findLaterThanDateByRestaurant(Date date, Restaurant restaurant);

    List<Reservation> findByHost(Guest guest);

    List<Reservation> findAll();

    void delete(Long id);

    Reservation save(Reservation reservation);

}
