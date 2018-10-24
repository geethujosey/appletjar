/*
 * 
 */
package com.jnj.jws.vo;

import java.util.List;

/**
 * The Class EventLogDownloadResVO.
 */
public class EventLogDownloadResVO {

	/** The success VO list. */
	private List<EventDownloadSuccessListVO> successVOList;

	/** The failure VO list. */
	private List<EventDownloadFailureListVO> failureVOList;

	/**
	 * Gets the success VO list.
	 *
	 * @return the success VO list
	 */
	public List<EventDownloadSuccessListVO> getSuccessVOList() {
		return successVOList;
	}

	/**
	 * Sets the success VO list.
	 *
	 * @param successVOList the new success VO list
	 */
	public void setSuccessVOList(List<EventDownloadSuccessListVO> successVOList) {
		this.successVOList = successVOList;
	}

	/**
	 * Gets the failure VO list.
	 *
	 * @return the failure VO list
	 */
	public List<EventDownloadFailureListVO> getFailureVOList() {
		return failureVOList;
	}

	/**
	 * Sets the failure VO list.
	 *
	 * @param failureVOList the new failure VO list
	 */
	public void setFailureVOList(List<EventDownloadFailureListVO> failureVOList) {
		this.failureVOList = failureVOList;
	}

}
