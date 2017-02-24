package ISAProject.repository;

import ISAProject.model.Restaurant;
import ISAProject.model.Tender;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Marko on 2/24/2017.
 */
@Repository
public interface TenderRepository extends JpaRepository<Tender, Long> {
    List<Tender> findByTId(Long id);

    List<Tender> findAll();

    void delete(Long id);

    Tender save(Tender tender);

    List<Tender> findByTRestaurantAndTStatus(Restaurant tRestaurant, String tStatus);

    List<Tender> findByTRestaurant(Restaurant tRestaurant);
}
