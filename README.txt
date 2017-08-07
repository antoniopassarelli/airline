Technology stack:
	-Spring Boot Data JPA
	-Google GSON

Prerequisites:
	-Java 8+
	-MySQL 5.7.19
	-Apache Maven 3+
	

Running the application:
	$ mvn spring-boot:run
	
Verifying the application via terminal:
	GET:		$ curl <url to test>
	POST/PUT:	$ curl -H "Content-Type: application/json" -X POST -d @JSONs/<path>.json <path_to_test>

NOTE: some URLs requires quoting in order to send all the request parameters


URLs available:
	1) Retrieve a list of available flights on a given date
		template:
			$ curl localhost:8080/flights_by_date?date={yyyy-MM-dd}
		ok:
			$ curl localhost:8080/flights_by_date?date=2017-03-15
		wrong:
			$ curl localhost:8080/flights_by_date?date=2017-15-03
	
	2) Retrieve a paginated list of destinations
		template:
			$ curl localhost:8080/destination_by_airline?airline={airline}&pageNumber{pagenumber}
		ok:
			$ curl localhost:8080/destination_by_airline?airline=Air%20Canada&pageNumber=0
		wrong:
			$ curl localhost:8080/destination_by_airline?airline=Air%20Canad&pageNumber=0
			$ curl localhost:8080/destination_by_airline?airline=Air%20Canada&pageNumber=x
			
	3) Simulate a ticket purchase by changing the seats available for a specified flight
		template:
			$ curl -H "Content-Type: application/json" -X POST -d @JSONs/ticket_purchase/template.json localhost:8080/ticket_purchase
		ok:
			$ curl -H "Content-Type: application/json" -X POST -d @JSONs/ticket_purchase/ok.json localhost:8080/ticket_purchase
		wrong:
			$ curl -H "Content-Type: application/json" -X POST -d @JSONs/ticket_purchase/wrong.json localhost:8080/ticket_purchase

	4) Simulate new flight availability by adding a new flight for a specified route
	   	template:
			$ curl -H "Content-Type: application/json" -X POST -d @JSONs/new_flight/template.json localhost:8080/new_flight
		ok:
			$ curl -H "Content-Type: application/json" -X POST -d @JSONs/new_flight/ok.json localhost:8080/new_flight
		test:
			$ curl localhost:8080/flightsByDate?date=2017-09-15
		wrong - no routes for given airports:
			$ curl -H "Content-Type: application/json" -X POST -d @JSONs/new_flight/wrong.json localhost:8080/new_flight

			
	5) Simulate change in flight price by increasing/decreasing the ticket price for a specified flight
		template:
			$ curl -H "Content-Type: application/json" -X PUT -d @JSONs/change_price/template.json localhost:8080/change_price
		ok:
			$ curl -H "Content-Type: application/json" -X PUT -d @JSONs/change_price/ok.json localhost:8080/change_price
		wrong - wrong flight code:
			$ curl -H "Content-Type: application/json" -X PUT -d @JSONs/change_price/wrong.json localhost:8080/change_price

