package ISAProject;

import ISAProject.model.Tender;
import ISAProject.model.TenderItem;
import ISAProject.service.TenderItemService;
import ISAProject.service.TenderService;
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
public class TenderItemServiceTest {

    @Autowired
    TenderItemService tenderItemService;

    @Autowired
    TenderService tenderService;

    @Test
    public void testFindAll(){
        List<TenderItem> tenderItems = tenderItemService.findAll();
        Assert.assertNotNull(tenderItems);
    }

    @Test
    public void testFindOne(){
        TenderItem tenderItem = tenderItemService.findOne(2L);
        Assert.assertEquals(2L, tenderItem.getTiId().longValue());
    }

    @Test
    public void testSave(){
        TenderItem tenderItem = new TenderItem();
        tenderItem.setTiName("Milk");
        tenderItem.setTiQuantity("50 l");
        tenderItem.setTiTender(tenderService.findOne(2L));
        tenderItem.setTiType("Foodstuff");

        TenderItem savedTenderItem = tenderItemService.save(tenderItem);
        TenderItem loadedTenderItem = tenderItemService.findOne(savedTenderItem.getTiId());

        Assert.assertNotNull(savedTenderItem);
        Assert.assertNotNull(loadedTenderItem);
        Assert.assertEquals(savedTenderItem.getTiId(), loadedTenderItem.getTiId());
    }

    @Test
    public void testDelete(){
        TenderItem tenderItem = new TenderItem();
        tenderItem.setTiName("Milk");
        tenderItem.setTiQuantity("50 l");
        tenderItem.setTiTender(tenderService.findOne(2L));
        tenderItem.setTiType("Foodstuff");

        TenderItem savedTenderItem = tenderItemService.save(tenderItem);
        tenderItemService.delete(savedTenderItem.getTiId());
        TenderItem loadedTenderItem = tenderItemService.findOne(savedTenderItem.getTiId());

        Assert.assertNull(loadedTenderItem);
    }

    @Test
    @Transactional
    public void findByTiTender(){
        Tender tender = tenderService.findOne(2L);
        List<TenderItem> tenderItems = tenderItemService.findByTiTender(tender);
        Assert.assertNotNull(tenderItems);
        for (TenderItem tenderItem : tenderItems) {
            Assert.assertEquals(tender.gettId(), tenderItem.getTiTender().gettId());
        }
    }
}
