package com.ricston.repository;

import org.springframework.data.repository.CrudRepository;

import com.ricston.model.AircraftType;

public interface AircraftRepository extends CrudRepository<AircraftType, String> {

}
