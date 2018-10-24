package com.airline.flightsapp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.airline.flightsapp.entity.Itinerary;
import com.airline.flightsapp.repository.ItineraryRepository;
import com.airline.flightsapp.service.ItineraryService;


@Service
public class ItineraryServiceImpl implements ItineraryService{
	private final ItineraryRepository repo;

	@Autowired
	public ItineraryServiceImpl(ItineraryRepository repo) {
		super();
		this.repo = repo;
	}

	@Override
	public Itinerary read(Long id) {
		List<Itinerary> itineraries = this.repo.findAll();
		for(Itinerary itin : itineraries) {
			if(itin.getId() == id) {
				return itin;
			}
		}
		return null;
	}
}
