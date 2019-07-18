package com.bank.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"status"})
public enum CreditCardStatus {
	OPEN("OPEN"),
	BLOCKED("BLOCKED"),
	UNREGISTERED("UNREGISTERED");

	private String status;

	private CreditCardStatus(String status) {
		this.status = status;
	}
	
	public String getStatus() {
		return status;
	}

}
