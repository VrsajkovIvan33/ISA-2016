package ISAProject.service;

import ISAProject.model.CalendarEvent;
import ISAProject.model.users.User;

import java.util.List;

/**
 * Created by Verpsychoff on 2/16/2017.
 */

public interface CalendarEventService {

    List<CalendarEvent> findAll();

    CalendarEvent findById(Long id);

    List<CalendarEvent> findByUser(User user);

    CalendarEvent save(CalendarEvent calendarEvent);

    void delete(Long id);

}
