/*
 * 
 */
package com.jnj.jws.vo;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * PostUpdateFileVO class.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class PostUpdateFileVO extends CommonAssetVO {

	/** The updated version. */
	@JsonProperty("updatedVersion")
	private PackageVO updatedVersion;

	/** The account name. */
	@JsonProperty("accountName")
	private String accountName;

	/** The location address. */
	@JsonProperty("locationAddress")
	private AssetLocation locationAddress;

	/** The update status. */
	@JsonProperty("updateStatus")
	private UpgradeStatusVO updateStatus;

	/** The completed on. */
	@JsonProperty("completedOn")
	private Date completedOn;

	/** The reason failure. */
	@JsonProperty("reasonFailure")
	private ReasonVO reasonFailure;

	/** The reason manual update. */
	@JsonProperty("reasonManualUpdate")
	private ReasonVO reasonManualUpdate;

	/** The handed over. */
	@JsonProperty("handedOver")
	private Boolean handedOver;

	/** The comments. */
	@JsonProperty("comments")
	private String comments;

	/** The processed by. */
	@JsonIgnore
	private String processedBy;

	/** The is process usb. */
	private boolean isProcessUsb;

	/** The other user wwid. */
	private String otherUserWwid;

	/** The file name. */
	// post update temp file name
	private String fileName;

	/** The manual file. */
	private boolean manualFile;

	/**
	 * Checks if is process usb.
	 *
	 * @return true, if is process usb
	 */
	public boolean isProcessUsb() {
		return isProcessUsb;
	}

	/**
	 * Sets the process usb.
	 *
	 * @param isProcessUsb the new process usb
	 */
	public void setProcessUsb(boolean isProcessUsb) {
		this.isProcessUsb = isProcessUsb;
	}

	/**
	 * Gets the updated version.
	 *
	 * @return the updated version
	 */
	public PackageVO getUpdatedVersion() {
		return updatedVersion;
	}

	/**
	 * Sets the updated version.
	 *
	 * @param updatedVersion the new updated version
	 */
	public void setUpdatedVersion(PackageVO updatedVersion) {
		this.updatedVersion = updatedVersion;
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
	 * Gets the reason failure.
	 *
	 * @return the reason failure
	 */
	public ReasonVO getReasonFailure() {
		return reasonFailure;
	}

	/**
	 * Sets the reason failure.
	 *
	 * @param reasonFailure the new reason failure
	 */
	public void setReasonFailure(ReasonVO reasonFailure) {
		this.reasonFailure = reasonFailure;
	}

	/**
	 * Gets the reason manual update.
	 *
	 * @return the reason manual update
	 */
	public ReasonVO getReasonManualUpdate() {
		return reasonManualUpdate;
	}

	/**
	 * Sets the reason manual update.
	 *
	 * @param reasonManualUpdate the new reason manual update
	 */
	public void setReasonManualUpdate(ReasonVO reasonManualUpdate) {
		this.reasonManualUpdate = reasonManualUpdate;
	}

	/**
	 * Gets the handed over.
	 *
	 * @return the handed over
	 */
	public Boolean getHandedOver() {
		return handedOver;
	}

	/**
	 * Sets the handed over.
	 *
	 * @param handedOver the new handed over
	 */
	public void setHandedOver(Boolean handedOver) {
		this.handedOver = handedOver;
	}

	/**
	 * Gets the comments.
	 *
	 * @return the comments
	 */
	public String getComments() {
		return comments;
	}

	/**
	 * Sets the comments.
	 *
	 * @param comments the new comments
	 */
	public void setComments(String comments) {
		this.comments = comments;
	}

	/**
	 * Gets the location address.
	 *
	 * @return the location address
	 */
	public AssetLocation getLocationAddress() {
		return locationAddress;
	}

	/**
	 * Sets the location address.
	 *
	 * @param locationAddress the new location address
	 */
	public void setLocationAddress(AssetLocation locationAddress) {
		this.locationAddress = locationAddress;
	}

	/**
	 * Gets the completed on.
	 *
	 * @return the completed on
	 */
	public Date getCompletedOn() {
		if (completedOn == null) {
			return null;
		}
		else {
			return new Date(completedOn.getTime());
		}
	}

	/**
	 * Sets the completed on.
	 *
	 * @param completedOn the new completed on
	 */
	public void setCompletedOn(Date completedOn) {
		this.completedOn = completedOn;
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
	 * Gets the processed by.
	 *
	 * @return the processed by
	 */
	public String getProcessedBy() {
		return processedBy;
	}

	/**
	 * Sets the processed by.
	 *
	 * @param processedBy the new processed by
	 */
	public void setProcessedBy(String processedBy) {
		this.processedBy = processedBy;
	}

	/**
	 * Gets the other user wwid.
	 *
	 * @return the other user wwid
	 */
	public String getOtherUserWwid() {
		return otherUserWwid;
	}

	/**
	 * Sets the other user wwid.
	 *
	 * @param otherUserWwid the new other user wwid
	 */
	public void setOtherUserWwid(String otherUserWwid) {
		this.otherUserWwid = otherUserWwid;
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

}
