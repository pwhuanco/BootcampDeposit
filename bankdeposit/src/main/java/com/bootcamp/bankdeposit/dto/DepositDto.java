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

}
