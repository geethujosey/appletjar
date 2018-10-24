/*
 * 
 */
package com.jnj.jws.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * CommonAssetVO.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class CommonAssetVO {

	/** The asset number. */
	@JsonProperty("assetId")
	private String assetNumber;

	/** The current version. */
	@JsonProperty("currentVersion")
	private String currentVersion;

	/** The previous version. */
	@JsonProperty("previousVersion")
	private String previousVersion;

	/**
	 * Gets the asset number.
	 *
	 * @return the asset number
	 */
	public String getAssetNumber() {
		return assetNumber;
	}

	/**
	 * Sets the asset number.
	 *
	 * @param assetNumber the new asset number
	 */
	public void setAssetNumber(String assetNumber) {
		this.assetNumber = assetNumber;
	}

	/**
	 * Gets the current version.
	 *
	 * @return the current version
	 */
	public String getCurrentVersion() {
		return currentVersion;
	}

	/**
	 * Sets the current version.
	 *
	 * @param currentVersion the new current version
	 */
	public void setCurrentVersion(String currentVersion) {
		this.currentVersion = currentVersion;
	}

	/**
	 * Gets the previous version.
	 *
	 * @return the previous version
	 */
	public String getPreviousVersion() {
		return previousVersion;
	}

	/**
	 * Sets the previous version.
	 *
	 * @param previousVersion the new previous version
	 */
	public void setPreviousVersion(String previousVersion) {
		this.previousVersion = previousVersion;
	}

}
