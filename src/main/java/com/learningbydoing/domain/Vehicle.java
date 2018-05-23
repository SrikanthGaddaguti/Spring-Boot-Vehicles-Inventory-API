package com.learningbydoing.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@MappedSuperclass
public class Vehicle implements Serializable {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    @ApiModelProperty(notes = "The database generated id for the Vehicle", hidden = true)
    private Integer vehicleId;

    @Column(name = "NAME")
    @NotNull
    @ApiModelProperty(notes = "Name of the Vehicle")
    private String name;

    @Column(name = "BRAND")
    @NotNull
    @ApiModelProperty(notes = "Brand name of the Vehicle")
    private String brand;

    @Column(name = "MODEL_NUMBER")
    @NotNull
    @ApiModelProperty(notes = "Model Number of the Vehicle")
    private String modelNumber;

    @Column(name = "MFG_YEAR")
    @NotNull
    @ApiModelProperty(notes = "Manufacturing year of the Vehicle")
    private Integer mfgYear;


    public Integer getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(Integer vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModelNumber() {
        return modelNumber;
    }

    public void setModelNumber(String modelNumber) {
        this.modelNumber = modelNumber;
    }

    public Integer getMfgYear() {
        return mfgYear;
    }

    public void setMfgYear(Integer mfgYear) {
        this.mfgYear = mfgYear;
    }
}
