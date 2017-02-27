package ISAProject;

import ISAProject.model.Restaurant;
import ISAProject.model.Tender;
import ISAProject.service.RestaurantService;
import ISAProject.service.TenderService;
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
public class TenderServiceTest {

    @Autowired
    TenderService tenderService;

    @Autowired
    RestaurantService restaurantService;

    @Test
    public void testFindAll(){
        List<Tender> tenders = tenderService.findAll();
        Assert.assertNotNull(tenders);
    }

    @Test
    public void testFindOne(){
        Tender tender = tenderService.findOne(2L);
        Assert.assertEquals(2L, tender.gettId().longValue());
    }

    @Test
    public void testSave(){
        Tender tender = new Tender();
        tender.settStatus("Active");
        tender.settRestaurant(restaurantService.findOne(1L));
        tender.settEnd(new Date());
        tender.settStart(new Date());

        Tender savedTender = tenderService.save(tender);
        Tender loadedTender = tenderService.findOne(savedTender.gettId());

        Assert.assertNotNull(savedTender);
        Assert.assertNotNull(loadedTender);
        Assert.assertEquals(savedTender.gettId(), loadedTender.gettId());
    }

    @Test
    public void testDelete(){
        Tender tender = new Tender();
        tender.settStatus("Active");
        tender.settRestaurant(restaurantService.findOne(1L));
        tender.settEnd(new Date());
        tender.settStart(new Date());

        Tender savedTender = tenderService.save(tender);
        tenderService.delete(savedTender.gettId());
        Tender loadedTender = tenderService.findOne(savedTender.gettId());

        Assert.assertNull(loadedTender);
    }

    @Test
    @Transactional
    public void findByTRestaurant(){
        Restaurant restaurant = restaurantService.findOne(1L);
        List<Tender> tenders = tenderService.findByTRestaurant(restaurant);
        Assert.assertNotNull(tenders);
        for (Tender tender : tenders) {
            Assert.assertEquals(restaurant.getId(), tender.gettRestaurant().getId());
        }
    }

    @Test
    @Transactional
    public void findByTRestaurantAndTStatus(){
        Restaurant restaurant = restaurantService.findOne(1L);
        List<Tender> tenders = tenderService.findByTRestaurantAndTStatus(restaurant, "Active");
        Assert.assertNotNull(tenders);
        for (Tender tender : tenders) {
            Assert.assertEquals(restaurant.getId(), tender.gettRestaurant().getId());
            Assert.assertEquals("Active", tender.gettStatus());
        }
    }
}
