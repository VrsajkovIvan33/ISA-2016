package ISAProject.controller;

import ISAProject.model.TableRegion;
import ISAProject.service.TableRegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Verpsychoff on 2/16/2017.
 */

@RestController
public class TableRegionController {

    @Autowired
    private TableRegionService tableRegionService;

    @RequestMapping(
            value = "/TableRegions",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<TableRegion>> getTableRegions(){
        List<TableRegion> tableRegions = tableRegionService.findAll();
        return new ResponseEntity<List<TableRegion>>(tableRegions, HttpStatus.OK);
    }
}
