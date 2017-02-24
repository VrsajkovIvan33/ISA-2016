package ISAProject.repository;

import ISAProject.model.Restaurant;
import ISAProject.model.Tender;
import ISAProject.model.TenderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Marko on 2/24/2017.
 */
@Repository
public interface TenderItemRepository extends JpaRepository<TenderItem, Long> {
    List<TenderItem> findByTiId(Long id);

    List<TenderItem> findAll();

    void delete(Long id);

    TenderItem save(TenderItem tenderItem);

    List<TenderItem> findByTiTender(Tender tiTender);
}
