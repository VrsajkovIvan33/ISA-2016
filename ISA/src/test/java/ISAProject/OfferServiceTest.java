package ISAProject;

import ISAProject.model.Offer;
import ISAProject.model.Tender;
import ISAProject.model.users.Provider;
import ISAProject.service.OfferService;
import ISAProject.service.ProviderService;
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
public class OfferServiceTest {

    @Autowired
    OfferService offerService;

    @Autowired
    TenderService tenderService;

    @Autowired
    ProviderService providerService;

    @Test
    public void testFindAll(){
        List<Offer> offers = offerService.findAll();
        Assert.assertNotNull(offers);
    }

    @Test
    public void testFindOne(){
        Offer offer = offerService.findOne(1L);
        Assert.assertEquals(1L, offer.getOffId().longValue());
    }

    @Test
    public void testSave(){
        Offer offer = new Offer();
        offer.setOffStatus("On hold");
        offer.setOffProvider(providerService.findOne(10L));
        offer.setOffTender(tenderService.findOne(3L));

        Offer savedOffer = offerService.save(offer);
        Offer loadedOffer = offerService.findOne(savedOffer.getOffId());

        Assert.assertNotNull(savedOffer);
        Assert.assertNotNull(loadedOffer);
        Assert.assertEquals(savedOffer.getOffId(), loadedOffer.getOffId());
    }

    @Test
    public void testDelete(){
        Offer offer = new Offer();
        offer.setOffStatus("On hold");
        offer.setOffProvider(providerService.findOne(10L));
        offer.setOffTender(tenderService.findOne(3L));

        Offer savedOffer = offerService.save(offer);
        offerService.delete(savedOffer.getOffId());
        Offer loadedOffer = offerService.findOne(savedOffer.getOffId());

        Assert.assertNull(loadedOffer);
    }

    @Test
    @Transactional
    public void findByOffTender(){
        Tender tender = tenderService.findOne(3L);
        List<Offer> offers = offerService.findByOffTender(tender);
        Assert.assertNotNull(offers);
        for (Offer offer : offers) {
            Assert.assertEquals(tender.gettId(), offer.getOffTender().gettId());
        }
    }

    @Test
    @Transactional
    public void findByOffTenderAndOffStatus(){
        Tender tender = tenderService.findOne(3L);
        List<Offer> offers = offerService.findByOffTenderAndOffStatus(tender, "On hold");
        Assert.assertNotNull(offers);
        for (Offer offer : offers) {
            Assert.assertEquals(tender.gettId(), offer.getOffTender().gettId());
            Assert.assertEquals("On hold", offer.getOffStatus());
        }
    }

    @Test
    @Transactional
    public void findByOffProvider(){
        Provider provider = providerService.findOne(9L);
        List<Offer> offers = offerService.findByOffProvider(provider);
        Assert.assertNotNull(offers);
        for (Offer offer : offers) {
            Assert.assertEquals(provider.getId(), offer.getOffProvider().getId());
        }
    }

    @Test
    @Transactional
    public void findByOffProviderAndOffStatus(){
        Provider provider = providerService.findOne(9L);
        List<Offer> offers = offerService.findByOffProviderAndOffStatus(provider, "On hold");
        Assert.assertNotNull(offers);
        for (Offer offer : offers) {
            Assert.assertEquals(provider.getId(), offer.getOffProvider().getId());
            Assert.assertEquals("On hold", offer.getOffStatus());
        }
    }

    @Test
    @Transactional
    public void findByOffTenderAndOffProvider(){
        Tender tender = tenderService.findOne(3L);
        Provider provider = providerService.findOne(10L);
        Offer offer = offerService.findByOffTenderAndOffProvider(tender, provider);
        Assert.assertNotNull(offer);
        Assert.assertEquals(tender.gettId(), offer.getOffTender().gettId());
        Assert.assertEquals(provider.getId(), offer.getOffProvider().getId());
    }
}
