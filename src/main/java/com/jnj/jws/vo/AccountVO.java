package com.jnj.jws.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * This class is used for holding account values from UI report.
 * 
 * @author CTS
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class AccountVO extends CommonBaseVO {

	/** The account id. */
	@JsonProperty("accountId")
	protected String accountId;

	/** The account name. */
	@JsonProperty("accountName")
	protected String accountName;

	/**
	 * Gets the account id.
	 *
	 * @return the account id
	 */
	public String getAccountId() {
		return accountId;
	}

	/**
	 * Sets the account id.
	 *
	 * @param accountId the new account id
	 */
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	/**
	 * Gets the account name.
	 *
	 * @return the account name
	 */
	public String getAccountName() {
		return accountName;
	}

	/**
	 * Sets the account name.
	 *
	 * @param accountName the new account name
	 */
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

}