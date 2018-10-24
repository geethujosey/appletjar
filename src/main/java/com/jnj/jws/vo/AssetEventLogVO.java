/*
 * 
 */
package com.jnj.jws.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * The Class AssetEventLogVO.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class AssetEventLogVO {

	/** The file ref id. */
	private String fileRefId;

	/** The folder path. */
	private String folderPath;

	/** The file source. */
	private String fileSource;

	/** The is error. */
	private boolean isError;

	/** The error message. */
	private String errorMessage;

	/**
	 * Gets the file ref id.
	 *
	 * @return the file ref id
	 */
	public String getFileRefId() {
		return fileRefId;
	}

	/**
	 * Sets the file ref id.
	 *
	 * @param fileRefId the new file ref id
	 */
	public void setFileRefId(String fileRefId) {
		this.fileRefId = fileRefId;
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
	 * Checks if is error.
	 *
	 * @return true, if is error
	 */
	public boolean isError() {
		return isError;
	}

	/**
	 * Sets the error.
	 *
	 * @param isError the new error
	 */
	public void setError(boolean isError) {
		this.isError = isError;
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
	 * Gets the file source.
	 *
	 * @return the file source
	 */
	public String getFileSource() {
		return fileSource;
	}

	/**
	 * Sets the file source.
	 *
	 * @param fileSource the new file source
	 */
	public void setFileSource(String fileSource) {
		this.fileSource = fileSource;
	}

}
