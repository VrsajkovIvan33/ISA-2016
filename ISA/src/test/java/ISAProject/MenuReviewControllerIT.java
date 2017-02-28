package ISAProject;

import ISAProject.model.MenuReview;
import ISAProject.model.Restaurant;
import ISAProject.service.MenuService;
import ISAProject.service.RestaurantService;
import ISAProject.service.UserService;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.hamcrest.CoreMatchers;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Transactional
public class MenuReviewControllerIT extends AbstractIT {

    @Autowired
    UserService userService;

    @Autowired
    RestaurantService restaurantService;

    @Autowired
    MenuService menuService;

    @Test
    public void getMenuReviewsTest() {
        RestAssured.when()
                .get("/getMenuReviews")
                .then()
                .contentType(ContentType.JSON)
                .statusCode(HttpStatus.SC_OK)
                .body("mrId", CoreMatchers.hasItems(2,3,4,5));
    }

    @Test
    public void getMenuReviewTest(){
        RestAssured.when()
                .get("/getMenuReview/1")
                .then()
                .contentType(ContentType.JSON)
                .statusCode(HttpStatus.SC_OK)
                .body("mrId", CoreMatchers.is(1));
    }

    @Test
    public void getMenuReviewsByMrRestaurantTest(){
        RestAssured.when()
                .get("/getMenuReviewsByMrRestaurant/1")
                .then()
                .contentType(ContentType.JSON)
                .statusCode(HttpStatus.SC_OK)
                .body("mrId", CoreMatchers.hasItems(2,3,4,5));
    }

    @Test
    public void getMenuReviewsByMrUserTest(){
        RestAssured.when()
                .get("/getMenuReviewsByMrUser/14")
                .then()
                .contentType(ContentType.JSON)
                .statusCode(HttpStatus.SC_OK)
                .body("mrId", CoreMatchers.hasItems(3,4,5));
    }

    @Test
    public void getMenuReviewsByMrMenu(){
        RestAssured.when()
                .get("/getMenuReviewsByMrMenu/3")
                .then()
                .contentType(ContentType.JSON)
                .statusCode(HttpStatus.SC_OK)
                .body("mrId", CoreMatchers.hasItems(3,4,5));
    }

    @Test
    public void removeMenuReviewTest(){
        RestAssured.when()
                .delete("/removeMenuReview/1")
                .then()
                .statusCode(HttpStatus.SC_NO_CONTENT);
    }

    @Test
    public void addMenuReviewTest(){
        MenuReview menuReview = new MenuReview();
        menuReview.setMrUser(userService.findOne(14L));
        menuReview.setMrRestaurant(restaurantService.findOne(1L));
        menuReview.setMrMenu(menuService.findOne(3L));
        Date date = new Date();
        menuReview.setMrDate(date);
        menuReview.setMrReview(5);

        RestAssured.given()
                .body(menuReview)
                .contentType(ContentType.JSON)
                .when()
                .post("/addMenuReview")
                .then()
                .contentType(ContentType.JSON)
                .statusCode(HttpStatus.SC_CREATED)
                .body("mrId", CoreMatchers.equalTo(6));
    }

    @Test
    public void updateMenuReviewTest(){
        MenuReview menuReview = new MenuReview();
        menuReview.setMrId(6L);
        menuReview.setMrUser(userService.findOne(14L));
        menuReview.setMrRestaurant(restaurantService.findOne(1L));
        menuReview.setMrMenu(menuService.findOne(3L));
        Date date = new Date();
        menuReview.setMrDate(date);
        menuReview.setMrReview(4);

        RestAssured.given()
                .body(menuReview)
                .contentType(ContentType.JSON)
                .when()
                .post("/updateMenuReview")
                .then()
                .contentType(ContentType.JSON)
                .statusCode(HttpStatus.SC_CREATED)
                .body("mrId", CoreMatchers.equalTo(6));
    }
}
