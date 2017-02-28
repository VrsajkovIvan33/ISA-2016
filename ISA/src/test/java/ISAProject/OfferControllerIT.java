package ISAProject;


import ISAProject.model.Offer;
import ISAProject.model.TenderItem;
import ISAProject.service.OfferService;
import ISAProject.service.ProviderService;
import ISAProject.service.TenderService;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.hamcrest.CoreMatchers;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

public class OfferControllerIT extends AbstractIT{

    @Autowired
    OfferService offerService;

    @Autowired
    ProviderService providerService;

    @Autowired
    TenderService tenderService;

    @Test
    public void testGetOffers(){
        RestAssured.when()
                .get("/getOffers")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .contentType(ContentType.JSON)
                .body("offId", CoreMatchers.hasItems(1,2,4,5));
    }

    @Test
    public void testGetOffer(){
        RestAssured.when()
                .get("/getOffer/1")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .contentType(ContentType.JSON)
                .body("offStatus", CoreMatchers.equalTo("On hold"));
    }

    @Test
    public void testGetOffersByOffTender(){
        RestAssured.when()
                .get("/getOffersByOffTender/1")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .contentType(ContentType.JSON)
                .body("offId", CoreMatchers.hasItems(4,5));
    }

    @Test
    public void testGetOffersByOffTenderAndOffStatus(){
        RestAssured.when()
                .get("/getOffersByOffTenderAndOffStatus/3/On hold")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .contentType(ContentType.JSON)
                .body("offId", CoreMatchers.hasItems(1));
    }

    @Test
    public void testGetOffersByOffProvider(){
        RestAssured.when()
                .get("/getOffersByOffProvider/9")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .contentType(ContentType.JSON)
                .body("offId", CoreMatchers.hasItems(1,2,4));
    }

    @Test
    public void testGetOffersByOffProviderAndOffStatus(){
        RestAssured.when()
                .get("/getOffersByOffProviderAndOffStatus/9/Canceled")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .contentType(ContentType.JSON)
                .body("offId", CoreMatchers.hasItems(2,4));
    }

    @Test
    public void testGetOffersByOffTenderAndOffProvider(){
        RestAssured.when()
                .get("/getOffersByOffTenderAndOffProvider/3/9")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .contentType(ContentType.JSON)
                .body("offId", CoreMatchers.equalTo(1));
    }

    @Test
    public void testRemoveOffer(){
        RestAssured.when()
                .delete("/removeOffer/3")
                .then()
                .statusCode(HttpStatus.SC_NO_CONTENT);
    }

    @Test
    @Transactional
    public void testAddOffer(){
        Offer offer = new Offer();
        offer.setOffStatus("On hold");
        offer.setOffProvider(providerService.findOne(10L));
        offer.setOffTender(tenderService.findOne(4L));

        RestAssured
                .given()
                .body(offer)
                .contentType(ContentType.JSON)
                .when()
                .post("/addOffer")
                .then()
                .statusCode(HttpStatus.SC_CREATED)
                .contentType(ContentType.JSON)
                .body("offStatus", CoreMatchers.equalTo("On hold"));
    }

    @Test
    @Transactional
    public void testUpdateOffer(){
        Offer offer = offerService.findOne(5L);
        offer.setOffStatus("Accepted");

        RestAssured
                .given()
                .body(offer)
                .contentType(ContentType.JSON)
                .when()
                .post("/updateOffer")
                .then()
                .statusCode(HttpStatus.SC_CREATED)
                .contentType(ContentType.JSON)
                .body("offStatus", CoreMatchers.equalTo("Accepted"));
    }
}
