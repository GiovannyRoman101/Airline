package com.airline.flightsapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.airline.flightsapp.entity.Location;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long>{

}
