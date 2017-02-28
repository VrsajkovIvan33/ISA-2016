package ISAProject;

import ISAProject.model.UnprocessedCalendarEvent;
import ISAProject.service.TableRegionService;
import ISAProject.service.UserService;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.hamcrest.CoreMatchers;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;

import static org.hamcrest.Matchers.isEmptyOrNullString;

public class CalendarEventControllerIT extends AbstractIT {

    @Autowired
    UserService userService;

    @Autowired
    TableRegionService tableRegionService;

    @Test
    public void testGetCalendarEventsByUser(){
        RestAssured.when()
                .get("/CalendarEventsByUser/6")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .contentType(ContentType.JSON)
                .body("id", CoreMatchers.hasItems(1, 2, 3))
                .body("day", CoreMatchers.hasItems(14, 21, 2));
    }

    @Test
    public void testGetCalendarEventsByRestaurant(){
        RestAssured.when()
                .get("/CalendarEventsByRestaurant/1")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .contentType(ContentType.JSON)
                .body("id", CoreMatchers.hasItems(1, 2, 3))
                .body("day", CoreMatchers.hasItems(14, 21, 2));
    }

    @Test
    public void testGetCalendarEventsByUserAndShift(){
        RestAssured.when()
                .get("/CalendarEventsByUserAndShift/7")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .contentType(isEmptyOrNullString());
    }

    @Test
    @Transactional
    public void testPostCalendarEvent(){

        UnprocessedCalendarEvent unprocessedCalendarEvent = new UnprocessedCalendarEvent();
        Calendar calendar = Calendar.getInstance();
        unprocessedCalendarEvent.setDayInWeek(7);
        calendar.set(2017, 1, 1, 0, 0);
        unprocessedCalendarEvent.setStartDate(calendar.getTime());
        calendar.set(2017, 1, 28, 0, 0);
        unprocessedCalendarEvent.setEndDate(calendar.getTime());
        unprocessedCalendarEvent.setShiftStart("12:00");
        unprocessedCalendarEvent.setShiftEnd("20:00");
        unprocessedCalendarEvent.setUser(userService.findOne(13L));
        unprocessedCalendarEvent.setTableRegion(tableRegionService.findById(1L));

        RestAssured
                .given()
                .body(unprocessedCalendarEvent)
                .contentType(ContentType.JSON)
                .when()
                .post("/CalendarEvents")
                .then()
                .statusCode(HttpStatus.SC_CREATED)
                .contentType(ContentType.JSON)
                .body("day", CoreMatchers.hasItems(4, 11, 18, 25))
                .body("month", CoreMatchers.hasItem(1))
                .body("year", CoreMatchers.hasItem(2017))
                .body("startHour", CoreMatchers.hasItem(12))
                .body("startMinute", CoreMatchers.hasItem(0))
                .body("endHour", CoreMatchers.hasItem(20))
                .body("endMinute", CoreMatchers.hasItem(0));
    }

    @Test
    public void testRemoveCalendarEvent() {
        RestAssured.when()
                .delete("/CalendarEvents/1")
                .then()
                .statusCode(HttpStatus.SC_NO_CONTENT)
                .contentType(isEmptyOrNullString());
    }

}
