package com.ricston.controllers;

import java.math.BigDecimal;
import java.net.URI;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ricston.enums.PriceAction;
import com.ricston.model.AircraftType;
import com.ricston.model.Airline;
import com.ricston.model.Airport;
import com.ricston.model.FlightsAPassarelli;
import com.ricston.model.FlightsAPassarelliPK;
import com.ricston.model.Route;
import com.ricston.model.RoutePK;
import com.ricston.repository.AircraftRepository;
import com.ricston.repository.AirlineRepository;
import com.ricston.repository.AirportRepository;
import com.ricston.repository.FlightRepository;
import com.ricston.repository.RouteRepository;
import com.ricston.rest.model.ChangePrice;
import com.ricston.rest.model.FlighCode;
import com.ricston.rest.model.NewFlight;

@RestController
@Validated
public class AirlineController {
	
	private static final String DATE_FORMAT="yyyy-MM-dd";
	private static final String DATE_TIME_FORMAT="yyyy-MM-dd HH:mm:ss";

	// Spring will auto generate the repositories bean
	@Autowired
	private FlightRepository flightRepository;
	@Autowired
	private AirlineRepository airlineRepository;
	@Autowired
	private AircraftRepository aircraftRepository;
	@Autowired
	private AirportRepository airportRepository;
	@Autowired
	private RouteRepository routeRepository;
    
	@RequestMapping(value = "/flights_by_date", method = RequestMethod.GET)
	public ResponseEntity<String> flightsByDate(
			@RequestParam(value = "date") 
			String dateStr) {
		Gson gson = new Gson();
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
		sdf.setLenient(false);
		try {
			sdf.parse(dateStr);
		} catch (ParseException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body("Check date format: \""+ DATE_FORMAT + "\".");
		}
		List<FlightsAPassarelli> flightsFound = flightRepository.getFlightsByDate(dateStr);
		String res = gson.toJson(flightsFound);
		return ResponseEntity.status(HttpStatus.OK).body(res);
	}

	@RequestMapping(value = "/destination_by_airline", method = RequestMethod.GET)
	public ResponseEntity<Object> destinationByAirline(
			@RequestParam(value = "airline") 
			String airlineName,
			@RequestParam(value = "pageNumber") 
			Integer pageNumber) {
		Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
		Airline airline = airlineRepository.getAirlineByName(airlineName);
		if (null == airline) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Check airline name.");
		} else {
			List<Route> routes = routeRepository.getRoutesByAirline(airline.getName(), new PageRequest(pageNumber, 10));
			if (null == routes || routes.isEmpty()) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST)
						.body("No routers for the airline " + airlineName + ".");
			} else {
				List<Airport> airportsFound = new ArrayList<Airport>();
				for (Route route : routes) {
					Airport currentAirport = route.getDestinationAirport();
					airportsFound.add(currentAirport);
				}

				if (null == airportsFound || airportsFound.isEmpty()) {
					return ResponseEntity.status(HttpStatus.BAD_REQUEST)
							.body("No destinations for the routes of the " + airlineName + " airline.");
				} else {
					return ResponseEntity.status(HttpStatus.OK).body(gson.toJson(airportsFound));
				}
			}
		}
	}

	@RequestMapping(value = "/ticket_purchase", method = RequestMethod.POST)
	public ResponseEntity<Object> ticketPurchase(
			@RequestBody 
			FlighCode flightCode) {
		FlightsAPassarelli flight = flightRepository.getFlightByFlightCode(flightCode.getFlightCode());
		if (null == flight) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Check flight code.");
		}
		short sa = (flight.getSeat_Availability());
		flight.setSeat_Availability((short) (sa - 1));
		flightRepository.save(flight);
		Gson gson = new Gson();
		String res = gson.toJson(flight);
		return ResponseEntity.status(HttpStatus.OK).body(res);
	}

	@RequestMapping(value = "/new_flight", method = RequestMethod.PUT)
	public ResponseEntity<String> newFlight(
			@Validated
			@RequestBody 
			NewFlight newFlight) {
		
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_TIME_FORMAT);
		sdf.setLenient(false);
		Date newFlightDate = null;
		try {
			newFlightDate = sdf.parse(newFlight.getDate());
		} catch (ParseException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body("Check date format: \""+ DATE_FORMAT + "\".");
		}

		Airline airline = airlineRepository.getAirlineByName(newFlight.getAirlineName());

		RoutePK routePk = new RoutePK(newFlight.getDestinationAirportID(), newFlight.getDepartureAirportID(),
				airline.getAirline_ID());
		Route route = routeRepository.findOne(routePk);
		if (null == route) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No route for requested airports.");
		}

		AircraftType aircraft = aircraftRepository.findOne(newFlight.getAircraftType());
		Airport sourceAirport = airportRepository.findOne(newFlight.getDepartureAirportID());
		Airport destinationAirport = airportRepository.findOne(newFlight.getDestinationAirportID());
		FlightsAPassarelliPK fId = new FlightsAPassarelliPK(newFlight.getFlightCode(), newFlightDate);
		FlightsAPassarelli f = new FlightsAPassarelli(fId, aircraft.getModel(), airline.getName(),
				sourceAirport.getIata(), destinationAirport.getIata(), newFlight.getPrice(),
				newFlight.getSeatAvailability());
		// add a flight for the route
		flightRepository.save(f);
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
			dateFormat.setLenient(false);
			String dateStr = dateFormat.format(f.getId().getDeparture_Date());
			return ResponseEntity.created(new URI("flights_by_date?date="+dateStr)).build();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Check date format: \""+ DATE_FORMAT + "\".");
		}
	}

	@RequestMapping(value = "/change_price", method = RequestMethod.PUT)
	public ResponseEntity<String> changePrice(
			@Validated
			@RequestBody
			ChangePrice changePrice) {
		Gson gson = new Gson();
		BigDecimal bd;
		try {
			bd = new BigDecimal(changePrice.getAmount());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Check \"amount\" value (ie: 123.45)");
		}
		
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_TIME_FORMAT);
		sdf.setLenient(false);
		Date flightDate = null;
		try {
			flightDate = sdf.parse(changePrice.getDate());
		} catch (ParseException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body("Check date format: \""+ DATE_TIME_FORMAT + "\".");
		}

		
		FlightsAPassarelliPK fId = new FlightsAPassarelliPK(changePrice.getFlightCode(), flightDate);
		FlightsAPassarelli flight = flightRepository.findOne(fId);
		if (null == flight) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Check flight code.");
		}
		BigDecimal currentPrice = flight.getPrice();
		BigDecimal newPrice;
		if (changePrice.getOperation() == PriceAction.increase) {
			newPrice = currentPrice.add(bd);
		} else {
			newPrice = currentPrice.subtract(bd);
		}
		flight.setPrice(newPrice);
		flightRepository.save(flight);
		return ResponseEntity.ok(gson.toJson(flight));
	}
}
