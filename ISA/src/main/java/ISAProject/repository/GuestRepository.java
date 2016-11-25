package ISAProject.repository;

import ISAProject.model.Guest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Nole on 11/20/2016.
 */

@Repository
@Transactional
public interface GuestRepository extends JpaRepository<Guest, Long> {

    List<Guest> findByGId(Long gid);

    List<Guest> findByGEmail(String gemail);

    @Transactional
    Guest save(Guest guest);
}
