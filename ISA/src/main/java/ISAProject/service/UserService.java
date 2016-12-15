package ISAProject.service;

import ISAProject.model.User;

import java.util.List;

/**
 * Created by Nole on 12/15/2016.
 */
public interface UserService {

    List<User> findAll();

    User findOne(Long id);

    User save(User user);

    User delete(Long id);

    User findByEmail(String email);

}
