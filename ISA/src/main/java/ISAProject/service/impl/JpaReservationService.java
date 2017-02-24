package ISAProject.service.impl;

import ISAProject.model.Reservation;
import ISAProject.repository.ReservationRepository;
import ISAProject.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by Nole on 2/23/2017.
 */

@Service
@Transactional
public class JpaReservationService implements ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    @Override
    public List<Reservation> findAll() {
        return reservationRepository.findAll();
    }

    @Override
    public Reservation findOne(Long id) {
        List<Reservation> reservations = reservationRepository.findById(id);
        return reservations.get(0);
    }

    @Override
    public List<Reservation> findByDate(Date date) {
        return reservationRepository.findByDate(date);
    }

    @Override
    public Reservation save(Reservation reservation) {
        return reservationRepository.save(reservation);
    }

    @Override
    public void delete(Long id) {
        reservationRepository.delete(id);
    }
}
