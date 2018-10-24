package com.airline.flightsapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.airline.flightsapp.entity.Itinerary;
import com.airline.flightsapp.service.ItineraryService;

@RestController
@RequestMapping("itineraries")
@CrossOrigin
public class ItineraryController {
	
	private final ItineraryService itineraryservice;

	@Autowired
	public ItineraryController(ItineraryService itineraryservice) {
		this.itineraryservice = itineraryservice;
	}
	
	@RequestMapping(value ="/{id}", method = RequestMethod.GET)
	public Itinerary read(@PathVariable Long id){
		return this.itineraryservice.read(id);
	}
	
	

}
