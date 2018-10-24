package com.jnj.jws.vo;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The Class AssetVO.
 *
 * @author cognizant
 */

public class AssetVO extends AssetCommonVO {

	/** The asset serial number. */
	@JsonProperty("assetId")
	private String assetSerialNumber;

	/** The asset status info VO. */
	@JsonProperty("assigned")
	private AssetStatusInfo assetStatusInfoVO;

	/** The check out history VO. */
	@JsonProperty("checkout")
	private CheckOutHistoryVO checkOutHistoryVO;

	/** The checkout by. */
	@JsonIgnore
	private String checkoutBy;

	/** The checkout on. */
	@JsonIgnore
	private Date checkoutOn;

	/** The locationaccount name. */
	private String locationaccountName;

	/** The location VO. */
	@JsonProperty("location")
	private AssetLocationVO locationVO;

	/** The properties list. */
	@JsonIgnore
	private List<String> propertiesList;

	/** The last updated on. */
	@JsonProperty("lastUpdated")
	private Date lastUpdatedOn;

	/** The last updated by. */
	private String lastUpdatedBy;

	/** The upgrade status. */
	private String upgradeStatus;

	/** The previous version. */
	private String previousVersion;

	/** The last upgraded on. */
	@JsonProperty("lastUpgradedOn")
	private Date lastUpgradedOn;

	/** The last updated by has value. */
	private boolean lastUpdatedByHasValue = true;

	/** The is upgrade info. */
	@JsonIgnore
	private boolean isUpgradeInfo;

	/**
	 * Gets the asset serial number.
	 *
	 * @return the asset serial number
	 */
	public String getAssetSerialNumber() {
		return assetSerialNumber;
	}

