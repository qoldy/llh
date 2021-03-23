package com.linelife.demo.service;

import com.linelife.demo.model.PersonalUser;

public interface PersonalUserService {

    PersonalUser findById(Long id);
    PersonalUser update(PersonalUser personalUser);
    void add(Long id);
}
