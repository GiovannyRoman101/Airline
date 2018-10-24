package com.airline.flightsapp.service;

import java.util.List;

import com.airline.flightsapp.entity.Location;

public interface LocationService {

	List<Location> getAll();

	Location get(long id);

	Location get(String cityName);

}
