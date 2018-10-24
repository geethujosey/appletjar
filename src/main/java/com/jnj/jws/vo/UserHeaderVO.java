/*
 * 
 */
package com.jnj.jws.vo;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * VO used to store User details in HTTP Header.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserHeaderVO {

	/** The user wwid. */
	@JsonProperty("wwid")
	private String userWwid;

	/** The first name. */
	private String firstName;

	/** The full name. */
	private String fullName;

	/** The user role name. */
	@JsonProperty("userRole")
	private String userRoleName;

	/** The country name. */
	@JsonProperty("country")
	private String countryName;

	/** The email address. */
	private String emailAddress;

	/** The user role ids. */
	private Set<Integer> userRoleIds;

	/** The is registered. */
	private boolean isRegistered;

	/** The is mandt training completed. */
	private boolean isMandtTrainingCompleted;

	/** The activation status. */
	private Character activationStatus;

	/** The timezone. */
	private String timezone;

	/**
	 * Gets the user wwid.
	 *
	 * @return the user wwid
	 */
	public String getUserWwid() {
		return userWwid;
	}

	/**
	 * Sets the user wwid.
	 *
	 * @param userWwid the new user wwid
	 */
	public void setUserWwid(String userWwid) {
		this.userWwid = userWwid;
	}

	/**
	 * Gets the first name.
	 *
	 * @return the first name
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Sets the first name.
	 *
	 * @param firstName the new first name
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * Gets the full name.
	 *
	 * @return the full name
	 */
	public String getFullName() {
		return fullName;
	}

	/**
	 * Sets the full name.
	 *
	 * @param fullName the new full name
	 */
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	/**
	 * Gets the user role name.
	 *
	 * @return the user role name
	 */
	public String getUserRoleName() {
		return userRoleName;
	}

	/**
	 * Sets the user role name.
	 *
	 * @param userRoleName the new user role name
	 */
	public void setUserRoleName(String userRoleName) {
		this.userRoleName = userRoleName;
	}

	/**
	 * Gets the country name.
	 *
	 * @return the country name
	 */
	public String getCountryName() {
		return countryName;
	}

	/**
	 * Sets the country name.
	 *
	 * @param countryName the new country name
	 */
	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	/**
	 * Gets the email address.
	 *
	 * @return the email address
	 */
	public String getEmailAddress() {
		return emailAddress;
	}

	/**
	 * Sets the email address.
	 *
	 * @param emailAddress the new email address
	 */
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	/**
	 * Gets the user role ids.
	 *
	 * @return the user role ids
	 */
	public Set<Integer> getUserRoleIds() {
		return userRoleIds;
	}

	/**
	 * Sets the user role ids.
	 *
	 * @param userRoleIds the new user role ids
	 */
	public void setUserRoleIds(Set<Integer> userRoleIds) {
		this.userRoleIds = userRoleIds;
	}

	/**
	 * Gets the checks if is registered.
	 *
	 * @return the checks if is registered
	 */
	public boolean getIsRegistered() {
		return isRegistered;
	}

	/**
	 * Sets the checks if is registered.
	 *
	 * @param isRegistered the new checks if is registered
	 */
	public void setIsRegistered(boolean isRegistered) {
		this.isRegistered = isRegistered;
	}

	/**
	 * Gets the checks if is mandt training completed.
	 *
	 * @return the checks if is mandt training completed
	 */
	public boolean getIsMandtTrainingCompleted() {
		return isMandtTrainingCompleted;
	}

	/**
	 * Sets the checks if is mandt training completed.
	 *
	 * @param isMandtTrainingCompleted the new checks if is mandt training completed
	 */
	public void setIsMandtTrainingCompleted(boolean isMandtTrainingCompleted) {
		this.isMandtTrainingCompleted = isMandtTrainingCompleted;
	}

	/**
	 * Gets the activation status.
	 *
	 * @return the activation status
	 */
	public Character getActivationStatus() {
		return activationStatus;
	}

	/**
	 * Sets the activation status.
	 *
	 * @param activationStatus the new activation status
	 */
	public void setActivationStatus(Character activationStatus) {
		this.activationStatus = activationStatus;
	}

	/**
	 * Gets the timezone.
	 *
	 * @return the timezone
	 */
	public String getTimezone() {
		return timezone;
	}

	/**
	 * Sets the timezone.
	 *
	 * @param timezone the new timezone
	 */
	public void setTimezone(String timezone) {
		this.timezone = timezone;
	}
}
