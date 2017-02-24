package ISAProject.service.impl;

import ISAProject.model.Offer;
import ISAProject.model.OfferItem;
import ISAProject.model.TenderItem;
import ISAProject.repository.OfferItemRepository;
import ISAProject.service.OfferItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Marko on 2/24/2017.
 */
@Service
@Transactional
public class JpaOfferItemService implements OfferItemService{
    @Autowired
    private OfferItemRepository offerItemRepository;

    @Override
    public List<OfferItem> findAll() {
        return offerItemRepository.findAll();
    }

    @Override
    public OfferItem findOne(Long id) { return offerItemRepository.findOne(id); }

    @Override
    public OfferItem save(OfferItem offerItem) { return offerItemRepository.save(offerItem); }

    @Override
    public void delete(Long id) { offerItemRepository.delete(id); }

    @Override
    public List<OfferItem> findByOffiOffer(Offer offiOffer) {
        return offerItemRepository.findByOffiOffer(offiOffer);
    }

    @Override
    public List<OfferItem> findByOffiTenderItem(TenderItem offiTenderItem) {
        return offerItemRepository.findByOffiTenderItem(offiTenderItem);
    }
}
