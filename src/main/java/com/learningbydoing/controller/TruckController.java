package com.learningbydoing.controller;

import com.learningbydoing.NotFoundException;
import com.learningbydoing.domain.Truck;
import com.learningbydoing.service.TruckService;
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
@RequestMapping(value = "/truck")
@Api(value = "Vehicles Inventory", description = "Operations pertaining to Truck in Vehicles Inventory")
public class TruckController {

    @Autowired
    private TruckService truckService;

    @ApiOperation(value = "Create an Truck")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successfully persisted in the database"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @RequestMapping(method = RequestMethod.POST, value = "/save", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<?> create(@NotNull @RequestBody Truck truck) {
        truckService.save(truck);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @ApiOperation(value = "Update an Truck")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully updated in the database"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @RequestMapping(method = RequestMethod.PUT, value = "/update", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<?> update(Truck truck) {
        Truck truck1 = truckService.getById(truck.getVehicleId());
        if (truck1 == null)
            return new ResponseEntity(new NotFoundException("Unable update. User with id: " + truck.getVehicleId() + " not found"), HttpStatus.NOT_FOUND);
        truckService.update(truck);
        return new ResponseEntity<String>(HttpStatus.OK);
    }

    @ApiOperation(value = "get an Truck by Id", response = Truck.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved an Truck by Id"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @RequestMapping(method = RequestMethod.GET, value = "/get/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<Truck> get(@PathVariable("id") Integer truckId) {
        Truck truck = truckService.getById(truckId);
        if (truck.getVehicleId() != 0)
            return new ResponseEntity<Truck>(truck, HttpStatus.OK);
        return new ResponseEntity(new NotFoundException("User with id: " + truck.getVehicleId() + " not found"), HttpStatus.NOT_FOUND);
    }

    @ApiOperation(value = "Delete an Truck by Id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully Deleted an Truck by Id"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @RequestMapping(method = RequestMethod.DELETE, value = "/delete/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<?> delete(@PathVariable("id") Integer truckId) {
        Truck Truck = truckService.getById(truckId);
        if (Truck.getVehicleId() == 0)
            return new ResponseEntity(new NotFoundException("User with id: " + Truck.getVehicleId() + " not found"), HttpStatus.NOT_FOUND);
        truckService.deleteById(truckId);
        return new ResponseEntity(HttpStatus.OK);
    }

    @ApiOperation(value = "Search an Truck by Brand name", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list of Truck by Brand name"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @RequestMapping(method = RequestMethod.GET, value = "/search/{brandName}/brandName", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<List<Truck>> findByBrand(@PathVariable("brandName") String brandName) {
        return new ResponseEntity<List<Truck>>(truckService.findByBrand(brandName), HttpStatus.OK);
    }

    @ApiOperation(value = "Search an Truck by Model number", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list of Truck by Model number"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @RequestMapping(method = RequestMethod.GET, value = "/search/{modelNumber}/modelNumber", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<List<Truck>> findByModelNumber(@PathVariable("modelNumber") String modelNumber) {
        return new ResponseEntity<List<Truck>>(truckService.findByModelNumber(modelNumber), HttpStatus.OK);
    }

    @ApiOperation(value = "Search an Truck by Name", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list of Truck by Name"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @RequestMapping(method = RequestMethod.GET, value = "/search/{name}/name", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<List<Truck>> findByName(@PathVariable("name") String name) {
        return new ResponseEntity<List<Truck>>(truckService.findByName(name), HttpStatus.OK);
    }

    @ApiOperation(value = "Search an Truck by Manufacturing date", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list of Truck by Manufacturing date"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @RequestMapping(method = RequestMethod.GET, value = "/search/{mfgYear}/mfgYear", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<List<Truck>> findByYear(@PathVariable("mfgYear") Integer mfgYear) {
        return new ResponseEntity<List<Truck>>(truckService.findByMfgYear(mfgYear), HttpStatus.OK);
    }

}
