package com.airline.flightsapp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.airline.flightsapp.entity.Itinerary;
import com.airline.flightsapp.entity.SaveFlight;
import com.airline.flightsapp.entity.User;
import com.airline.flightsapp.repository.UserRepository;
import com.airline.flightsapp.service.UserService;


@Service
public class UserServiceImpl implements UserService{
	
	private final UserRepository repo;

	@Autowired
	public UserServiceImpl(UserRepository repo) {
		super();
		this.repo = repo;
	}

	@Override
	public User read(String email) {
		List<User> users = this.repo.findAll();
		for (User us : users) {
			if (email.equals(us.getEmail())) {
				return us;
			}
		}
		return null;
	}

	@Override
	public User create(User user) {
		return this.repo.saveAndFlush(user);
	}

	@Override
	public Itinerary saveItinerary(String email, Itinerary itinerary) {
		List<User> users = this.repo.findAll();
		User owner = null;
		for (User us : users) {
			if (email.equals(us.getEmail())) {
				owner = us;
			}
		}
		List<SaveFlight> flights = itinerary.getFlights();
		for(SaveFlight fligh: flights){
			fligh.setItinerary(itinerary);
		}
		itinerary.setOwner(owner);
		owner.getItineraries().add(itinerary);
		this.repo.saveAndFlush(owner);
		return itinerary;
	}
	
	
}
