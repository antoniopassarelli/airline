package com.ricston.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

/**
 * The primary key class for the flights_a_passarelli database table.
 * 
 */
@Embeddable
public class FlightsAPassarelliPK implements Serializable {
	// default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name = "Flight_Code")
	private String flight_Code;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "Departure_Date")
	private java.util.Date departure_Date;

	public FlightsAPassarelliPK() {
	}

	public FlightsAPassarelliPK(String flightCode, Date date) {
		this.flight_Code = flightCode;
		this.departure_Date = date;
	}

	public String getFlight_Code() {
		return this.flight_Code;
	}

	public void setFlight_Code(String flight_Code) {
		this.flight_Code = flight_Code;
	}

	public java.util.Date getDeparture_Date() {
		return this.departure_Date;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof FlightsAPassarelliPK)) {
			return false;
		}
		FlightsAPassarelliPK castOther = (FlightsAPassarelliPK) other;
		return this.flight_Code.equals(castOther.flight_Code) && this.departure_Date.equals(castOther.departure_Date);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.flight_Code.hashCode();
		hash = hash * prime + this.departure_Date.hashCode();

		return hash;
	}
}