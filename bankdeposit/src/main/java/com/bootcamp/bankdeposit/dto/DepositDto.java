package com.bootcamp.bankdeposit.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DepositDto {
	private String id;
	private String balance;
	private String currency;
	private String idClient;
	private String depositNumber;
	private String typeSavingAcc;
	private String typeCurrentAcc;
	private String typeTermAcc;

}
