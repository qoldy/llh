package com.linelife.demo.repository.constant;

import com.linelife.demo.model.constant.Temperature;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TemperatureRepository extends JpaRepository<Temperature, Long> {
    List<Temperature> findAllByUserId(Long id);
}
