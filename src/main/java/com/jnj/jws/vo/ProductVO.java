/*
 * 
 */
package com.jnj.jws.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * The Class ProductVO.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductVO {

	/** The siebel product code id. */
	private String siebelProductCodeId;

	/** The siebel product code name. */
	private String siebelProductCodeName;

	/**
	 * Gets the siebel product code id.
	 *
	 * @return the siebel product code id
	 */
	public String getSiebelProductCodeId() {
		return siebelProductCodeId;
	}

	/**
	 * Sets the siebel product code id.
	 *
	 * @param siebelProductCodeId the new siebel product code id
	 */
	public void setSiebelProductCodeId(String siebelProductCodeId) {
		this.siebelProductCodeId = siebelProductCodeId;
	}

	/**
	 * Gets the siebel product code name.
	 *
	 * @return the siebel product code name
	 */
	public String getSiebelProductCodeName() {
		return siebelProductCodeName;
	}

	/**
	 * Sets the siebel product code name.
	 *
	 * @param siebelProductCodeName the new siebel product code name
	 */
	public void setSiebelProductCodeName(String siebelProductCodeName) {
		this.siebelProductCodeName = siebelProductCodeName;
	}

}
