package ISAProject;

import ISAProject.model.Bill;
import ISAProject.model.Restaurant;
import ISAProject.model.users.Waiter;
import ISAProject.service.BillService;
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
public class BillServiceTest {

    @Autowired
    BillService billService;

    @Autowired
    WaiterService waiterService;

    @Autowired
    RestaurantService restaurantService;

    @Test
    public void testFindOne() {
        Bill bill = billService.findOne(1L);
        Assert.assertEquals(1L, bill.getId().longValue());
    }

    @Test
    @Transactional
    public void testFindByRestaurant() {
        Restaurant restaurant = restaurantService.findOne(1L);
        List<Bill> bills = billService.findByRestaurant(restaurant);
        Assert.assertNotNull(bills);
        for (Bill bill : bills) {
            Assert.assertEquals(restaurant.getId(), bill.getWaiter().getRestaurant().getId());
        }
    }

    @Test
    @Transactional
    public void testFindByWaiter() {
        Waiter waiter = waiterService.findOne(6L);
        List<Bill> bills = billService.findByWaiter(waiter);
        Assert.assertNotNull(bills);
        for (Bill bill : bills) {
            Assert.assertEquals(waiter.getId(), bill.getWaiter().getId());
        }
    }

    @Test
    public void testSave() {
        Bill bill = new Bill();
        bill.setTotal(101);
        bill.setDate(new Date());

        Bill savedBill = billService.save(bill);
        Bill loadedBill = billService.findOne(savedBill.getId());

        Assert.assertNotNull(savedBill);
        Assert.assertNotNull(loadedBill);
        Assert.assertEquals(savedBill.getId(), loadedBill.getId());
    }

    @Test
    public void testDelete() {
        Bill bill = new Bill();
        bill.setTotal(101);
        bill.setDate(new Date());

        Bill savedBill = billService.save(bill);
        billService.delete(savedBill.getId());
        Bill loadedBill = billService.findOne(savedBill.getId());

        Assert.assertNull(loadedBill);
    }

}
