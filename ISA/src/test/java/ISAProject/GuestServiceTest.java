package ISAProject;

import ISAProject.model.users.Guest;
import ISAProject.service.GuestService;
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
public class GuestServiceTest {

    @Autowired
    GuestService guestService;

    @Test
    public void testFindAll() {
        List<Guest> guests = guestService.findAll();
        Assert.assertNotNull(guests);
    }

    @Test
    public void testFindOne() {
        Guest guest = guestService.findOne(1L);
        Assert.assertEquals(1L, guest.getId());
    }

    @Test
    public void testFindByName() {
        List<Guest> guests = guestService.findByName("Marko");
        Assert.assertNotNull(guests);
        for (Guest guest : guests) {
            Assert.assertEquals(guest.getName(), "Marko");
        }
    }

    @Test
    public void testFindBySurname() {
        List<Guest> guests = guestService.findBySurname("Jankovic");
        Assert.assertNotNull(guests);
        for (Guest guest : guests) {
            Assert.assertEquals(guest.getSurname(), "Jankovic");
        }
    }

    @Test
    public void testFindByNameAndSurname() {
        List<Guest> guests = guestService.findByNameAndSurname("Boris", "Janjic");
        Assert.assertNotNull(guests);
        for (Guest guest : guests) {
            Assert.assertEquals(guest.getName(), "Boris");
            Assert.assertEquals(guest.getSurname(), "Janjic");
        }
    }

    @Test
    public void testSave() {
        Guest guest = new Guest();
        guest.setName("TestName");
        guest.setSurname("TestSurname");
        guest.setEmail("test@test.com");
        guest.setPassword("TestPassword");
        guest.setActive(false);

        Guest savedGuest = guestService.save(guest);
        Guest loadedGuest = guestService.findOne(savedGuest.getId());

        Assert.assertNotNull(savedGuest);
        Assert.assertNotNull(loadedGuest);
        Assert.assertEquals(savedGuest.getId(), loadedGuest.getId());
    }

    @Test
    public void testDelete() {
        Guest guest = new Guest();
        guest.setName("TestName");
        guest.setSurname("TestSurname");
        guest.setEmail("test@test.com");
        guest.setPassword("TestPassword");
        guest.setActive(false);

        Guest savedGuest = guestService.save(guest);
        guestService.delete(savedGuest.getId());
        Guest loadedGuest = guestService.findOne(savedGuest.getId());

        Assert.assertNull(loadedGuest);
    }

}
