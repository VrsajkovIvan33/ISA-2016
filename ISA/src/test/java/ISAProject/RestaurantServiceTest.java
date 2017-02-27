package ISAProject;

import ISAProject.model.Restaurant;
import ISAProject.service.RestaurantService;
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
public class RestaurantServiceTest {

    @Autowired
    RestaurantService restaurantService;

    @Test
    public void testFindAll() {
        List<Restaurant> restaurants = restaurantService.findAll();
        Assert.assertNotNull(restaurants);
    }

    @Test
    public void testFindOne() {
        Restaurant restaurant = restaurantService.findOne(1L);
        Assert.assertEquals(1L, restaurant.getId().longValue());
    }

    @Test
    public void testFindByName() {
        List<Restaurant> restaurants = restaurantService.findByName("Bob's Country Bunker");
        Assert.assertNotNull(restaurants);
        for (Restaurant restaurant : restaurants) {
            Assert.assertEquals("Bob's Country Bunker", restaurant.getrName());
        }
    }

    @Test
    public void testFindByType() {
        List<Restaurant> restaurants = restaurantService.findByName("Vegan");
        Assert.assertNotNull(restaurants);
        for (Restaurant restaurant : restaurants) {
            Assert.assertEquals("Vegan", restaurant.getrName());
        }
    }

    @Test
    public void testSave() {
        Restaurant restaurant = new Restaurant();
        restaurant.setrName("TestName");
        restaurant.setrType("Vegan");

        Restaurant savedRestaurant = restaurantService.save(restaurant);
        Restaurant loadedRestaurant = restaurantService.findOne(savedRestaurant.getId());

        Assert.assertNotNull(savedRestaurant);
        Assert.assertNotNull(loadedRestaurant);
        Assert.assertEquals(savedRestaurant.getId(), loadedRestaurant.getId());
    }

    @Test
    public void testDelete() {
        Restaurant restaurant = new Restaurant();
        restaurant.setrName("TestName");
        restaurant.setrType("Vegan");

        Restaurant savedRestaurant = restaurantService.save(restaurant);
        restaurantService.delete(savedRestaurant.getId());
        Restaurant loadedRestaurant = restaurantService.findOne(savedRestaurant.getId());

        Assert.assertNull(loadedRestaurant);
    }

}
