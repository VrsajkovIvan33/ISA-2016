package ISAProject.controller;

import ISAProject.model.Menu;
import ISAProject.model.Restaurant;
import ISAProject.service.MenuService;
import ISAProject.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Marko on 2/18/2017.
 */
@RestController
public class MenuController {
    @Autowired
    private MenuService menuService;

    @Autowired
    private RestaurantService restaurantService;

    @RequestMapping(
            value = "/getMenus",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Menu>> getMenus(){
        List<Menu> menus = menuService.findAll();
        return new ResponseEntity<List<Menu>>(menus, HttpStatus.OK);
    }

    @RequestMapping(
            value = "/getMenu/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Menu> getMenu(@PathVariable("id") Long menuId) {
        Menu menu = menuService.findOne(menuId);
        return new ResponseEntity<Menu>(menu, HttpStatus.OK);
    }

    @RequestMapping(
            value = "/getMenusByMRestaurant/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Menu>> getMenusByMRestaurant(@PathVariable("id") Long restaurantId){
        Restaurant restaurant = restaurantService.findOne(restaurantId);
        List<Menu> menus = menuService.findByMRestaurant(restaurant);
        return new ResponseEntity<List<Menu>>(menus, HttpStatus.OK);
    }

    @RequestMapping(
            value = "/getMenusByMRestaurantAndMType/{id}/{type}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Menu>> getMenusByMRestaurantAndMType(@PathVariable("id") Long restaurantId, @PathVariable("type") String mType){
        Restaurant restaurant = restaurantService.findOne(restaurantId);
        if(mType.equals("Menu")) {
            List<Menu> menus = menuService.findByMRestaurant(restaurant);
            List<Menu> food = new ArrayList<>();
            for(Menu m: menus){
                if(!m.getmType().equals("Drink")){
                    food.add(m);
                }
            }
            return new ResponseEntity<List<Menu>>(food, HttpStatus.OK);
        }else{
            List<Menu> menus = menuService.findByMRestaurantAndMType(restaurant, mType);
            return new ResponseEntity<List<Menu>>(menus, HttpStatus.OK);
        }
    }

    @RequestMapping(
            value = "/removeMenu/{id}",
            method = RequestMethod.DELETE)
    public ResponseEntity<Menu> removeMenu(@PathVariable("id") Long id) {
        menuService.delete(id);
        return new ResponseEntity<Menu>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(
            value = "/addMenu",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Menu> createMenu(@RequestBody Menu menu) throws Exception {
        Menu savedMenu = menuService.save(menu);
        return new ResponseEntity<Menu>(savedMenu, HttpStatus.CREATED);
    }

    @RequestMapping(
            value = "/updateMenu",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Menu> updateMenu(@RequestBody Menu menu) throws Exception {
        Menu savedMenu = menuService.save(menu);
        return new ResponseEntity<Menu>(savedMenu, HttpStatus.CREATED);
    }
}
