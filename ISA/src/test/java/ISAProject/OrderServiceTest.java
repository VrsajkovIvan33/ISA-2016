package ISAProject;

import ISAProject.model.Order;
import ISAProject.model.RestaurantTable;
import ISAProject.model.users.Waiter;
import ISAProject.service.OrderService;
import ISAProject.service.RestaurantTableService;
import ISAProject.service.WaiterService;
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
public class OrderServiceTest {

    @Autowired
    OrderService orderService;

    @Autowired
    RestaurantTableService restaurantTableService;

    @Autowired
    WaiterService waiterService;

    @Test
    public void testFindAll() {
        List<Order> orders = orderService.findAll();
        Assert.assertNotNull(orders);
    }

    @Test
    public void testFindById() {
        Order order = orderService.findById(1L);
        Assert.assertEquals(1L, order.getId().longValue());
    }

    @Test
    @Transactional
    public void testFindByAssignedAndRestaurantTableAndDate() {
        RestaurantTable table = restaurantTableService.findById(1L);
        List<Order> orders = orderService.findByAssignedAndRestaurantTableAndDate(false, table, 2017, 2, 2);
        Assert.assertNotNull(orders);
        for (Order order : orders) {
            Assert.assertEquals(false, order.getoAssigned());
            Assert.assertEquals(table.getId(), order.getRestaurantTable().getId());
            Assert.assertEquals(2017, order.getYear());
            Assert.assertEquals(2, order.getMonth());
            Assert.assertEquals(2, order.getDay());
        }
    }

    @Test
    @Transactional
    public void testFindByWaiter() {
        Waiter waiter = waiterService.findOne(6L);
        List<Order> orders = orderService.findByWaiter(waiter);
        Assert.assertTrue(orders.size() == 0);
    }

    @Test
    public void testSave() {
        Order order = new Order();
        order.setoStatus("TestStatus");
        order.setoAssigned(false);

        Order savedOrder = orderService.save(order);
        Order loadedOrder = orderService.findById(savedOrder.getId());

        Assert.assertNotNull(savedOrder);
        Assert.assertNotNull(loadedOrder);
        Assert.assertEquals(savedOrder.getId(), loadedOrder.getId());
    }

    @Test
    public void testDelete() {
        Order order = new Order();
        order.setoStatus("TestStatus");
        order.setoAssigned(false);

        Order savedOrder = orderService.save(order);
        orderService.delete(savedOrder.getId());
        Order loadedOrder = orderService.findById(savedOrder.getId());

        Assert.assertNull(loadedOrder);
    }

}
