package ISAProject;

import ISAProject.model.Tender;
import ISAProject.model.TenderItem;
import ISAProject.service.TenderItemService;
import ISAProject.service.TenderService;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.hamcrest.CoreMatchers;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;


public class TenderItemControllerIT extends AbstractIT{

    @Autowired
    TenderService tenderService;

    @Autowired
    TenderItemService tenderItemService;

    @Test
    public void testGetTenderItems(){
        RestAssured.when()
                .get("/getTenderItems")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .contentType(ContentType.JSON)
                .body("tiId", CoreMatchers.hasItems(1,2,3,4,5,6,7,8,10,11));
    }

    @Test
    public void testGetTenderItem(){
        RestAssured.when()
                .get("/getTenderItem/10")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .contentType(ContentType.JSON)
                .body("tiName", CoreMatchers.equalTo("Coca Cola"));
    }

    @Test
    public void testGetTenderItemsByTiTender(){
        RestAssured.when()
                .get("/getTenderItemsByTiTender/5")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .contentType(ContentType.JSON)
                .body("tiId", CoreMatchers.hasItems(10,11));
    }

    @Test
    public void testRemoveTenderItem(){
        RestAssured.when()
                .delete("/removeTenderItem/9")
                .then()
                .statusCode(HttpStatus.SC_NO_CONTENT);
    }

    @Test
    @Transactional
    public void testAddTenderItem(){
        TenderItem tenderItem = new TenderItem();
        tenderItem.setTiName("Milk");
        tenderItem.setTiQuantity("50 l");
        tenderItem.setTiTender(tenderService.findOne(2L));
        tenderItem.setTiType("Foodstuff");

        RestAssured
                .given()
                .body(tenderItem)
                .contentType(ContentType.JSON)
                .when()
                .post("/addTenderItem")
                .then()
                .statusCode(HttpStatus.SC_CREATED)
                .contentType(ContentType.JSON)
                .body("tiName", CoreMatchers.equalTo("Milk"));
    }

    @Test
    @Transactional
    public void testUpdateTenderItem(){
        TenderItem tenderItem = tenderItemService.findOne(11L);
        tenderItem.setTiName("Cabbage");

        RestAssured
                .given()
                .body(tenderItem)
                .contentType(ContentType.JSON)
                .when()
                .post("/updateTenderItem")
                .then()
                .statusCode(HttpStatus.SC_CREATED)
                .contentType(ContentType.JSON)
                .body("tiName", CoreMatchers.equalTo("Cabbage"));
    }
}
