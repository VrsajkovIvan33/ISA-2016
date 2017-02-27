package ISAProject;


import ISAProject.model.Restaurant;
import ISAProject.model.users.Waiter;
import ISAProject.service.RestaurantService;
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
public class WaiterServiceTest {

    @Autowired
    WaiterService waiterService;

    @Autowired
    RestaurantService restaurantService;

    @Test
    public void testFindAll() {
        List<Waiter> waiters = waiterService.findAll();
        Assert.assertNotNull(waiters);
    }

    @Test
    public void testFindOne() {
        Waiter waiter = waiterService.findOne(6L);
        Assert.assertEquals(6L, waiter.getId());
    }

    @Test
    @Transactional
    public void testFindByRestaurant() {
        Restaurant restaurant = restaurantService.findOne(1l);
        List<Waiter> waiters = waiterService.findByRestaurant(restaurant);
        Assert.assertNotNull(waiters);
        for (Waiter waiter : waiters) {
            Assert.assertEquals(restaurant.getId(), waiter.getRestaurant().getId());
        }
    }

    @Test
    public void testSave() {
        Waiter waiter = new Waiter();
        waiter.setName("TestName");
        waiter.setSurname("TestSurname");
        waiter.setEmail("test@test.com");
        waiter.setRestaurant(restaurantService.findOne(1L));
        waiter.setDate_of_birth(new Date());
        waiter.setPassword("TestPassword");
        waiter.setShoe_size(1);
        waiter.setDress_size(1);
        waiter.setPasswordChanged(false);

        Waiter savedWaiter = waiterService.save(waiter);
        Waiter loadedWaiter = waiterService.findOne(savedWaiter.getId());

        Assert.assertNotNull(savedWaiter);
        Assert.assertNotNull(loadedWaiter);
        Assert.assertEquals(savedWaiter.getId(), loadedWaiter.getId());
    }

    @Test
    public void testDelete() {
        Waiter waiter = new Waiter();
        waiter.setName("TestName");
        waiter.setSurname("TestSurname");
        waiter.setEmail("test@test.com");
        waiter.setRestaurant(restaurantService.findOne(1L));
        waiter.setDate_of_birth(new Date());
        waiter.setPassword("TestPassword");
        waiter.setShoe_size(1);
        waiter.setDress_size(1);
        waiter.setPasswordChanged(false);

        Waiter savedWaiter = waiterService.save(waiter);
        waiterService.delete(savedWaiter.getId());
        Waiter loadedWaiter = waiterService.findOne(savedWaiter.getId());

        Assert.assertNull(loadedWaiter);
    }


}
