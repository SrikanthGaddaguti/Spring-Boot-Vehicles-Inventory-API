package com.learningbydoing.service;

import com.learningbydoing.dao.AirplaneRepository;
import com.learningbydoing.dao.BoatRepository;
import com.learningbydoing.domain.Airplane;
import com.learningbydoing.domain.Boat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BoatService {

    @Autowired
    private BoatRepository boatRepository;

    public void save(Boat boat) {
        boatRepository.save(boat);
    }

    public void update(Boat boat) {
        boatRepository.save(boat);
    }

    public void deleteById(Integer boatId) {
        boatRepository.deleteById(boatId);
    }

    public Boat getById(Integer boatId) {
        Optional<Boat> optional = boatRepository.findById(boatId);
        if (optional.isPresent())
            return optional.get();
        return new Boat();
    }

    public List<Boat> findByBrand(String brandName) {
        return boatRepository.findByBrandIgnoreCase(brandName);
    }

    public List<Boat> findByMfgYear(Integer mfgYear) {
        return boatRepository.findByMfgYear(mfgYear);
    }

    public List<Boat> findByModelNumber(String modelNumber) {
        return boatRepository.findByModelNumberIgnoreCase(modelNumber);
    }

    public List<Boat> findByName(String name) {
        return boatRepository.findByNameIgnoreCase(name);
    }

}
