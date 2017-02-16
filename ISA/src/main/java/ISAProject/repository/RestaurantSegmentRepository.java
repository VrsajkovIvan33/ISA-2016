package ISAProject.repository;

import ISAProject.model.RestaurantSegment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Verpsyichoff on 2/16/2017.
 */

@Repository
public interface RestaurantSegmentRepository extends JpaRepository<RestaurantSegment, Long> {

    List<RestaurantSegment> findAll();

    RestaurantSegment findByRsId(Long id);

    RestaurantSegment findByRsName(String name);

}
