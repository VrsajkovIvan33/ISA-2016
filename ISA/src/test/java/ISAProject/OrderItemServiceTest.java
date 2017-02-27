package ISAProject;

import ISAProject.model.OrderItem;
import ISAProject.model.Restaurant;
import ISAProject.model.users.User;
import ISAProject.service.OrderItemService;
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

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Isa2016Application.class)
@WebAppConfiguration
public class OrderItemServiceTest {

    @Autowired
    OrderItemService orderItemService;

    @Autowired
    RestaurantService restaurantService;

    @Autowired
    UserService userService;

    @Test
    public void testFindAll() {
        List<OrderItem> items = orderItemService.findAll();
        Assert.assertNotNull(items);
    }

    @Test
    public void testFindOne() {
        OrderItem orderItem = orderItemService.findOne(3L);
        Assert.assertEquals(3L, orderItem.getId().longValue());
    }

    @Test
    @Transactional
    public void testFindByFoodTypeAndRestaurantAndStatus() {
        Restaurant restaurant = restaurantService.findOne(1L);
        List<OrderItem> items = orderItemService.findByFoodTypeAndRestaurantAndStatus("Grilled Dish", restaurant, "Ready");
        Assert.assertNotNull(items);
        for (OrderItem item : items) {
            Assert.assertEquals(restaurant.getId(), item.getOrder().getRestaurantTable().getRestaurant().getId());
            Assert.assertEquals("Grilled Dish", item.getMenu().getmType());
            Assert.assertEquals("Ready", item.getOiStatus());
        }
    }

    @Test
    @Transactional
    public void testFindByRestaurantAndStatus() {
        Restaurant restaurant = restaurantService.findOne(1L);
        List<OrderItem> items = orderItemService.findByRestaurantAndStatus(restaurant, "Ready");
        Assert.assertNotNull(items);
        for (OrderItem item : items) {
            Assert.assertEquals(restaurant.getId(), item.getOrder().getRestaurantTable().getRestaurant().getId());
            Assert.assertEquals("Ready", item.getOiStatus());
        }
    }

    @Test
    @Transactional
    public void testFindByStaffAndOiStatus() {
        User user = userService.findOne(15L);
        List<OrderItem> items = orderItemService.findByStaffAndOiStatus(user, "Ready");
        Assert.assertNotNull(items);
        for (OrderItem item : items) {
            Assert.assertEquals(user.getId(), item.getStaff().getId());
            Assert.assertEquals("Ready", item.getOiStatus());
        }
    }

    @Test
    public void testSave() {
        OrderItem item = new OrderItem();
        item.setOiStatus("TestStatus");
        item.setOiReadyByArrival(false);

        OrderItem savedItem = orderItemService.save(item);
        OrderItem loadedItem = orderItemService.findOne(savedItem.getId());

        Assert.assertNotNull(savedItem);
        Assert.assertNotNull(loadedItem);
        Assert.assertEquals(savedItem.getId(), loadedItem.getId());
    }

    @Test
    public void testDelete() {
        OrderItem item = new OrderItem();
        item.setOiStatus("TestStatus");
        item.setOiReadyByArrival(false);

        OrderItem savedItem = orderItemService.save(item);
        orderItemService.delete(savedItem.getId());
        OrderItem loadedItem = orderItemService.findOne(savedItem.getId());

        Assert.assertNull(loadedItem);
    }


}
