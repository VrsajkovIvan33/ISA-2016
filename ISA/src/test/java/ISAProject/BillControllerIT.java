package ISAProject;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.hamcrest.CoreMatchers;
import org.junit.Test;

public class BillControllerIT extends AbstractIT {

    @Test
    public void testGetBillsByWaiter(){
        RestAssured.when()
                .get("/getBillsByWaiter/6")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .contentType(ContentType.JSON)
                .body("id", CoreMatchers.hasItems(1, 2, 4, 6, 7, 9, 10, 11, 12, 14, 16, 17, 19, 20, 21, 22, 24));

    }

    @Test
    public void testGetBillsByRestaurant(){
        RestAssured.when()
                .get("/getBillsByRestaurant/2")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .contentType(ContentType.JSON)
                .body("id", CoreMatchers.hasItems(5));

    }
}
