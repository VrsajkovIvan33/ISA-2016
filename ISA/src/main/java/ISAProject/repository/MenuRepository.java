package ISAProject.repository;

import ISAProject.model.Menu;
import ISAProject.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Marko on 2/18/2017.
 */
@Repository
public interface MenuRepository extends JpaRepository<Menu, Long> {
    List<Menu> findByMId(Long id);

    List<Menu> findAll();

    void delete(Long id);

    Menu save(Menu menu);

    List<Menu> findByMRestaurantAndMType(Restaurant mRestaurant, String mType);

    List<Menu> findByMRestaurant(Restaurant mRestaurant);
}
