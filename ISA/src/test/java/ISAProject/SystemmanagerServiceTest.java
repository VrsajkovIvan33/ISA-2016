package ISAProject;

import ISAProject.model.users.SystemManager;
import ISAProject.model.users.UserType;
import ISAProject.service.SystemmanagerService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Isa2016Application.class)
@WebAppConfiguration
public class SystemmanagerServiceTest {

    @Autowired
    SystemmanagerService systemmanagerService;

    @Test
    public void testFindAll(){
        List<SystemManager> systemManagers = systemmanagerService.findAll();
        Assert.assertNotNull(systemManagers);
    }

    @Test
    public void testFindOne(){
        SystemManager systemManager = systemmanagerService.findOne(5L);
        Assert.assertEquals(5L, systemManager.getId());
    }

    @Test
    public void testSave(){
        SystemManager systemManager = new SystemManager();
        systemManager.setType(UserType.SYSTEMMANAGER);
        systemManager.setEmail("sysman@gmail.com");
        systemManager.setName("Name");
        systemManager.setSurname("Surname");
        systemManager.setPassword("password");

        SystemManager savedSystemManager = systemmanagerService.save(systemManager);
        SystemManager loadedSystemManager = systemmanagerService.findOne(savedSystemManager.getId());

        Assert.assertNotNull(savedSystemManager);
        Assert.assertNotNull(loadedSystemManager);
        Assert.assertEquals(savedSystemManager.getId(), loadedSystemManager.getId());
    }

    @Test
    public void testDelete(){
        SystemManager systemManager = new SystemManager();
        systemManager.setType(UserType.SYSTEMMANAGER);
        systemManager.setEmail("sysman@gmail.com");
        systemManager.setName("Name");
        systemManager.setSurname("Surname");
        systemManager.setPassword("password");

        SystemManager savedSystemManager = systemmanagerService.save(systemManager);
        systemmanagerService.delete(savedSystemManager.getId());
        SystemManager loadedSystemManager = systemmanagerService.findOne(savedSystemManager.getId());

        Assert.assertNull(loadedSystemManager);
    }
}
