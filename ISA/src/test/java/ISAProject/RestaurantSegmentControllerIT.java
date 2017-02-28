package ISAProject;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.hamcrest.CoreMatchers;
import org.junit.Test;

public class RestaurantSegmentControllerIT extends AbstractIT {

    @Test
    public void testGetRestaurantSegments(){
        RestAssured.when()
                .get("/RestaurantSegments")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .contentType(ContentType.JSON)
                .body("rsId", CoreMatchers.hasItems(1, 2, 3, 4));

    }

}
