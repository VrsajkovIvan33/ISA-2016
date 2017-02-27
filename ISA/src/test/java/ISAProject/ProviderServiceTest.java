package ISAProject;

import ISAProject.model.users.Provider;
import ISAProject.model.users.UserType;
import ISAProject.service.ProviderService;
import ISAProject.service.RestaurantService;
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
public class ProviderServiceTest {

    @Autowired
    ProviderService providerService;

    @Autowired
    RestaurantService restaurantService;

    @Test
    public void testFindAll(){
        List<Provider> providers = providerService.findAll();
        Assert.assertNotNull(providers);
    }

    @Test
    public void testFindOne(){
        Provider provider = providerService.findOne(9L);
        Assert.assertEquals(9L, provider.getId());
    }

    @Test
    public void testSave(){
        Provider provider = new Provider();
        provider.setpPasswordChanged(false);
        provider.setRestaurants(null);
        provider.setEmail("provider@gmail.com");
        provider.setName("Name");
        provider.setSurname("Surname");
        provider.setPassword("Password");
        provider.setType(UserType.PROVIDER);

        Provider savedProvider = providerService.save(provider);
        Provider loadedProvider = providerService.findOne(savedProvider.getId());

        Assert.assertNotNull(savedProvider);
        Assert.assertNotNull(loadedProvider);
        Assert.assertEquals(savedProvider.getId(), loadedProvider.getId());
    }

    @Test
    public void testDelete(){
        Provider provider = new Provider();
        provider.setpPasswordChanged(false);
        provider.setRestaurants(null);
        provider.setEmail("provider@gmail.com");
        provider.setName("Name");
        provider.setSurname("Surname");
        provider.setPassword("Password");
        provider.setType(UserType.PROVIDER);

        Provider savedProvider = providerService.save(provider);
        providerService.delete(savedProvider.getId());
        Provider loadedMenu = providerService.findOne(savedProvider.getId());

        Assert.assertNull(loadedMenu);
    }

    @Test
    public void findByName(){
        List<Provider> providers = providerService.findByName("proName2");
        Assert.assertNotNull(providers);
        for (Provider provider : providers) {
            Assert.assertEquals("proName2", provider.getName());
        }
    }

    @Test
    public void findBySurname(){
        List<Provider> providers = providerService.findBySurname("proSurname");
        Assert.assertNotNull(providers);
        for (Provider provider : providers) {
            Assert.assertEquals("proSurname", provider.getSurname());
        }
    }

    @Test
    public void findByNameAndSurname(){
        List<Provider> providers = providerService.findByNameAndSurname("proName", "proSurname");
        Assert.assertNotNull(providers);
        for (Provider provider : providers) {
            Assert.assertEquals("proName", provider.getName());
            Assert.assertEquals("proSurname", provider.getSurname());
        }
    }
}
