package ISAProject.repository;

import ISAProject.model.CalendarEvent;
import ISAProject.model.users.User;
import org.springframework.data.jpa.repository.JpaRepository;
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

    CalendarEvent save(CalendarEvent calendarEvent);

    void delete(Long id);

}
