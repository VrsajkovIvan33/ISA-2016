package ISAProject.repository;

import ISAProject.model.Offer;
import ISAProject.model.Tender;
import ISAProject.model.users.Provider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Marko on 2/24/2017.
 */
@Repository
public interface OfferRepository extends JpaRepository<Offer, Long> {
    List<Offer> findByOffId(Long id);

    List<Offer> findAll();

    void delete(Long id);

    Offer save(Offer offer);

    List<Offer> findByOffTenderAndOffStatus(Tender offTender, String offStatus);

    List<Offer> findByOffTender(Tender offTender);

    List<Offer> findByOffProviderAndOffStatus(Provider offProvider, String offStatus);

    List<Offer> findByOffProvider(Provider offProvider);
}
