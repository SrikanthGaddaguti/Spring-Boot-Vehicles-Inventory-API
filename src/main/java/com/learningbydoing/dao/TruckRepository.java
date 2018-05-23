package com.learningbydoing.dao;

import com.learningbydoing.domain.Airplane;
import com.learningbydoing.domain.Truck;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TruckRepository extends JpaRepository<Truck, Integer> {
    List<Truck> findByBrandIgnoreCase(String brand);
    List<Truck> findByMfgYear(Integer mfgYear);
    List<Truck> findByModelNumberIgnoreCase(String modelNumber);
    List<Truck> findByNameIgnoreCase(String name);

}
