package com.airline.flightsapp.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.airline.flightsapp.entity.Itinerary;
import com.airline.flightsapp.pojo.Flight;
import com.airline.flightsapp.service.FlightService;


@RestController
@RequestMapping("flights")
@CrossOrigin
public class FlightController {

	private final FlightService flightservice;

	@Autowired
	public FlightController(FlightService flightservice) {
		this.flightservice = flightservice;
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ArrayList<Flight> getFlightList()
	{
		return flightservice.getDailyFlightList();
	}
	
	@RequestMapping(value = "{origin}/{destination}",method = RequestMethod.GET)
	public ArrayList<Itinerary> getItinerariesTo(@PathVariable String origin, @PathVariable String destination){
		return this.flightservice.getItinerariesTo(origin,destination);
	}
	
	
	
	
}
