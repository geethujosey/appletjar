/*
 * 
 */
package com.jnj.jws.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * The Class FileUploadRespVo.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class FileUploadRespVo {

	/** The asset number. */
	private String assetNumber;

	/** The upload status. */
	private boolean uploadStatus = false;

	/** The event logfile id. */
	private String eventLogfileId;

	/** The message. */
	private String message;

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
	 * Gets the event logfile id.
	 *
	 * @return the event logfile id
	 */
	public String getEventLogfileId() {
		return eventLogfileId;
	}

	/**
	 * Sets the event logfile id.
	 *
	 * @param eventLogfileId the new event logfile id
	 */
	public void setEventLogfileId(String eventLogfileId) {
		this.eventLogfileId = eventLogfileId;
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
	 * Checks if is upload status.
	 *
	 * @return true, if is upload status
	 */
	public boolean isUploadStatus() {
		return uploadStatus;
	}

	/**
	 * Sets the upload status.
	 *
	 * @param uploadStatus the new upload status
	 */
	public void setUploadStatus(boolean uploadStatus) {
		this.uploadStatus = uploadStatus;
	}

}
