/*
 * 
 */
package com.jnj.jws.vo;

import java.util.List;

import javafx.collections.ObservableList;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;

/**
 * VO class to share info between manage usb dialog and log file details screen.
 *
 * @author 555786
 */
public class ProcessUSBGenericVO {

	/** The jwt token. */
	private String jwtToken;

	/** The username. */
	private String username;

	/** The other wwid. */
	private String otherWwid;

	/** The other user name. */
	private String otherUserName;

	/** The user header VO. */
	private UserHeaderVO userHeaderVO;

	/** The preupdate not reconcile list. */
	private List<UsbAssetVO> preupdateNotReconcileList;

	/** The postupdate list. */
	private List<UsbAssetVO> postupdateList;

	/** The preupdate list. */
	private List<UsbAssetVO> preupdateList;

	/** The row. */
	private TableRow row;

	/** The tv. */
	private TableView tv;

	/** The datav. */
	private ObservableList<UsbAssetVO> datav;

	/** The usbasset tableview. */
	private TableView<UsbAssetVO> usbassetTableview;

	/** The selected record. */
	private UsbAssetVO selectedRecord;

	/** The usb error vo. */
	private UsbErrorVo usbErrorVo;

	/**
	 * Gets the jwt token.
	 *
	 * @return the jwt token
	 */
	public String getJwtToken() {
		return jwtToken;
	}

	/**
	 * Sets the jwt token.
	 *
	 * @param jwtToken the new jwt token
	 */
	public void setJwtToken(String jwtToken) {
		this.jwtToken = jwtToken;
	}

	/**
	 * Gets the username.
	 *
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * Sets the username.
	 *
	 * @param username the new username
	 */
	public void setUsername(String username) {
		this.username = username;
	}

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
	 * Gets the user header VO.
	 *
	 * @return the user header VO
	 */
	public UserHeaderVO getUserHeaderVO() {
		return userHeaderVO;
	}

	/**
	 * Sets the user header VO.
	 *
	 * @param userHeaderVO the new user header VO
	 */
	public void setUserHeaderVO(UserHeaderVO userHeaderVO) {
		this.userHeaderVO = userHeaderVO;
	}

	/**
	 * Gets the preupdate not reconcile list.
	 *
	 * @return the preupdate not reconcile list
	 */
	public List<UsbAssetVO> getPreupdateNotReconcileList() {
		return preupdateNotReconcileList;
	}

	/**
	 * Sets the preupdate not reconcile list.
	 *
	 * @param preupdateNotReconcileList the new preupdate not reconcile list
	 */
	public void setPreupdateNotReconcileList(List<UsbAssetVO> preupdateNotReconcileList) {
		this.preupdateNotReconcileList = preupdateNotReconcileList;
	}

	/**
	 * Gets the postupdate list.
	 *
	 * @return the postupdate list
	 */
	public List<UsbAssetVO> getPostupdateList() {
		return postupdateList;
	}

	/**
	 * Sets the postupdate list.
	 *
	 * @param postupdateList the new postupdate list
	 */
	public void setPostupdateList(List<UsbAssetVO> postupdateList) {
		this.postupdateList = postupdateList;
	}

	/**
	 * Gets the preupdate list.
	 *
	 * @return the preupdate list
	 */
	public List<UsbAssetVO> getPreupdateList() {
		return preupdateList;
	}

	/**
	 * Sets the preupdate list.
	 *
	 * @param preupdateList the new preupdate list
	 */
	public void setPreupdateList(List<UsbAssetVO> preupdateList) {
		this.preupdateList = preupdateList;
	}

	/**
	 * Gets the row.
	 *
	 * @return the row
	 */
	public TableRow getRow() {
		return row;
	}

	/**
	 * Sets the row.
	 *
	 * @param row the new row
	 */
	public void setRow(TableRow row) {
		this.row = row;
	}

	/**
	 * Gets the tv.
	 *
	 * @return the tv
	 */
	public TableView getTv() {
		return tv;
	}

	/**
	 * Sets the tv.
	 *
	 * @param tv the new tv
	 */
	public void setTv(TableView tv) {
		this.tv = tv;
	}

	/**
	 * Gets the datav.
	 *
	 * @return the datav
	 */
	public ObservableList<UsbAssetVO> getDatav() {
		return datav;
	}

	/**
	 * Sets the datav.
	 *
	 * @param datav the new datav
	 */
	public void setDatav(ObservableList<UsbAssetVO> datav) {
		this.datav = datav;
	}

	/**
	 * Gets the selected record.
	 *
	 * @return the selected record
	 */
	public UsbAssetVO getSelectedRecord() {
		return selectedRecord;
	}

	/**
	 * Sets the selected record.
	 *
	 * @param selectedRecord the new selected record
	 */
	public void setSelectedRecord(UsbAssetVO selectedRecord) {
		this.selectedRecord = selectedRecord;
	}

	/**
	 * Gets the usbasset tableview.
	 *
	 * @return the usbasset tableview
	 */
	public TableView<UsbAssetVO> getUsbassetTableview() {
		return usbassetTableview;
	}

	/**
	 * Sets the usbasset tableview.
	 *
	 * @param usbassetTableview the new usbasset tableview
	 */
	public void setUsbassetTableview(TableView<UsbAssetVO> usbassetTableview) {
		this.usbassetTableview = usbassetTableview;
	}

	/**
	 * Gets the other user name.
	 *
	 * @return the other user name
	 */
	public String getOtherUserName() {
		return otherUserName;
	}

	/**
	 * Sets the other user name.
	 *
	 * @param otherUserName the new other user name
	 */
	public void setOtherUserName(String otherUserName) {
		this.otherUserName = otherUserName;
	}

	/**
	 * Gets the usb error vo.
	 *
	 * @return the usb error vo
	 */
	public UsbErrorVo getUsbErrorVo() {
		return usbErrorVo;
	}

	/**
	 * Sets the usb error vo.
	 *
	 * @param usbErrorVo the new usb error vo
	 */
	public void setUsbErrorVo(UsbErrorVo usbErrorVo) {
		this.usbErrorVo = usbErrorVo;
	}

}
