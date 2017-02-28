package ISAProject;


import ISAProject.model.users.Guest;
import ISAProject.service.GuestService;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.hamcrest.CoreMatchers;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class GuestControllerIT extends AbstractIT{

    @Autowired
    GuestService guestService;

    @Test
    public void getFriendsTest(){
        RestAssured.when()
                .get("/getFriends/4")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body("id", CoreMatchers.hasItems());
    }

    @Test
    public void ignoreFriendRequestTest(){
        Guest user = guestService.findOne(3L);
        Guest friend = guestService.findOne(4L);

        user.getPendingList().add(friend);
        friend.getSentList().add(user);
        guestService.save((Guest)user);
        guestService.save((Guest)friend);

        RestAssured.when()
                .get("/ignoreFriendRequest/4/3")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .contentType(ContentType.JSON);
    }

    @Test
    public void getFriendRequestsNumberTest(){
        RestAssured.when()
                .get("/getFriendRequestsNumber/1")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .contentType(ContentType.JSON);
    }

    @Test
    public void getFriendRequestsTest(){
        RestAssured.when()
                .get("/getFriendRequests/1")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .contentType(ContentType.JSON)
                .body("id", CoreMatchers.hasItems());
    }
}
