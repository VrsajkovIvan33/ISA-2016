package ISAProject.service.impl;

import ISAProject.model.Offer;
import ISAProject.model.Tender;
import ISAProject.model.users.Provider;
import ISAProject.repository.OfferRepository;
import ISAProject.service.OfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Marko on 2/24/2017.
 */
@Service
@Transactional
public class JpaOfferService implements OfferService{
    @Autowired
    private OfferRepository offerRepository;

    @Override
    public List<Offer> findAll() {
        return offerRepository.findAll();
    }

    @Override
    public Offer findOne(Long id) { return offerRepository.findOne(id); }

    @Override
    public Offer save(Offer offer) { return offerRepository.save(offer); }

    @Override
    public void delete(Long id) { offerRepository.delete(id); }

    @Override
    public List<Offer> findByOffTenderAndOffStatus(Tender offTender, String offStatus) {
        return offerRepository.findByOffTenderAndOffStatus(offTender, offStatus);
    }

    @Override
    public List<Offer> findByOffTender(Tender offTender) {
        return offerRepository.findByOffTender(offTender);
    }

    @Override
    public List<Offer> findByOffProviderAndOffStatus(Provider offProvider, String offStatus) {
        return offerRepository.findByOffProviderAndOffStatus(offProvider, offStatus);
    }

    @Override
    public List<Offer> findByOffProvider(Provider offProvider) {
        return offerRepository.findByOffProvider(offProvider);
    }

    @Override
    public Offer findByOffTenderAndOffProvider(Tender offTender, Provider offProvider) {
        return offerRepository.findByOffTenderAndOffProvider(offTender, offProvider);
    }
}
