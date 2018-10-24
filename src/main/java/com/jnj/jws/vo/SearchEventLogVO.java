/*
 * 
 */
package com.jnj.jws.vo;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The Class SearchEventLogVO.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class SearchEventLogVO {

	/** The folder path. */
	private String folderPath;

	/** The asset id. */
	private String assetId;

	/** The account name. */
	private String accountName;

	/** The account number. */
	private String accountNumber;

	/** The from date. */
	private String fromDate;

	/** The to date. */
	private String toDate;

	/** The upgrade type VO. */
	@JsonProperty("upgradeType")
	private UpgradeTypeVO upgradeTypeVO;

	/** The update status VO. */
	@JsonProperty("softwareUpgradestatus")
	private UpgradeStatusVO updateStatusVO;

	/** The software version VO. */
	@JsonProperty("softwareVersion")
	private ProductVO softwareVersionVO;

	/** The country VO. */
	@JsonProperty("country")
	private CountryVO countryVO;

	/** The user role ids. */
	private Set<Integer> userRoleIds;

	/**
	 * Gets the asset id.
	 *
	 * @return the asset id
	 */
	public String getAssetId() {
		return assetId;
	}

	/**
	 * Sets the asset id.
	 *
	 * @param assetId the new asset id
	 */
	public void setAssetId(String assetId) {
		this.assetId = assetId;
	}

	/**
	 * Gets the account name.
	 *
	 * @return the account name
	 */
	public String getAccountName() {
		return accountName;
	}

	/**
	 * Sets the account name.
	 *
	 * @param accountName the new account name
	 */
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	/**
	 * Gets the account number.
	 *
	 * @return the account number
	 */
	public String getAccountNumber() {
		return accountNumber;
	}

	/**
	 * Sets the account number.
	 *
	 * @param accountNumber the new account number
	 */
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	/**
	 * Gets the from date.
	 *
	 * @return the from date
	 */
	public String getFromDate() {
		return fromDate;
	}

	/**
	 * Sets the from date.
	 *
	 * @param fromDate the new from date
	 */
	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}

	/**
	 * Gets the to date.
	 *
	 * @return the to date
	 */
	public String getToDate() {
		return toDate;
	}

	/**
	 * Sets the to date.
	 *
	 * @param toDate the new to date
	 */
	public void setToDate(String toDate) {
		this.toDate = toDate;
	}

	/**
	 * Gets the update status VO.
	 *
	 * @return the update status VO
	 */
	public UpgradeStatusVO getUpdateStatusVO() {
		return updateStatusVO;
	}

	/**
	 * Sets the update status VO.
	 *
	 * @param updateStatusVO the new update status VO
	 */
	public void setUpdateStatusVO(UpgradeStatusVO updateStatusVO) {
		this.updateStatusVO = updateStatusVO;
	}

	/**
	 * Gets the software version VO.
	 *
	 * @return the software version VO
	 */
	public ProductVO getSoftwareVersionVO() {
		return softwareVersionVO;
	}

	/**
	 * Sets the software version VO.
	 *
	 * @param softwareVersionVO the new software version VO
	 */
	public void setSoftwareVersionVO(ProductVO softwareVersionVO) {
		this.softwareVersionVO = softwareVersionVO;
	}

	/**
	 * Gets the country VO.
	 *
	 * @return the country VO
	 */
	public CountryVO getCountryVO() {
		return countryVO;
	}

	/**
	 * Sets the country VO.
	 *
	 * @param countryVO the new country VO
	 */
	public void setCountryVO(CountryVO countryVO) {
		this.countryVO = countryVO;
	}

	/**
	 * Gets the folder path.
	 *
	 * @return the folder path
	 */
	public String getFolderPath() {
		return folderPath;
	}

	/**
	 * Sets the folder path.
	 *
	 * @param folderPath the new folder path
	 */
	public void setFolderPath(String folderPath) {
		this.folderPath = folderPath;
	}

	/**
	 * Gets the upgrade type VO.
	 *
	 * @return the upgrade type VO
	 */
	public UpgradeTypeVO getUpgradeTypeVO() {
		return upgradeTypeVO;
	}

	/**
	 * Sets the upgrade type VO.
	 *
	 * @param upgradeTypeVO the new upgrade type VO
	 */
	public void setUpgradeTypeVO(UpgradeTypeVO upgradeTypeVO) {
		this.upgradeTypeVO = upgradeTypeVO;
	}

	/**
	 * Gets the user role ids.
	 *
	 * @return the user role ids
	 */
	public Set<Integer> getUserRoleIds() {
		return userRoleIds;
	}

	/**
	 * Sets the user role ids.
	 *
	 * @param userRoleIds the new user role ids
	 */
	public void setUserRoleIds(Set<Integer> userRoleIds) {
		this.userRoleIds = userRoleIds;
	}

}
