/*
 * 
 */
package com.jnj.jws.vo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * The Class UsbFileVo.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class UsbFileVo {

	/** The other wwid. */
	private String otherWwid;

	/** The usb file names. */
	private List<String> usbFileNames;

	/** The existng asset ser num lst. */
	private List<String> existngAssetSerNumLst;

	/** The unwanted file names. */
	private List<String> unwantedFileNames;

	/** The others usb. */
	private boolean othersUsb;

	/** The is stanlel exists. */
	private boolean isStanlelExists;

	/** The is meta file exists. */
	private boolean isMetaFileExists;

	/** The contains xml file. */
	private boolean containsXmlFile;

	/** The contains asset key file. */
	private boolean containsAssetKeyFile;

	/**
	 * Gets the other wwid.
	 *
	 * @return the other wwid
	 */
	public String getOtherWwid() {
		return otherWwid;
	}

	/**
	 * Sets the other wwid.
	 *
	 * @param otherWwid the new other wwid
	 */
	public void setOtherWwid(String otherWwid) {
		this.otherWwid = otherWwid;
	}

	/**
	 * Gets the usb file names.
	 *
	 * @return the usb file names
	 */
	public List<String> getUsbFileNames() {
		return usbFileNames;
	}

	/**
	 * Sets the usb file names.
	 *
	 * @param usbFileNames the new usb file names
	 */
	public void setUsbFileNames(List<String> usbFileNames) {
		this.usbFileNames = usbFileNames;
	}

	/**
	 * Checks if is others usb.
	 *
	 * @return true, if is others usb
	 */
	public boolean isOthersUsb() {
		return othersUsb;
	}

	/**
	 * Sets the others usb.
	 *
	 * @param othersUsb the new others usb
	 */
	public void setOthersUsb(boolean othersUsb) {
		this.othersUsb = othersUsb;
	}

	/**
	 * Checks if is stanlel exists.
	 *
	 * @return true, if is stanlel exists
	 */
	public boolean isStanlelExists() {
		return isStanlelExists;
	}

	/**
	 * Sets the stanlel exists.
	 *
	 * @param isStanlelExists the new stanlel exists
	 */
	public void setStanlelExists(boolean isStanlelExists) {
		this.isStanlelExists = isStanlelExists;
	}

	/**
	 * Checks if is meta file exists.
	 *
	 * @return true, if is meta file exists
	 */
	public boolean isMetaFileExists() {
		return isMetaFileExists;
	}

	/**
	 * Sets the meta file exists.
	 *
	 * @param isMetaFileExists the new meta file exists
	 */
	public void setMetaFileExists(boolean isMetaFileExists) {
		this.isMetaFileExists = isMetaFileExists;
	}

	/**
	 * Gets the unwanted file names.
	 *
	 * @return the unwanted file names
	 */
	public List<String> getUnwantedFileNames() {
		return unwantedFileNames;
	}

	/**
	 * Sets the unwanted file names.
	 *
	 * @param unwantedFileNames the new unwanted file names
	 */
	public void setUnwantedFileNames(List<String> unwantedFileNames) {
		this.unwantedFileNames = unwantedFileNames;
	}

	/**
	 * Checks if is contains xml file.
	 *
	 * @return true, if is contains xml file
	 */
	public boolean isContainsXmlFile() {
		return containsXmlFile;
	}

	/**
	 * Sets the contains xml file.
	 *
	 * @param containsXmlFile the new contains xml file
	 */
	public void setContainsXmlFile(boolean containsXmlFile) {
		this.containsXmlFile = containsXmlFile;
	}

	/**
	 * Checks if is contains asset key file.
	 *
	 * @return true, if is contains asset key file
	 */
	public boolean isContainsAssetKeyFile() {
		return containsAssetKeyFile;
	}

	/**
	 * Sets the contains asset key file.
	 *
	 * @param containsAssetKeyFile the new contains asset key file
	 */
	public void setContainsAssetKeyFile(boolean containsAssetKeyFile) {
		this.containsAssetKeyFile = containsAssetKeyFile;
	}

	/**
	 * Gets the existng asset ser num lst.
	 *
	 * @return the existng asset ser num lst
	 */
	public List<String> getExistngAssetSerNumLst() {
		return existngAssetSerNumLst;
	}

	/**
	 * Sets the existng asset ser num lst.
	 *
	 * @param existngAssetSerNumLst the new existng asset ser num lst
	 */
	public void setExistngAssetSerNumLst(List<String> existngAssetSerNumLst) {
		this.existngAssetSerNumLst = existngAssetSerNumLst;
	}

}
