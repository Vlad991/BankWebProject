package com.bank.dto;

public enum CreditCardStatus {
	OPEN("open"),
	BLOCKED("blocked"),
	UNREGISTERED("unregistered");

	private String status;

	private CreditCardStatus(String status) {
		this.status = status;
	}
	
	public String getStatus() {
		return status;
	}
}
