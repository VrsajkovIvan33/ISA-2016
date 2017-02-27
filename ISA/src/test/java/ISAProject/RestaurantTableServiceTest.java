package ISAProject;

import ISAProject.model.Restaurant;
import ISAProject.model.RestaurantTable;
import ISAProject.model.TableRegion;
import ISAProject.service.RestaurantService;
import ISAProject.service.RestaurantTableService;
import ISAProject.service.TableRegionService;
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
public class RestaurantTableServiceTest {

    @Autowired
    RestaurantTableService restaurantTableService;

    @Autowired
    RestaurantService restaurantService;

    @Autowired
    TableRegionService tableRegionService;

    @Test
    public void testFindAll() {
        List<RestaurantTable> tables = restaurantTableService.findAll();
        Assert.assertNotNull(tables);
    }

    @Test
    public void testFindById() {
        RestaurantTable restaurantTable = restaurantTableService.findById(1L);
        Assert.assertEquals(1L, restaurantTable.getId().longValue());
    }

    @Test
    @Transactional
    public void testFindByRestaurant() {
        Restaurant restaurant = restaurantService.findOne(1L);
        List<RestaurantTable> tables = restaurantTableService.findByRestaurant(restaurant);
        Assert.assertNotNull(tables);
        for (RestaurantTable table : tables) {
            Assert.assertEquals(restaurant.getId(), table.getRestaurant().getId());
        }
    }

    @Test
    @Transactional
    public void testFindByRestaurantAndActive() {
        Restaurant restaurant = restaurantService.findOne(1L);
        List<RestaurantTable> tables = restaurantTableService.findByRestaurantAndActive(restaurant, true);
        Assert.assertNotNull(tables);
        for (RestaurantTable table : tables) {
            Assert.assertEquals(restaurant.getId(), table.getRestaurant().getId());
            Assert.assertEquals(true, table.getRtActive());
        }
    }

    @Test
    @Transactional
    public void testFindByRestaurantAndTableRegion() {
        Restaurant restaurant = restaurantService.findOne(1L);
        TableRegion region = tableRegionService.findById(1L);
        List<RestaurantTable> tables = restaurantTableService.findByRestaurantAndTableRegion(restaurant, region);
        Assert.assertNotNull(tables);
        for (RestaurantTable table : tables) {
            Assert.assertEquals(restaurant.getId(), table.getRestaurant().getId());
            Assert.assertEquals(region.getId(), table.getTableRegion().getId());
        }
    }

    @Test
    public void testSave() {
        RestaurantTable table = new RestaurantTable();
        table.setRtPosition(33);
        table.setRtNumber(33);
        table.setRtActive(true);

        RestaurantTable savedTable = restaurantTableService.save(table);
        RestaurantTable loadedTable = restaurantTableService.findById(savedTable.getId());

        Assert.assertNotNull(savedTable);
        Assert.assertNotNull(loadedTable);
        Assert.assertEquals(savedTable.getId(), loadedTable.getId());
    }

    @Test
    public void testDelete() {
        RestaurantTable table = new RestaurantTable();
        table.setRtPosition(33);
        table.setRtNumber(33);
        table.setRtActive(true);

        RestaurantTable savedTable = restaurantTableService.save(table);
        restaurantTableService.delete(savedTable.getId());
        RestaurantTable loadedTable = restaurantTableService.findById(savedTable.getId());

        Assert.assertNull(loadedTable);
    }

}
