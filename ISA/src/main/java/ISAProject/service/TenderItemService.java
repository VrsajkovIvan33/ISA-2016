package ISAProject.service;

import ISAProject.model.Tender;
import ISAProject.model.TenderItem;

import java.util.List;

/**
 * Created by Marko on 2/24/2017.
 */
public interface TenderItemService {
    List<TenderItem> findAll();

    TenderItem findOne(Long id);

    TenderItem save(TenderItem tenderItem);

    void delete(Long id);

    List<TenderItem> findByTiTender(Tender tiTender);
}
