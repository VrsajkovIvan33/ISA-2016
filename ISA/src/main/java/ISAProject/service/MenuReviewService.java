package ISAProject.service;

import ISAProject.model.Menu;
import ISAProject.model.MenuReview;
import ISAProject.model.Restaurant;
import ISAProject.model.users.User;

import java.util.List;

/**
 * Created by Marko on 2/19/2017.
 */
public interface MenuReviewService {
    List<MenuReview> findAll();

    MenuReview findOne(Long id);

    MenuReview save(MenuReview menuReview);

    void delete(Long id);

    List<MenuReview> findByMrRestaurant(Restaurant restaurant);

    List<MenuReview> findByMrUser(User user);

    List<MenuReview> findByMrMenu(Menu menu);
}
