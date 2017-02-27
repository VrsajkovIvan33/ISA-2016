package ISAProject;

import ISAProject.model.Menu;
import ISAProject.model.MenuReview;
import ISAProject.model.Restaurant;
import ISAProject.model.users.User;
import ISAProject.service.MenuReviewService;
import ISAProject.service.MenuService;
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

import java.util.Date;
import java.util.List;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Isa2016Application.class)
@WebAppConfiguration
public class MenuReviewServiceTest {

    @Autowired
    MenuReviewService menuReviewService;

    @Autowired
    MenuService menuService;

    @Autowired
    UserService userService;

    @Autowired
    RestaurantService restaurantService;

    @Test
    public void testFindAll(){
        List<MenuReview> menuReviews = menuReviewService.findAll();
        Assert.assertNotNull(menuReviews);
    }

    @Test
    public void testFindOne(){
        MenuReview menuReview = menuReviewService.findOne(3L);
        Assert.assertEquals(3L, menuReview.getMrId().longValue());
    }

    @Test
    public void testSave(){
        MenuReview menuReview = new MenuReview();
        menuReview.setMrMenu(menuService.findOne(1L));
        menuReview.setMrUser(userService.findOne(1L));
        menuReview.setMrDate(new Date());
        menuReview.setMrRestaurant(restaurantService.findOne(1L));
        menuReview.setMrReview(4F);

        MenuReview savedMenuReview = menuReviewService.save(menuReview);
        MenuReview loadedMenuReview = menuReviewService.findOne(savedMenuReview.getMrId());

        Assert.assertNotNull(savedMenuReview);
        Assert.assertNotNull(loadedMenuReview);
        Assert.assertEquals(savedMenuReview.getMrId(), loadedMenuReview.getMrId());
    }

    @Test
    public void testDelete(){
        MenuReview menuReview = new MenuReview();
        menuReview.setMrMenu(menuService.findOne(1L));
        menuReview.setMrUser(userService.findOne(1L));
        menuReview.setMrDate(new Date());
        menuReview.setMrRestaurant(restaurantService.findOne(1L));
        menuReview.setMrReview(4F);

        MenuReview savedMenuReview = menuReviewService.save(menuReview);
        menuReviewService.delete(savedMenuReview.getMrId());
        MenuReview loadedMenuReview = menuReviewService.findOne(savedMenuReview.getMrId());

        Assert.assertNull(loadedMenuReview);
    }

    @Test
    @Transactional
    public void testFindByMrRestaurant(){
        Restaurant restaurant = restaurantService.findOne(1L);
        List<MenuReview> menuReviews = menuReviewService.findByMrRestaurant(restaurant);
        Assert.assertNotNull(menuReviews);
        for (MenuReview menuReview : menuReviews) {
            Assert.assertEquals(restaurant.getId(), menuReview.getMrRestaurant().getId());
        }
    }

    @Test
    public void testFindByMrUser(){
        User user = userService.findOne(1L);
        List<MenuReview> menuReviews = menuReviewService.findByMrUser(user);
        Assert.assertNotNull(menuReviews);
        for (MenuReview menuReview : menuReviews) {
            Assert.assertEquals(user.getId(), menuReview.getMrUser().getId());
        }
    }

    @Test
    public void testFindByMrMenu(){
        Menu menu = menuService.findOne(1l);
        List<MenuReview> menuReviews = menuReviewService.findByMrMenu(menu);
        Assert.assertNotNull(menuReviews);
        for (MenuReview menuReview : menuReviews) {
            Assert.assertEquals(menu.getmId(), menuReview.getMrMenu().getmId());
        }
    }
}
