package com.jnj.jws.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Asset Location Value Object.
 *
 * @author uradhakr
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class AssetLocationVO extends AssetLocationBaseVO {

	/** The country. */
	@JsonProperty("country")
	private String country;

	/** The line 1. */
	@JsonProperty("line1")
	private String line1;

	/** The line 2. */
	@JsonProperty("line2")
	private String line2;

	/** The old country. */
	@JsonProperty("oldCountry")
	private String oldCountry;

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

	/**
	 * Gets the line 1.
	 *
	 * @return the line 1
	 */
	public String getLine1() {
		return line1;
	}

	/**
	 * Sets the line 1.
	 *
	 * @param line1 the new line 1
	 */
	public void setLine1(String line1) {
		this.line1 = line1;
	}

	/**
	 * Gets the line 2.
	 *
	 * @return the line 2
	 */
	public String getLine2() {
		return line2;
	}

	/**
	 * Sets the line 2.
	 *
	 * @param line2 the new line 2
	 */
	public void setLine2(String line2) {
		this.line2 = line2;
	}

	/**
	 * Gets the old country.
	 *
	 * @return the old country
	 */
	public String getOldCountry() {
		return oldCountry;
	}

	/**
	 * Sets the old country.
	 *
	 * @param oldCountry the new old country
	 */
	public void setOldCountry(String oldCountry) {
		this.oldCountry = oldCountry;
	}

}
