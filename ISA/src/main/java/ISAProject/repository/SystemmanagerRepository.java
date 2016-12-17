package ISAProject.repository;

import ISAProject.model.users.SystemManager;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Marko on 12/18/2016.
 */
@Repository
public interface SystemmanagerRepository extends JpaRepository<SystemManager, Long> {
    List<SystemManager> findById(Long id);
}
