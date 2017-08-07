package com.ricston.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.ricston.model.Route;
import com.ricston.model.RoutePK;

public interface RouteRepository extends CrudRepository<Route, RoutePK> {

	@Query("select r from Route r where r.airlineBean.name = ?1")
	List<Route> getRoutesByAirline(String airline, Pageable pageable);

}
