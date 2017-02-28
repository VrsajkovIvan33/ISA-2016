package ISAProject;

import ISAProject.model.Tender;
import ISAProject.model.users.Provider;
import ISAProject.model.users.UserType;
import ISAProject.service.RestaurantService;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.hamcrest.CoreMatchers;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;


public class TenderControllerIT extends AbstractIT{

    @Autowired
    RestaurantService restaurantService;

    @Test
    public void testGetTenders(){
        RestAssured.when()
                .get("/getTenders")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .contentType(ContentType.JSON)
                .body("tId", CoreMatchers.hasItems(2,3,4,5));
    }

    @Test
    public void testGetTender(){
        RestAssured.when()
                .get("/getTender/5")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .contentType(ContentType.JSON)
                .body("tStatus", CoreMatchers.equalTo("Active"));
    }

    @Test
    public void testGetTendersByTRestaurant(){
        RestAssured.when()
                .get("/getTendersByTRestaurant/2")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .contentType(ContentType.JSON)
                .body("tId", CoreMatchers.hasItems(4,5));
    }

    @Test
    public void testGetTendersByTRestaurantAndTStatus(){
        RestAssured.when()
                .get("/getTendersByTRestaurantAndTStatus/2/Active")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .contentType(ContentType.JSON)
                .body("tId", CoreMatchers.hasItems(5));
    }

    @Test
    public void testRemoveTender(){
        RestAssured.when()
                .delete("/removeTender/1")
                .then()
                .statusCode(HttpStatus.SC_NO_CONTENT);
    }

    @Test
    public void testAddTender(){
        Tender tender = new Tender();
        tender.settStatus("Active");
        tender.settRestaurant(restaurantService.findOne(1L));
        tender.settEnd(new Date());
        tender.settStart(new Date());

        RestAssured
                .given()
                .body(tender)
                .contentType(ContentType.JSON)
                .when()
                .post("/addTender")
                .then()
                .statusCode(HttpStatus.SC_CREATED)
                .contentType(ContentType.JSON)
                .body("tStatus", CoreMatchers.equalTo("Active"));
    }

    @Test
    public void testUpdateTender(){
        Tender tender = new Tender();
        tender.settStatus("Closed");
        tender.settRestaurant(restaurantService.findOne(1L));
        tender.settEnd(new Date());
        tender.settStart(new Date());
        tender.settId(1L);
        tender.setVersion(1);

        RestAssured
                .given()
                .body(tender)
                .contentType(ContentType.JSON)
                .when()
                .post("/updateTender")
                .then()
                .statusCode(HttpStatus.SC_CREATED)
                .contentType(ContentType.JSON)
                .body("tStatus", CoreMatchers.equalTo("Closed"));
    }
}
