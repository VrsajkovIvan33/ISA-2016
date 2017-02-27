package ISAProject;

import ISAProject.model.Offer;
import ISAProject.model.OfferItem;
import ISAProject.model.TenderItem;
import ISAProject.service.OfferItemService;
import ISAProject.service.OfferService;
import ISAProject.service.TenderItemService;
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
public class OfferItemServiceTest {

    @Autowired
    OfferItemService offerItemService;

    @Autowired
    OfferService offerService;

    @Autowired
    TenderItemService tenderItemService;

    @Test
    public void testFindAll(){
        List<OfferItem> offerItems = offerItemService.findAll();
        Assert.assertNotNull(offerItems);
    }

    @Test
    public void testFindOne(){
        OfferItem offerItem = offerItemService.findOne(2L);
        Assert.assertEquals(2L, offerItem.getOffiId().longValue());
    }

    @Test
    public void testSave(){
        OfferItem offerItem = new OfferItem();
        offerItem.setOffiDeliveryTime("5 days");
        offerItem.setOffiGuarantee("No guarantee");
        offerItem.setOffiOffer(offerService.findOne(1L));
        offerItem.setOffiPrice(250F);
        offerItem.setOffiTenderItem(tenderItemService.findOne(7L));

        OfferItem savedOfferItem = offerItemService.save(offerItem);
        OfferItem loadedOfferItem = offerItemService.findOne(savedOfferItem.getOffiId());

        Assert.assertNotNull(savedOfferItem);
        Assert.assertNotNull(loadedOfferItem);
        Assert.assertEquals(savedOfferItem.getOffiId(), loadedOfferItem.getOffiId());
    }

    @Test
    public void testDelete(){
        OfferItem offerItem = new OfferItem();
        offerItem.setOffiDeliveryTime("5 days");
        offerItem.setOffiGuarantee("No guarantee");
        offerItem.setOffiOffer(offerService.findOne(1L));
        offerItem.setOffiPrice(250F);
        offerItem.setOffiTenderItem(tenderItemService.findOne(7L));

        OfferItem savedOfferItem = offerItemService.save(offerItem);
        offerItemService.delete(savedOfferItem.getOffiId());
        OfferItem loadedOfferItem = offerItemService.findOne(savedOfferItem.getOffiId());

        Assert.assertNull(loadedOfferItem);
    }

    @Test
    @Transactional
    public void findByOffiOffer(){
        Offer offer = offerService.findOne(1L);
        List<OfferItem> offerItems = offerItemService.findByOffiOffer(offer);
        Assert.assertNotNull(offerItems);
        for (OfferItem offerItem: offerItems) {
            Assert.assertEquals(offer.getOffId(), offerItem.getOffiOffer().getOffId());
        }
    }

    @Test
    @Transactional
    public void findByOffiTenderItem(){
        TenderItem tenderItem = tenderItemService.findOne(7L);
        List<OfferItem> offerItems = offerItemService.findByOffiTenderItem(tenderItem);
        Assert.assertNotNull(offerItems);
        for (OfferItem offerItem : offerItems) {
            Assert.assertEquals(tenderItem.getTiId(), offerItem.getOffiTenderItem().getTiId());
        }
    }
}
