package com.jnj.jws.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * AssetCommonVO - sub class AssetVO.
 */
public class AssetCommonVO extends AccountVO {

	/** The asset available. */
	@JsonProperty("isAssetAvailable")
	private String assetAvailable;

	/** The asset check status. */
	private String assetCheckStatus;

	/** The status. */
	private String status;

	/** The address. */
	private String address;

	/** The account. */
	private String account;

	/** The current software. */
	private String currentSoftware;

	/** The available software. */
	private String availableSoftware;

	/** The model name. */
	private String modelName;

	/**
	 * Gets the asset available.
	 *
	 * @return the asset available
	 */
	public String getAssetAvailable() {
		return assetAvailable;
	}

	/**
	 * Sets the asset available.
	 *
	 * @param assetAvailable the new asset available
	 */
	public void setAssetAvailable(String assetAvailable) {
		this.assetAvailable = assetAvailable;
	}

	/**
	 * Gets the asset check status.
	 *
	 * @return the asset check status
	 */
	public String getAssetCheckStatus() {
		return assetCheckStatus;
	}

	/**
	 * Sets the asset check status.
	 *
	 * @param assetCheckStatus the new asset check status
	 */
	public void setAssetCheckStatus(String assetCheckStatus) {
		this.assetCheckStatus = assetCheckStatus;
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
	 * Gets the address.
	 *
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * Sets the address.
	 *
	 * @param address the new address
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * Gets the account.
	 *
	 * @return the account
	 */
	public String getAccount() {
		return account;
	}

	/**
	 * Sets the account.
	 *
	 * @param account the new account
	 */
	public void setAccount(String account) {
		this.account = account;
	}

	/**
	 * Gets the current software.
	 *
	 * @return the current software
	 */
	public String getCurrentSoftware() {
		return currentSoftware;
	}

	/**
	 * Sets the current software.
	 *
	 * @param currentSoftware the new current software
	 */
	public void setCurrentSoftware(String currentSoftware) {
		this.currentSoftware = currentSoftware;
	}

	/**
	 * Gets the available software.
	 *
	 * @return the available software
	 */
	public String getAvailableSoftware() {
		return availableSoftware;
	}

	/**
	 * Sets the available software.
	 *
	 * @param availableSoftware the new available software
	 */
	public void setAvailableSoftware(String availableSoftware) {
		this.availableSoftware = availableSoftware;
	}

	/**
	 * Gets the model name.
	 *
	 * @return the model name
	 */
	public String getModelName() {
		return modelName;
	}

	/**
	 * Sets the model name.
	 *
	 * @param modelName the new model name
	 */
	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

}
