package com.learningbydoing.controller;

import com.learningbydoing.NotFoundException;
import com.learningbydoing.domain.Boat;
import com.learningbydoing.service.BoatService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping(value = "/boat")
@Api(value = "Vehicles Inventory", description = "Operations pertaining to Boat in Vehicles Inventory")
public class BoatController {

    @Autowired
    private BoatService boatService;

    @ApiOperation(value = "Create an Boat")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successfully persisted in the database"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @RequestMapping(method = RequestMethod.POST, value = "/save", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<?> create(@NotNull @RequestBody Boat boat) {
        boatService.save(boat);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @ApiOperation(value = "Update an Boat")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully updated in the database"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @RequestMapping(method = RequestMethod.PUT, value = "/update", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<?> update(Boat boat) {
        Boat boat1 = boatService.getById(boat.getVehicleId());
        if (boat1 == null)
            return new ResponseEntity(new NotFoundException("Unable update. User with id: " + boat.getVehicleId() + " not found"), HttpStatus.NOT_FOUND);
        boatService.update(boat);
        return new ResponseEntity<String>(HttpStatus.OK);
    }

    @ApiOperation(value = "get an Boat by Id", response = Boat.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved an Boat by Id"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @RequestMapping(method = RequestMethod.GET, value = "/get/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<Boat> get(@PathVariable("id") Integer boatId) {
        Boat boat = boatService.getById(boatId);
        if (boat.getVehicleId() != 0)
            return new ResponseEntity<Boat>(boat, HttpStatus.OK);
        return new ResponseEntity(new NotFoundException("User with id: " + boat.getVehicleId() + " not found"), HttpStatus.NOT_FOUND);
    }

    @ApiOperation(value = "Delete an Boat by Id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully Deleted an Boat by Id"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @RequestMapping(method = RequestMethod.DELETE, value = "/delete/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<?> delete(@PathVariable("id") Integer boatId) {
        Boat boat = boatService.getById(boatId);
        if (boat.getVehicleId() == 0)
            return new ResponseEntity(new NotFoundException("User with id: " + boat.getVehicleId() + " not found"), HttpStatus.NOT_FOUND);
        boatService.deleteById(boatId);
        return new ResponseEntity(HttpStatus.OK);
    }

    @ApiOperation(value = "Search an Boat by Brand name", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list of Boat by Brand name"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @RequestMapping(method = RequestMethod.GET, value = "/search/{brandName}/brandName", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<List<Boat>> findByBrand(@PathVariable("brandName") String brandName) {
        return new ResponseEntity<List<Boat>>(boatService.findByBrand(brandName), HttpStatus.OK);
    }

    @ApiOperation(value = "Search an Boat by Model number", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list of Boat by Model number"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @RequestMapping(method = RequestMethod.GET, value = "/search/{modelNumber}/modelNumber", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<List<Boat>> findByModelNumber(@PathVariable("modelNumber") String modelNumber) {
        return new ResponseEntity<List<Boat>>(boatService.findByModelNumber(modelNumber), HttpStatus.OK);
    }

    @ApiOperation(value = "Search an Boat by Name", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list of Boat by Name"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @RequestMapping(method = RequestMethod.GET, value = "/search/{name}/name", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<List<Boat>> findByName(@PathVariable("name") String name) {
        return new ResponseEntity<List<Boat>>(boatService.findByName(name), HttpStatus.OK);
    }

    @ApiOperation(value = "Search an Boat by Manufacturing date", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list of Boat by Manufacturing date"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @RequestMapping(method = RequestMethod.GET, value = "/search/{mfgYear}/mfgYear", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<List<Boat>> findByYear(@PathVariable("mfgYear") Integer mfgYear) {
        return new ResponseEntity<List<Boat>>(boatService.findByMfgYear(mfgYear), HttpStatus.OK);
    }

}
