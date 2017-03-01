package ISAProject.controller;

import ISAProject.model.Offer;
import ISAProject.model.OfferItem;
import ISAProject.model.Tender;
import ISAProject.model.users.Provider;
import ISAProject.service.OfferItemService;
import ISAProject.service.OfferService;
import ISAProject.service.ProviderService;
import ISAProject.service.TenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Marko on 2/24/2017.
 */
@RestController
public class OfferController {
    @Autowired
    private OfferService offerService;

    @Autowired
    private TenderService tenderService;

    @Autowired
    private ProviderService providerService;

    @Autowired
    private OfferItemService offerItemService;

    @RequestMapping(
            value = "/getOffers",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Offer>> getOffers(){
        List<Offer> offers = offerService.findAll();
        return new ResponseEntity<List<Offer>>(offers, HttpStatus.OK);
    }

    @RequestMapping(
            value = "/getOffer/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Offer> getOffer(@PathVariable("id") Long offerId) {
        Offer offer = offerService.findOne(offerId);
        return new ResponseEntity<Offer>(offer, HttpStatus.OK);
    }

    @RequestMapping(
            value = "/getOffersByOffTender/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Offer>> getOffersByOffTender(@PathVariable("id") Long tenderId){
        Tender tender = tenderService.findOne(tenderId);
        List<Offer> offers = offerService.findByOffTender(tender);
        return new ResponseEntity<List<Offer>>(offers, HttpStatus.OK);
    }

    @RequestMapping(
            value = "/getOffersByOffTenderAndOffStatus/{id}/{status}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Offer>> getOffersByOffTenderAndOffStatus(@PathVariable("id") Long tenderId, @PathVariable("status") String offStatus){
        Tender tender = tenderService.findOne(tenderId);
        if(offStatus.equals("All")) {
            List<Offer> offers = offerService.findByOffTender(tender);
            return new ResponseEntity<List<Offer>>(offers, HttpStatus.OK);
        }else{
            List<Offer> offers = offerService.findByOffTenderAndOffStatus(tender, offStatus);
            return new ResponseEntity<List<Offer>>(offers, HttpStatus.OK);
        }
    }

    @RequestMapping(
            value = "/getOffersByOffProvider/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Offer>> getOffersByOffProvider(@PathVariable("id") Long providerId){
        Provider provider = providerService.findOne(providerId);
        List<Offer> offers = offerService.findByOffProvider(provider);
        return new ResponseEntity<List<Offer>>(offers, HttpStatus.OK);
    }

    @RequestMapping(
            value = "/getOffersByOffProviderAndOffStatus/{id}/{status}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Offer>> getOffersByOffProviderAndOffStatus(@PathVariable("id") Long providerId, @PathVariable("status") String offStatus){
        Provider provider = providerService.findOne(providerId);
        if(offStatus.equals("All")) {
            List<Offer> offers = offerService.findByOffProvider(provider);
            return new ResponseEntity<List<Offer>>(offers, HttpStatus.OK);
        }else{
            List<Offer> offers = offerService.findByOffProviderAndOffStatus(provider, offStatus);
            return new ResponseEntity<List<Offer>>(offers, HttpStatus.OK);
        }
    }

    @RequestMapping(
            value = "/getOffersByOffTenderAndOffProvider/{tid}/{pid}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Offer> getOffersByOffTenderAndOffProvider(@PathVariable("tid") Long tenderId, @PathVariable("pid") Long providerId){
        Tender tender = tenderService.findOne(tenderId);
        Provider provider = providerService.findOne(providerId);
        Offer offer = offerService.findByOffTenderAndOffProvider(tender, provider);
        return new ResponseEntity<Offer>(offer, HttpStatus.OK);
    }

    @RequestMapping(
            value = "/removeOffer/{id}",
            method = RequestMethod.DELETE)
    public ResponseEntity<Offer> removeOffer(@PathVariable("id") Long id) {

        Offer offer = offerService.findOne(id);

        //delete tender items
        List<OfferItem> offerItems = offerItemService.findByOffiOffer(offer);
        for(OfferItem oi: offerItems){
            offerItemService.delete(oi.getOffiId());
        }

        offerService.delete(id);
        return new ResponseEntity<Offer>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(
            value = "/addOffer",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Offer> addOffer(@RequestBody Offer offer) throws Exception {
        boolean validationResult = true;
        if(offer.getOffStatus() == null)
            validationResult = false;
        if(validationResult) {
            Tender tender = tenderService.findOne(offer.getOffTender().gettId());
            if (tender.gettStatus().equals("Active")) {
                System.out.println(tender.gettStatus());
                Offer savedOffer = offerService.save(offer);
                return new ResponseEntity<Offer>(savedOffer, HttpStatus.CREATED);
            } else {
                return new ResponseEntity<Offer>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }else{
            return new ResponseEntity<Offer>(HttpStatus.FORBIDDEN);
        }
    }

    @RequestMapping(
            value = "/updateOffer",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Offer> updateOffer(@RequestBody Offer offer) throws Exception {
        boolean validationResult = true;
        if(offer.getOffStatus() == null)
            validationResult = false;
        if(validationResult) {
            Tender tender = tenderService.findOne(offer.getOffTender().gettId());
            if (tender.gettStatus().equals("Active")) {
                //System.out.println(offer.getOffTender().gettStatus());
                Offer savedOffer = offerService.save(offer);
            /*List<OfferItem> offerItems = offerItemService.findByOffiOffer(savedOffer);
            for(OfferItem offerItem: offerItems){
                offerItem.setVersion(offerItem.getVersion() + 1);
                offerItemService.save(offerItem);
            }*/
                return new ResponseEntity<Offer>(savedOffer, HttpStatus.CREATED);
            } else {
                return new ResponseEntity<Offer>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }else {
            return new ResponseEntity<Offer>(HttpStatus.FORBIDDEN);
        }
    }

    @MessageMapping("/acceptOffer/{id}")
    @SendTo("/topic/offers/{id}")
    public Long acceptOffer(@DestinationVariable Long id, Offer offer){
        return offer.getOffId();
    }
}
