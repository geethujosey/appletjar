/*
 * 
 */
package com.jnj.jws.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * The Class ErrorVo.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ErrorVo {

	/** The error. */
	private UsbErrorVo error;

	/**
	 * Gets the error.
	 *
	 * @return the error
	 */
	public UsbErrorVo getError() {
		return error;
	}

	/**
	 * Sets the error.
	 *
	 * @param error the new error
	 */
	public void setError(UsbErrorVo error) {
		this.error = error;
	}

}
