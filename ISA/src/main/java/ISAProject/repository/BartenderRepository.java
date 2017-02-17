package ISAProject.repository;

import ISAProject.model.Restaurant;
import ISAProject.model.users.Bartender;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Nole on 11/26/2016.
 */

@Repository
public interface BartenderRepository extends JpaRepository<Bartender, Long>{

    List<Bartender> findById(Long bid);

    List<Bartender> findByRestaurant(Restaurant restaurant);

    Bartender save(Bartender bartender);

    void delete(Long id);
}
