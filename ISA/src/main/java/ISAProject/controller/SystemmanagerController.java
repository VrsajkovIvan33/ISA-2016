package ISAProject.controller;

import ISAProject.model.users.SystemManager;
import ISAProject.service.SystemmanagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Marko on 12/18/2016.
 */
@RestController
public class SystemmanagerController {
    @Autowired
    private SystemmanagerService systemmanagerService;

    @RequestMapping(
            value = "/getSystemManagers",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<SystemManager>> getSystemManagers(){
        List<SystemManager> systemManagers = systemmanagerService.findAll();
        return new ResponseEntity<List<SystemManager>>(systemManagers, HttpStatus.OK);
    }

    @RequestMapping(
            value = "/removeSystemManager/{id}",
            method = RequestMethod.DELETE)
    public ResponseEntity<SystemManager> removeSystemManager(@PathVariable("id") Long id) {
        systemmanagerService.delete(id);
        return new ResponseEntity<SystemManager>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(
            value = "/addSystemManager",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SystemManager> createSystemManager(@RequestBody SystemManager systemManager) throws Exception {
        SystemManager savedSystemManager = systemmanagerService.save(systemManager);
        return new ResponseEntity<SystemManager>(savedSystemManager, HttpStatus.CREATED);
    }

    @RequestMapping(
            value = "/updateSystemManager",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SystemManager> updateSystemManager(@RequestBody SystemManager systemManager) throws Exception {
        SystemManager savedSystemManager = systemmanagerService.save(systemManager);
        return new ResponseEntity<SystemManager>(savedSystemManager, HttpStatus.CREATED);
    }
}
