/*
 * 
 */
package com.jnj.jws.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * The Class UserVO.
 *
 * @author 317650
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserVO {

	/** The first name. */
	private String firstName;

	/** The has role. */
	private boolean hasRole;

	/** The last name. */
	private String lastName;

	/**
	 * Checks if is checks for role.
	 *
	 * @return true, if is checks for role
	 */
	public boolean isHasRole() {
		return hasRole;
	}

	/**
	 * Sets the checks for role.
	 *
	 * @param hasRole the new checks for role
	 */
	public void setHasRole(boolean hasRole) {
		this.hasRole = hasRole;
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
	 * Gets the last name.
	 *
	 * @return the last name
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Sets the last name.
	 *
	 * @param lastName the new last name
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

}
