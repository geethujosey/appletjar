/*
 * 
 */
package com.jnj.jws.vo;

/**
 * The Enum FileUploadStatusEnum.
 */
public enum FileUploadStatusEnum {

	/** The in progress. */
	IN_PROGRESS("4", "In Progress"),
	/** The success. */
	SUCCESS("3", "Success"),
	/** The failed. */
	FAILED("2", "Failure"),
	/** The in complete. */
	IN_COMPLETE("1", "Incomplete"),
	/** The uptodate. */
	UPTODATE("0", "Up To Date"),
	/** The not attempted. */
	NOT_ATTEMPTED("5", "Not Attempted");

	/** The status code. */
	private String statusCode;

	/** The status val. */
	private String statusVal;

	/**
	 * Instantiates a new file upload status enum.
	 *
	 * @param statusCode the status code
	 * @param statusVal the status val
	 */
	FileUploadStatusEnum(String statusCode, String statusVal) {
		this.statusCode = statusCode;
		this.statusVal = statusVal;
	}

	/**
	 * Gets the status code.
	 *
	 * @return the status code
	 */
	public String getStatusCode() {
		return statusCode;
	}

	/**
	 * Sets the status code.
	 *
	 * @param statusCode the new status code
	 */
	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	/**
	 * Gets the status val.
	 *
	 * @return the status val
	 */
	public String getStatusVal() {
		return statusVal;
	}

	/**
	 * Sets the status val.
	 *
	 * @param statusVal the new status val
	 */
	public void setStatusVal(String statusVal) {
		this.statusVal = statusVal;
	}

}
