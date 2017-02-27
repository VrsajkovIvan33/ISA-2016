package ISAProject;

import ISAProject.model.Restaurant;
import ISAProject.model.WaiterReview;
import ISAProject.model.users.Waiter;
import ISAProject.service.RestaurantService;
import ISAProject.service.WaiterReviewService;
import ISAProject.service.WaiterService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Isa2016Application.class)
@WebAppConfiguration
public class WaiterReviewServiceTest {

    @Autowired
    WaiterReviewService waiterReviewService;

    @Autowired
    RestaurantService restaurantService;

    @Autowired
    WaiterService waiterService;

    @Test
    public void testFindAll(){
        List<WaiterReview> waiterReviews = waiterReviewService.findAll();
        Assert.assertNotNull(waiterReviews);
    }

    @Test
    public void testFindOne(){
        WaiterReview waiterReview = waiterReviewService.findOne(2L);
        Assert.assertEquals(2L, waiterReview.getWrId().longValue());
    }

    @Test
    public void testSave(){
        WaiterReview waiterReview = new WaiterReview();
        waiterReview.setWrDate(new Date());
        waiterReview.setWrRestaurant(restaurantService.findOne(1L));
        waiterReview.setWrReview(4F);
        waiterReview.setWrWaiter(waiterService.findOne(6L));

        WaiterReview savedWaiterReview = waiterReviewService.save(waiterReview);
        WaiterReview loadedWaiterReview = waiterReviewService.findOne(savedWaiterReview.getWrId());

        Assert.assertNotNull(savedWaiterReview);
        Assert.assertNotNull(loadedWaiterReview);
        Assert.assertEquals(savedWaiterReview.getWrId(), loadedWaiterReview.getWrId());
    }

    @Test
    public void testDelete(){
        WaiterReview waiterReview = new WaiterReview();
        waiterReview.setWrDate(new Date());
        waiterReview.setWrRestaurant(restaurantService.findOne(1L));
        waiterReview.setWrReview(4F);
        waiterReview.setWrWaiter(waiterService.findOne(6L));

        WaiterReview savedWaiterReview = waiterReviewService.save(waiterReview);
        waiterReviewService.delete(savedWaiterReview.getWrId());
        WaiterReview loadedWaiterReview = waiterReviewService.findOne(savedWaiterReview.getWrId());

        Assert.assertNull(loadedWaiterReview);
    }

    @Test
    @Transactional
    public void findByWrRestaurant(){
        Restaurant restaurant = restaurantService.findOne(1L);
        List<WaiterReview> waiterReviews = waiterReviewService.findByWrRestaurant(restaurant);
        Assert.assertNotNull(waiterReviews);
        for (WaiterReview waiterReview : waiterReviews) {
            Assert.assertEquals(restaurant.getId(), waiterReview.getWrRestaurant().getId());
        }
    }

    @Test
    @Transactional
    public void findByWrWaiter(){
        Waiter waiter = waiterService.findOne(6L);
        List<WaiterReview> waiterReviews = waiterReviewService.findByWrWaiter(waiter);
        Assert.assertNotNull(waiterReviews);
        for (WaiterReview waiterReview : waiterReviews) {
            Assert.assertEquals(waiter.getId(), waiterReview.getWrWaiter().getId());
        }
    }
}
