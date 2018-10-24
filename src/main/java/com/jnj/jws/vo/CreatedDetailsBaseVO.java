/*
 * 
 */
package com.jnj.jws.vo;

import java.util.Date;

/**
 * Created details Value Object.
 *
 * @author uradhakr
 */
public class CreatedDetailsBaseVO {

	/** The created on. */
	private Date createdOn;

	/** The updated by. */
	private String updatedBy;

	/** The updated on. */
	private Date updatedOn;

	/** The created by. */
	private String createdBy;

	/**
	 * Gets the created on.
	 *
	 * @return the created on
	 */
	public Date getCreatedOn() {
		if (createdOn == null) {
			return null;
		}
		else {
			return new Date(createdOn.getTime());
		}
	}

	/**
	 * Sets the created on.
	 *
	 * @param createdOn the new created on
	 */
	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	/**
	 * Gets the updated by.
	 *
	 * @return the updated by
	 */
	public String getUpdatedBy() {
		return updatedBy;
	}

	/**
	 * Sets the updated by.
	 *
	 * @param updatedBy the new updated by
	 */
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	/**
	 * Gets the updated on.
	 *
	 * @return the updated on
	 */
	public Date getUpdatedOn() {
		if (updatedOn == null) {
			return null;
		}
		else {
			return new Date(updatedOn.getTime());
		}
	}

	/**
	 * Sets the updated on.
	 *
	 * @param updatedOn the new updated on
	 */
	public void setUpdatedOn(Date updatedOn) {
		this.updatedOn = updatedOn;
	}

	/**
	 * Gets the created by.
	 *
	 * @return the created by
	 */
	public String getCreatedBy() {
		return createdBy;
	}

	/**
	 * Sets the created by.
	 *
	 * @param createdBy the new created by
	 */
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

}
