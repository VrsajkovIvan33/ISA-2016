package ISAProject.service.impl;

import ISAProject.model.Tender;
import ISAProject.model.TenderItem;
import ISAProject.repository.TenderItemRepository;
import ISAProject.service.TenderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Marko on 2/24/2017.
 */
@Service
@Transactional
public class JpaTenderItemService implements TenderItemService{
    @Autowired
    private TenderItemRepository tenderItemRepository;

    @Override
    public List<TenderItem> findAll() {
        return tenderItemRepository.findAll();
    }

    @Override
    public TenderItem findOne(Long id) { return tenderItemRepository.findOne(id); }

    @Override
    public TenderItem save(TenderItem tenderItem) { return tenderItemRepository.save(tenderItem); }

    @Override
    public void delete(Long id) { tenderItemRepository.delete(id); }

    @Override
    public List<TenderItem> findByTiTender(Tender tiTender) {
        return tenderItemRepository.findByTiTender(tiTender);
    }
}
