package com.ricston.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the aircraft_type database table.
 * 
 */
@Entity
@Table(name="aircraft_type")
@NamedQuery(name="AircraftType.findAll", query="SELECT a FROM AircraftType a")
public class AircraftType implements Serializable {
	private static final long serialVersionUID = 1L;

	private String IATA_Code;

	private String ICAO_Code;

	@Id
	@Column(name="Model")
	private String model;

	public AircraftType() {
	}

	public String getIATA_Code() {
		return this.IATA_Code;
	}

	public void setIATA_Code(String IATA_Code) {
		this.IATA_Code = IATA_Code;
	}

	public String getICAO_Code() {
		return this.ICAO_Code;
	}

	public void setICAO_Code(String ICAO_Code) {
		this.ICAO_Code = ICAO_Code;
	}

	public String getModel() {
		return this.model;
	}

	public void setModel(String model) {
		this.model = model;
	}

}