package com.learningbydoing.service;

import com.learningbydoing.dao.AirplaneRepository;
import com.learningbydoing.dao.TruckRepository;
import com.learningbydoing.domain.Airplane;
import com.learningbydoing.domain.Truck;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TruckService {

    @Autowired
    private TruckRepository truckRepository;

    public void save(Truck truck) {
        truckRepository.save(truck);
    }

    public void update(Truck truck) {
        truckRepository.save(truck);
    }

    public void deleteById(Integer truckId) {
        truckRepository.deleteById(truckId);
    }

    public Truck getById(Integer truckId) {
        Optional<Truck> optional = truckRepository.findById(truckId);
        if (optional.isPresent())
            return optional.get();
        return new Truck();
    }

    public List<Truck> findByBrand(String brandName) {
        return truckRepository.findByBrandIgnoreCase(brandName);
    }

    public List<Truck> findByMfgYear(Integer mfgYear) {
        return truckRepository.findByMfgYear(mfgYear);
    }

    public List<Truck> findByModelNumber(String modelNumber) {
        return truckRepository.findByModelNumberIgnoreCase(modelNumber);
    }

    public List<Truck> findByName(String name) {
        return truckRepository.findByNameIgnoreCase(name);
    }

}
