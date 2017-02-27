package ISAProject;

import ISAProject.model.CalendarEvent;
import ISAProject.model.users.User;
import ISAProject.service.CalendarEventService;
import ISAProject.service.TableRegionService;
import ISAProject.service.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Isa2016Application.class)
@WebAppConfiguration
public class CalendarEventServiceTest {

    @Autowired
    CalendarEventService calendarEventService;

    @Autowired
    UserService userService;

    @Autowired
    TableRegionService tableRegionService;

    @Test
    public void testFindAll() {
        List<CalendarEvent> events = calendarEventService.findAll();
        Assert.assertNotNull(events);
    }

    @Test
    public void testFindById() {
        CalendarEvent event = calendarEventService.findById(1L);
        Assert.assertEquals(1L, event.getId().longValue());
    }

    @Test
    @Transactional
    public void testFindByUser() {
        User user = userService.findOne(6L);
        List<CalendarEvent> events = calendarEventService.findByUser(user);
        Assert.assertNotNull(events);
        for (CalendarEvent event : events) {
            Assert.assertEquals(user.getId(), event.getUser().getId());
        }
    }

    @Test
    @Transactional
    public void testFindByUserAndShift() {
        User user = userService.findOne(6L);
        CalendarEvent event = calendarEventService.findByUserAndShift(user.getId(), 2017, 2, 14, 16);
        Assert.assertNotNull(event);
        Assert.assertEquals(user.getId(), event.getUser().getId());
        Assert.assertEquals(2017, event.getYear());
        Assert.assertEquals(2, event.getMonth());
        Assert.assertEquals(14, event.getDay());
        Assert.assertTrue(event.getStartHour() <= 16);
        Assert.assertTrue(event.getEndHour() >= 16);
    }

    @Test
    @Transactional
    public void testFindByUserAndYearAndMonthAndDay() {
        User user = userService.findOne(6L);
        CalendarEvent event = calendarEventService.findByUserAndYearAndMonthAndDay(user, 2017, 2, 14);
        Assert.assertNotNull(event);
        Assert.assertEquals(user.getId(), event.getUser().getId());
        Assert.assertEquals(2017, event.getYear());
        Assert.assertEquals(2, event.getMonth());
        Assert.assertEquals(14, event.getDay());
    }

    @Test
    public void testSave() {
        CalendarEvent event = new CalendarEvent();
        event.setYear(2017);
        event.setMonth(2);
        event.setMonth(23);
        event.setStartHour(12);
        event.setStartMinute(0);
        event.setEndHour(20);
        event.setEndMinute(0);

        CalendarEvent savedEvent = calendarEventService.save(event);
        CalendarEvent loadedEvent = calendarEventService.findById(savedEvent.getId());

        Assert.assertNotNull(savedEvent);
        Assert.assertNotNull(loadedEvent);
        Assert.assertEquals(savedEvent.getId(), loadedEvent.getId());
    }

    @Test
    public void testDelete() {
        CalendarEvent event = new CalendarEvent();
        event.setYear(2017);
        event.setMonth(2);
        event.setMonth(23);
        event.setStartHour(12);
        event.setStartMinute(0);
        event.setEndHour(20);
        event.setEndMinute(0);

        CalendarEvent savedEvent = calendarEventService.save(event);
        calendarEventService.delete(savedEvent.getId());
        CalendarEvent loadedEvent = calendarEventService.findById(savedEvent.getId());

        Assert.assertNull(loadedEvent);
    }
}
