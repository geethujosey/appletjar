/**
 * 
 */
package com.jnj.jws.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * The Class ReasonVO.
 *
 * @author 317650
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ReasonVO extends CreatedDetailsBaseVO {

	/** The reason id. */
	private int reasonId;

	/** The description. */
	private String description;

	/** The category. */
	private String category;

	/**
	 * Gets the reason id.
	 *
	 * @return the reason id
	 */
	public int getReasonId() {
		return reasonId;
	}

	/**
	 * Sets the reason id.
	 *
	 * @param reasonId the new reason id
	 */
	public void setReasonId(int reasonId) {
		this.reasonId = reasonId;
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
	 * Gets the category.
	 *
	 * @return the category
	 */
	public String getCategory() {
		return category;
	}

	/**
	 * Sets the category.
	 *
	 * @param category the new category
	 */
	public void setCategory(String category) {
		this.category = category;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return getDescription();
	}
}
