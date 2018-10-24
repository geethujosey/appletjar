package com.jnj.jws.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Axeda asset location response Value object.
 *
 * @author uradhakr
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class AssetLocation extends AssetLocationBaseVO {

	/** The name. */
	@JsonProperty("name")
	private String name;

	/** The oraganization. */
	@JsonProperty("organization")
	private Organization oraganization;

	/** The region. */
	@JsonProperty("region")
	private Region region;

	/** The country. */
	@JsonProperty("country")
	private String country;

	/** The postal code. */
	@JsonProperty("postalCode")
	private String postalCode;

	/** The line 1. */
	@JsonProperty("line1")
	private String line1;

	/** The line 2. */
	@JsonProperty("line2")
	private String line2;

	/** The language. */
	@JsonProperty("language")
	private String language;

	/** The geocode. */
	@JsonProperty("geocode")
	private String geocode;

	/** The country code. */
	@JsonProperty("countryCode")
	private String countryCode;

	/** The description. */
	private String description;

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

	/**
	 * Gets the oraganization.
	 *
	 * @return the oraganization
	 */
	public Organization getOraganization() {
		return oraganization;
	}

	/**
	 * Sets the oraganization.
	 *
	 * @param oraganization the new oraganization
	 */
	public void setOraganization(Organization oraganization) {
		this.oraganization = oraganization;
	}

	/**
	 * Gets the region.
	 *
	 * @return the region
	 */
	public Region getRegion() {
		return region;
	}

	/**
	 * Sets the region.
	 *
	 * @param region the new region
	 */
	public void setRegion(Region region) {
		this.region = region;
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

	/**
	 * Gets the postal code.
	 *
	 * @return the postal code
	 */
	public String getPostalCode() {
		return postalCode;
	}

	/**
	 * Sets the postal code.
	 *
	 * @param postalCode the new postal code
	 */
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
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
	 * Gets the language.
	 *
	 * @return the language
	 */
	public String getLanguage() {
		return language;
	}

	/**
	 * Sets the language.
	 *
	 * @param language the new language
	 */
	public void setLanguage(String language) {
		this.language = language;
	}

	/**
	 * Gets the geocode.
	 *
	 * @return the geocode
	 */
	public String getGeocode() {
		return geocode;
	}

	/**
	 * Sets the geocode.
	 *
	 * @param geocode the new geocode
	 */
	public void setGeocode(String geocode) {
		this.geocode = geocode;
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
	 * Gets the description.
	 *
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Sets the description.
	 *
	 * @param description the new description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

}
