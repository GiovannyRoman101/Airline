package com.airline.flightsapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.airline.flightsapp.entity.Itinerary;
import com.airline.flightsapp.entity.User;
import com.airline.flightsapp.service.UserService;


@RestController
@RequestMapping("users")
@CrossOrigin
public class UserController {
	
	private final UserService userService;

	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	@RequestMapping(value="/{email}",method = RequestMethod.GET)
	public User read(@PathVariable String email){
		return this.userService.read(email);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public User create(@RequestBody User user){
		return this.userService.create(user);
	}
	
	@RequestMapping(value ="/{email}/Itineraries" ,method = RequestMethod.PUT)
	public Itinerary saveItinerary(@PathVariable String email,@RequestBody Itinerary itinerary){
		return this.userService.saveItinerary(email, itinerary);
	}

}
