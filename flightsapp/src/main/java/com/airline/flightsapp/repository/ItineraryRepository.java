package com.airline.flightsapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.airline.flightsapp.entity.Itinerary;

@Repository
public interface ItineraryRepository extends JpaRepository<Itinerary,Long>{

}
