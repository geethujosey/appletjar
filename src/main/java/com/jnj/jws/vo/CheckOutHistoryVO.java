/*
 * 
 */
package com.jnj.jws.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * This class hold the check out history details from genesis-core.
 *
 * @author
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class CheckOutHistoryVO {

	/** The checkout by. */
	@JsonProperty("checkoutBy")
	private Object checkoutBy;

	/** The checkout on. */
	@JsonProperty("checkoutOn")
	private Object checkoutOn;

	/** The checkout info. */
	@JsonProperty("checkoutInfo")
	private Object checkoutInfo;

	/** The wwid. */
	@JsonIgnore
	private String wwid;

	/**
	 * Gets the checkout by.
	 *
	 * @return the checkout by
	 */
	public Object getCheckoutBy() {
		return checkoutBy;
	}

	/**
	 * Sets the checkout by.
	 *
	 * @param checkoutBy the new checkout by
	 */
	public void setCheckoutBy(Object checkoutBy) {
		this.checkoutBy = checkoutBy;
	}

	/**
	 * Gets the checkout on.
	 *
	 * @return the checkout on
	 */
	public Object getCheckoutOn() {
		return checkoutOn;
	}

	/**
	 * Sets the checkout on.
	 *
	 * @param checkoutOn the new checkout on
	 */
	public void setCheckoutOn(Object checkoutOn) {
		this.checkoutOn = checkoutOn;
	}

	/**
	 * Gets the checkout info.
	 *
	 * @return the checkout info
	 */
	public Object getCheckoutInfo() {
		return checkoutInfo;
	}

	/**
	 * Sets the checkout info.
	 *
	 * @param checkoutInfo the new checkout info
	 */
	public void setCheckoutInfo(Object checkoutInfo) {
		this.checkoutInfo = checkoutInfo;
	}

	/**
	 * Gets the wwid.
	 *
	 * @return the wwid
	 */
	public String getWwid() {
		return wwid;
	}

	/**
	 * Sets the wwid.
	 *
	 * @param wwid the new wwid
	 */
	public void setWwid(String wwid) {
		this.wwid = wwid;
	}

}
