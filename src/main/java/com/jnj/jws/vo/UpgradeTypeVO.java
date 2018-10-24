/**
 * This package provides classes to transfer data from Genesis core application to UI application
 */
package com.jnj.jws.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * This class is using to transfer upgrade information from core to ui application.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class UpgradeTypeVO {

	/** The type id. */
	@JsonProperty("upgradeTypeId")
	private String typeId;

	/** The type name. */
	@JsonProperty("upgradeTypeName")
	private String typeName;

	/**
	 * Gets the type id.
	 *
	 * @return the type id
	 */
	public String getTypeId() {
		return typeId;
	}

	/**
	 * Sets the type id.
	 *
	 * @param typeId the new type id
	 */
	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}

	/**
	 * Gets the type name.
	 *
	 * @return the type name
	 */
	public String getTypeName() {
		return typeName;
	}

	/**
	 * Sets the type name.
	 *
	 * @param typeName the new type name
	 */
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

}
