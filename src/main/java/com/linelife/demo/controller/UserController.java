package com.linelife.demo.controller;

import com.linelife.demo.model.ParametersUser;
import com.linelife.demo.model.PersonalUser;
import com.linelife.demo.repository.ParametersUserRepository;
import com.linelife.demo.service.ParametersUserService;
import com.linelife.demo.service.PersonalUserService;
import com.linelife.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "api/users/")
public class UserController {

    private final ParametersUserService parametersUserService;
    private final PersonalUserService personalUserService;


    @Autowired
    public UserController(ParametersUserService parametersUserService, PersonalUserService personalUserService) {
        this.parametersUserService = parametersUserService;
        this.personalUserService = personalUserService;
    }

    @GetMapping(value = "parameters/{id}")
    public ResponseEntity<ParametersUser> getParameters(@PathVariable Long id){
        ParametersUser parametersUser = parametersUserService.findById(id);
        if(parametersUser==null){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(parametersUser, HttpStatus.OK);
    }

    @GetMapping(value = "personal/{id}")
    public ResponseEntity<PersonalUser> getPersonal(@PathVariable Long id){

        PersonalUser personalUser = personalUserService.findById(id);
        if(personalUser==null){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(personalUser, HttpStatus.OK);
    }

    @PutMapping(value = "parameters/{id}")
    public ResponseEntity<ParametersUser> updateParameters(@RequestBody ParametersUser parametersUser, @PathVariable Long id){

        ParametersUser result = parametersUserService.findById(id);
        if(result==null){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        result.setBirthDate(parametersUser.getBirthDate());
        result.setGender(parametersUser.getGender());
        result.setHeight(parametersUser.getHeight());
        result.setWeight(parametersUser.getWeight());
        return new ResponseEntity<>(parametersUserService.update(result), HttpStatus.OK);
    }

    @PutMapping(value = "personal/{id}")
    public ResponseEntity<PersonalUser> updatePersonal(@RequestBody PersonalUser personalUser, @PathVariable Long id){

       PersonalUser result = personalUserService.findById(id);
        if(result==null){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        result.setFullName(personalUser.getFullName());
        result.setAddress(personalUser.getAddress());
        result.setPolicy(personalUser.getPolicy());
        return new ResponseEntity<>(personalUserService.update(result), HttpStatus.OK);
    }


}
