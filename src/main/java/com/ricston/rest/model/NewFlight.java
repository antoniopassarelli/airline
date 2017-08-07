package com.ricston.rest.model;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.ricston.rest.util.CustomerDateAndTimeDeserialize;

public class NewFlight {

	private String flightCode;
	@JsonDeserialize(using=CustomerDateAndTimeDeserialize .class)
	Date date;
	String aircraftType;
	String airlineName;
	int departureAirportID;
	int destinationAirportID;
	BigDecimal price;
	short seatAvailability;

	public String getFlightCode() {
		return flightCode;
	}

	public void setFlightCode(String flightCode) {
		this.flightCode = flightCode;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getAircraftType() {
		return aircraftType;
	}

	public void setAircraftType(String aircraftType) {
		this.aircraftType = aircraftType;
	}

	public String getAirlineName() {
		return airlineName;
	}

	public void setAirlineName(String airlineName) {
		this.airlineName = airlineName;
	}

	public int getDepartureAirportID() {
		return departureAirportID;
	}

	public void setDepartureAirportID(int departureAirportID) {
		this.departureAirportID = departureAirportID;
	}

	public int getDestinationAirportID() {
		return destinationAirportID;
	}

	public void setDestinationAirportID(int destinationAirportID) {
		this.destinationAirportID = destinationAirportID;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public short getSeatAvailability() {
		return seatAvailability;
	}

	public void setSeatAvailability(short seatAvailability) {
		this.seatAvailability = seatAvailability;
	}

}
