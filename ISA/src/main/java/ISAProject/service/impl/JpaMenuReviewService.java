package ISAProject.service.impl;

import ISAProject.model.Menu;
import ISAProject.model.MenuReview;
import ISAProject.model.Restaurant;
import ISAProject.model.users.User;
import ISAProject.repository.MenuReviewRepository;
import ISAProject.service.MenuReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Marko on 2/19/2017.
 */
@Service
@Transactional
public class JpaMenuReviewService implements MenuReviewService {
    @Autowired
    private MenuReviewRepository menuReviewRepository;

    @Override
    public List<MenuReview> findAll() {
        return menuReviewRepository.findAll();
    }

    @Override
    public MenuReview findOne(Long id) { return menuReviewRepository.findOne(id); }

    @Override
    public MenuReview save(MenuReview menuReview) {
        return menuReviewRepository.save(menuReview);
    }

    @Override
    public void delete(Long id) { menuReviewRepository.delete(id); }

    @Override
    public List<MenuReview> findByMrRestaurant(Restaurant restaurant) {
        return menuReviewRepository.findByMrRestaurant(restaurant);
    }

    @Override
    public List<MenuReview> findByMrUser(User user) {
        return menuReviewRepository.findByMrUser(user);
    }

    @Override
    public List<MenuReview> findByMrMenu(Menu menu) {
        return menuReviewRepository.findByMrMenu(menu);
    }
}
