package com.learningbydoing.dao;

import com.learningbydoing.domain.Airplane;
import com.learningbydoing.domain.Boat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoatRepository extends JpaRepository<Boat, Integer> {
    List<Boat> findByBrandIgnoreCase(String brand);
    List<Boat> findByMfgYear(Integer mfgYear);
    List<Boat> findByModelNumberIgnoreCase(String modelNumber);
    List<Boat> findByNameIgnoreCase(String name);

}
