package ISAProject.service;

import ISAProject.model.users.Provider;

import java.util.List;

/**
 * Created by Marko on 12/21/2016.
 */
public interface ProviderService {
    List<Provider> findAll();

    Provider findOne(Long id);

    Provider save(Provider provider);

    void delete(Long id);

    List<Provider> findByName(String name);

    List<Provider> findBySurname(String surname);

    List<Provider> findByNameAndSurname(String name, String surname);
}
