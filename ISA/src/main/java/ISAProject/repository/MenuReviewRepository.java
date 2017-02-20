package ISAProject.repository;

import ISAProject.model.Menu;
import ISAProject.model.MenuReview;
import ISAProject.model.Restaurant;
import ISAProject.model.users.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Marko on 2/19/2017.
 */
@Repository
public interface MenuReviewRepository extends JpaRepository<MenuReview, Long> {
    List<MenuReview> findByMrId(Long id);

    List<MenuReview> findAll();

    void delete(Long id);

    MenuReview save(MenuReview menuReview);

    List<MenuReview> findByMrRestaurant(Restaurant restaurant);

    List<MenuReview> findByMrUser(User user);

    List<MenuReview> findByMrMenu(Menu menu);
}
