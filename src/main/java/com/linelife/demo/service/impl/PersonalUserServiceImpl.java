package com.linelife.demo.service.impl;

import com.linelife.demo.model.PersonalUser;
import com.linelife.demo.repository.PersonalUserRepository;
import com.linelife.demo.service.PersonalUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PersonalUserServiceImpl implements PersonalUserService {

    private final PersonalUserRepository personalUserRepository;

    @Autowired
    public PersonalUserServiceImpl(PersonalUserRepository personalUserRepository) {
        this.personalUserRepository = personalUserRepository;
    }

    @Override
    public PersonalUser findById(Long id) {

        PersonalUser result = personalUserRepository.findById(id).orElse(null);
        return result;
    }

    @Override
    public PersonalUser update(PersonalUser personalUser) {
        personalUserRepository.save(personalUser);
        return personalUser;
    }

    @Override
    public void add(Long id) {
        PersonalUser user = new PersonalUser();
        user.setId(id);
        personalUserRepository.save(user);
    }
}
