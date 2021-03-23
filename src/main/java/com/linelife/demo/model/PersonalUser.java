package com.linelife.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "personal")
public class PersonalUser {

    @Id
    @Column(name = "id")
    @JsonIgnore
    private Long id;
    @Column(name = "full_name")
    @JsonProperty(value = "full_name")
    private String fullName;
    @Column(name = "address")
    private String address;
    @Column(name = "policy")
    private String policy;
}
