package com.learningbydoing.service;

import com.learningbydoing.dao.AirplaneRepository;
import com.learningbydoing.dao.AmphibianRepository;
import com.learningbydoing.domain.Airplane;
import com.learningbydoing.domain.Amphibian;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AmphibianService {

    @Autowired
    private AmphibianRepository amphibianRepository;

    public void save(Amphibian amphibian) {
        amphibianRepository.save(amphibian);
    }

    public void update(Amphibian amphibian) {
        amphibianRepository.save(amphibian);
    }

    public void deleteById(Integer amphibianId) {
        amphibianRepository.deleteById(amphibianId);
    }

    public Amphibian getById(Integer amphibianId) {
        Optional<Amphibian> optional = amphibianRepository.findById(amphibianId);
        if (optional.isPresent())
            return optional.get();
        return new Amphibian();
    }

    public List<Amphibian> findByBrand(String brandName) {
        return amphibianRepository.findByBrandIgnoreCase(brandName);
    }

    public List<Amphibian> findByMfgYear(Integer mfgYear) {
        return amphibianRepository.findByMfgYear(mfgYear);
    }

    public List<Amphibian> findByModelNumber(String modelNumber) {
        return amphibianRepository.findByModelNumberIgnoreCase(modelNumber);
    }

    public List<Amphibian> findByName(String name) {
        return amphibianRepository.findByNameIgnoreCase(name);
    }

}
