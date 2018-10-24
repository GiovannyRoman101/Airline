package com.airline.flightsapp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.airline.flightsapp.entity.Location;
import com.airline.flightsapp.repository.LocationRepository;
import com.airline.flightsapp.service.LocationService;

@Service
public class LocationServiceImpl implements LocationService{
	
	private final LocationRepository repo;

	@Autowired
	public LocationServiceImpl(LocationRepository repo) {
		super();
		this.repo = repo;
	}

	@Override
	public Location get(long id) {
		List<Location> locations = this.repo.findAll();
		for(Location loc: locations) {
			if(loc.getId() == id) {
				return loc;
			}
		}
		return null;
	}
	
	@Override
	public Location get(String name) {
		List<Location> locations = this.repo.findAll();
		for(Location loc: locations ) {
			if(name.equalsIgnoreCase(loc.getCity())) {
				return loc;
			}
		}
		return null;
	}

	@Override
	public List<Location> getAll() {
		// TODO Auto-generated method stub
		return this.repo.findAll();
	}

}
