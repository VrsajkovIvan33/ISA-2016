package ISAProject.service;

import ISAProject.model.Offer;
import ISAProject.model.Tender;
import ISAProject.model.users.Provider;

import java.util.List;

/**
 * Created by Marko on 2/24/2017.
 */
public interface OfferService {
    List<Offer> findAll();

    Offer findOne(Long id);

    Offer save(Offer offer);

    void delete(Long id);

    List<Offer> findByOffTenderAndOffStatus(Tender offTender, String offStatus);

    List<Offer> findByOffTender(Tender offTender);

    List<Offer> findByOffProviderAndOffStatus(Provider offProvider, String offStatus);

    List<Offer> findByOffProvider(Provider offProvider);
}
