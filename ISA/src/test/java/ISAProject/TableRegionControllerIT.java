package ISAProject;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.hamcrest.CoreMatchers;
import org.junit.Test;

public class TableRegionControllerIT extends AbstractIT {

    @Test
    public void testGetTableRegions(){
        RestAssured.when()
                .get("/TableRegions")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .contentType(ContentType.JSON)
                .body("id", CoreMatchers.hasItems(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));

    }

}
