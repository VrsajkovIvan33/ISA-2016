package ISAProject.service.impl;

import ISAProject.model.TableRegion;
import ISAProject.repository.TableRegionRepository;
import ISAProject.service.TableRegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Verpsychoff on 2/16/2017.
 */

@Transactional
@Service
public class JpaTableRegionService implements TableRegionService {

    @Autowired
    private TableRegionRepository tableRegionRepository;

    @Override
    public List<TableRegion> findAll() {
        return tableRegionRepository.findAll();
    }

    @Override
    public TableRegion findById(Long id) {
        return tableRegionRepository.findById(id);
    }

    @Override
    public TableRegion findByTrMark(int mark) {
        return tableRegionRepository.findByTrMark(mark);
    }
}
