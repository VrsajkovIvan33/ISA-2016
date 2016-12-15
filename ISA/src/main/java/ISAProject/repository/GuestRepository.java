package ISAProject.repository;

import ISAProject.model.Guest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Nole on 11/20/2016.
 */

@Repository
public interface GuestRepository extends JpaRepository<Guest, Long> {

    List<Guest> findById(Long gid);

}
