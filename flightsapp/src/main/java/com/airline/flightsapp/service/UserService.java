package com.airline.flightsapp.service;

import com.airline.flightsapp.entity.Itinerary;
import com.airline.flightsapp.entity.User;

public interface UserService {

	User read(String email);

	User create(User user);

	Itinerary saveItinerary(String email, Itinerary itinerary);

}
