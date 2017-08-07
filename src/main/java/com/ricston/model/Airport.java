package com.ricston.model;

import java.io.Serializable;
import javax.persistence.*;

import com.google.gson.annotations.Expose;

import java.math.BigDecimal;
import java.util.List;

/**
 * The persistent class for the airports database table.
 * 
 */
@Entity
@Table(name = "airports")
@NamedQuery(name = "Airport.findAll", query = "SELECT a FROM Airport a")
public class Airport implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "Airport_ID")
	private int airport_ID;

	@Column(name = "Altitude")
	private int altitude;

	@Expose
	@Column(name = "City")
	private String city;

	@Expose
	@Column(name = "Country")
	private String country;

	@Column(name = "DST")
	private String dst;

	@Column(name = "IATA")
	private String iata;

	@Column(name = "ICAO")
	private String icao;

	@Column(name = "Latitude")
	private BigDecimal latitude;

	@Column(name = "Longitude")
	private BigDecimal longitude;

	@Expose
	@Column(name = "Name")
	private String name;

	@Column(name = "Source")
	private String source;

	@Column(name = "Timezone")
	private int timezone;

	@Column(name = "Type")
	private String type;

	@Column(name = "Tz_Timezone")
	private String tz_Timezone;

	// bi-directional many-to-one association to Route
	@OneToMany(mappedBy = "destinationAirport")
	private List<Route> routes1;

	// bi-directional many-to-one association to Route
	@OneToMany(mappedBy = "sourceAirport")
	private List<Route> routes2;

	public Airport() {
	}

	public int getAirport_ID() {
		return this.airport_ID;
	}

	public void setAirport_ID(int airport_ID) {
		this.airport_ID = airport_ID;
	}

	public int getAltitude() {
		return this.altitude;
	}

	public void setAltitude(int altitude) {
		this.altitude = altitude;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return this.country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getDst() {
		return this.dst;
	}

	public void setDst(String dst) {
		this.dst = dst;
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

	public BigDecimal getLatitude() {
		return this.latitude;
	}

	public void setLatitude(BigDecimal latitude) {
		this.latitude = latitude;
	}

	public BigDecimal getLongitude() {
		return this.longitude;
	}

	public void setLongitude(BigDecimal longitude) {
		this.longitude = longitude;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSource() {
		return this.source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public int getTimezone() {
		return this.timezone;
	}

	public void setTimezone(int timezone) {
		this.timezone = timezone;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTz_Timezone() {
		return this.tz_Timezone;
	}

	public void setTz_Timezone(String tz_Timezone) {
		this.tz_Timezone = tz_Timezone;
	}

	public List<Route> getRoutes1() {
		return this.routes1;
	}

	public void setRoutes1(List<Route> routes1) {
		this.routes1 = routes1;
	}

	public Route addRoutes1(Route routes1) {
		getRoutes1().add(routes1);
		routes1.setDestinationAirport(this);

		return routes1;
	}

	public Route removeRoutes1(Route routes1) {
		getRoutes1().remove(routes1);
		routes1.setDestinationAirport(null);

		return routes1;
	}

	public List<Route> getRoutes2() {
		return this.routes2;
	}

	public void setRoutes2(List<Route> routes2) {
		this.routes2 = routes2;
	}

	public Route addRoutes2(Route routes2) {
		getRoutes2().add(routes2);
		routes2.setSourceAirport(this);

		return routes2;
	}

	public Route removeRoutes2(Route routes2) {
		getRoutes2().remove(routes2);
		routes2.setSourceAirport(null);

		return routes2;
	}

}