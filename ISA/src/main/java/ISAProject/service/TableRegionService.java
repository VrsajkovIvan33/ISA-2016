package ISAProject.service;

import ISAProject.model.TableRegion;

import java.util.List;

/**
 * Created by Verpsychoff on 2/16/2017.
 */

public interface TableRegionService {

    List<TableRegion> findAll();

    TableRegion findById(Long id);

    TableRegion findByTrMark(int mark);
}
