package ISAProject;

import ISAProject.model.VisitHistory;
import ISAProject.model.users.Guest;
import ISAProject.service.GuestService;
import ISAProject.service.VisitHistoryService;
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
public class VisitHistoryServiceTest {

    @Autowired
    VisitHistoryService visitHistoryService;

    @Autowired
    GuestService guestService;

    @Test
    public void testFindOne() {
        VisitHistory history = visitHistoryService.findOne(1L);
        Assert.assertEquals(1L, history.getId().longValue());
    }

    @Test
    @Transactional
    public void testFindByGuest() {
        Guest guest = guestService.findOne(1L);
        List<VisitHistory> histories = visitHistoryService.findByGuest(guest);
        Assert.assertNotNull(histories);
        for (VisitHistory visitHistory : histories) {
            Assert.assertEquals(1L, visitHistory.getGuest().getId());
        }
    }

    @Test
    public void testSave() {
        VisitHistory visitHistory = new VisitHistory();
        visitHistory.setDate(new Date());
        visitHistory.setMenuGrade(-1);
        visitHistory.setRestaurantGrade(-1);
        visitHistory.setServiceGrade(-1);

        VisitHistory savedHistory = visitHistoryService.save(visitHistory);
        VisitHistory loadedHistory = visitHistoryService.findOne(savedHistory.getId());

        Assert.assertNotNull(savedHistory);
        Assert.assertNotNull(loadedHistory);
        Assert.assertEquals(savedHistory.getId(), loadedHistory.getId());
    }

    @Test
    public void testDelete() {
        VisitHistory visitHistory = new VisitHistory();
        visitHistory.setDate(new Date());
        visitHistory.setMenuGrade(-1);
        visitHistory.setRestaurantGrade(-1);
        visitHistory.setServiceGrade(-1);

        VisitHistory savedHistory = visitHistoryService.save(visitHistory);
        visitHistoryService.delete(savedHistory.getId());
        VisitHistory loadedHistory = visitHistoryService.findOne(savedHistory.getId());

        Assert.assertNull(loadedHistory);
    }

}
