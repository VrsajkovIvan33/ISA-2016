package ISAProject.repository;

import ISAProject.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.jws.soap.SOAPBinding;
import java.util.List;

/**
 * Created by Nole on 12/15/2016.
 */

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findById(Long bid);

    List<User> findByEmail(String email);
}
