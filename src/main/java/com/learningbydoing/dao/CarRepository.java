package com.learningbydoing.dao;

import com.learningbydoing.domain.Airplane;
import com.learningbydoing.domain.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, Integer> {
    List<Car> findByBrandIgnoreCase(String brand);
    List<Car> findByMfgYear(Integer mfgYear);
    List<Car> findByModelNumberIgnoreCase(String modelNumber);
    List<Car> findByNameIgnoreCase(String name);

}
