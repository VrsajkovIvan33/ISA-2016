package ISAProject;

import ISAProject.model.users.User;
import ISAProject.service.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Isa2016Application.class)
@WebAppConfiguration
public class UserServiceTest {

    @Autowired
    UserService userService;

    @Test
    public void testFindAll() {
        List<User> users = userService.findAll();
        Assert.assertNotNull(users);
    }

    @Test
    public void testFindOne() {
        User user = userService.findOne(1L);
        Assert.assertEquals(1L, user.getId());
    }

    @Test
    @Transactional
    public void testFindByEmail() {
        User user = userService.findByEmail("cmd3395@yahoo.com");
        Assert.assertNotNull(user);
        Assert.assertEquals("cmd3395@yahoo.com", user.getEmail());
    }

}
