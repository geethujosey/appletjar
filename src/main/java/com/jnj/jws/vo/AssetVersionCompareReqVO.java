/*
 * 
 */
package com.jnj.jws.vo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * The Class AssetVersionCompareReqVO.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class AssetVersionCompareReqVO {

	/** The asset serial number list. */
	private List<String> assetSerialNumberList;

	/** The usb asset ser num list. */
	private List<String> usbAssetSerNumList;

	/**
	 * Gets the asset serial number list.
	 *
	 * @return the asset serial number list
	 */
	public List<String> getAssetSerialNumberList() {
		return assetSerialNumberList;
	}

	/**
	 * Sets the asset serial number list.
	 *
	 * @param assetSerialNumberList the new asset serial number list
	 */
	public void setAssetSerialNumberList(List<String> assetSerialNumberList) {
		this.assetSerialNumberList = assetSerialNumberList;
	}

	/**
	 * Gets the usb asset ser num list.
	 *
	 * @return the usb asset ser num list
	 */
	public List<String> getUsbAssetSerNumList() {
		return usbAssetSerNumList;
	}

	/**
	 * Sets the usb asset ser num list.
	 *
	 * @param usbAssetSerNumList the new usb asset ser num list
	 */
	public void setUsbAssetSerNumList(List<String> usbAssetSerNumList) {
		this.usbAssetSerNumList = usbAssetSerNumList;
	}

}
