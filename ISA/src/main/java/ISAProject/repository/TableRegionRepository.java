package ISAProject.repository;

import ISAProject.model.TableRegion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Verpsychoff on 2/16/2017.
 */

@Repository
public interface TableRegionRepository extends JpaRepository<TableRegion, Long> {

    List<TableRegion> findAll();

    TableRegion findById(Long id);

    TableRegion findByTrMark(int mark);
}
