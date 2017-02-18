package ISAProject.controller;

import ISAProject.model.CalendarEvent;
import ISAProject.model.Restaurant;
import ISAProject.model.UnprocessedCalendarEvent;
import ISAProject.model.users.Bartender;
import ISAProject.model.users.Cook;
import ISAProject.model.users.User;
import ISAProject.model.users.Waiter;
import ISAProject.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.InterceptingAsyncClientHttpRequestFactory;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Verpsychoff on 2/16/2017.
 */

@RestController
public class CalendarEventController {

    @Autowired
    private CalendarEventService calendarEventService;

    @Autowired
    private UserService userService;

    @Autowired
    private WaiterService waiterService;

    @Autowired
    private CookService cookService;

    @Autowired
    private BartenderService bartenderService;

    @Autowired
    private RestaurantService restaurantService;

    @RequestMapping(
            value = "/CalendarEventsByUser/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CalendarEvent>> getCalendarEventsByUser(@PathVariable("id") Long userId) {
        User userById = userService.findOne(userId);
        List<CalendarEvent> calendarEvents = calendarEventService.findByUser(userById);
        return new ResponseEntity<List<CalendarEvent>>(calendarEvents, HttpStatus.OK);
    }

    @RequestMapping(
            value = "/CalendarEventsByRestaurant/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CalendarEvent>> getCalendarEventsByRestaurant(@PathVariable("id") Long restaurantId) {
        Restaurant restaurantById = restaurantService.findOne(restaurantId);
        List<CalendarEvent> calendarEvents = new ArrayList<CalendarEvent>();

        List<Waiter> waitersInRestaurant = waiterService.findByRestaurant(restaurantById);
        for (Waiter waiter : waitersInRestaurant) {
            User waiterUser = userService.findOne(waiter.getId());
            calendarEvents.addAll(calendarEventService.findByUser(waiterUser));
        }

        List<Cook> cooksInRestaurant = cookService.findByRestaurant(restaurantById);
        for (Cook cook : cooksInRestaurant) {
            User cookUser = userService.findOne(cook.getId());
            calendarEvents.addAll(calendarEventService.findByUser(cookUser));
        }

        List<Bartender> bartendersInRestaurant = bartenderService.findByRestaurant(restaurantById);
        for (Bartender bartender : bartendersInRestaurant) {
            User bartenderUser = userService.findOne(bartender.getId());
            calendarEvents.addAll(calendarEventService.findByUser(bartenderUser));
        }

        return new ResponseEntity<List<CalendarEvent>>(calendarEvents, HttpStatus.OK);
    }

    @RequestMapping(
            value = "/CalendarEvents",
            method = RequestMethod.POST,
            consumes = "application/json")
    public ResponseEntity<List<CalendarEvent>> addCalendarEvents(@RequestBody UnprocessedCalendarEvent unprocessedCalendarEvent) throws Exception {
        Calendar calendarStart = Calendar.getInstance();
        Calendar calendarEnd = Calendar.getInstance();
        Calendar endingCalendar = Calendar.getInstance();
        endingCalendar.setTime(unprocessedCalendarEvent.getEndDate());

        // push until the day of the week is the one we want
        calendarStart.setTime(unprocessedCalendarEvent.getStartDate());
        while (calendarStart.get(Calendar.DAY_OF_WEEK) != unprocessedCalendarEvent.getDayInWeek()) {
            calendarStart.add(Calendar.DATE, 1);
        }

        // set the calendar to the shift beginning
        String[] hourMinutesStart = unprocessedCalendarEvent.getShiftStart().split(":");
        calendarStart.set(calendarStart.get(Calendar.YEAR), calendarStart.get(Calendar.MONTH), calendarStart.get(Calendar.DAY_OF_MONTH),
                Integer.parseInt(hourMinutesStart[0]), Integer.parseInt(hourMinutesStart[1]));

        // set the calendar to the shift end
        String[] hourMinutesEnd = unprocessedCalendarEvent.getShiftEnd().split(":");
        calendarEnd.set(calendarStart.get(Calendar.YEAR), calendarStart.get(Calendar.MONTH), calendarStart.get(Calendar.DAY_OF_MONTH),
                Integer.parseInt(hourMinutesEnd[0]), Integer.parseInt(hourMinutesEnd[1]));

        System.out.println("Calendar start: " + calendarStart.getTime());
        System.out.println("Calendar end: " + calendarEnd.getTime());
        // create an event in every week until the ending date is passed
        List<CalendarEvent> calendarEvents = new ArrayList<CalendarEvent>();
        while (endingCalendar.getTimeInMillis() >= calendarEnd.getTimeInMillis()) {
            CalendarEvent calendarEvent = new CalendarEvent();
            calendarEvent.setStart(calendarStart.getTime());
            calendarEvent.setEnd(calendarEnd.getTime());
            calendarEvent.setUser(unprocessedCalendarEvent.getUser());
            calendarEvent.setTableRegion(unprocessedCalendarEvent.getTableRegion());
            calendarEventService.save(calendarEvent);
            calendarEvents.add(calendarEvent);
            calendarStart.add(Calendar.DATE, 7);
            calendarEnd.add(Calendar.DATE, 7);
        }

        return new ResponseEntity<List<CalendarEvent>>(calendarEvents, HttpStatus.CREATED);
    }

}
