package ISAProject;

import ISAProject.model.Restaurant;
import ISAProject.model.users.RestaurantManager;
import ISAProject.model.users.UserType;
import ISAProject.service.RestaurantService;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.hamcrest.CoreMatchers;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Transactional
public class RestaurantManagerControllerIT extends AbstractIT{

    @Autowired
    RestaurantService restaurantService;

    @Test
    public void getRestaurantManagersTest(){
        RestAssured.when()
                .get("/getRestaurantManagers")
                .then()
                .contentType(ContentType.JSON)
                .statusCode(HttpStatus.SC_OK)
                .body("id", CoreMatchers.hasItems(8));
    }

    @Test
    public void getRestaurantManagerTest(){
        RestAssured.when()
                .get("/getRestaurantManager/8")
                .then()
                .contentType(ContentType.JSON)
                .statusCode(HttpStatus.SC_OK)
                .body("name", CoreMatchers.equalTo("Dick"));
    }

    @Test
    public void removeRestaurantManagerTest(){
        RestAssured.when()
                .delete("/removeRestaurantManager/12")
                .then()
                .statusCode(HttpStatus.SC_NO_CONTENT);
    }

    @Test
    public void createRestaurantManagerTest(){
        RestaurantManager restaurantManager = new RestaurantManager();
        restaurantManager.setName("Milan");
        restaurantManager.setSurname("Milanovic");
        restaurantManager.setEmail("mm@gmail.com");
        restaurantManager.setPassword("sasasas");
        restaurantManager.setType(UserType.RESTAURANTMANAGER);
        Date date = new Date();
        restaurantManager.setDate_of_birth(date);
        Restaurant restaurant = restaurantService.findOne(1L);
        restaurantManager.setRestaurant(restaurant);

        RestAssured.given()
                .body(restaurantManager)
                .contentType(ContentType.JSON)
                .when()
                .post("/addRestaurantManager")
                .then()
                .statusCode(HttpStatus.SC_CREATED)
                .contentType(ContentType.JSON)
                .body("name", CoreMatchers.equalTo("Milan"));
    }

    @Test
    public void updateRestaurantManagerTest(){
        RestaurantManager restaurantManager = new RestaurantManager();
        restaurantManager.setId(17L);
        restaurantManager.setName("Dragan");
        restaurantManager.setSurname("Milanovic");
        restaurantManager.setEmail("mm@gmail.com");
        restaurantManager.setPassword("sasasas");
        restaurantManager.setType(UserType.RESTAURANTMANAGER);
        Date date = new Date();
        restaurantManager.setDate_of_birth(date);
        Restaurant restaurant = restaurantService.findOne(1L);
        restaurantManager.setRestaurant(restaurant);

        RestAssured.given()
                .body(restaurantManager)
                .contentType(ContentType.JSON)
                .when()
                .post("/updateRestaurantManager")
                .then()
                .statusCode(HttpStatus.SC_CREATED)
                .contentType(ContentType.JSON)
                .body("name", CoreMatchers.equalTo("Dragan"));
    }
}
