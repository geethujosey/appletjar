/*
 * 
 */
package com.jnj.jws.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Copy to usb- meta file validation response vo.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class MetaInfoValidationRespVO {

	/** The check sum. */
	private boolean checkSum;

	/** The downloaded user. */
	private boolean downloadedUser;

	/** The software version. */
	private boolean softwareVersion;

	/**
	 * Checks if is check sum.
	 *
	 * @return true, if is check sum
	 */
	public boolean isCheckSum() {
		return checkSum;
	}

	/**
	 * Sets the check sum.
	 *
	 * @param checkSum the new check sum
	 */
	public void setCheckSum(boolean checkSum) {
		this.checkSum = checkSum;
	}

	/**
	 * Checks if is downloaded user.
	 *
	 * @return true, if is downloaded user
	 */
	public boolean isDownloadedUser() {
		return downloadedUser;
	}

	/**
	 * Sets the downloaded user.
	 *
	 * @param downloadedUser the new downloaded user
	 */
	public void setDownloadedUser(boolean downloadedUser) {
		this.downloadedUser = downloadedUser;
	}

	/**
	 * Checks if is software version.
	 *
	 * @return true, if is software version
	 */
	public boolean isSoftwareVersion() {
		return softwareVersion;
	}

	/**
	 * Sets the software version.
	 *
	 * @param softwareVersion the new software version
	 */
	public void setSoftwareVersion(boolean softwareVersion) {
		this.softwareVersion = softwareVersion;
	}
}
