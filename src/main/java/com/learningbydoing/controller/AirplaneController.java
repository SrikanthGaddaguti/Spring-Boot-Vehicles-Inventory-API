package com.learningbydoing.controller;

import com.learningbydoing.NotFoundException;
import com.learningbydoing.domain.Airplane;
import com.learningbydoing.service.AirplaneService;
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
@RequestMapping(value = "/airplane")
@Api(value = "Vehicles Inventory", description = "Operations pertaining to Airplane in Vehicles Inventory")
public class AirplaneController {

    @Autowired
    private AirplaneService airplaneService;

    @ApiOperation(value = "Create an Airplane")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successfully persisted in the database"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @RequestMapping(method = RequestMethod.POST, value = "/save", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<?> create(@NotNull @RequestBody Airplane airplane) {
        airplaneService.save(airplane);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @ApiOperation(value = "Update an Airplane")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully updated in the database"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @RequestMapping(method = RequestMethod.PUT, value = "/update", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<?> update(@NotNull @RequestBody Airplane airplane) {
        Airplane airplane1 = airplaneService.getById(airplane.getVehicleId());
        if (airplane1 == null)
            return new ResponseEntity(new NotFoundException("Unable update. User with id: " + airplane.getVehicleId() + " not found"), HttpStatus.NOT_FOUND);
        airplaneService.update(airplane);
        return new ResponseEntity<String>(HttpStatus.OK);
    }

    @ApiOperation(value = "get an Airplane by Id", response = Airplane.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved an Airplane by Id"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @RequestMapping(method = RequestMethod.GET, value = "/get/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<Airplane> get(@PathVariable("id") Integer airplaneId) {
        Airplane airplane = airplaneService.getById(airplaneId);
        if (airplane.getVehicleId() != 0)
            return new ResponseEntity<Airplane>(airplane, HttpStatus.OK);
        return new ResponseEntity(new NotFoundException("User with id: " + airplane.getVehicleId() + " not found"), HttpStatus.NOT_FOUND);
    }

    @ApiOperation(value = "Delete an Airplane by Id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully Deleted an Airplane by Id"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @RequestMapping(method = RequestMethod.DELETE, value = "/delete/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<?> delete(@PathVariable("id") Integer airplaneId) {
        Airplane airplane = airplaneService.getById(airplaneId);
        if (airplane.getVehicleId() == 0)
            return new ResponseEntity(new NotFoundException("User with id: " + airplane.getVehicleId() + " not found"), HttpStatus.NOT_FOUND);
        airplaneService.deleteById(airplaneId);
        return new ResponseEntity(HttpStatus.OK);
    }

    @ApiOperation(value = "Search an Airplane by Brand name", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list of Airplane by Brand name"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @RequestMapping(method = RequestMethod.GET, value = "/search/{brandName}/brandName", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<List<Airplane>> findByBrand(@PathVariable("brandName") String brandName) {
        return new ResponseEntity<List<Airplane>>(airplaneService.findByBrand(brandName), HttpStatus.OK);
    }

    @ApiOperation(value = "Search an Airplane by Model number", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list of Airplane by Model number"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @RequestMapping(method = RequestMethod.GET, value = "/search/{modelNumber}/modelNumber", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<List<Airplane>> findByModelNumber(@PathVariable("modelNumber") String modelNumber) {
        return new ResponseEntity<List<Airplane>>(airplaneService.findByModelNumber(modelNumber), HttpStatus.OK);
    }

    @ApiOperation(value = "Search an Airplane by Name", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list of Airplane by Name"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @RequestMapping(method = RequestMethod.GET, value = "/search/{name}/name", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<List<Airplane>> findByName(@PathVariable("name") String name) {
        return new ResponseEntity<List<Airplane>>(airplaneService.findByName(name), HttpStatus.OK);
    }

    @ApiOperation(value = "Search an Airplane by Manufacturing date", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list of Airplane by Manufacturing date"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @RequestMapping(method = RequestMethod.GET, value = "/search/{mfgYear}/mfgYear", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<List<Airplane>> findByYear(@PathVariable("mfgYear") Integer mfgYear) {
        return new ResponseEntity<List<Airplane>>(airplaneService.findByMfgYear(mfgYear), HttpStatus.OK);
    }

}
