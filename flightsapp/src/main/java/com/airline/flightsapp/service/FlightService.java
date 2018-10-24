package com.airline.flightsapp.service;

import java.util.ArrayList;

import com.airline.flightsapp.entity.Itinerary;
import com.airline.flightsapp.pojo.Flight;

public interface FlightService {

	ArrayList<Flight> getDailyFlightList();

	ArrayList<Itinerary> getItinerariesTo(String origin, String destination);

}
