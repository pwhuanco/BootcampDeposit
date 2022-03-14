package com.bootcamp.bankdeposit.bean;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 *
 */
@Document("account")
@Data
public class Account {
	@Id
	private String id;
	private Double balance;
	private String currency;
	private String idClient;
	private String accountNumber;
	/**
	 * typeSavingAcc: indica si una cuenta de ahorro
	 * 1: si
	 * 0: no
	 */
	private String typeSavingAcc;
	/**
	 * typeCurrentAcc: indica si una cuenta corriente
	 * 1: si
	 * 0: no
	 */
	private String typeCurrentAcc;
	/**
	 * typeTermAcc: indica si una cuenta a plazo
	 * 1: si
	 * 0: no
	 */
	private String typeTermAcc;
	/**
	 * canBeDeposit: indica si es una cuenta a la que se puede depositar
	 * 1: si
	 * 0: no
	 */
	private String canBeDeposit;

}
