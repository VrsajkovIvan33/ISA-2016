package ISAProject.controller;

import ISAProject.model.Offer;
import ISAProject.model.Restaurant;
import ISAProject.model.users.Provider;
import ISAProject.service.OfferService;
import ISAProject.service.ProviderService;
import ISAProject.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Marko on 12/21/2016.
 */
@RestController
public class ProviderController {
    @Autowired
    private ProviderService providerService;

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private OfferService offerService;


    @RequestMapping(
            value = "/getProviders",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Provider>> getProviders(){
        List<Provider> providers = providerService.findAll();
        return new ResponseEntity<List<Provider>>(providers, HttpStatus.OK);
    }

    @RequestMapping(
            value = "/removeProvider/{id}",
            method = RequestMethod.DELETE)
    public ResponseEntity<Provider> removeProvider(@PathVariable("id") Long id) {

        Provider provider = providerService.findOne(id);

        //delete offers
        List<Offer> offers = offerService.findByOffProvider(provider);
        for(Offer o: offers){
            o.setOffProvider(null);
            o.setOffStatus("Closed");
            offerService.save(o);
        }

        List<Restaurant> restaurants = provider.getRestaurants();
        for(Restaurant restaurant: restaurants){
            List<Provider> restaurantProviders = restaurant.getProviders();
            for(Provider p: restaurantProviders){
                if(p.getId() == id){
                    restaurantProviders.remove(p);
                    restaurant.setProviders(restaurantProviders);
                    restaurantService.save(restaurant);
                    break;
                }
            }
        }

        providerService.delete(id);
        return new ResponseEntity<Provider>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(
            value = "/addProvider",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Provider> createProvider(@RequestBody Provider provider) throws Exception {
        Provider savedProvider = providerService.save(provider);
        return new ResponseEntity<Provider>(savedProvider, HttpStatus.CREATED);
    }

    @RequestMapping(
            value = "/updateProvider",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Provider> updateProvider(@RequestBody Provider provider) throws Exception {
        Provider savedProvider = providerService.save(provider);
        return new ResponseEntity<Provider>(savedProvider, HttpStatus.CREATED);
    }

    @RequestMapping(
            value = "/searchProviders/{searchedNameSurname}/{rid}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Provider>> searchProviders(@PathVariable("searchedNameSurname") String searchedNameSurname,
                                                          @PathVariable("rid") Long rid) throws Exception{

        Restaurant restaurant = restaurantService.findOne(rid);
        List<Provider> providers = new ArrayList<Provider>();
        String[] splitNameSurname = searchedNameSurname.split(" ");

        //System.out.println(searchedNameSurname);
        //System.out.println(restaurant.getrName());

        if(splitNameSurname.length != 2){
            for(String nameSurname : splitNameSurname){
                List<Provider> providersByName = providerService.findByName(nameSurname);
                List<Provider> providersSurname = providerService.findBySurname(nameSurname);
                for(Provider provider : providersByName){
                    if(!restaurant.getProviders().contains(provider) && !providers.contains(provider))
                        providers.add(provider);
                }
                for(Provider provider : providersSurname){
                    if(!restaurant.getProviders().contains(provider) && !providers.contains(provider))
                        providers.add(provider);
                }
            }
        }else{
            List<Provider> providersByNameAndSurname = providerService.findByNameAndSurname(splitNameSurname[0], splitNameSurname[1]);
            List<Provider> providersBySurnameAndName = providerService.findByNameAndSurname(splitNameSurname[1], splitNameSurname[0]);
            for(Provider provider : providersByNameAndSurname){
                if(!restaurant.getProviders().contains(provider) && !providers.contains(provider))
                    providers.add(provider);
            }
            for(Provider provider : providersBySurnameAndName){
                if(!restaurant.getProviders().contains(provider) && !providers.contains(provider))
                    providers.add(provider);
            }
        }

        return new ResponseEntity<List<Provider>>(providers, HttpStatus.OK);
    }
}
