package ISAProject.repository;

import ISAProject.model.users.Provider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Marko on 12/21/2016.
 */
@Repository
@Transactional
public interface ProviderRepository extends JpaRepository<Provider, Long> {
    List<Provider> findById(Long id);

    List<Provider> findAll();

    void delete(Long id);

    Provider save(Provider provider);

    List<Provider> findByName(String name);

    List<Provider> findBySurname(String surname);

    List<Provider> findByNameAndSurname(String name, String surname);
}
