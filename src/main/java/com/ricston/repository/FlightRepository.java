package com.ricston.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.ricston.model.FlightsAPassarelli;
import com.ricston.model.FlightsAPassarelliPK;

public interface FlightRepository extends CrudRepository<FlightsAPassarelli, FlightsAPassarelliPK> {

	@Query("select f from FlightsAPassarelli f where f.id.departure_Date like concat(?1, '%')")
	List<FlightsAPassarelli> getFlightsByDate(@Param("date") String date);

	@Query("select f from FlightsAPassarelli f where f.id.flight_Code = ?1")
	FlightsAPassarelli getFlightByFlightCode(String flightCode);

}
