package ISAProject.service;

import ISAProject.model.Restaurant;
import ISAProject.model.Tender;

import java.util.List;

/**
 * Created by Marko on 2/24/2017.
 */
public interface TenderService {
    List<Tender> findAll();

    Tender findOne(Long id);

    Tender save(Tender tender);

    void delete(Long id);

    List<Tender> findByTRestaurantAndTStatus(Restaurant tRestaurant, String tStatus);

    List<Tender> findByTRestaurant(Restaurant tRestaurant);
}
