package com.linelife.demo.repository;

import com.linelife.demo.model.PersonalUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonalUserRepository extends JpaRepository<PersonalUser, Long> {
}
