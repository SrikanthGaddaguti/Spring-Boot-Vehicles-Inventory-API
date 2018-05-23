package com.learningbydoing.service;

import com.learningbydoing.dao.AirplaneRepository;
import com.learningbydoing.dao.CarRepository;
import com.learningbydoing.domain.Airplane;
import com.learningbydoing.domain.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarService {

    @Autowired
    private CarRepository carRepository;

    public void save(Car car) {
        carRepository.save(car);
    }

    public void update(Car car) {
        carRepository.save(car);
    }

    public void deleteById(Integer carId) {
        carRepository.deleteById(carId);
    }

    public Car getById(Integer carId) {
        Optional<Car> optional = carRepository.findById(carId);
        if (optional.isPresent())
            return optional.get();
        return new Car();
    }

    public List<Car> findByBrand(String brandName) {
        return carRepository.findByBrandIgnoreCase(brandName);
    }

    public List<Car> findByMfgYear(Integer mfgYear) {
        return carRepository.findByMfgYear(mfgYear);
    }

    public List<Car> findByModelNumber(String modelNumber) {
        return carRepository.findByModelNumberIgnoreCase(modelNumber);
    }

    public List<Car> findByName(String name) {
        return carRepository.findByNameIgnoreCase(name);
    }

}
