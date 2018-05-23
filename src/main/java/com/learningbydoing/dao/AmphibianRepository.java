package com.learningbydoing.dao;

import com.learningbydoing.domain.Airplane;
import com.learningbydoing.domain.Amphibian;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AmphibianRepository extends JpaRepository<Amphibian, Integer> {
    List<Amphibian> findByBrandIgnoreCase(String brand);
    List<Amphibian> findByMfgYear(Integer mfgYear);
    List<Amphibian> findByModelNumberIgnoreCase(String modelNumber);
    List<Amphibian> findByNameIgnoreCase(String name);

}
