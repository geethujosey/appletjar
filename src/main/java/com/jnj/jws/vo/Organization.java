package com.jnj.jws.vo;

import java.util.List;

/**
 * The Class Organization.
 *
 * @author
 */
public class Organization extends AssetAxedaBaseVO {

	/** The name. */
	private String name;

	/** The description. */
	private String description;

	/** The locations. */
	private List<Location> locations;

	/** The responsecode. */
	private String responsecode;

	/**
	 * Auto-generated constructor stub.
	 */
	public Organization() {
		super();
	}

	/**
	 * Gets the responsecode.
	 *
	 * @return the responsecode
	 */
	public String getResponsecode() {
		return responsecode;
	}

	/**
	 * Sets the responsecode.
	 *
	 * @param responsecode the new responsecode
	 */
	public void setResponsecode(String responsecode) {
		this.responsecode = responsecode;
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

	/**
	 * Gets the locations.
	 *
	 * @return the locations
	 */
	public List<Location> getLocations() {
		return locations;
	}

	/**
	 * Sets the locations.
	 *
	 * @param locations the new locations
	 */
	public void setLocations(List<Location> locations) {
		this.locations = locations;
	}

}
