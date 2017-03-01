package ISAProject.controller;

import ISAProject.model.Offer;
import ISAProject.model.OfferItem;
import ISAProject.model.TenderItem;
import ISAProject.service.OfferItemService;
import ISAProject.service.OfferService;
import ISAProject.service.TenderItemService;
import org.apache.http.protocol.HTTP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Marko on 2/24/2017.
 */
@RestController
public class OfferItemController {
    @Autowired
    private OfferItemService offerItemService;

    @Autowired
    private OfferService offerService;

    @Autowired
    private TenderItemService tenderItemService;

    @RequestMapping(
            value = "/getOfferItems",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<OfferItem>> getOfferItems(){
        List<OfferItem> offerItems = offerItemService.findAll();
        return new ResponseEntity<List<OfferItem>>(offerItems, HttpStatus.OK);
    }

    @RequestMapping(
            value = "/getOfferItem/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OfferItem> getOfferItem(@PathVariable("id") Long offerItemId) {
        OfferItem offerItem = offerItemService.findOne(offerItemId);
        return new ResponseEntity<OfferItem>(offerItem, HttpStatus.OK);
    }

    @RequestMapping(
            value = "/getOfferItemsByOffiOffer/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<OfferItem>> getOfferItemsByOffiOffer(@PathVariable("id") Long offerId){
        Offer offer = offerService.findOne(offerId);
        List<OfferItem> offerItems = offerItemService.findByOffiOffer(offer);
        return new ResponseEntity<List<OfferItem>>(offerItems, HttpStatus.OK);
    }

    @RequestMapping(
            value = "/getOfferItemsByOffiTenderItem/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<OfferItem>> getOfferItemsByOffiTenderItem(@PathVariable("id") Long tenderItemId){
        TenderItem tenderItem = tenderItemService.findOne(tenderItemId);
        List<OfferItem> offerItems = offerItemService.findByOffiTenderItem(tenderItem);
        return new ResponseEntity<List<OfferItem>>(offerItems, HttpStatus.OK);
    }

    @RequestMapping(
            value = "/removeOfferItem/{id}",
            method = RequestMethod.DELETE)
    public ResponseEntity<OfferItem> removeOfferItem(@PathVariable("id") Long id) {
        offerItemService.delete(id);
        return new ResponseEntity<OfferItem>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(
            value = "/addOfferItem",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OfferItem> addOfferItem(@RequestBody OfferItem offerItem) throws Exception {
        if(offerItem.getOffiOffer().getOffTender().gettStatus().equals("Active")) {
            OfferItem savedOfferItem = offerItemService.save(offerItem);
            return new ResponseEntity<OfferItem>(savedOfferItem, HttpStatus.CREATED);
        }else{
            return new ResponseEntity<OfferItem>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(
            value = "/updateOfferItem",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OfferItem> updateOfferItem(@RequestBody OfferItem offerItem) throws Exception {
        if(offerItem.getOffiOffer().getOffStatus().equals("On hold")) {
            OfferItem savedOfferItem = offerItemService.save(offerItem);
            return new ResponseEntity<OfferItem>(savedOfferItem, HttpStatus.CREATED);
        }else{
            return new ResponseEntity<OfferItem>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
