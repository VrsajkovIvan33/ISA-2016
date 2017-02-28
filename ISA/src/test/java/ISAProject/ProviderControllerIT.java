package ISAProject;

import ISAProject.model.users.Provider;
import ISAProject.model.users.UserType;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.hamcrest.CoreMatchers;
import org.junit.Test;


public class ProviderControllerIT extends AbstractIT{

    @Test
    public void testGetProviders(){
        RestAssured.when()
                .get("/getProviders")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .contentType(ContentType.JSON)
                .body("id", CoreMatchers.hasItems(9, 11));
    }

    @Test
    public void testRemoveProvider(){
        RestAssured.when()
                .delete("/removeProvider/10")
                .then()
                .statusCode(HttpStatus.SC_NO_CONTENT);
    }

    @Test
    public void testAddProvider(){
        Provider provider = new Provider();
        provider.setpPasswordChanged(false);
        provider.setRestaurants(null);
        provider.setEmail("provider123@gmail.com");
        provider.setName("Name");
        provider.setSurname("Surname");
        provider.setPassword("Password");
        provider.setType(UserType.PROVIDER);

        RestAssured
                .given()
                .body(provider)
                .contentType(ContentType.JSON)
                .when()
                .post("/addProvider")
                .then()
                .statusCode(HttpStatus.SC_CREATED)
                .contentType(ContentType.JSON)
                .body("email", CoreMatchers.equalTo("provider123@gmail.com"));
    }

    @Test
    public void testUpdateProvider(){
        Provider provider = new Provider();
        provider.setpPasswordChanged(false);
        provider.setRestaurants(null);
        provider.setEmail("provider456@gmail.com");
        provider.setName("Name");
        provider.setSurname("Surname");
        provider.setPassword("Password");
        provider.setType(UserType.PROVIDER);
        provider.setId(9L);
        provider.setVersion(0);

        RestAssured
                .given()
                .body(provider)
                .contentType(ContentType.JSON)
                .when()
                .post("/updateProvider")
                .then()
                .statusCode(HttpStatus.SC_CREATED)
                .contentType(ContentType.JSON)
                .body("email", CoreMatchers.equalTo("provider456@gmail.com"));
    }

    @Test
    public void testSearchProviders(){
        RestAssured.when()
                .get("/searchProviders/proName2/1")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .contentType(ContentType.JSON)
                .body("id", CoreMatchers.hasItems(11));
    }
}
