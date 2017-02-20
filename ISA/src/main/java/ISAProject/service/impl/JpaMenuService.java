package ISAProject.service.impl;

import ISAProject.model.Menu;
import ISAProject.model.Restaurant;
import ISAProject.repository.MenuRepository;
import ISAProject.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Marko on 2/18/2017.
 */
@Service
@Transactional
public class JpaMenuService implements MenuService {
    @Autowired
    private MenuRepository menuRepository;

    @Override
    public List<Menu> findAll() {
        return menuRepository.findAll();
    }

    @Override
    public Menu findOne(Long id) { return menuRepository.findOne(id); }

    @Override
    public Menu save(Menu menu) { return menuRepository.save(menu); }

    @Override
    public void delete(Long id) { menuRepository.delete(id); }

    @Override
    public List<Menu> findByMRestaurantAndMType(Restaurant mRestaurant, String mType) {
        return menuRepository.findByMRestaurantAndMType(mRestaurant, mType);
    }

    @Override
    public List<Menu> findByMRestaurant(Restaurant mRestaurant) {
        return menuRepository.findByMRestaurant(mRestaurant);
    }

}
