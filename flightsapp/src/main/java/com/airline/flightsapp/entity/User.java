package com.airline.flightsapp.entity;

import java.util.List;

import javax.persistence.*;


@Entity
@Table(name = "User",uniqueConstraints = @UniqueConstraint(columnNames = { "email" }))
public class User {
	@Id
	@GeneratedValue
	private long id;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "password")
	private String password;
	
	@OneToMany(mappedBy = "owner" ,fetch=FetchType.EAGER,cascade = CascadeType.ALL)
	private List<Itinerary> itineraries;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Itinerary> getItineraries() {
		return itineraries;
	}

	public void setItineraries(List<Itinerary> itineraries) {
		this.itineraries = itineraries;
	}
	
	
}
