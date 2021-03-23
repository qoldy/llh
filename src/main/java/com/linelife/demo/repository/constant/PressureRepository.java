package com.linelife.demo.repository.constant;

import com.linelife.demo.model.constant.Pressure;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PressureRepository extends JpaRepository<Pressure, Long> {
    List<Pressure> findAllByUserId(Long id);
}