	/**
	 * Sets the asset serial number.
	 *
	 * @param assetSerialNumber the new asset serial number
	 */
	public void setAssetSerialNumber(String assetSerialNumber) {
		this.assetSerialNumber = assetSerialNumber;
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

	/**
	 * Gets the properties list.
	 *
	 * @return the properties list
	 */
	public List<String> getPropertiesList() {
		return propertiesList;
	}

	/**
	 * Sets the properties list.
	 *
	 * @param propertiesList the new properties list
	 */
	public void setPropertiesList(List<String> propertiesList) {
		this.propertiesList = propertiesList;
	}

	/**
	 * Gets the asset status info VO.
	 *
	 * @return the asset status info VO
	 */
	public AssetStatusInfo getAssetStatusInfoVO() {
		return assetStatusInfoVO;
	}

	/**
	 * Sets the asset status info VO.
	 *
	 * @param assetStatusInfoVO the new asset status info VO
	 */
	public void setAssetStatusInfoVO(AssetStatusInfo assetStatusInfoVO) {
		this.assetStatusInfoVO = assetStatusInfoVO;
	}

	/**
	 * Gets the checkout on.
	 *
	 * @return the checkout on
	 */
	public Date getCheckoutOn() {
		if (checkoutOn == null) {
			return null;
		}
		else {
			return new Date(checkoutOn.getTime());
		}
	}

	/**
	 * Sets the checkout on.
	 *
	 * @param checkoutOn the new checkout on
	 */
	public void setCheckoutOn(Date checkoutOn) {
		this.checkoutOn = checkoutOn;
	}

	/**
	 * Gets the check out history VO.
	 *
	 * @return the check out history VO
	 */
	public CheckOutHistoryVO getCheckOutHistoryVO() {
		return checkOutHistoryVO;
	}

	/**
	 * Sets the check out history VO.
	 *
	 * @param checkOutHistoryVO the new check out history VO
	 */
	public void setCheckOutHistoryVO(CheckOutHistoryVO checkOutHistoryVO) {
		this.checkOutHistoryVO = checkOutHistoryVO;
	}

	/**
	 * Gets the location VO.
	 *
	 * @return the location VO
	 */
	public AssetLocationVO getLocationVO() {
		return locationVO;
	}

	/**
	 * Sets the location VO.
	 *
	 * @param locationVO the new location VO
	 */
	public void setLocationVO(AssetLocationVO locationVO) {
		this.locationVO = locationVO;
	}

	/**
	 * Gets the locationaccount name.
	 *
	 * @return the locationaccount name
	 */
	public String getLocationaccountName() {
		return locationaccountName;
	}

	/**
	 * Sets the locationaccount name.
	 *
	 * @param locationaccountName the new locationaccount name
	 */
	public void setLocationaccountName(String locationaccountName) {
		this.locationaccountName = locationaccountName;
	}

	/**
	 * Gets the last updated on.
	 *
	 * @return the last updated on
	 */
	public Date getLastUpdatedOn() {
		if (lastUpdatedOn == null) {
			return null;
		}
		else {
			return new Date(lastUpdatedOn.getTime());
		}
	}

	/**
	 * Sets the last updated on.
	 *
	 * @param lastUpdatedOn the new last updated on
	 */
	public void setLastUpdatedOn(Date lastUpdatedOn) {
		this.lastUpdatedOn = lastUpdatedOn;
	}

	/**
	 * Gets the last updated by.
	 *
	 * @return the last updated by
	 */
	public String getLastUpdatedBy() {
		return lastUpdatedBy;
	}

	/**
	 * Sets the last updated by.
	 *
	 * @param lastUpdatedBy the new last updated by
	 */
	public void setLastUpdatedBy(String lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}

	/**
	 * Gets the upgrade status.
	 *
	 * @return the upgrade status
	 */
	public String getUpgradeStatus() {
		return upgradeStatus;
	}

	/**
	 * Sets the upgrade status.
	 *
	 * @param upgradeStatus the new upgrade status
	 */
	public void setUpgradeStatus(String upgradeStatus) {
		this.upgradeStatus = upgradeStatus;
	}

	/**
	 * Checks if is upgrade info.
	 *
	 * @return true, if is upgrade info
	 */
	public boolean isUpgradeInfo() {
		return isUpgradeInfo;
	}

	/**
	 * Sets the upgrade info.
	 *
	 * @param isUpgradeInfo the new upgrade info
	 */
	public void setUpgradeInfo(boolean isUpgradeInfo) {
		this.isUpgradeInfo = isUpgradeInfo;
	}

	/**
	 * Checks if is last updated by has value.
	 *
	 * @return true, if is last updated by has value
	 */
	public boolean isLastUpdatedByHasValue() {
		return lastUpdatedByHasValue;
	}

	/**
	 * Sets the last updated by has value.
	 *
	 * @param lastUpdatedByHasValue the new last updated by has value
	 */
	public void setLastUpdatedByHasValue(boolean lastUpdatedByHasValue) {
		this.lastUpdatedByHasValue = lastUpdatedByHasValue;
	}

	/**
	 * Gets the last upgraded on.
	 *
	 * @return the last upgraded on
	 */
	public Date getLastUpgradedOn() {
		if (lastUpgradedOn == null) {
			return null;
		}
		else {
			return new Date(lastUpgradedOn.getTime());
		}
	}

	/**
	 * Sets the last upgraded on.
	 *
	 * @param lastUpgradedOn the new last upgraded on
	 */
	public void setLastUpgradedOn(Date lastUpgradedOn) {
		this.lastUpgradedOn = lastUpgradedOn;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		int hashCode = 0;
		if (assetSerialNumber != null) {
			hashCode = assetSerialNumber.hashCode();
		}
		result = prime * result + (hashCode);
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		boolean equals = true;
		if (this == obj) {
			return true;
		}
		else if (obj == null) {
			return false;
		}
		else if (getClass() != obj.getClass()) {
			equals = false;
		}
		if (equals) {
			AssetVO other = (AssetVO) obj;
			if (assetSerialNumber == null) {
				if (other.assetSerialNumber != null) {
					equals = false;
				}
			}
			else if (!assetSerialNumber.equals(other.assetSerialNumber)) {
				equals = false;
			}
		}
		return equals;
	}

	/**
	 * Gets the checkout by.
	 *
	 * @return the checkout by
	 */
	public String getCheckoutBy() {
		return checkoutBy;
	}

	/**
	 * Sets the checkout by.
	 *
	 * @param checkoutBy the new checkout by
	 */
	public void setCheckoutBy(String checkoutBy) {
		this.checkoutBy = checkoutBy;
	}

}
