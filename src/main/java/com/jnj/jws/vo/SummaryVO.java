/*
 * 
 */

package com.jnj.jws.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * The Class SummaryVO.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class SummaryVO {

	/** The column sort order. */
	@JsonInclude(Include.NON_NULL)
	private String columnSortOrder;

	/** The column sort name. */
	@JsonInclude(Include.NON_NULL)
	private String columnSortName;

	/** The data. */
	private Object data;

	/**
	 * Gets the column sort order.
	 *
	 * @return the columnSortOrder
	 */
	public String getColumnSortOrder() {
		return columnSortOrder;
	}

	/**
	 * Sets the column sort order.
	 *
	 * @param columnSortOrder the columnSortOrder to set
	 */
	public void setColumnSortOrder(String columnSortOrder) {
		this.columnSortOrder = columnSortOrder;
	}

	/**
	 * Gets the column sort name.
	 *
	 * @return the columnSortName
	 */
	public String getColumnSortName() {
		return columnSortName;
	}

	/**
	 * Sets the column sort name.
	 *
	 * @param columnSortName the columnSortName to set
	 */
	public void setColumnSortName(String columnSortName) {
		this.columnSortName = columnSortName;
	}

	/**
	 * Gets the data.
	 *
	 * @return the data
	 */
	public Object getData() {
		return data;
	}

	/**
	 * Sets the data.
	 *
	 * @param data the data to set
	 */
	public void setData(Object data) {
		this.data = data;
	}

}
