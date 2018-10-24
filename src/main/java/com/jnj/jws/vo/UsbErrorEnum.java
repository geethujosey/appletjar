/*
 * 
 */
package com.jnj.jws.vo;

/**
 * The Enum UsbErrorEnum.
 */
public enum UsbErrorEnum {

	/** The code 421. */
	CODE_421("CODE 421", "No Usb found"),
	/** The code 422. */
	CODE_422("CODE 422", "No Space Available"),
	/** The code 423. */
	CODE_423("CODE 423", "Usb got Unplugged"),
	/** The code 424. */
	CODE_424("CODE 424", "Unwanted Files"),
	/** The code 425. */
	CODE_425("CODE 425", "USB - File System Error"),
	/** The code 427. */
	CODE_427("CODE 427", "Unknown Error");

	/** The type. */
	private String type;

	/** The value. */
	private String value;

	/**
	 * Instantiates a new usb error enum.
	 *
	 * @param type the type
	 * @param value the value
	 */
	private UsbErrorEnum(String type, String value) {
		this.type = type;
		this.value = value;
	}

	/**
	 * Gets the type.
	 *
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * Sets the type.
	 *
	 * @param type the new type
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * Gets the value.
	 *
	 * @return the value
	 */
	public String getValue() {
		return value;
	}

	/**
	 * Sets the value.
	 *
	 * @param value the new value
	 */
	public void setValue(String value) {
		this.value = value;
	}

}
