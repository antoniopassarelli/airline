package com.ricston.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;

/**
 * The persistent class for the flights_a_passarelli database table.
 * 
 */
@Entity
@Table(name = "flights_a_passarelli")
@NamedQuery(name = "FlightsAPassarelli.findAll", query = "SELECT f FROM FlightsAPassarelli f")
public class FlightsAPassarelli implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private FlightsAPassarelliPK id;

	@Column(name = "Aircraft_Type")
	private String aircraft_Type;

	@Column(name = "Airline_Name")
	private String airline_Name;

	@Column(name = "Departure_Airport")
	private String departure_Airport; // IATA

	@Column(name = "Destination_Airport")
	private String destination_Airport;

	@Column(name = "Price")
	private BigDecimal price;

	@Column(name = "Seat_Availability")
	private short seat_Availability;

	public FlightsAPassarelli() {
	}

	public FlightsAPassarelli(FlightsAPassarelliPK fId, 
			String aircraft, 
			String airline, 
			String sourceAirport,
			String destinationAirport, 
			BigDecimal price, 
			short seatAvailability) {
		this.id = fId;
		this.aircraft_Type = aircraft;
		this.airline_Name = airline;
		this.departure_Airport = sourceAirport;
		this.destination_Airport = destinationAirport;
		this.price = price;
		this.seat_Availability = seatAvailability;
	}

	public FlightsAPassarelliPK getId() {
		return this.id;
	}

	public void setId(FlightsAPassarelliPK id) {
		this.id = id;
	}

	public String getAircraft_Type() {
		return this.aircraft_Type;
	}

	public void setAircraft_Type(String aircraft_Type) {
		this.aircraft_Type = aircraft_Type;
	}

	public String getAirline_Name() {
		return this.airline_Name;
	}

	public void setAirline_Name(String airline_Name) {
		this.airline_Name = airline_Name;
	}

	public String getDeparture_Airport() {
		return this.departure_Airport;
	}

	public void setDeparture_Airport(String departure_Airport) {
		this.departure_Airport = departure_Airport;
	}

	public String getDestination_Airport() {
		return this.destination_Airport;
	}

	public void setDestination_Airport(String destination_Airport) {
		this.destination_Airport = destination_Airport;
	}

	public BigDecimal getPrice() {
		return this.price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public short getSeat_Availability() {
		return this.seat_Availability;
	}

	public void setSeat_Availability(short seat_Availability) {
		this.seat_Availability = seat_Availability;
	}

}