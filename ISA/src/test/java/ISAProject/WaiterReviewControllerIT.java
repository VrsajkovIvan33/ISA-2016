package ISAProject;


import ISAProject.model.WaiterReview;
import ISAProject.service.RestaurantService;
import ISAProject.service.WaiterReviewService;
import ISAProject.service.WaiterService;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.hamcrest.CoreMatchers;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

public class WaiterReviewControllerIT extends AbstractIT{

    @Autowired
    WaiterReviewService waiterReviewService;

    @Autowired
    WaiterService waiterService;

    @Autowired
    RestaurantService restaurantService;

    @Test
    public void testGetWaiterReviews(){
        RestAssured.when()
                .get("/getWaiterReviews")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .contentType(ContentType.JSON)
                .body("wrId", CoreMatchers.hasItems(1,3,4,5));
    }

    @Test
    public void testGetWaiterReview(){
        RestAssured.when()
                .get("/getWaiterReview/4")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .contentType(ContentType.JSON)
                .body("wrReview", CoreMatchers.equalTo(3F));
    }

    @Test
    public void testGetWaiterReviewsByWrRestaurant(){
        RestAssured.when()
                .get("/getWaiterReviewsByWrRestaurant/1")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .contentType(ContentType.JSON)
                .body("wrId", CoreMatchers.hasItems(1,3,4,5));
    }

    @Test
    public void testGetWaiterReviewsByWrWaiter(){
        RestAssured.when()
                .get("/getWaiterReviewsByWrWaiter/7")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .contentType(ContentType.JSON)
                .body("wrId", CoreMatchers.hasItems(3,4,5));
    }

    @Test
    public void testRemoveWaiterReview(){
        RestAssured.when()
                .delete("/removeWaiterReview/2")
                .then()
                .statusCode(HttpStatus.SC_NO_CONTENT);
    }

    @Test
    @Transactional
    public void testAddWaiterReview(){
        WaiterReview waiterReview = new WaiterReview();
        waiterReview.setWrDate(new Date());
        waiterReview.setWrRestaurant(restaurantService.findOne(1L));
        waiterReview.setWrReview(4F);
        waiterReview.setWrWaiter(waiterService.findOne(6L));

        RestAssured
                .given()
                .body(waiterReview)
                .contentType(ContentType.JSON)
                .when()
                .post("/addWaiterReview")
                .then()
                .statusCode(HttpStatus.SC_CREATED)
                .contentType(ContentType.JSON)
                .body("wrReview", CoreMatchers.equalTo(4F));
    }

    @Test
    @Transactional
    public void testUpdateWaiterReview(){
        WaiterReview waiterReview = waiterReviewService.findOne(5L);
        waiterReview.setWrReview(1F);

        RestAssured
                .given()
                .body(waiterReview)
                .contentType(ContentType.JSON)
                .when()
                .post("/updateWaiterReview")
                .then()
                .statusCode(HttpStatus.SC_CREATED)
                .contentType(ContentType.JSON)
                .body("wrReview", CoreMatchers.equalTo(1F));
    }
}
