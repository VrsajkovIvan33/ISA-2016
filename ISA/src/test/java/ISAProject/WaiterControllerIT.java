package ISAProject;


import ISAProject.model.users.Waiter;
import ISAProject.service.RestaurantService;
import ISAProject.service.WaiterService;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.hamcrest.CoreMatchers;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

public class WaiterControllerIT extends AbstractIT{

    @Autowired
    WaiterService waiterService;

    @Autowired
    RestaurantService restaurantService;

    @Test
    public void testGetWaiters(){
        RestAssured.when()
                .get("/getWaiters")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .contentType(ContentType.JSON)
                .body("id", CoreMatchers.hasItems(6,7,16));
    }

    @Test
    public void testGetWaiter(){
        RestAssured.when()
                .get("/getWaiters/16")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .contentType(ContentType.JSON)
                .body("email", CoreMatchers.equalTo("w.waiter@gmail.com"));
    }

    @Test
    public void testGetWaitersByRestaurant(){
        RestAssured.when()
                .get("/getWaitersByRestaurant/1")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .contentType(ContentType.JSON)
                .body("id", CoreMatchers.hasItems(6,7));
    }

    @Test
    public void testRemoveWaiter(){
        Waiter waiter = new Waiter();
        waiter.setName("TestName");
        waiter.setSurname("TestSurname");
        waiter.setEmail("test@test.com");
        waiter.setRestaurant(restaurantService.findOne(1L));
        waiter.setDate_of_birth(new Date());
        waiter.setPassword("TestPassword");
        waiter.setShoe_size(10);
        waiter.setDress_size(10);
        waiter.setPasswordChanged(false);

        Waiter savedWaiter = waiterService.save(waiter);

        RestAssured.when()
                .delete("/removeWaiter/" + savedWaiter.getId())
                .then()
                .statusCode(HttpStatus.SC_NO_CONTENT);
    }

    @Test
    @Transactional
    public void testAddWaiter(){
        Waiter waiter = new Waiter();
        waiter.setName("TestName");
        waiter.setSurname("TestSurname");
        waiter.setEmail("test@test.com");
        waiter.setRestaurant(restaurantService.findOne(1L));
        waiter.setDate_of_birth(new Date());
        waiter.setPassword("TestPassword");
        waiter.setShoe_size(10);
        waiter.setDress_size(10);
        waiter.setPasswordChanged(false);

        RestAssured
                .given()
                .body(waiter)
                .contentType(ContentType.JSON)
                .when()
                .post("/addWaiter")
                .then()
                .statusCode(HttpStatus.SC_CREATED)
                .contentType(ContentType.JSON)
                .body("email", CoreMatchers.equalTo("test@test.com"));
    }

    @Test
    @Transactional
    public void testUpdateWaiter(){
        Waiter waiter = waiterService.findOne(7L);
        waiter.setEmail("newtestemail@gmail.com");

        RestAssured
                .given()
                .body(waiter)
                .contentType(ContentType.JSON)
                .when()
                .post("/updateWaiter")
                .then()
                .statusCode(HttpStatus.SC_CREATED)
                .contentType(ContentType.JSON)
                .body("email", CoreMatchers.equalTo("newtestemail@gmail.com"));
    }
}
