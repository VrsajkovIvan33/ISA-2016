package ISAProject;

import ISAProject.model.Menu;
import ISAProject.model.Restaurant;
import ISAProject.service.MenuService;
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
public class MenuServiceTest {

    @Autowired
    MenuService menuService;

    @Autowired
    RestaurantService restaurantService;

    @Test
    public void testFindAll(){
        List<Menu> menus = menuService.findAll();
        Assert.assertNotNull(menus);
    }

    @Test
    public void testFindOne(){
        Menu menu = menuService.findOne(2L);
        Assert.assertEquals(2L, menu.getmId().longValue());
    }

    @Test
    public void testSave(){
        Menu menu = new Menu();
        menu.setmDescription("Desc");
        menu.setmName("Name");
        menu.setmPrice(500F);
        menu.setmRestaurant(restaurantService.findOne(1L));
        menu.setmReview(1F);
        menu.setmType("Salad");

        Menu savedMenu = menuService.save(menu);
        Menu loadedMenu = menuService.findOne(savedMenu.getmId());

        Assert.assertNotNull(savedMenu);
        Assert.assertNotNull(loadedMenu);
        Assert.assertEquals(savedMenu.getmId(), loadedMenu.getmId());
    }

    @Test
    public void testDelete(){
        Menu menu = new Menu();
        menu.setmDescription("Desc");
        menu.setmName("Name");
        menu.setmPrice(500F);
        menu.setmRestaurant(restaurantService.findOne(1L));
        menu.setmReview(1F);
        menu.setmType("Salad");

        Menu savedMenu = menuService.save(menu);
        menuService.delete(savedMenu.getmId());
        Menu loadedMenu = menuService.findOne(savedMenu.getmId());

        Assert.assertNull(loadedMenu);
    }

    @Test
    @Transactional
    public void findByMRestaurant(){
        Restaurant restaurant = restaurantService.findOne(1L);
        List<Menu> menus = menuService.findByMRestaurant(restaurant);
        Assert.assertNotNull(menus);
        for (Menu menu : menus) {
            Assert.assertEquals(restaurant.getId(), menu.getmRestaurant().getId());
        }
    }

    @Test
    @Transactional
    public void findByMRestaurantAndMType(){
        Restaurant restaurant = restaurantService.findOne(1L);
        List<Menu> menus = menuService.findByMRestaurantAndMType(restaurant, "Drink");
        Assert.assertNotNull(menus);
        for (Menu menu : menus) {
            Assert.assertEquals(restaurant.getId(), menu.getmRestaurant().getId());
            Assert.assertEquals("Drink", menu.getmType());
        }
    }
}
