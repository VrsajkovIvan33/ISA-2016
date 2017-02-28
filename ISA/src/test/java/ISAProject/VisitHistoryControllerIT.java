package ISAProject;

import ISAProject.model.VisitHistory;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.hamcrest.CoreMatchers;
import org.junit.Test;

public class VisitHistoryControllerIT extends AbstractIT {

    @Test
    public void testGetVisitHistoriesByGuest(){
        RestAssured.when()
                .get("/VisitHistories/1")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .contentType(ContentType.JSON)
                .body("id", CoreMatchers.hasItem(1));
    }

    @Test
    public void testPutVisitHistory(){

        VisitHistory visitHistory = new VisitHistory();
        visitHistory.setId(1L);
        visitHistory.setMenuGrade(3);
        visitHistory.setServiceGrade(3);
        visitHistory.setRestaurantGrade(3);

        RestAssured
                .given()
                .body(visitHistory)
                .contentType(ContentType.JSON)
                .when()
                .put("/UpdateHistory")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .contentType(ContentType.JSON)
                .body("id", CoreMatchers.is(1));

    }

}
