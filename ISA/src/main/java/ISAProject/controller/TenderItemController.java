package ISAProject.controller;

import ISAProject.model.OfferItem;
import ISAProject.model.Tender;
import ISAProject.model.TenderItem;
import ISAProject.service.OfferItemService;
import ISAProject.service.TenderItemService;
import ISAProject.service.TenderService;
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
public class TenderItemController {
    @Autowired
    private TenderItemService tenderItemService;

    @Autowired
    private TenderService tenderService;

    @Autowired
    private OfferItemService offerItemService;

    @RequestMapping(
            value = "/getTenderItems",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<TenderItem>> getTenderItems(){
        List<TenderItem> tenderItems = tenderItemService.findAll();
        return new ResponseEntity<List<TenderItem>>(tenderItems, HttpStatus.OK);
    }

    @RequestMapping(
            value = "/getTenderItem/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TenderItem> getTenderItem(@PathVariable("id") Long tenderItemId) {
        TenderItem tenderItem = tenderItemService.findOne(tenderItemId);
        return new ResponseEntity<TenderItem>(tenderItem, HttpStatus.OK);
    }

    @RequestMapping(
            value = "/getTenderItemsByTiTender/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<TenderItem>> getTenderItemsByTiTender(@PathVariable("id") Long tenderId){
        Tender tender = tenderService.findOne(tenderId);
        List<TenderItem> tenderItems = tenderItemService.findByTiTender(tender);
        return new ResponseEntity<List<TenderItem>>(tenderItems, HttpStatus.OK);
    }

    @RequestMapping(
            value = "/removeTenderItem/{id}",
            method = RequestMethod.DELETE)
    public ResponseEntity<TenderItem> removeTenderItem(@PathVariable("id") Long id) {

        TenderItem tenderItem = tenderItemService.findOne(id);

        //delete offer items
        List<OfferItem> offerItems = offerItemService.findByOffiTenderItem(tenderItem);
        for(OfferItem oi: offerItems){
            offerItemService.delete(oi.getOffiId());
        }

        tenderItemService.delete(id);
        return new ResponseEntity<TenderItem>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(
            value = "/addTenderItem",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TenderItem> addTenderItem(@RequestBody TenderItem tenderItem) throws Exception {
        TenderItem savedTenderItem = tenderItemService.save(tenderItem);
        return new ResponseEntity<TenderItem>(savedTenderItem, HttpStatus.CREATED);
    }

    @RequestMapping(
            value = "/updateTenderItem",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TenderItem> updateTenderItem(@RequestBody TenderItem tenderItem) throws Exception {
        TenderItem savedTenderItem = tenderItemService.save(tenderItem);
        return new ResponseEntity<TenderItem>(savedTenderItem, HttpStatus.CREATED);
    }
}
