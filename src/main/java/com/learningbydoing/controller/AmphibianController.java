package com.learningbydoing.controller;

import com.learningbydoing.NotFoundException;
import com.learningbydoing.domain.Amphibian;
import com.learningbydoing.domain.Amphibian;
import com.learningbydoing.service.AmphibianService;
import com.learningbydoing.service.AmphibianService;
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
@RequestMapping(value = "/amphibian")
@Api(value = "Vehicles Inventory", description = "Operations pertaining to Amphibian in Vehicles Inventory")
public class AmphibianController {

    @Autowired
    private AmphibianService amphibianService;

    @ApiOperation(value = "Create an Amphibian")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successfully persisted in the database"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @RequestMapping(method = RequestMethod.POST, value = "/save", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<?> create(@NotNull @RequestBody Amphibian amphibian) {
        amphibianService.save(amphibian);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @ApiOperation(value = "Update an Amphibian")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully updated in the database"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @RequestMapping(method = RequestMethod.PUT, value = "/update", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<?> update(@NotNull @RequestBody Amphibian amphibian) {
        Amphibian amphibian1 = amphibianService.getById(amphibian.getVehicleId());
        if (amphibian1 == null)
            return new ResponseEntity(new NotFoundException("Unable update. User with id: " + amphibian.getVehicleId() + " not found"), HttpStatus.NOT_FOUND);
        amphibianService.update(amphibian);
        return new ResponseEntity<String>(HttpStatus.OK);
    }

    @ApiOperation(value = "get an Amphibian by Id", response = Amphibian.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved an Amphibian by Id"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @RequestMapping(method = RequestMethod.GET, value = "/get/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<Amphibian> get(@PathVariable("id") Integer amphibianId) {
        Amphibian amphibian = amphibianService.getById(amphibianId);
        if (amphibian.getVehicleId() != 0)
            return new ResponseEntity<Amphibian>(amphibian, HttpStatus.OK);
        return new ResponseEntity(new NotFoundException("User with id: " + amphibian.getVehicleId() + " not found"), HttpStatus.NOT_FOUND);
    }

    @ApiOperation(value = "Delete an Amphibian by Id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully Deleted an Amphibian by Id"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @RequestMapping(method = RequestMethod.DELETE, value = "/delete/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<?> delete(@PathVariable("id") Integer amphibianId) {
        Amphibian amphibian = amphibianService.getById(amphibianId);
        if (amphibian.getVehicleId() == 0)
            return new ResponseEntity(new NotFoundException("User with id: " + amphibian.getVehicleId() + " not found"), HttpStatus.NOT_FOUND);
        amphibianService.deleteById(amphibianId);
        return new ResponseEntity(HttpStatus.OK);
    }

    @ApiOperation(value = "Search an Amphibian by Brand name", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list of Amphibian by Brand name"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @RequestMapping(method = RequestMethod.GET, value = "/search/{brandName}/brandName", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<List<Amphibian>> findByBrand(@PathVariable("brandName") String brandName) {
        return new ResponseEntity<List<Amphibian>>(amphibianService.findByBrand(brandName), HttpStatus.OK);
    }

    @ApiOperation(value = "Search an Amphibian by Model number", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list of Amphibian by Model number"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @RequestMapping(method = RequestMethod.GET, value = "/search/{modelNumber}/modelNumber", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<List<Amphibian>> findByModelNumber(@PathVariable("modelNumber") String modelNumber) {
        return new ResponseEntity<List<Amphibian>>(amphibianService.findByModelNumber(modelNumber), HttpStatus.OK);
    }

    @ApiOperation(value = "Search an Amphibian by Name", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list of Amphibian by Name"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @RequestMapping(method = RequestMethod.GET, value = "/search/{name}/name", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<List<Amphibian>> findByName(@PathVariable("name") String name) {
        return new ResponseEntity<List<Amphibian>>(amphibianService.findByName(name), HttpStatus.OK);
    }

    @ApiOperation(value = "Search an Amphibian by Manufacturing date", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list of Amphibian by Manufacturing date"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @RequestMapping(method = RequestMethod.GET, value = "/search/{mfgYear}/mfgYear", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<List<Amphibian>> findByYear(@PathVariable("mfgYear") Integer mfgYear) {
        return new ResponseEntity<List<Amphibian>>(amphibianService.findByMfgYear(mfgYear), HttpStatus.OK);
    }

}
