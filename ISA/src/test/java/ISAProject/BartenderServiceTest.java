package ISAProject;

import ISAProject.model.Restaurant;
import ISAProject.model.users.Bartender;
import ISAProject.service.BartenderService;
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
public class BartenderServiceTest {

    @Autowired
    BartenderService bartenderService;

    @Autowired
    RestaurantService restaurantService;

    @Test
    public void testFindAll() {
        List<Bartender> bartenders = bartenderService.findAll();
        Assert.assertNotNull(bartenders);
    }

    @Test
    public void testFindOne() {
        Bartender bartender = bartenderService.findOne(13L);
        Assert.assertEquals(13L, bartender.getId());
    }

    @Test
    @Transactional
    public void testFindByRestaurant() {
        Restaurant restaurant = restaurantService.findOne(1l);
        List<Bartender> bartenders = bartenderService.findByRestaurant(restaurant);
        Assert.assertNotNull(bartenders);
        for (Bartender bartender : bartenders) {
            Assert.assertEquals(restaurant.getId(), bartender.getRestaurant().getId());
        }
    }

    @Test
    public void testSave() {
        Bartender bartender = new Bartender();
        bartender.setName("TestName");
        bartender.setSurname("TestSurname");
        bartender.setEmail("test@test.com");
        bartender.setRestaurant(restaurantService.findOne(1L));
        bartender.setDate_of_birth(new Date());
        bartender.setPassword("TestPassword");
        bartender.setShoe_size(1);
        bartender.setDress_size(1);
        bartender.setPasswordChanged(false);

        Bartender savedBartender = bartenderService.save(bartender);
        Bartender loadedBartender = bartenderService.findOne(savedBartender.getId());

        Assert.assertNotNull(savedBartender);
        Assert.assertNotNull(loadedBartender);
        Assert.assertEquals(savedBartender.getId(), loadedBartender.getId());
    }

    @Test
    public void testDelete() {
        Bartender bartender = new Bartender();
        bartender.setName("TestName");
        bartender.setSurname("TestSurname");
        bartender.setEmail("test@test.com");
        bartender.setRestaurant(restaurantService.findOne(1L));
        bartender.setDate_of_birth(new Date());
        bartender.setPassword("TestPassword");
        bartender.setShoe_size(1);
        bartender.setDress_size(1);
        bartender.setPasswordChanged(false);

        Bartender savedBartender = bartenderService.save(bartender);
        bartenderService.delete(savedBartender.getId());
        Bartender loadedBartender = bartenderService.findOne(savedBartender.getId());

        Assert.assertNull(loadedBartender);
    }



}
