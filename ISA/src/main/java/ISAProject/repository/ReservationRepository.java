package ISAProject.repository;

import ISAProject.model.Reservation;
import ISAProject.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
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

    List<Reservation> findAll();

    void delete(Long id);

    Reservation save(Reservation reservation);

}
