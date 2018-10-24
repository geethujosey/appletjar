/*
 * 
 */
package com.jnj.jws.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * The Class DeleteFileRespVo.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class DeleteFileRespVo {

	/** The asset id. */
	private String assetId;

	/** The delete status. */
	private boolean deleteStatus;

	/**
	 * Gets the asset id.
	 *
	 * @return the asset id
	 */
	public String getAssetId() {
		return assetId;
	}

	/**
	 * Sets the asset id.
	 *
	 * @param assetId the new asset id
	 */
	public void setAssetId(String assetId) {
		this.assetId = assetId;
	}

	/**
	 * Checks if is delete status.
	 *
	 * @return true, if is delete status
	 */
	public boolean isDeleteStatus() {
		return deleteStatus;
	}

	/**
	 * Sets the delete status.
	 *
	 * @param deleteStatus the new delete status
	 */
	public void setDeleteStatus(boolean deleteStatus) {
		this.deleteStatus = deleteStatus;
	}

}
