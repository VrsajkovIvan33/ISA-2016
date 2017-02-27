package ISAProject;

import com.jayway.restassured.RestAssured;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.internal.MethodSorter;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public abstract class AbstractIT {

    @LocalServerPort
    private int serverPort;

    @Before
    public void setUp(){
        RestAssured.port = serverPort;
    }
}
