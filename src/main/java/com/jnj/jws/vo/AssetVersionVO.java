/*
 * 
 */
package com.jnj.jws.vo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * The Class AssetVersionVO.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class AssetVersionVO {

	/** The available version. */
	private String availableVersion;

	/** The status. */
	private String status;

	/** The message. */
	private String message;

	/** The asset serial number list. */
	private List<String> assetSerialNumberList;

	/** The only key files. */
	private boolean onlyKeyFiles;

	/** The same country assets. */
	private boolean sameCountryAssets;

	/** The country code list. */
	private List<String> countryCodeList;

	/**
	 * Gets the available version.
	 *
	 * @return the available version
	 */
	public String getAvailableVersion() {
		return availableVersion;
	}

	/**
	 * Sets the available version.
	 *
	 * @param availableVersion the new available version
	 */
	public void setAvailableVersion(String availableVersion) {
		this.availableVersion = availableVersion;
	}

	/**
	 * Gets the status.
	 *
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * Sets the status.
	 *
	 * @param status the new status
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * Gets the message.
	 *
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * Sets the message.
	 *
	 * @param message the new message
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * Gets the asset serial number list.
	 *
	 * @return the asset serial number list
	 */
	public List<String> getAssetSerialNumberList() {
		return assetSerialNumberList;
	}

	/**
	 * Sets the asset serial number list.
	 *
	 * @param assetSerialNumberList the new asset serial number list
	 */
	public void setAssetSerialNumberList(List<String> assetSerialNumberList) {
		this.assetSerialNumberList = assetSerialNumberList;
	}

	/**
	 * Checks if is only key files.
	 *
	 * @return true, if is only key files
	 */
	public boolean isOnlyKeyFiles() {
		return onlyKeyFiles;
	}

	/**
	 * Sets the only key files.
	 *
	 * @param onlyKeyFiles the new only key files
	 */
	public void setOnlyKeyFiles(boolean onlyKeyFiles) {
		this.onlyKeyFiles = onlyKeyFiles;
	}

	/**
	 * Checks if is same country assets.
	 *
	 * @return true, if is same country assets
	 */
	public boolean isSameCountryAssets() {
		return sameCountryAssets;
	}

	/**
	 * Sets the same country assets.
	 *
	 * @param sameCountryAssets the new same country assets
	 */
	public void setSameCountryAssets(boolean sameCountryAssets) {
		this.sameCountryAssets = sameCountryAssets;
	}

	/**
	 * Gets the country code list.
	 *
	 * @return the country code list
	 */
	public List<String> getCountryCodeList() {
		return countryCodeList;
	}

	/**
	 * Sets the country code list.
	 *
	 * @param countryCodeList the new country code list
	 */
	public void setCountryCodeList(List<String> countryCodeList) {
		this.countryCodeList = countryCodeList;
	}

}
