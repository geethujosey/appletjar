/*
 * 
 */
package com.jnj.jws.vo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The Class ReasonForRemovalVo.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ReasonForRemovalVo {

	/** The asset serial nmbrs. */
	private List<String> assetSerialNmbrs;

	/** The reason. */
	@JsonProperty("reasons")
	private ReasonVO reason;

	/**
	 * Gets the asset serial nmbrs.
	 *
	 * @return the asset serial nmbrs
	 */
	public List<String> getAssetSerialNmbrs() {
		return assetSerialNmbrs;
	}

	/**
	 * Sets the asset serial nmbrs.
	 *
	 * @param assetSerialNmbrs the new asset serial nmbrs
	 */
	public void setAssetSerialNmbrs(List<String> assetSerialNmbrs) {
		this.assetSerialNmbrs = assetSerialNmbrs;
	}

	/**
	 * Gets the reason.
	 *
	 * @return the reason
	 */
	public ReasonVO getReason() {
		return reason;
	}

	/**
	 * Sets the reason.
	 *
	 * @param reason the new reason
	 */
	public void setReason(ReasonVO reason) {
		this.reason = reason;
	}

}
