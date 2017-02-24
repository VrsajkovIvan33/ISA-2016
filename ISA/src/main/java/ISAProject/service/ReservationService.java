package ISAProject.service;

import ISAProject.model.Reservation;

import java.util.Date;
import java.util.List;

/**
 * Created by Nole on 2/23/2017.
 */
public interface ReservationService {

    List<Reservation> findAll();

    Reservation findOne(Long id);

    List<Reservation> findByDate(Date date);

    Reservation save(Reservation reservation);

    void delete(Long id);
}
