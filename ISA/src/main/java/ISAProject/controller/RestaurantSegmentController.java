package ISAProject.controller;

import ISAProject.model.RestaurantSegment;
import ISAProject.service.RestaurantSegmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by verpsychoff on 2/16/2017.
 */

@RestController
public class RestaurantSegmentController {

    @Autowired
    private RestaurantSegmentService restaurantSegmentService;

    @RequestMapping(
            value = "/RestaurantSegments",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<RestaurantSegment>> getRestaurantSegments(){
        List<RestaurantSegment> restaurantSegments = restaurantSegmentService.findAll();
        return new ResponseEntity<List<RestaurantSegment>>(restaurantSegments, HttpStatus.OK);
    }

}
