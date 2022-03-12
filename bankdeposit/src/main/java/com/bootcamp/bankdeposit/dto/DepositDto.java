package com.bootcamp.bankdeposit.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DepositDto {
	private String id;
	private String amount;
	private String currency;
	private String idClient;
	private String fromAccountNumber;
	private String toAccountNumber;
	private String depositor;
	private String timestamp;

	public DepositDto(String amount, String currency, String idClient, String fromAccountNumber, String toAccountNumber, String depositor, String timestamp) {
		this.amount = amount;
		this.currency = currency;
		this.idClient = idClient;
		this.fromAccountNumber = fromAccountNumber;
		this.toAccountNumber = toAccountNumber;
		this.depositor = depositor;
		this.timestamp = timestamp;
	}
}
