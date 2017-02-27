package ISAProject;

import ISAProject.model.Restaurant;
import ISAProject.model.RestaurantReview;
import ISAProject.service.RestaurantReviewService;
import ISAProject.service.RestaurantService;
import ISAProject.service.UserService;
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
public class RestaurantReviewServiceTest {

    @Autowired
    RestaurantReviewService restaurantReviewService;

    @Autowired
    RestaurantService restaurantService;

    @Autowired
    UserService userService;

    @Test
    public void testFindAll(){
        List<RestaurantReview> restaurantReviews = restaurantReviewService.findAll();
        Assert.assertNotNull(restaurantReviews);
    }

    @Test
    public void testFindOne(){
        RestaurantReview restaurantReview = restaurantReviewService.findOne(2L);
        Assert.assertEquals(2L, restaurantReview.getRrId().longValue());
    }

    @Test
    public void testSave(){
        RestaurantReview restaurantReview = new RestaurantReview();
        restaurantReview.setRrDate(new Date());
        restaurantReview.setRrRestaurant(restaurantService.findOne(1L));
        restaurantReview.setRrReview(4F);
        restaurantReview.setRrUser(userService.findOne(3L));

        RestaurantReview savedRestaurantReview = restaurantReviewService.save(restaurantReview);
        RestaurantReview loadedRestaurantReview = restaurantReviewService.findOne(savedRestaurantReview.getRrId());

        Assert.assertNotNull(savedRestaurantReview);
        Assert.assertNotNull(loadedRestaurantReview);
        Assert.assertEquals(savedRestaurantReview.getRrId(), loadedRestaurantReview.getRrId());
    }

    @Test
    public void testDelete(){
        RestaurantReview restaurantReview = new RestaurantReview();
        restaurantReview.setRrDate(new Date());
        restaurantReview.setRrRestaurant(restaurantService.findOne(1L));
        restaurantReview.setRrReview(4F);
        restaurantReview.setRrUser(userService.findOne(3L));

        RestaurantReview savedRestaurantReview = restaurantReviewService.save(restaurantReview);
        restaurantReviewService.delete(savedRestaurantReview.getRrId());
        RestaurantReview loadedRestaurantReview = restaurantReviewService.findOne(savedRestaurantReview.getRrId());

        Assert.assertNull(loadedRestaurantReview);
    }

    @Test
    @Transactional
    public void findByRrRestaurant(){
        Restaurant restaurant = restaurantService.findOne(1L);
        List<RestaurantReview> restaurantReviews = restaurantReviewService.findByRrRestaurant(restaurant);
        Assert.assertNotNull(restaurantReviews);
        for (RestaurantReview restaurantReview : restaurantReviews) {
            Assert.assertEquals(restaurant.getId(), restaurantReview.getRrRestaurant().getId());
        }
    }
}
