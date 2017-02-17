package ISAProject.service;

import ISAProject.model.Restaurant;
import ISAProject.model.users.Bartender;

import java.util.List;

/**
 * Created by Nole on 11/26/2016.
 */
public interface BartenderService {

    List<Bartender> findAll();

    Bartender findOne(Long id);

    List<Bartender> findByRestaurant(Restaurant restaurant);

    Bartender save(Bartender bartender);

    Bartender delete(Long id);

}
