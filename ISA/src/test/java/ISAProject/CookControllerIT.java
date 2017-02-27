package ISAProject;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.hamcrest.CoreMatchers;
import org.junit.Test;

/**
 * Created by Nole on 2/27/2017.
 */
public class CookControllerIT extends AbstractIT {

    @Test
    public void testGetCookById(){
        RestAssured.when()
                .get("/getCooks/14")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .contentType(ContentType.JSON)
                .body("name", CoreMatchers.equalTo("Novica"));
    }
}
