/**
 * com.jnj.usb.vo
 * @author CTS
 * 
 */
package com.jnj.jws.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * vo class used in process USB.
 * @author CTS
 * @since 1.0
 * 
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class FileProcessResponseVo extends CommonAssetVO {
	/** The update status. */
	private UpgradeStatusVO updateStatus;
	/** The account name. */
	private String accountName;
	/** The location address. */
	// private Address locationAddress
	private AssetLocationVO locationAddress;
	/** The file name. */
	private String fileName;
	/** The error message. */
	private String errorMessage;
	/** The manual file. */
	private boolean manualFile;
	/** The corrupted log file. */
	private boolean corruptedLogFile;
	/** The warningMessage. */
	private String warningMessage;

	/**
	 * Checks if is corrupted log file.
	 *
	 * @return true, if is corrupted log file
	 */
	public boolean isCorruptedLogFile() {
		return corruptedLogFile;
	}

	/**
	 * Sets the corrupted log file.
	 *
	 * @param corruptedLogFile the new corrupted log file
	 */
	public void setCorruptedLogFile(boolean corruptedLogFile) {
		this.corruptedLogFile = corruptedLogFile;
	}

	/**
	 * Gets the update status.
	 *
	 * @return the update status
	 */
	public UpgradeStatusVO getUpdateStatus() {
		return updateStatus;
	}

	/**
	 * Sets the update status.
	 *
	 * @param updateStatus the new update status
	 */
	public void setUpdateStatus(UpgradeStatusVO updateStatus) {
		this.updateStatus = updateStatus;
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
	 * Gets the location address.
	 *
	 * @return the location address
	 */
	public AssetLocationVO getLocationAddress() {
		return locationAddress;
	}

	/**
	 * Sets the location address.
	 *
	 * @param locationAddress the new location address
	 */
	public void setLocationAddress(AssetLocationVO locationAddress) {
		this.locationAddress = locationAddress;
	}

	/**
	 * Gets the file name.
	 *
	 * @return the file name
	 */
	public String getFileName() {
		return fileName;
	}

	/**
	 * Sets the file name.
	 *
	 * @param fileName the new file name
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	/**
	 * Gets the error message.
	 *
	 * @return the error message
	 */
	public String getErrorMessage() {
		return errorMessage;
	}

	/**
	 * Sets the error message.
	 *
	 * @param errorMessage the new error message
	 */
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	/**
	 * Checks if is manual file.
	 *
	 * @return true, if is manual file
	 */
	public boolean isManualFile() {
		return manualFile;
	}

	/**
	 * Sets the manual file.
	 *
	 * @param manualFile the new manual file
	 */
	public void setManualFile(boolean manualFile) {
		this.manualFile = manualFile;
	}

	public String getWarningMessage() {
		return warningMessage;
	}

	public void setWarningMessage(String warningMessage) {
		this.warningMessage = warningMessage;
	}
}
