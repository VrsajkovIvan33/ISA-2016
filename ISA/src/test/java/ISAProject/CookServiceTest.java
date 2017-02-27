package ISAProject;

import ISAProject.model.Restaurant;
import ISAProject.model.users.Cook;
import ISAProject.service.CookService;
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
public class CookServiceTest {

    @Autowired
    CookService cookService;

    @Autowired
    RestaurantService restaurantService;

    @Test
    public void testFindAll() {
        List<Cook> cooks = cookService.findAll();
        Assert.assertNotNull(cooks);
    }

    @Test
    public void testFindOne() {
        Cook cook = cookService.findOne(14L);
        Assert.assertEquals(14L, cook.getId());
    }

    @Test
    @Transactional
    public void testFindByRestaurant() {
        Restaurant restaurant = restaurantService.findOne(1l);
        List<Cook> cooks = cookService.findByRestaurant(restaurant);
        Assert.assertNotNull(cooks);
        for (Cook cook : cooks) {
            Assert.assertEquals(restaurant.getId(), cook.getRestaurant().getId());
        }
    }

    @Test
    public void testSave() {
        Cook cook = new Cook();
        cook.setName("TestName");
        cook.setSurname("TestSurname");
        cook.setEmail("test@test.com");
        cook.setRestaurant(restaurantService.findOne(1L));
        cook.setDate_of_birth(new Date());
        cook.setPassword("TestPassword");
        cook.setShoe_size(1);
        cook.setDress_size(1);
        cook.setPasswordChanged(false);
        cook.setTypeCook("All");

        Cook savedCook = cookService.save(cook);
        Cook loadedCook = cookService.findOne(savedCook.getId());

        Assert.assertNotNull(savedCook);
        Assert.assertNotNull(loadedCook);
        Assert.assertEquals(savedCook.getId(), loadedCook.getId());
    }

    @Test
    public void testDelete() {
        Cook cook = new Cook();
        cook.setName("TestName");
        cook.setSurname("TestSurname");
        cook.setEmail("test@test.com");
        cook.setRestaurant(restaurantService.findOne(1L));
        cook.setDate_of_birth(new Date());
        cook.setPassword("TestPassword");
        cook.setShoe_size(1);
        cook.setDress_size(1);
        cook.setPasswordChanged(false);
        cook.setTypeCook("All");

        Cook savedCook = cookService.save(cook);
        cookService.delete(savedCook.getId());
        Cook loadedCook = cookService.findOne(savedCook.getId());

        Assert.assertNull(loadedCook);
    }



}
