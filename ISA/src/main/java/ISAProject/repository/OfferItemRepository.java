package ISAProject.repository;

import ISAProject.model.Offer;
import ISAProject.model.OfferItem;
import ISAProject.model.TenderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Marko on 2/24/2017.
 */
@Repository
public interface OfferItemRepository extends JpaRepository<OfferItem, Long> {
    List<OfferItem> findByOffiId(Long id);

    List<OfferItem> findAll();

    void delete(Long id);

    OfferItem save(OfferItem offerItem);

    List<OfferItem> findByOffiOffer(Offer offiOffer);

    List<OfferItem> findByOffiTenderItem(TenderItem offiTenderItem);
}
