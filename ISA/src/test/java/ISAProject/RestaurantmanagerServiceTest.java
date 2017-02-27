package ISAProject;

import ISAProject.model.Restaurant;
import ISAProject.model.users.RestaurantManager;
import ISAProject.model.users.UserType;
import ISAProject.service.RestaurantService;
import ISAProject.service.RestaurantmanagerService;
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
public class RestaurantmanagerServiceTest {

    @Autowired
    RestaurantmanagerService restaurantmanagerService;

    @Autowired
    RestaurantService restaurantService;

    @Test
    public void testFindAll(){
        List<RestaurantManager> restaurantManagers = restaurantmanagerService.findAll();
        Assert.assertNotNull(restaurantManagers);
    }

    @Test
    public void testFindOne(){
        RestaurantManager restaurantManager = restaurantmanagerService.findOne(8L);
        Assert.assertEquals(8L, restaurantManager.getId());
    }

    @Test
    public void testSave(){
        RestaurantManager restaurantManager = new RestaurantManager();
        restaurantManager.setRestaurant(restaurantService.findOne(1L));
        restaurantManager.setType(UserType.RESTAURANTMANAGER);
        restaurantManager.setPassword("password");
        restaurantManager.setSurname("Surname");
        restaurantManager.setName("Name");
        restaurantManager.setEmail("resMan@gmail.com");
        restaurantManager.setDate_of_birth(new Date());

        RestaurantManager savedRestaurantManager = restaurantmanagerService.save(restaurantManager);
        RestaurantManager loadedRestaurantManager = restaurantmanagerService.findOne(savedRestaurantManager.getId());

        Assert.assertNotNull(savedRestaurantManager);
        Assert.assertNotNull(loadedRestaurantManager);
        Assert.assertEquals(savedRestaurantManager.getId(), loadedRestaurantManager.getId());
    }

    @Test
    public void testDelete(){
        RestaurantManager restaurantManager = new RestaurantManager();
        restaurantManager.setRestaurant(restaurantService.findOne(1L));
        restaurantManager.setType(UserType.RESTAURANTMANAGER);
        restaurantManager.setPassword("password");
        restaurantManager.setSurname("Surname");
        restaurantManager.setName("Name");
        restaurantManager.setEmail("resMan@gmail.com");
        restaurantManager.setDate_of_birth(new Date());

        RestaurantManager savedRestaurantManager = restaurantmanagerService.save(restaurantManager);
        restaurantmanagerService.delete(savedRestaurantManager.getId());
        RestaurantManager loadedRestaurantManager = restaurantmanagerService.findOne(savedRestaurantManager.getId());

        Assert.assertNull(loadedRestaurantManager);
    }

    @Test
    @Transactional
    public void findByRestaurant(){
        Restaurant restaurant = restaurantService.findOne(1L);
        List<RestaurantManager> restaurantManagers = restaurantmanagerService.findByRestaurant(restaurant);
        Assert.assertNotNull(restaurantManagers);
        for (RestaurantManager restaurantManager : restaurantManagers) {
            Assert.assertEquals(restaurant.getId(), restaurantManager.getRestaurant().getId());
        }
    }
}
