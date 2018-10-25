package com.airline.flightsapp.service.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.airline.flightsapp.component.FlightGenerator;
import com.airline.flightsapp.entity.Itinerary;
import com.airline.flightsapp.entity.SaveFlight;
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
	@Scheduled(fixedDelay = 500000)
	private void refreshFlights() {
		flightList = generator.generateNewFlightList();
	}

	@Override
	public ArrayList<Itinerary> getItinerariesTo(String origin, String destination) {
		// TODO Auto-generated method stub
		ArrayList<Flight> initial= new ArrayList<Flight>();
		ArrayList<ArrayList<Flight>> validItin = new ArrayList<ArrayList<Flight>>();
		
		findAllPaths(validItin,initial,origin,destination,0);

		ArrayList<Itinerary> possibleItin = new ArrayList<Itinerary>();
		for(ArrayList<Flight> fli : validItin) {
			if(convertPathsToItinerary(fli) != null) {
				possibleItin.add(convertPathsToItinerary(fli));
			}
		}
//		printflights(possibleItin);
		return possibleItin;
	}
//	private void printflights(ArrayList<Itinerary> routes) {
//		for(Itinerary route : routes) {
//			System.out.println( route.getOrigin() +" I-> "+ route.getDestination());
//			for(SaveFlight fli : route.getFlights()) {
//				System.out.println( fli.getOrigin() +" -> "+ fli.getDestination());
//			}
//		}
//	}
	
	private Itinerary convertPathsToItinerary(ArrayList<Flight> flights) {
		long flightTime = 0;
		long totalDelay = 0;
		if(!flights.isEmpty()) {
			String origin = flights.get(0).getOrigin();
			String destination = flights.get(flights.size()-1).getDestination();
			ArrayList<SaveFlight> flight = new ArrayList<SaveFlight>();
			for(Flight fli : flights) {
				flight.add(new SaveFlight(fli.getOrigin(),fli.getDestination(),fli.getFlightTime(),fli.getOffset()));
				flightTime += fli.getFlightTime();
				totalDelay += fli.getOffset();
			}
			return new Itinerary(flight,origin,destination,flightTime,totalDelay);
		}
		return null;
	}
	
	private void findAllPaths(ArrayList<ArrayList<Flight>> pathsFound, ArrayList<Flight> currentTaken,
			String origin, String destination, long offset){
		//if origin is the same as the destination
		if(origin.equalsIgnoreCase(destination)) {
			pathsFound.add(currentTaken);
			return;
		}
		ArrayList<Flight> adjFlights = new ArrayList<Flight>();
		for(Flight flight: flightList) {
//			System.out.println("origin: "+ flight.getOrigin() + "=? " +origin);
//			System.out.println("destination: " +flight.getDestination() + "=?" + destination);
			if(origin.equalsIgnoreCase(flight.getOrigin()) && flight.getOffset() >= offset) {
//				System.out.println("added "+flight.getOrigin()+ " ->" + flight.getDestination() );
				adjFlights.add(flight);
			}
		}
//		 System.out.println("the size of adj array:" + adjFlights.size());
		for(Flight check: adjFlights) {
			ArrayList<Flight> copy = new ArrayList<Flight>(currentTaken);
			if(!copy.contains(check)) {
				copy.add(check);
				long arriveTime = check.getFlightTime() + check.getOffset();
				findAllPaths(pathsFound,copy,check.getDestination(),destination, arriveTime);
			}
			
		}

	}
	

}
