package com.linelife.demo.repository.constant;

import com.linelife.demo.model.constant.Pulse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PulseRepository extends JpaRepository<Pulse, Long> {
    List<Pulse> findAllByUserId(Long id);
}
