package ISAProject;


import ISAProject.controller.MenuController;
import ISAProject.model.Menu;
import ISAProject.service.MenuService;
import ISAProject.service.RestaurantService;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.hamcrest.CoreMatchers;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

public class MenuControllerIT extends AbstractIT{

    @Autowired
    MenuService menuService;

    @Autowired
    RestaurantService restaurantService;

    @Test
    public void testGetMenus(){
        RestAssured.when()
                .get("/getMenus")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .contentType(ContentType.JSON)
                .body("mId", CoreMatchers.hasItems(1,2,4,5,6));
    }

    @Test
    public void testGetMenu(){
        RestAssured.when()
                .get("/getMenu/2")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .contentType(ContentType.JSON)
                .body("mName", CoreMatchers.equalTo("Juice"));
    }

    @Test
    public void testGetMenusByMRestaurant(){
        RestAssured.when()
                .get("/getMenusByMRestaurant/1")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .contentType(ContentType.JSON)
                .body("mId", CoreMatchers.hasItems(1,2,4,5,6));
    }

    @Test
    public void testGetMenusByMRestaurantAndMType(){
        RestAssured.when()
                .get("/getMenusByMRestaurantAndMType/1/Drink")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .contentType(ContentType.JSON)
                .body("mId", CoreMatchers.hasItems(1,2));
    }

    @Test
    public void testRemoveMenu(){
        RestAssured.when()
                .delete("/removeMenu/3")
                .then()
                .statusCode(HttpStatus.SC_NO_CONTENT);
    }

    @Test
    @Transactional
    public void testAddMenu(){
        Menu menu = new Menu();
        menu.setmDescription("Desc");
        menu.setmName("MenuName");
        menu.setmPrice(500F);
        menu.setmRestaurant(restaurantService.findOne(1L));
        menu.setmReview(1F);
        menu.setmType("Salad");

        RestAssured
                .given()
                .body(menu)
                .contentType(ContentType.JSON)
                .when()
                .post("/addMenu")
                .then()
                .statusCode(HttpStatus.SC_CREATED)
                .contentType(ContentType.JSON)
                .body("mName", CoreMatchers.equalTo("MenuName"));
    }

    @Test
    @Transactional
    public void testUpdateMenu(){
        Menu menu = menuService.findOne(1L);
        menu.setmName("Wine");

        RestAssured
                .given()
                .body(menu)
                .contentType(ContentType.JSON)
                .when()
                .post("/updateMenu")
                .then()
                .statusCode(HttpStatus.SC_CREATED)
                .contentType(ContentType.JSON)
                .body("mName", CoreMatchers.equalTo("Wine"));
    }
}
