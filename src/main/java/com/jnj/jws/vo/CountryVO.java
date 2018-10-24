/*
 * 
 */
package com.jnj.jws.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * This class act as value object for country entity.
 *
 * @author 551012
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class CountryVO extends CreatedDetailsBaseVO {

	/** The country code. */
	@JsonProperty("id")
	private String countryCode;

	/** The name. */
	@JsonProperty("country")
	private String name;

	/**
	 * Constructor.
	 *
	 * @param countryCode the country code
	 * @param name the name
	 */
	public CountryVO(String countryCode, String name) {
		this.countryCode = countryCode;
		this.name = name;
	}

	/**
	 * Default Constructor.
	 */
	public CountryVO() {
		super();
	}

	/**
	 * Gets the country code.
	 *
	 * @return the country code
	 */
	public String getCountryCode() {
		return countryCode;
	}

	/**
	 * Sets the country code.
	 *
	 * @param countryCode the new country code
	 */
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setName(String name) {
		this.name = name;
	}

}
