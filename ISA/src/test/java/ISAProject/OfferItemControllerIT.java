package ISAProject;


import ISAProject.model.Offer;
import ISAProject.model.OfferItem;
import ISAProject.service.OfferItemService;
import ISAProject.service.OfferService;
import ISAProject.service.TenderItemService;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.hamcrest.CoreMatchers;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

public class OfferItemControllerIT extends AbstractIT{

    @Autowired
    OfferItemService offerItemService;

    @Autowired
    OfferService offerService;

    @Autowired
    TenderItemService tenderItemService;

    @Test
    public void testGetOfferItems(){
        RestAssured.when()
                .get("/getOfferItems")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .contentType(ContentType.JSON)
                .body("offiId", CoreMatchers.hasItems(2,3));
    }

    @Test
    public void testGetOfferItem(){
        RestAssured.when()
                .get("/getOfferItem/2")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .contentType(ContentType.JSON)
                .body("offiPrice", CoreMatchers.equalTo(300F));
    }

    @Test
    public void testGetOfferItemsByOffiOffer(){
        RestAssured.when()
                .get("/getOfferItemsByOffiOffer/1")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .contentType(ContentType.JSON)
                .body("offiId", CoreMatchers.hasItems(2,3));
    }

    @Test
    public void testGetOfferItemsByOffiTenderItem(){
        RestAssured.when()
                .get("/getOfferItemsByOffiTenderItem/8")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .contentType(ContentType.JSON)
                .body("offiId", CoreMatchers.hasItems(3));
    }

    @Test
    public void testRemoveOfferItem(){
        RestAssured.when()
                .delete("/removeOfferItem/1")
                .then()
                .statusCode(HttpStatus.SC_NO_CONTENT);
    }

    @Test
    @Transactional
    public void testAddOfferItem(){
        OfferItem offerItem = new OfferItem();
        offerItem.setOffiDeliveryTime("5 days");
        offerItem.setOffiGuarantee("No guarantee");
        offerItem.setOffiOffer(offerService.findOne(1L));
        offerItem.setOffiPrice(250F);
        offerItem.setOffiTenderItem(tenderItemService.findOne(6L));

        RestAssured
                .given()
                .body(offerItem)
                .contentType(ContentType.JSON)
                .when()
                .post("/addOfferItem")
                .then()
                .statusCode(HttpStatus.SC_CREATED)
                .contentType(ContentType.JSON)
                .body("offiPrice", CoreMatchers.equalTo(250F));
    }

    @Test
    @Transactional
    public void testUpdateOfferItem(){
        OfferItem offerItem = offerItemService.findOne(2L);
        offerItem.setOffiDeliveryTime("2 days");

        RestAssured
                .given()
                .body(offerItem)
                .contentType(ContentType.JSON)
                .when()
                .post("/updateOfferItem")
                .then()
                .statusCode(HttpStatus.SC_CREATED)
                .contentType(ContentType.JSON)
                .body("offiDeliveryTime", CoreMatchers.equalTo("2 days"));
    }
}
