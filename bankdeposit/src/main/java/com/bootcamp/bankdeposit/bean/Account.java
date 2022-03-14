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
	/**
	 * depositLimited: indica la cantidad maxima de veces que se puede depositar
	 * X: cantidad maxima de despositos mensuales
	 * 0: no se puede depositar
	 */
	private int depositLimited;
	/**
	 * accountType: indica  el tipo de cuenta
	 * 1: Cuenta de Ahorro
	 * 2: Cuenta Corriente
	 * 3: Plazo fijo
	 */
	private String accountType;
}
