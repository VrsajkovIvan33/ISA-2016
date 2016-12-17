package ISAProject.service;

import ISAProject.model.users.Cook;

import java.util.List;

/**
 * Created by Nole on 11/26/2016.
 */
public interface CookService {

    List<Cook> findAll();

    Cook findOne(Long id);

    Cook save(Cook cook);

    Cook delete(Long id);

}
