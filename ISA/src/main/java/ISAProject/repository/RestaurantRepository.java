package ISAProject.repository;

import ISAProject.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Marko on 12/18/2016.
 */
@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
    List<Restaurant> findById(Long id);

    List<Restaurant> findAll();

    void delete(Long id);

    Restaurant save(Restaurant restaurant);
}
