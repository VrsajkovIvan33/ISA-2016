package ISAProject.service;

import ISAProject.model.users.SystemManager;

import java.util.List;

/**
 * Created by Marko on 12/18/2016.
 */
public interface SystemmanagerService {
    List<SystemManager> findAll();

    SystemManager findOne(Long id);

    SystemManager save(SystemManager systemManager);

    SystemManager delete(Long id);
}
