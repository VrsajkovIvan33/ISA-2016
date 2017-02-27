package ISAProject;

import ISAProject.model.RestaurantSegment;
import ISAProject.service.RestaurantSegmentService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Isa2016Application.class)
@WebAppConfiguration
public class RestaurantSegmentServiceTest {

    @Autowired
    RestaurantSegmentService restaurantSegmentService;

    @Test
    public void testFindAll() {
        List<RestaurantSegment> restaurantSegments = restaurantSegmentService.findAll();
        Assert.assertNotNull(restaurantSegments);
    }

    @Test
    public void testFindByRsId() {
        RestaurantSegment restaurantSegment = restaurantSegmentService.findByRsId(1l);
        Assert.assertEquals(1L, restaurantSegment.getRsId().longValue());
    }

    @Test
    public void testFindByRsName() {
        RestaurantSegment restaurantSegment = restaurantSegmentService.findByRsName("Indoors");
        Assert.assertEquals("Indoors", restaurantSegment.getRsName());
    }

}
