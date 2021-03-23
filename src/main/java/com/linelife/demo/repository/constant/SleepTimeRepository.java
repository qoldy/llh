package com.linelife.demo.repository.constant;

import com.linelife.demo.model.constant.SleepTime;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SleepTimeRepository extends JpaRepository<SleepTime, Long> {
    List<SleepTime> findAllByUserId(Long id);
}
