/*
 * 
 */
package com.jnj.jws.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Copy to usb- meta file validation vo.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class MetaInfoValidationRequestVO {

	/** The file content. */
	private String fileContent;

	/** The check sum. */
	private String checkSum;

	/** The software version. */
	private String softwareVersion;

	/**
	 * Gets the check sum.
	 *
	 * @return the check sum
	 */
	public String getCheckSum() {
		return checkSum;
	}

	/**
	 * Sets the check sum.
	 *
	 * @param checkSum the new check sum
	 */
	public void setCheckSum(String checkSum) {
		this.checkSum = checkSum;
	}

	/**
	 * Gets the software version.
	 *
	 * @return the software version
	 */
	public String getSoftwareVersion() {
		return softwareVersion;
	}

	/**
	 * Sets the software version.
	 *
	 * @param softwareVersion the new software version
	 */
	public void setSoftwareVersion(String softwareVersion) {
		this.softwareVersion = softwareVersion;
	}

	/**
	 * Gets the file content.
	 *
	 * @return the file content
	 */
	public String getFileContent() {
		return fileContent;
	}

	/**
	 * Sets the file content.
	 *
	 * @param fileContent the new file content
	 */
	public void setFileContent(String fileContent) {
		this.fileContent = fileContent;
	}

}
