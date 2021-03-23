package com.linelife.demo.service;

import com.linelife.demo.model.ParametersUser;

public interface ParametersUserService {

    ParametersUser findById(Long id);
    ParametersUser update(ParametersUser parametersUser);
    void add(Long id);
}
