package ISAProject;

import ISAProject.model.Order;
import ISAProject.model.OrderItem;
import ISAProject.service.WaiterService;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.hamcrest.CoreMatchers;
import org.hamcrest.Matchers;
import org.junit.Test;

import java.util.ArrayList;

import static org.hamcrest.Matchers.isEmptyOrNullString;

public class OrderControllerIT extends AbstractIT {

    @Test
    public void testGetOrdersUnassignedByUser() {
        RestAssured.when()
                .get("/OrdersUnassignedByUser/7")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .contentType(ContentType.JSON)
                .body("", Matchers.hasSize(0));
    }

    @Test
    public void testGetOrdersByWaiter() {
        RestAssured.when()
                .get("/OrdersByWaiter/6")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .contentType(ContentType.JSON)
                .body("", Matchers.hasSize(0));
    }

    @Test
    public void testGetOrderById() {
        RestAssured.when()
                .get("/Orders/1")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .contentType(ContentType.JSON)
                .body("id", CoreMatchers.is(1));
    }

    @Test
    public void testPostOrder() {

        Order order = new Order();
        order.setoStatus("Waiting for waiter");
        order.setoAssigned(false);
        order.setBillCreated(false);

        RestAssured
                .given()
                .body(order)
                .contentType(ContentType.JSON)
                .when()
                .post("/Orders")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .contentType(ContentType.JSON)
                .body("id", CoreMatchers.is(3))
                .body("oStatus", CoreMatchers.is("Waiting for waiter"))
                .body("oAssigned", CoreMatchers.is(false))
                .body("billCreated", CoreMatchers.is(false));
    }

    @Test
    public void testPostPutOrder() {

        Order order = new Order();
        order.setId(1L);
        order.setoStatus("Waiting");
        order.setoAssigned(true);
        order.setBillCreated(false);
        order.setOrderItems(new ArrayList<OrderItem>());

        RestAssured
                .given()
                .body(order)
                .contentType(ContentType.JSON)
                .when()
                .put("/Orders")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .contentType(ContentType.JSON)
                .body("id", CoreMatchers.is(1))
                .body("oStatus", CoreMatchers.is("Waiting"))
                .body("oAssigned", CoreMatchers.is(true))
                .body("billCreated", CoreMatchers.is(false));
    }

    @Test
    public void testRemoveOrder() {
        RestAssured.when()
                .delete("/removeOrder/3")
                .then()
                .statusCode(HttpStatus.SC_NO_CONTENT)
                .contentType(isEmptyOrNullString());
    }
}
