package ISAProject;

import ISAProject.model.Restaurant;
import ISAProject.model.users.Bartender;
import ISAProject.model.users.UserType;
import ISAProject.service.BartenderService;
import ISAProject.service.RestaurantService;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.hamcrest.CoreMatchers;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.StaticListableBeanFactory;

import java.util.Date;

public class BartenderControllerIT extends AbstractIT{

    @Autowired
    BartenderService bartenderService;

    @Autowired
    RestaurantService restaurantService;

    @Test
    public void getBartendersTest(){
        RestAssured.when()
                .get("/getBartenders")
                .then()
                .contentType(ContentType.JSON)
                .statusCode(HttpStatus.SC_OK)
                .body("id", CoreMatchers.hasItems(13));
    }

    @Test
    public void getBartenderByIdTest(){
        RestAssured.when()
                .get("/getBartenders/13")
                .then()
                .contentType(ContentType.JSON)
                .statusCode(HttpStatus.SC_OK)
                .body("name", CoreMatchers.equalTo("bar"));
    }

    @Test
    public void getBartendersByRestaurantTest(){
        RestAssured.when()
                .get("/getBartendersByRestaurant/1")
                .then()
                .contentType(ContentType.JSON)
                .statusCode(HttpStatus.SC_OK)
                .body("id", CoreMatchers.hasItems(13, 18));
    }

    @Test
    public void removeBartenderTest(){
        Bartender bartender = new Bartender();
        bartender.setName("Milan");
        bartender.setSurname("Milanovic");
        bartender.setPassword("mico");
        bartender.setEmail("mico@gmail.com");
        bartender.setType(UserType.BARTENDER);
        bartender.setPasswordChanged(true);
        Date date = new Date();
        bartender.setDate_of_birth(date);
        bartender.setDress_size(13);
        bartender.setShoe_size(13);
        Restaurant r = restaurantService.findOne(1L);
        bartender.setRestaurant(r);
        bartenderService.save(bartender);

        RestAssured.when()
                .delete("/removeBartender/17")
                .then()
                .statusCode(HttpStatus.SC_NO_CONTENT);
    }

    @Test
    public void addBartenderTest(){
        Bartender bartender = new Bartender();
        bartender.setName("Milan");
        bartender.setSurname("Milanovic");
        bartender.setPassword("mico");
        bartender.setEmail("mico@gmail.com");
        bartender.setType(UserType.BARTENDER);
        bartender.setPasswordChanged(true);
        Date date = new Date();
        bartender.setDate_of_birth(date);
        bartender.setDress_size(13);
        bartender.setShoe_size(13);
        Restaurant r = restaurantService.findOne(1L);
        bartender.setRestaurant(r);

        RestAssured.given()
                .body(bartender)
                .contentType(ContentType.JSON)
                .when()
                .post("/addBartender")
                .then()
                .statusCode(HttpStatus.SC_CREATED)
                .contentType(ContentType.JSON)
                .body("name", CoreMatchers.equalTo("Milan"));
    }

    @Test
    public void updateBartenderTest(){
        Bartender bartender = new Bartender();
        bartender.setId(18L);
        bartender.setName("Dragan");
        bartender.setSurname("man");
        bartender.setPassword("mico");
        bartender.setEmail("mico@gmail.com");
        bartender.setType(UserType.BARTENDER);
        bartender.setPasswordChanged(true);
        Date date = new Date();
        bartender.setDate_of_birth(date);
        bartender.setDress_size(13);
        bartender.setShoe_size(13);
        Restaurant r = restaurantService.findOne(1L);
        bartender.setRestaurant(r);

        RestAssured.given()
                .body(bartender)
                .contentType(ContentType.JSON)
                .when()
                .post("/updateBartender")
                .then()
                .statusCode(HttpStatus.SC_CREATED)
                .contentType(ContentType.JSON)
                .body("name", CoreMatchers.equalTo("Dragan"));
    }
}
