package ISAProject;

import ISAProject.model.RestaurantTable;
import ISAProject.service.RestaurantSegmentService;
import ISAProject.service.TableRegionService;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.hamcrest.CoreMatchers;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

public class RestaurantTableControllerIT extends AbstractIT {

    @Autowired
    RestaurantSegmentService restaurantSegmentService;

    @Autowired
    TableRegionService tableRegionService;

    @Test
    public void testGetTablesByRestaurant(){
        RestAssured.when()
                .get("/RestaurantTablesByRestaurant/1")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .contentType(ContentType.JSON)
                .body("", Matchers.hasSize(50));

    }

    @Test
    public void testGetActiveTablesByRestaurant(){
        RestAssured.when()
                .get("/RestaurantTablesActiveByRestaurant/1")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .contentType(ContentType.JSON)
                .body("", Matchers.hasSize(46));

    }

    @Test
    public void testGetTablesAll(){
        RestAssured.when()
                .get("/RestaurantTables")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .contentType(ContentType.JSON)
                .body("", Matchers.hasSize(50));

    }

    @Test
    @Transactional
    public void testPutTables(){

        RestaurantTable restaurantTable = new RestaurantTable();
        restaurantTable.setId(2L);
        restaurantTable.setRtNumber(55);
        restaurantTable.setRtActive(true);
        restaurantTable.setTableRegion(tableRegionService.findById(2L));
        restaurantTable.setRestaurantSegment(restaurantSegmentService.findByRsId(2L));
        List<RestaurantTable> tables = new ArrayList<RestaurantTable>();
        tables.add(restaurantTable);

        RestAssured
                .given()
                .body(tables)
                .contentType(ContentType.JSON)
                .when()
                .put("/RestaurantTables")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .contentType(ContentType.JSON)
                .body("", Matchers.hasSize(1))
                .body("id", CoreMatchers.hasItem(2))
                .body("rtNumber", CoreMatchers.hasItem(55));

    }


}
