package com.jnj.jws.vo;

/**
 * Sharing common properties for all value objects.
 *
 * @author uradhakr
 */
public class CommonBaseVO {

	/** The country. */
	protected String country;

	/** The system id. */
	protected String systemId;

	/**
	 * Gets the system id.
	 *
	 * @return the system id
	 */
	public String getSystemId() {
		return systemId;
	}

	/**
	 * Sets the system id.
	 *
	 * @param systemId the new system id
	 */
	public void setSystemId(String systemId) {
		this.systemId = systemId;
	}

	/**
	 * Gets the country.
	 *
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * Sets the country.
	 *
	 * @param country the new country
	 */
	public void setCountry(String country) {
		this.country = country;
	}

}
