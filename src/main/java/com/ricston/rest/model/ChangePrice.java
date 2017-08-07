package com.ricston.rest.model;

import com.ricston.enums.PriceAction;

public class ChangePrice {

	PriceAction operation;
	String amount;
	String flightCode;
//	@JsonDeserialize(using = CustomerDateAndTimeDeserialize.class)
//	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss", style="yyyy-MM-dd hh:mm:ss")
	String date;

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
//
//	public Date getDate() {
//		return date;
//	}
//
//	public void setDate(Date date) {
//		this.date = date;
//	}
	

	public PriceAction getOperation() {
		return operation;
	}

	public void setOperation(PriceAction operation) {
		this.operation = operation;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

}
