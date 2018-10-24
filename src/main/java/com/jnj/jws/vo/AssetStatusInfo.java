/*
 * 
 */
package com.jnj.jws.vo;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * The Class AssetStatusInfo.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class AssetStatusInfo {

	/** The assigned to. */
	private String assignedTo;

	/** The assigned date. */
	private Date assignedDate;

	/** The contact info. */
	private String contactInfo;

	/** The wwid. */
	private String wwid;

	/** The contact number. */
	private String contactNumber;

	/** The checkout. */
	private boolean checkout;

	/**
	 * Gets the assigned to.
	 *
	 * @return the assigned to
	 */
	public String getAssignedTo() {
		return assignedTo;
	}

	/**
	 * Sets the assigned to.
	 *
	 * @param assignedTo the new assigned to
	 */
	public void setAssignedTo(String assignedTo) {
		this.assignedTo = assignedTo;
	}

	/**
	 * Gets the assigned date.
	 *
	 * @return the assigned date
	 */
	public Date getAssignedDate() {
		if (assignedDate == null) {
			return null;
		}
		else {
			return new Date(assignedDate.getTime());
		}
	}

	/**
	 * Sets the assigned date.
	 *
	 * @param assignedDate the new assigned date
	 */
	public void setAssignedDate(Date assignedDate) {
		this.assignedDate = assignedDate;
	}

	/**
	 * Gets the contact info.
	 *
	 * @return the contact info
	 */
	public String getContactInfo() {
		return contactInfo;
	}

	/**
	 * Sets the contact info.
	 *
	 * @param contactInfo the new contact info
	 */
	public void setContactInfo(String contactInfo) {
		this.contactInfo = contactInfo;
	}

	/**
	 * Checks if is checkout.
	 *
	 * @return true, if is checkout
	 */
	public boolean isCheckout() {
		return checkout;
	}

	/**
	 * Sets the checkout.
	 *
	 * @param checkout the new checkout
	 */
	public void setCheckout(boolean checkout) {
		this.checkout = checkout;
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

	/**
	 * Gets the contact number.
	 *
	 * @return the contact number
	 */
	public String getContactNumber() {
		return contactNumber;
	}

	/**
	 * Sets the contact number.
	 *
	 * @param contactNumber the new contact number
	 */
	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

}
