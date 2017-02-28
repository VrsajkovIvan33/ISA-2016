package ISAProject;

import ISAProject.model.Restaurant;
import ISAProject.model.users.Cook;
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

import static org.hamcrest.Matchers.isEmptyOrNullString;

@Transactional
public class CookControllerIT extends AbstractIT {

    @Autowired
    RestaurantService restaurantService;

    @Test
    public void getCooksTest(){
        RestAssured.when()
                .get("/getCooks")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .contentType(ContentType.JSON)
                .body("id", CoreMatchers.hasItems(15));
    }

    @Test
    public void getCookByIdTest(){
        RestAssured.when()
                .get("/getCooks/15")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .contentType(ContentType.JSON)
                .body("name", CoreMatchers.equalTo("Ivan"));
    }

    @Test
    public void getCooksByRestaurantTest(){
        RestAssured.when()
                .get("/getCooksByRestaurant/1")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .contentType(ContentType.JSON)
                .body("id", CoreMatchers.hasItems(15));
    }

    @Test
    public void removeCookTest(){
        RestAssured.when()
                .delete("/removeCook/14")
                .then()
                .statusCode(HttpStatus.SC_NO_CONTENT)
                .contentType(isEmptyOrNullString());
    }

    @Test
    public void createCookTest(){
        Cook cook = new Cook();
        cook.setName("Milan");
        cook.setSurname("Milanovic");
        cook.setPassword("mico");
        cook.setEmail("mico@gmail.com");
        cook.setType(UserType.COOK);
        cook.setPasswordChanged(true);
        cook.setTypeCook("All");
        Date date = new Date();
        cook.setDate_of_birth(date);
        cook.setDress_size(13);
        cook.setShoe_size(13);
        Restaurant r = restaurantService.findOne(1L);
        cook.setRestaurant(r);


        RestAssured.given()
                .body(cook)
                .contentType(ContentType.JSON)
                .when()
                .post("/addCook")
                .then()
                .statusCode(HttpStatus.SC_CREATED)
                .contentType(ContentType.JSON)
                .body("name", CoreMatchers.equalTo("Milan"));
    }

    @Test
    public void updateCookTest(){
        Cook cook = new Cook();
        cook.setId(15L);
        cook.setName("Ivan");
        cook.setSurname("Vrsajkov");
        cook.setPassword("mico");
        cook.setEmail("mico@gmail.com");
        cook.setType(UserType.COOK);
        cook.setPasswordChanged(true);
        cook.setTypeCook("All");
        Date date = new Date();
        cook.setDate_of_birth(date);
        cook.setDress_size(13);
        cook.setShoe_size(13);
        Restaurant r = restaurantService.findOne(1L);
        cook.setRestaurant(r);

        RestAssured.given()
                .body(cook)
                .contentType(ContentType.JSON)
                .when()
                .post("/updateCook")
                .then()
                .statusCode(HttpStatus.SC_CREATED)
                .contentType(ContentType.JSON)
                .body("id", CoreMatchers.equalTo(15));
    }
}
