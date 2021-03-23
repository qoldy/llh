package com.linelife.demo.service.impl;

import com.linelife.demo.model.ParametersUser;
import com.linelife.demo.repository.ParametersUserRepository;
import com.linelife.demo.service.ParametersUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ParametersUserServiceImpl implements ParametersUserService {

    private final ParametersUserRepository parametersUserRepository;

    @Autowired
    public ParametersUserServiceImpl(ParametersUserRepository parametersUserRepository) {
        this.parametersUserRepository = parametersUserRepository;
    }

    @Override
    public ParametersUser findById(Long id) {

        ParametersUser result = parametersUserRepository.findById(id).orElse(null);
        return result;
    }

    @Override
    public ParametersUser update(ParametersUser parametersUser) {
        parametersUserRepository.save(parametersUser);
        return parametersUser;
    }

    @Override
    public void add(Long id) {
        ParametersUser user = new ParametersUser();
        user.setId(id);
        parametersUserRepository.save(user);
    }
}
