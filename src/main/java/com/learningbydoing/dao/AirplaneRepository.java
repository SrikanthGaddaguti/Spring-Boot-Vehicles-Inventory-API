package com.learningbydoing.dao;

import com.learningbydoing.domain.Airplane;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AirplaneRepository extends JpaRepository<Airplane, Integer> {
    List<Airplane> findByBrandIgnoreCase(String brand);
    List<Airplane> findByMfgYear(Integer mfgYear);
    List<Airplane> findByModelNumberIgnoreCase(String modelNumber);
    List<Airplane> findByNameIgnoreCase(String name);
}

