package com.airline.flightsapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.airline.flightsapp.entity.Location;
import com.airline.flightsapp.service.LocationService;


@RestController
@RequestMapping("locations")
@CrossOrigin
public class LocationController {
	private final LocationService locationservice;

	@Autowired
	public LocationController(LocationService locationservice) {
		this.locationservice = locationservice;
	}
	@RequestMapping(method = RequestMethod.GET)
	public List<Location> get(){
		return locationservice.getAll();
	}
	
	@RequestMapping("/{id}")
	public Location get(@PathVariable("id") long id) {
		return locationservice.get(id);
	}
	
	@RequestMapping("/name")
	public Location get(@RequestParam("name") String cityName)
	{
		return locationservice.get(cityName);
	}
	
	

}
