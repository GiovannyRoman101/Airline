package com.airline.flightsapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.airline.flightsapp.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

}
