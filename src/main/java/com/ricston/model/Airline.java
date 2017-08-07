package com.ricston.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;

/**
 * The persistent class for the airlines database table.
 * 
 */
@Entity
@Table(name = "airlines")
@NamedQuery(name = "Airline.findAll", query = "SELECT a FROM Airline a")
public class Airline implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "Airline_ID")
	private Integer airline_ID;

	@Column(name = "Active")
	private String active;

	@Column(name = "Alias")
	private String alias;

	@Column(name = "Callsign")
	private String callsign;

	@Column(name = "Country")
	private String country;

	@Column(name = "IATA")
	private String iata;

	@Column(name = "ICAO")
	private String icao;

	@Column(name = "Name")
	private String name;

	// bi-directional many-to-one association to Route
	@OneToMany(mappedBy = "airlineBean")
	private List<Route> routes;

	public Airline() {
	}

	public int getAirline_ID() {
		return this.airline_ID;
	}

	public void setAirline_ID(int airline_ID) {
		this.airline_ID = airline_ID;
	}

	public String getActive() {
		return this.active;
	}

	public void setActive(String active) {
		this.active = active;
	}

	public String getAlias() {
		return this.alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getCallsign() {
		return this.callsign;
	}

	public void setCallsign(String callsign) {
		this.callsign = callsign;
	}

	public String getCountry() {
		return this.country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getIata() {
		return this.iata;
	}

	public void setIata(String iata) {
		this.iata = iata;
	}

	public String getIcao() {
		return this.icao;
	}

	public void setIcao(String icao) {
		this.icao = icao;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Route> getRoutes() {
		return this.routes;
	}

	public void setRoutes(List<Route> routes) {
		this.routes = routes;
	}

	public Route addRoute(Route route) {
		getRoutes().add(route);
		route.setAirlineBean(this);

		return route;
	}

	public Route removeRoute(Route route) {
		getRoutes().remove(route);
		route.setAirlineBean(null);

		return route;
	}

}