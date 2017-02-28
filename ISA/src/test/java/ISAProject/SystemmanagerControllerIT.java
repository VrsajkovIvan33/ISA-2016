package ISAProject;


import ISAProject.model.OfferItem;
import ISAProject.model.users.SystemManager;
import ISAProject.model.users.UserType;
import ISAProject.service.SystemmanagerService;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.hamcrest.CoreMatchers;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

public class SystemmanagerControllerIT extends AbstractIT{

    @Autowired
    SystemmanagerService systemmanagerService;

    @Test
    public void testGetSystemManagers(){
        RestAssured.when()
                .get("/getSystemManagers")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .contentType(ContentType.JSON)
                .body("id", CoreMatchers.hasItems(5));
    }

    @Test
    public void testRemoveSystemManager(){
        SystemManager systemManager = new SystemManager();
        systemManager.setType(UserType.SYSTEMMANAGER);
        systemManager.setEmail("sysman@gmail.com");
        systemManager.setName("Name");
        systemManager.setSurname("Surname");
        systemManager.setPassword("password");

        SystemManager savedSystemManager = systemmanagerService.save(systemManager);

        RestAssured.when()
                .delete("/removeSystemManager/" + savedSystemManager.getId())
                .then()
                .statusCode(HttpStatus.SC_NO_CONTENT);
    }

    @Test
    @Transactional
    public void testAddSystemManager(){
        SystemManager systemManager = new SystemManager();
        systemManager.setType(UserType.SYSTEMMANAGER);
        systemManager.setEmail("manager@gmail.com");
        systemManager.setName("NameMan");
        systemManager.setSurname("SurnameMan");
        systemManager.setPassword("password");

        RestAssured
                .given()
                .body(systemManager)
                .contentType(ContentType.JSON)
                .when()
                .post("/addSystemManager")
                .then()
                .statusCode(HttpStatus.SC_CREATED)
                .contentType(ContentType.JSON)
                .body("email", CoreMatchers.equalTo("manager@gmail.com"));
    }

    @Test
    @Transactional
    public void testUpdateSystemManager(){
        SystemManager systemManager = systemmanagerService.findOne(5L);
        systemManager.setEmail("manager123@gmail.com");

        RestAssured
                .given()
                .body(systemManager)
                .contentType(ContentType.JSON)
                .when()
                .post("/updateSystemManager")
                .then()
                .statusCode(HttpStatus.SC_CREATED)
                .contentType(ContentType.JSON)
                .body("email", CoreMatchers.equalTo("manager123@gmail.com"));
    }
}
