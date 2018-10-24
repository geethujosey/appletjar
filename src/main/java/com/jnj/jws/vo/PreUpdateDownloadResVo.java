/*
 * 
 */
package com.jnj.jws.vo;

/**
 * The Class PreUpdateDownloadResVo.
 */
public class PreUpdateDownloadResVo {

	/** The check sum str. */
	private String checkSumStr;

	/** The error string. */
	private String errorString;

	/** The error. */
	private boolean error;

	/**
	 * Gets the check sum str.
	 *
	 * @return the check sum str
	 */
	public String getCheckSumStr() {
		return checkSumStr;
	}

	/**
	 * Sets the check sum str.
	 *
	 * @param checkSumStr the new check sum str
	 */
	public void setCheckSumStr(String checkSumStr) {
		this.checkSumStr = checkSumStr;
	}

	/**
	 * Gets the error string.
	 *
	 * @return the error string
	 */
	public String getErrorString() {
		return errorString;
	}

	/**
	 * Sets the error string.
	 *
	 * @param errorString the new error string
	 */
	public void setErrorString(String errorString) {
		this.errorString = errorString;
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

}
