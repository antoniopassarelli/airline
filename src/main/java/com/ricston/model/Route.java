package com.ricston.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the routes database table.
 * 
 */
@Entity
@Table(name = "routes")
@NamedQuery(name = "Route.findAll", query = "SELECT r FROM Route r")
public class Route implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private RoutePK id;

	@Column(name = "Airline")
	private String airline;

	@Column(name = "Codeshare")
	private String codeshare;

	@Column(name = "Destination_Airport")
	private String destination_Airport;

	@Column(name = "Equipment")
	private String equipment;

	@Column(name = "Source_Airport")
	private String source_Airport;

	@Column(name = "Stops")
	private byte stops;

	// bi-directional many-to-one association to Airline
	@ManyToOne
	@JoinColumn(name = "Airline_ID", insertable = false, updatable = false)
	private Airline airlineBean;

	// bi-directional many-to-one association to Airport
	@ManyToOne
	@JoinColumn(name = "Destination_Airport_ID", insertable = false, updatable = false)
	private Airport destinationAirport;

	// bi-directional many-to-one association to Airport
	@ManyToOne
	@JoinColumn(name = "Source_Airport_ID", insertable = false, updatable = false)
	private Airport sourceAirport;

	public Route() {
	}

	public RoutePK getId() {
		return this.id;
	}

	public void setId(RoutePK id) {
		this.id = id;
	}

	public String getAirline() {
		return this.airline;
	}

	public void setAirline(String airline) {
		this.airline = airline;
	}

	public String getCodeshare() {
		return this.codeshare;
	}

	public void setCodeshare(String codeshare) {
		this.codeshare = codeshare;
	}

	public String getDestination_Airport() {
		return this.destination_Airport;
	}

	public void setDestination_Airport(String destination_Airport) {
		this.destination_Airport = destination_Airport;
	}

	public String getEquipment() {
		return this.equipment;
	}

	public void setEquipment(String equipment) {
		this.equipment = equipment;
	}

	public String getSource_Airport() {
		return this.source_Airport;
	}

	public void setSource_Airport(String source_Airport) {
		this.source_Airport = source_Airport;
	}

	public byte getStops() {
		return this.stops;
	}

	public void setStops(byte stops) {
		this.stops = stops;
	}

	public Airline getAirlineBean() {
		return this.airlineBean;
	}

	public void setAirlineBean(Airline airlineBean) {
		this.airlineBean = airlineBean;
	}

	public Airport getDestinationAirport() {
		return destinationAirport;
	}

	public void setDestinationAirport(Airport destinationAirport) {
		this.destinationAirport = destinationAirport;
	}

	public Airport getSourceAirport() {
		return sourceAirport;
	}

	public void setSourceAirport(Airport sourceAirport) {
		this.sourceAirport = sourceAirport;
	}

}