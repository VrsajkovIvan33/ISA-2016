package ISAProject.service;

import ISAProject.model.Offer;
import ISAProject.model.OfferItem;
import ISAProject.model.TenderItem;

import java.util.List;

/**
 * Created by Marko on 2/24/2017.
 */
public interface OfferItemService {
    List<OfferItem> findAll();

    OfferItem findOne(Long id);

    OfferItem save(OfferItem offerItem);

    void delete(Long id);

    List<OfferItem> findByOffiOffer(Offer offiOffer);

    List<OfferItem> findByOffiTenderItem(TenderItem offiTenderItem);
}
