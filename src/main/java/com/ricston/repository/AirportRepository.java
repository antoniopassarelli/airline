package com.ricston.repository;

import org.springframework.data.repository.CrudRepository;

import com.ricston.model.Airport;

public interface AirportRepository extends CrudRepository<Airport, Integer> {

}
