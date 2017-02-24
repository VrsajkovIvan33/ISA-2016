package ISAProject.controller;

import ISAProject.model.Offer;
import ISAProject.model.Restaurant;
import ISAProject.model.Tender;
import ISAProject.model.TenderItem;
import ISAProject.service.OfferService;
import ISAProject.service.RestaurantService;
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
public class TenderController {
    @Autowired
    private TenderService tenderService;

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private TenderItemService tenderItemService;

    @Autowired
    private OfferService offerService;

    @RequestMapping(
            value = "/getTenders",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Tender>> getTenders(){
        List<Tender> tenders = tenderService.findAll();
        return new ResponseEntity<List<Tender>>(tenders, HttpStatus.OK);
    }

    @RequestMapping(
            value = "/getTender/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Tender> getTender(@PathVariable("id") Long tenderId) {
        Tender tender = tenderService.findOne(tenderId);
        return new ResponseEntity<Tender>(tender, HttpStatus.OK);
    }

    @RequestMapping(
            value = "/getTendersByTRestaurant/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Tender>> getTendersByTRestaurant(@PathVariable("id") Long restaurantId){
        Restaurant restaurant = restaurantService.findOne(restaurantId);
        List<Tender> tenders = tenderService.findByTRestaurant(restaurant);
        return new ResponseEntity<List<Tender>>(tenders, HttpStatus.OK);
    }

    @RequestMapping(
            value = "/getTendersByTRestaurantAndTStatus/{id}/{status}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Tender>> getTendersByTRestaurantAndTStatus(@PathVariable("id") Long restaurantId, @PathVariable("status") String tStatus){
        Restaurant restaurant = restaurantService.findOne(restaurantId);
        if(tStatus.equals("All")) {
            List<Tender> tenders = tenderService.findByTRestaurant(restaurant);
            return new ResponseEntity<List<Tender>>(tenders, HttpStatus.OK);
        }else{
            List<Tender> tenders = tenderService.findByTRestaurantAndTStatus(restaurant, tStatus);
            return new ResponseEntity<List<Tender>>(tenders, HttpStatus.OK);
        }
    }

    @RequestMapping(
            value = "/removeTender/{id}",
            method = RequestMethod.DELETE)
    public ResponseEntity<Tender> removeTender(@PathVariable("id") Long id) {

        Tender tender = tenderService.findOne(id);

        //delte offers
        List<Offer> offers = offerService.findByOffTender(tender);
        for(Offer o: offers){
            o.setOffTender(null);
            o.setOffStatus("Closed");
            offerService.save(o);
        }

        //delete tender items
        List<TenderItem> tenderItems = tenderItemService.findByTiTender(tender);
        for(TenderItem ti: tenderItems){
            tenderItemService.delete(ti.getTiId());
        }

        tenderService.delete(id);
        return new ResponseEntity<Tender>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(
            value = "/addTender",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Tender> addTender(@RequestBody Tender tender) throws Exception {
        Tender savedTender = tenderService.save(tender);
        return new ResponseEntity<Tender>(savedTender, HttpStatus.CREATED);
    }

    @RequestMapping(
            value = "/updateTender",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Tender> updateTender(@RequestBody Tender tender) throws Exception {
        Tender savedTender = tenderService.save(tender);
        return new ResponseEntity<Tender>(savedTender, HttpStatus.CREATED);
    }
}
