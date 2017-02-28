package ISAProject;


import ISAProject.model.Restaurant;
import ISAProject.service.RestaurantService;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.hamcrest.CoreMatchers;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

public class RestaurantControllerIT extends AbstractIT{

    @Autowired
    RestaurantService restaurantService;

    @Test
    public void testGetRestaurants(){
        RestAssured.when()
                .get("/getRestaurants")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .contentType(ContentType.JSON)
                .body("id", CoreMatchers.hasItems(1,2,3,4));
    }

    @Test
    public void testRemoveRestaurant(){
        Restaurant restaurant = new Restaurant();
        restaurant.setrName("TestName");
        restaurant.setrType("Vegan");
        restaurant.setLatitude(12.3654);
        restaurant.setLongitude(15.8564);

        Restaurant savedRestaurant = restaurantService.save(restaurant);

        RestAssured.when()
                .delete("/removeRestaurant/" + savedRestaurant.getId())
                .then()
                .statusCode(HttpStatus.SC_NO_CONTENT);
    }

    @Test
    @Transactional
    public void testAddRestaurant(){
        Restaurant restaurant = new Restaurant();
        restaurant.setrName("TestName");
        restaurant.setrType("Vegan");
        restaurant.setLatitude(12.3654);
        restaurant.setLongitude(15.8564);

        RestAssured
                .given()
                .body(restaurant)
                .contentType(ContentType.JSON)
                .when()
                .post("/addRestaurant")
                .then()
                .statusCode(HttpStatus.SC_CREATED)
                .contentType(ContentType.JSON)
                .body("rName", CoreMatchers.equalTo("TestName"));
    }

    @Test
    @Transactional
    public void testUpdateRestaurant(){
        Restaurant restaurant = restaurantService.findOne(2L);
        restaurant.setrName("NewName");

        RestAssured
                .given()
                .body(restaurant)
                .contentType(ContentType.JSON)
                .when()
                .post("/updateRestaurant")
                .then()
                .statusCode(HttpStatus.SC_CREATED)
                .contentType(ContentType.JSON)
                .body("rName", CoreMatchers.equalTo("NewName"));
    }
}
