package com.learningbydoing.service;

import com.learningbydoing.dao.AirplaneRepository;
import com.learningbydoing.domain.Airplane;
import jdk.nashorn.internal.runtime.options.Option;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AirplaneService {

    @Autowired
    private AirplaneRepository airplaneRepository;

    public void save(Airplane airplane) {
        airplaneRepository.save(airplane);
    }

    public void update(Airplane airplane) {
        airplaneRepository.save(airplane);
    }

    public void deleteById(Integer airplaneId) {
        airplaneRepository.deleteById(airplaneId);
    }

    public Airplane getById(Integer airplaneId) {
        Optional<Airplane> optional = airplaneRepository.findById(airplaneId);
        if (optional.isPresent())
            return optional.get();
        return new Airplane();
    }

    public List<Airplane> findByBrand(String brandName) {
        return airplaneRepository.findByBrandIgnoreCase(brandName);
    }

    public List<Airplane> findByMfgYear(Integer mfgYear) {
        return airplaneRepository.findByMfgYear(mfgYear);
    }

    public List<Airplane> findByModelNumber(String modelNumber) {
        return airplaneRepository.findByModelNumberIgnoreCase(modelNumber);
    }

    public List<Airplane> findByName(String name) {
        return airplaneRepository.findByNameIgnoreCase(name);
    }

}
