package ISAProject.service.impl;

import ISAProject.model.users.Provider;
import ISAProject.repository.ProviderRepository;
import ISAProject.service.ProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Marko on 12/21/2016.
 */
@Service
@Transactional
public class JpaProviderService implements ProviderService {
    @Autowired
    private ProviderRepository providerRepository;

    @Override
    public List<Provider> findAll() {
        return providerRepository.findAll();
    }

    @Override
    public Provider findOne(Long id) { return providerRepository.findOne(id); }

    @Override
    public Provider save(Provider provider) {
        return providerRepository.save(provider);
    }

    @Override
    public void delete(Long id) { providerRepository.delete(id); }

    @Override
    public List<Provider> findByName(String name) {
        return providerRepository.findByName(name);
    }

    @Override
    public List<Provider> findBySurname(String surname) {
        return providerRepository.findBySurname(surname);
    }

    @Override
    public List<Provider> findByNameAndSurname(String name, String surname) {
        return providerRepository.findByNameAndSurname(name, surname);
    }
}
