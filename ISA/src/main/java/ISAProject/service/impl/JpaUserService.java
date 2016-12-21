package ISAProject.service.impl;

import ISAProject.model.users.User;
import ISAProject.repository.UserRepository;
import ISAProject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Nole on 12/15/2016.
 */
@Service
@Transactional
public class JpaUserService implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findOne(Long id) {
        return userRepository.findOne(id);
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public User delete(Long id) {
        User user = userRepository.findOne(id);
        if(user == null){
            userRepository.delete(user);
        }else{
            try{
                throw new Exception("User cannot be found");
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        return user;
    }

    @Override
    public User findByEmail(String email) {
        List<User> userList = userRepository.findByEmail(email);
        if(!userList.isEmpty()) {
            User user = userList.get(0);
            return user;
        }else
            return null;
    }
}
