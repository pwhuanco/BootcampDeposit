package com.bootcamp.bankdeposit.bean;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("deposit")
@Data
public class Deposit {
	@Id
	private String id;
	private String amount;
	private String currency;
	private String idClient;
	private String fromAccountNumber;
	private String toAccountNumber;
	private String depositor;
	private String timestamp;
}
