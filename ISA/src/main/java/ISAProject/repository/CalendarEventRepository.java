package ISAProject.repository;

import ISAProject.model.CalendarEvent;
import ISAProject.model.users.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Verpsychoff on 2/16/2017.
 */

@Repository
public interface CalendarEventRepository extends JpaRepository<CalendarEvent, Long> {

    List<CalendarEvent> findAll();

    CalendarEvent findById(Long id);

    List<CalendarEvent> findByUser(User user);

    @Query("select ce from CalendarEvent ce where ce.user.id = ?1 and ce.year = ?2 and ce.month = ?3 and" +
            " ce.day = ?4 and ce.startHour <= ?5 and ce.endHour >= ?5")
    CalendarEvent findByUserAndShift(Long userID, int year, int month, int day, int hour);

    CalendarEvent save(CalendarEvent calendarEvent);

    void delete(Long id);

}
