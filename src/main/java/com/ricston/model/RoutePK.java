package com.ricston.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the routes database table.
 * 
 */
@Embeddable
public class RoutePK implements Serializable {
	// default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name = "Destination_Airport_ID", insertable = false, updatable = false)
	private int destination_Airport_ID;

	@Column(name = "Source_Airport_ID", insertable = false, updatable = false)
	private int source_Airport_ID;

	@Column(name = "Airline_ID", insertable = false, updatable = false)
	private int airline_ID;

	public RoutePK() {
	}

	public RoutePK(int destinationAirportID, int departureAirportID, int airline_ID) {
		this.destination_Airport_ID = destinationAirportID;
		this.source_Airport_ID = departureAirportID;
		this.airline_ID = airline_ID;
	}

	public int getDestination_Airport_ID() {
		return this.destination_Airport_ID;
	}

	// public void setDestination_Airport_ID(int destination_Airport_ID) {
	// this.destination_Airport_ID = destination_Airport_ID;
	// }

	public int getSource_Airport_ID() {
		return this.source_Airport_ID;
	}

	// public void setSource_Airport_ID(int source_Airport_ID) {
	// this.source_Airport_ID = source_Airport_ID;
	// }

	public int getAirline_ID() {
		return this.airline_ID;
	}

	// public void setAirline_ID(int airline_ID) {
	// this.airline_ID = airline_ID;
	// }

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof RoutePK)) {
			return false;
		}
		RoutePK castOther = (RoutePK) other;
		return (this.destination_Airport_ID == castOther.destination_Airport_ID)
				&& (this.source_Airport_ID == castOther.source_Airport_ID) && (this.airline_ID == castOther.airline_ID);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.destination_Airport_ID;
		hash = hash * prime + this.source_Airport_ID;
		hash = hash * prime + this.airline_ID;

		return hash;
	}
}