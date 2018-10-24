package com.jnj.jws.util;

import java.io.Serializable;
import java.util.Comparator;

import com.jnj.jws.vo.UsbAssetVO;

/**
 * The Class AssetComparator.
 */
public class AssetComparator implements Comparator<UsbAssetVO>, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 */
	@Override
	public int compare(UsbAssetVO o1, UsbAssetVO o2) {
		if (Integer.parseInt(o1.getAssetSerialNumber()) > Integer.parseInt(o2
				.getAssetSerialNumber())) {
			return 1;
		}
		else {
			return -1;
		}
	}

}
