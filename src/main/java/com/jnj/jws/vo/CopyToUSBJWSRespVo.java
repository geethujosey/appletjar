/*
 * 
 */
package com.jnj.jws.vo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * The Class CopyToUSBJWSRespVo.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class CopyToUSBJWSRespVo {

	/** The asset ser num list. */
	private List<String> assetSerNumList;

	/** The usb ser num list. */
	private List<String> usbSerNumList;

	/** The available version. */
	private String availableVersion;

	/** The error. */
	private boolean error;

	/** The error message. */
	private String errorMessage;

	/** The warning. */
	private boolean warning;

	/** The warning message. */
	private String warningMessage;

	/** The title. */
	private String title;

	/** The to confirm. */
	private boolean toConfirm;

	/** The is post update file. */
	private boolean isPostUpdateFile;

	/**
	 * Checks if is warning.
	 *
	 * @return true, if is warning
	 */
	public boolean isWarning() {
		return warning;
	}

	/**
	 * Sets the warning.
	 *
	 * @param warning the new warning
	 */
	public void setWarning(boolean warning) {
		this.warning = warning;
	}

	/**
	 * Gets the warning message.
	 *
	 * @return the warning message
	 */
	public String getWarningMessage() {
		return warningMessage;
	}

	/**
	 * Sets the warning message.
	 *
	 * @param warningMessage the new warning message
	 */
	public void setWarningMessage(String warningMessage) {
		this.warningMessage = warningMessage;
	}

	/**
	 * Gets the asset ser num list.
	 *
	 * @return the asset ser num list
	 */
	public List<String> getAssetSerNumList() {
		return assetSerNumList;
	}

	/**
	 * Sets the asset ser num list.
	 *
	 * @param assetSerNumList the new asset ser num list
	 */
	public void setAssetSerNumList(List<String> assetSerNumList) {
		this.assetSerNumList = assetSerNumList;
	}

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
	 * Checks if is error.
	 *
	 * @return true, if is error
	 */
	public boolean isError() {
		return error;
	}

	/**
	 * Sets the error.
	 *
	 * @param error the new error
	 */
	public void setError(boolean error) {
		this.error = error;
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
	 * Gets the title.
	 *
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Sets the title.
	 *
	 * @param title the new title
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * Checks if is to confirm.
	 *
	 * @return true, if is to confirm
	 */
	public boolean isToConfirm() {
		return toConfirm;
	}

	/**
	 * Sets the to confirm.
	 *
	 * @param toConfirm the new to confirm
	 */
	public void setToConfirm(boolean toConfirm) {
		this.toConfirm = toConfirm;
	}

	/**
	 * Gets the usb ser num list.
	 *
	 * @return the usb ser num list
	 */
	public List<String> getUsbSerNumList() {
		return usbSerNumList;
	}

	/**
	 * Sets the usb ser num list.
	 *
	 * @param usbSerNumList the new usb ser num list
	 */
	public void setUsbSerNumList(List<String> usbSerNumList) {
		this.usbSerNumList = usbSerNumList;
	}

	/**
	 * Checks if is post update file.
	 *
	 * @return true, if is post update file
	 */
	public boolean isPostUpdateFile() {
		return isPostUpdateFile;
	}

	/**
	 * Sets the post update file.
	 *
	 * @param isPostUpdateFile the new post update file
	 */
	public void setPostUpdateFile(boolean isPostUpdateFile) {
		this.isPostUpdateFile = isPostUpdateFile;
	}

}
