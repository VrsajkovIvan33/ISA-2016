package ISAProject.controller;

import ISAProject.model.users.Provider;
import ISAProject.service.ProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Marko on 12/21/2016.
 */
@RestController
public class ProviderController {
    @Autowired
    private ProviderService providerService;

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
}
