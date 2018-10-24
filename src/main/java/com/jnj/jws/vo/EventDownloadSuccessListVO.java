/*
 * 
 */
package com.jnj.jws.vo;

/**
 * The Class EventDownloadSuccessListVO.
 */
public class EventDownloadSuccessListVO {

	/** The file ref id. */
	private String fileRefId;

	/** The description. */
	private String description;

	/** total file count in zip */
	private String countOfTotalFilesInZip;

	/** total count of successfully downloaded files */
	private String countOfDownloadedFiles;

	/**
	 * Gets the description.
	 *
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Sets the description.
	 *
	 * @param description the new description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

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
	 * @return the countOfTotalFilesInZip
	 */
	public String getCountOfTotalFilesInZip() {
		return countOfTotalFilesInZip;
	}

	/**
	 * @param countOfTotalFilesInZip the countOfTotalFilesInZip to set
	 */
	public void setCountOfTotalFilesInZip(String countOfTotalFilesInZip) {
		this.countOfTotalFilesInZip = countOfTotalFilesInZip;
	}

	/**
	 * @return the countOfDownloadedFiles
	 */
	public String getCountOfDownloadedFiles() {
		return countOfDownloadedFiles;
	}

	/**
	 * @param countOfDownloadedFiles the countOfDownloadedFiles to set
	 */
	public void setCountOfDownloadedFiles(String countOfDownloadedFiles) {
		this.countOfDownloadedFiles = countOfDownloadedFiles;
	}

}
