package ISAProject.service.impl;

import ISAProject.model.CalendarEvent;
import ISAProject.model.users.User;
import ISAProject.repository.CalendarEventRepository;
import ISAProject.service.CalendarEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Verpsychoff on 2/16/2017.
 */

@Service
@Transactional
public class JpaCalendarEventService implements CalendarEventService {

    @Autowired
    private CalendarEventRepository calendarEventRepository;

    @Override
    public List<CalendarEvent> findAll() {
        return calendarEventRepository.findAll();
    }

    @Override
    public CalendarEvent findById(Long id) {
        return calendarEventRepository.findById(id);
    }

    @Override
    public List<CalendarEvent> findByUser(User user) {
        return calendarEventRepository.findByUser(user);
    }

    @Override
    public CalendarEvent save(CalendarEvent calendarEvent) {
        return calendarEventRepository.save(calendarEvent);
    }

    @Override
    public void delete(Long id) {
        calendarEventRepository.delete(id);
    }
}
