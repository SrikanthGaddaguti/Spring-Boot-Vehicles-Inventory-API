package com.learningbydoing.controller;

import com.learningbydoing.NotFoundException;
import com.learningbydoing.domain.Car;
import com.learningbydoing.service.CarService;
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
@RequestMapping(value = "/car")
@Api(value = "Vehicles Inventory", description = "Operations pertaining to Car in Vehicles Inventory")
public class CarController {

    @Autowired
    private CarService carService;

    @ApiOperation(value = "Create an Car")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successfully persisted in the database"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @RequestMapping(method = RequestMethod.POST, value = "/save", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<?> create(@NotNull @RequestBody Car car) {
        carService.save(car);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @ApiOperation(value = "Update an Car")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully updated in the database"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @RequestMapping(method = RequestMethod.PUT, value = "/update", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<?> update(Car car) {
        Car car1 = carService.getById(car.getVehicleId());
        if (car1 == null)
            return new ResponseEntity(new NotFoundException("Unable update. User with id: " + car.getVehicleId() + " not found"), HttpStatus.NOT_FOUND);
        carService.update(car);
        return new ResponseEntity<String>(HttpStatus.OK);
    }

    @ApiOperation(value = "get an Car by Id", response = Car.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved an Car by Id"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @RequestMapping(method = RequestMethod.GET, value = "/get/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<Car> get(@PathVariable("id") Integer carId) {
        Car car = carService.getById(carId);
        if (car.getVehicleId() != 0)
            return new ResponseEntity<Car>(car, HttpStatus.OK);
        return new ResponseEntity(new NotFoundException("User with id: " + car.getVehicleId() + " not found"), HttpStatus.NOT_FOUND);
    }

    @ApiOperation(value = "Delete an Car by Id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully Deleted an Car by Id"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @RequestMapping(method = RequestMethod.DELETE, value = "/delete/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<?> delete(@PathVariable("id") Integer carId) {
        Car car = carService.getById(carId);
        if (car.getVehicleId() == 0)
            return new ResponseEntity(new NotFoundException("User with id: " + car.getVehicleId() + " not found"), HttpStatus.NOT_FOUND);
        carService.deleteById(carId);
        return new ResponseEntity(HttpStatus.OK);
    }

    @ApiOperation(value = "Search an Car by Brand name", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list of Car by Brand name"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @RequestMapping(method = RequestMethod.GET, value = "/search/{brandName}/brandName", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<List<Car>> findByBrand(@PathVariable("brandName") String brandName) {
        return new ResponseEntity<List<Car>>(carService.findByBrand(brandName), HttpStatus.OK);
    }

    @ApiOperation(value = "Search an Car by Model number", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list of Car by Model number"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @RequestMapping(method = RequestMethod.GET, value = "/search/{modelNumber}/modelNumber", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<List<Car>> findByModelNumber(@PathVariable("modelNumber") String modelNumber) {
        return new ResponseEntity<List<Car>>(carService.findByModelNumber(modelNumber), HttpStatus.OK);
    }

    @ApiOperation(value = "Search an Car by Name", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list of Car by Name"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @RequestMapping(method = RequestMethod.GET, value = "/search/{name}/name", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<List<Car>> findByName(@PathVariable("name") String name) {
        return new ResponseEntity<List<Car>>(carService.findByName(name), HttpStatus.OK);
    }

    @ApiOperation(value = "Search an Car by Manufacturing date", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list of Car by Manufacturing date"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @RequestMapping(method = RequestMethod.GET, value = "/search/{mfgYear}/mfgYear", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<List<Car>> findByYear(@PathVariable("mfgYear") Integer mfgYear) {
        return new ResponseEntity<List<Car>>(carService.findByMfgYear(mfgYear), HttpStatus.OK);
    }

}
