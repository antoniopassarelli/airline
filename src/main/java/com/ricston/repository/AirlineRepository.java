package com.ricston.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.ricston.model.Airline;

public interface AirlineRepository extends CrudRepository<Airline, Integer> {

	@Query("select a from Airline a where a.name = ?1")
	Airline getAirlineByName(String airline);

}
