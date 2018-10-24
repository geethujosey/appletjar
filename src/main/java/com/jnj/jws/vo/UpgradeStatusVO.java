/*
 * 
 */
package com.jnj.jws.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The Class UpgradeStatusVO.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class UpgradeStatusVO {

	/** The status code. */
	@JsonProperty("upgradeStatusId")
	private String statusCode;

	/** The status name. */
	@JsonProperty("upgradeStatusName")
	private String statusName;

	/**
	 * Gets the status code.
	 *
	 * @return the status code
	 */
	public String getStatusCode() {
		return statusCode;
	}

	/**
	 * Sets the status code.
	 *
	 * @param statusCode the new status code
	 */
	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	/**
	 * Gets the status name.
	 *
	 * @return the status name
	 */
	public String getStatusName() {
		return statusName;
	}

	/**
	 * Sets the status name.
	 *
	 * @param statusName the new status name
	 */
	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

}
