package ISAProject.service;

import ISAProject.model.RestaurantSegment;

import java.util.List;

/**
 * Created by Verpsychoff on 2/16/2017.
 */
public interface RestaurantSegmentService {

    List<RestaurantSegment> findAll();

    RestaurantSegment findByRsId(Long id);

    RestaurantSegment findByRsName(String name);

}
