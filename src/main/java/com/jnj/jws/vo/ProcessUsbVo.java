/*
 * 
 */
package com.jnj.jws.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * response vo for asset reconciliation service- process usb.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProcessUsbVo {

	/** The name. */
	private String name;

	/** The pre update files reconcile with system. */
	// private List<AssetSummaryVO> preUpdateFilesReconcileWithSystem= new
	// ArrayList<AssetSummaryVO>()
	private AssetSummaryVO preUpdateFilesReconcileWithSystem = new AssetSummaryVO();

	/** The pre update files not reconcile with system. */
	// private List<AssetSummaryVO> preUpdateFilesNotReconcileWithSystem= new
	// ArrayList<AssetSummaryVO>()
	private AssetSummaryVO preUpdateFilesNotReconcileWithSystem = new AssetSummaryVO();

	/** The post update files. */
	// private List<AssetSummaryVO> postUpdateFiles= new ArrayList<AssetSummaryVO>()
	private AssetSummaryVO postUpdateFiles = new AssetSummaryVO();

	/** The reconciliation warning. */
	private String reconciliationWarning = "";

	/** The usb error vo. */
	@JsonProperty("error")
	private UsbErrorVo usbErrorVo;

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the pre update files reconcile with system.
	 *
	 * @return the pre update files reconcile with system
	 */
	public AssetSummaryVO getPreUpdateFilesReconcileWithSystem() {
		return preUpdateFilesReconcileWithSystem;
	}

	/**
	 * Sets the pre update files reconcile with system.
	 *
	 * @param preUpdateFilesReconcileWithSystem the new pre update files reconcile with
	 * system
	 */
	public void setPreUpdateFilesReconcileWithSystem(
			AssetSummaryVO preUpdateFilesReconcileWithSystem) {
		this.preUpdateFilesReconcileWithSystem = preUpdateFilesReconcileWithSystem;
	}

	/**
	 * Gets the pre update files not reconcile with system.
	 *
	 * @return the pre update files not reconcile with system
	 */
	public AssetSummaryVO getPreUpdateFilesNotReconcileWithSystem() {
		return preUpdateFilesNotReconcileWithSystem;
	}

	/**
	 * Sets the pre update files not reconcile with system.
	 *
	 * @param preUpdateFilesNotReconcileWithSystem the new pre update files not reconcile
	 * with system
	 */
	public void setPreUpdateFilesNotReconcileWithSystem(
			AssetSummaryVO preUpdateFilesNotReconcileWithSystem) {
		this.preUpdateFilesNotReconcileWithSystem = preUpdateFilesNotReconcileWithSystem;
	}

	/**
	 * Gets the post update files.
	 *
	 * @return the post update files
	 */
	public AssetSummaryVO getPostUpdateFiles() {
		return postUpdateFiles;
	}

	/**
	 * Sets the post update files.
	 *
	 * @param postUpdateFiles the new post update files
	 */
	public void setPostUpdateFiles(AssetSummaryVO postUpdateFiles) {
		this.postUpdateFiles = postUpdateFiles;
	}

	/**
	 * Gets the reconciliation warning.
	 *
	 * @return the reconciliation warning
	 */
	public String getReconciliationWarning() {
		return reconciliationWarning;
	}

	/**
	 * Sets the reconciliation warning.
	 *
	 * @param reconciliationWarning the new reconciliation warning
	 */
	public void setReconciliationWarning(String reconciliationWarning) {
		this.reconciliationWarning = reconciliationWarning;
	}

	/**
	 * Gets the usb error vo.
	 *
	 * @return the usb error vo
	 */
	@JsonProperty("error")
	public UsbErrorVo getUsbErrorVo() {
		return usbErrorVo;
	}

	/**
	 * Sets the usb error vo.
	 *
	 * @param usbErrorVo the new usb error vo
	 */
	@JsonProperty("error")
	public void setUsbErrorVo(UsbErrorVo usbErrorVo) {
		this.usbErrorVo = usbErrorVo;
	}

}
