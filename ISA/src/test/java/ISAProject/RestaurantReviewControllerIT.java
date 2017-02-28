package ISAProject;


import ISAProject.model.RestaurantReview;
import ISAProject.service.RestaurantReviewService;
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

public class RestaurantReviewControllerIT extends AbstractIT{

    @Autowired
    RestaurantReviewService restaurantReviewService;

    @Autowired
    RestaurantService restaurantService;

    @Autowired
    UserService userService;

    @Test
    public void testGetRestaurantReviews(){
        RestAssured.when()
                .get("/getRestaurantReviews")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .contentType(ContentType.JSON)
                .body("rrId", CoreMatchers.hasItems(1,2,3,4,5));
    }

    @Test
    public void testGetRestaurantReview(){
        RestAssured.when()
                .get("/getRestaurantReview/2")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .contentType(ContentType.JSON)
                .body("rrReview", CoreMatchers.equalTo(4F));
    }

    @Test
    public void testGetRestaurantReviewsByRrRestaurant(){
        RestAssured.when()
                .get("/getRestaurantReviewsByRrRestaurant/1")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .contentType(ContentType.JSON)
                .body("rrId", CoreMatchers.hasItems(1,2,3,4,5));
    }

    @Test
    public void testRemoveRestaurantReview(){
        RestaurantReview restaurantReview = new RestaurantReview();
        restaurantReview.setRrDate(new Date());
        restaurantReview.setRrRestaurant(restaurantService.findOne(1L));
        restaurantReview.setRrReview(4F);
        restaurantReview.setRrUser(userService.findOne(3L));

        RestaurantReview savedRestaurantReview = restaurantReviewService.save(restaurantReview);

        RestAssured.when()
                .delete("/removeRestaurantReview/" + savedRestaurantReview.getRrId())
                .then()
                .statusCode(HttpStatus.SC_NO_CONTENT);
    }

    @Test
    @Transactional
    public void testAddRestaurantReview(){
        RestaurantReview restaurantReview = new RestaurantReview();
        restaurantReview.setRrDate(new Date());
        restaurantReview.setRrRestaurant(restaurantService.findOne(1L));
        restaurantReview.setRrReview(4F);
        restaurantReview.setRrUser(userService.findOne(3L));

        RestAssured
                .given()
                .body(restaurantReview)
                .contentType(ContentType.JSON)
                .when()
                .post("/addRestaurantReview")
                .then()
                .statusCode(HttpStatus.SC_CREATED)
                .contentType(ContentType.JSON)
                .body("rrReview", CoreMatchers.equalTo(4F));
    }

    @Test
    @Transactional
    public void testUpdateRestaurantReview(){
        RestaurantReview restaurantReview = restaurantReviewService.findOne(4L);
        restaurantReview.setRrReview(1F);

        RestAssured
                .given()
                .body(restaurantReview)
                .contentType(ContentType.JSON)
                .when()
                .post("/updateRestaurantReview")
                .then()
                .statusCode(HttpStatus.SC_CREATED)
                .contentType(ContentType.JSON)
                .body("rrReview", CoreMatchers.equalTo(1F));
    }
}
