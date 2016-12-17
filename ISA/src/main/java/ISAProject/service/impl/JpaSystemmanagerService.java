package ISAProject.service.impl;

import ISAProject.model.users.SystemManager;
import ISAProject.repository.SystemmanagerRepository;
import ISAProject.service.SystemmanagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Marko on 12/18/2016.
 */

@Service
@Transactional
public class JpaSystemmanagerService implements SystemmanagerService {
    @Autowired
    private SystemmanagerRepository systemmanagerRepository;

    @Override
    public List<SystemManager> findAll() {
        return systemmanagerRepository.findAll();
    }

    @Override
    public SystemManager findOne(Long id) {
        return systemmanagerRepository.findOne(id);
    }

    @Override
    public SystemManager save(SystemManager systemManager) {
        return systemmanagerRepository.save(systemManager);
    }

    @Override
    public SystemManager delete(Long id) {
        SystemManager systemManager = systemmanagerRepository.findOne(id);
        if(systemManager == null){
            try{
                throw new Exception("Waiter cannot be found");
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        systemmanagerRepository.delete(systemManager);
        return systemManager;
    }
}
