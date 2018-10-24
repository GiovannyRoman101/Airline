package com.airline.flightsapp.service.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.airline.flightsapp.component.FlightGenerator;
import com.airline.flightsapp.pojo.Flight;
import com.airline.flightsapp.service.FlightService;


@Service
public class FlightServiceImpl implements FlightService {
	
	@Autowired
	FlightGenerator generator;

	private ArrayList<Flight> flightList = new ArrayList<>();

	public ArrayList<Flight> getDailyFlightList() {
		return flightList;
	}

	// The fixedDelay parameter determines how often a new day is generated as
	// expressed in milliseconds
	@Scheduled(fixedDelay = 5000)
	private void refreshFlights() {
		flightList = generator.generateNewFlightList();
	}

}
