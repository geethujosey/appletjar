/*
 * 
 */
package com.jnj.jws.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * UsbAssetVO class.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class UsbAssetVO {

	/** The asset serial number. */
	@JsonProperty("assetId")
	private String assetSerialNumber;

	/** The software name. */
	private String softwareName = "";

	/** The model. */
	private String model = "";

	/** The version number. */
	private String versionNumber = "";

	/** The asset status. */
	private String assetStatus = "";

	/** The system id. */
	private int systemId;

	/** The check out history VO. */
	@JsonProperty("checkout")
	private CheckOutHistoryVO checkOutHistoryVO;

	/**
	 * Gets the asset serial number.
	 *
	 * @return the asset serial number
	 */
	@JsonProperty("assetId")
	public String getAssetSerialNumber() {
		return assetSerialNumber;
	}

	/**
	 * Sets the asset serial number.
	 *
	 * @param assetSerialNumber the new asset serial number
	 */
	@JsonProperty("assetId")
	public void setAssetSerialNumber(String assetSerialNumber) {
		this.assetSerialNumber = assetSerialNumber;
	}

	/**
	 * Gets the software name.
	 *
	 * @return the software name
	 */
	public String getSoftwareName() {
		return softwareName;
	}

	/**
	 * Sets the software name.
	 *
	 * @param softwareName the new software name
	 */
	public void setSoftwareName(String softwareName) {
		this.softwareName = softwareName;
	}

	/**
	 * Gets the model.
	 *
	 * @return the model
	 */
	public String getModel() {
		return model;
	}

	/**
	 * Sets the model.
	 *
	 * @param model the new model
	 */
	public void setModel(String model) {
		this.model = model;
	}

	/**
	 * Gets the version number.
	 *
	 * @return the version number
	 */
	public String getVersionNumber() {
		return versionNumber;
	}

	/**
	 * Sets the version number.
	 *
	 * @param versionNumber the new version number
	 */
	public void setVersionNumber(String versionNumber) {
		this.versionNumber = versionNumber;
	}

	/**
	 * Gets the asset status.
	 *
	 * @return the asset status
	 */
	public String getAssetStatus() {
		return assetStatus;
	}

	/**
	 * Sets the asset status.
	 *
	 * @param assetStatus the new asset status
	 */
	public void setAssetStatus(String assetStatus) {
		this.assetStatus = assetStatus;
	}

	/**
	 * Gets the check out history VO.
	 *
	 * @return the check out history VO
	 */
	@JsonProperty("checkout")
	public CheckOutHistoryVO getCheckOutHistoryVO() {
		return checkOutHistoryVO;
	}

	/**
	 * Sets the check out history VO.
	 *
	 * @param checkOutHistoryVO the new check out history VO
	 */
	@JsonProperty("checkout")
	public void setCheckOutHistoryVO(CheckOutHistoryVO checkOutHistoryVO) {
		this.checkOutHistoryVO = checkOutHistoryVO;
	}

	/**
	 * Gets the system id.
	 *
	 * @return the system id
	 */
	public int getSystemId() {
		return systemId;
	}

	/**
	 * Sets the system id.
	 *
	 * @param systemId the new system id
	 */
	public void setSystemId(int systemId) {
		this.systemId = systemId;
	}

}
