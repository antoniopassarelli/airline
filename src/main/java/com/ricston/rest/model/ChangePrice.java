package com.ricston.rest.model;

import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.ricston.enums.PriceAction;
import com.ricston.rest.util.CustomerDateAndTimeDeserialize;

public class ChangePrice {

	PriceAction operation;
	String amount;
	String flightCode;
	@JsonDeserialize(using = CustomerDateAndTimeDeserialize.class)
	Date date;

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getFlightCode() {
		return flightCode;
	}

	public void setFlightCode(String flightCode) {
		this.flightCode = flightCode;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public PriceAction getOperation() {
		return operation;
	}

	public void setOperation(PriceAction operation) {
		this.operation = operation;
	}

}
