/*
 * 
 */
package com.jnj.jws.vo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * The Class ProcessUSBContentVO.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProcessUSBContentVO {

	/** The postupdatefiles. */
	private List<UsbAssetVO> postupdatefiles;

	/** The preupdatefiles. */
	private List<UsbAssetVO> preupdatefiles;

	/** The preupdate notreconcilefile list. */
	private List<UsbAssetVO> preupdateNotreconcilefileList;

	/**
	 * Gets the postupdatefiles.
	 *
	 * @return the postupdatefiles
	 */
	public List<UsbAssetVO> getPostupdatefiles() {
		return postupdatefiles;
	}

	/**
	 * Sets the postupdatefiles.
	 *
	 * @param postupdatefiles the new postupdatefiles
	 */
	public void setPostupdatefiles(List<UsbAssetVO> postupdatefiles) {
		this.postupdatefiles = postupdatefiles;
	}

	/**
	 * Gets the preupdatefiles.
	 *
	 * @return the preupdatefiles
	 */
	public List<UsbAssetVO> getPreupdatefiles() {
		return preupdatefiles;
	}

	/**
	 * Sets the preupdatefiles.
	 *
	 * @param preupdatefiles the new preupdatefiles
	 */
	public void setPreupdatefiles(List<UsbAssetVO> preupdatefiles) {
		this.preupdatefiles = preupdatefiles;
	}

	/**
	 * Gets the preupdate notreconcilefile list.
	 *
	 * @return the preupdate notreconcilefile list
	 */
	public List<UsbAssetVO> getPreupdateNotreconcilefileList() {
		return preupdateNotreconcilefileList;
	}

	/**
	 * Sets the preupdate notreconcilefile list.
	 *
	 * @param preupdateNotreconcilefileList the new preupdate notreconcilefile list
	 */
	public void setPreupdateNotreconcilefileList(
			List<UsbAssetVO> preupdateNotreconcilefileList) {
		this.preupdateNotreconcilefileList = preupdateNotreconcilefileList;
	}

}
