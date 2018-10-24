package com.airline.flightsapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.airline.flightsapp.entity.SaveFlight;

@Repository
public interface SaveFlightRepository extends JpaRepository<SaveFlight, Long> {

}
