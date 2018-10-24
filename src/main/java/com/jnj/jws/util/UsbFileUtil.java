/*
 * 
 */
package com.jnj.jws.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.filechooser.FileSystemView;

/**
 * Util class to detect USB and read write delete files in USB.
 * @author Cognizant
 * @version 1.0
 * @serial 1.0
 * @since 1.0
 */
public class UsbFileUtil {

	/** The jwslogger. */
	private static JavaWebStartLogger jwslogger = new JavaWebStartLogger();

	/**
	 * Instantiates a new usb file util.
	 */
	private UsbFileUtil() {

	}

	/**
	 * Method to detect USB.
	 *
	 * @return the file
	 */
	public static File detectUSB() {
		jwslogger.printLogger(UsbFileUtil.class.getSimpleName(), "",
				"detectUSB:inside detectUSB");
		FileSystemView fsv = FileSystemView.getFileSystemView();
		File[] f = File.listRoots();
		for (int i = 0; i < f.length; i++) {
			String displayName = fsv.getSystemDisplayName(f[i]);
			jwslogger.printLogger(UsbFileUtil.class.getSimpleName(), "",
					"detectUSB:displayName=" + displayName);
			String fileType = fsv.getSystemTypeDescription(f[i]);
			jwslogger.printLogger(
					UsbFileUtil.class.getSimpleName(),
					"detectUSB",
					"fileType=" + fileType + " isDrive=" + fsv.isDrive(f[i])
							+ " canRead= " + f[i].canRead() + "canWrite="
							+ f[i].canWrite());
			if (fsv.isDrive(f[i]) && f[i].canRead() && f[i].canWrite()
					&& isValidUsbDrive(fileType)) {
				// removable drive detected.
				jwslogger.printLogger(UsbFileUtil.class.getSimpleName(), "detectUSB",
						"removable drive detected");
				return f[i];
			}
		}
		return null;
	}

	/**
	 * Method to find files in USB.
	 *
	 * @param folder the folder
	 * @param assetSerNo the asset ser no
	 * @return the file
	 */

	public static List<File> findLogFilesInUsb(final File folder, String assetSerNo) {
		jwslogger.printLogger(
				UsbFileUtil.class.getSimpleName(),
				"",
				"inside findLogFilesInUsb:" + assetSerNo + "path="
						+ folder.getAbsolutePath());
		List<File> filesToUpload = new ArrayList<>();
		File[] fileEntries = folder.listFiles();
		if (fileEntries == null) {
			return filesToUpload;
		}
		for (final File fileEntry : fileEntries) {
			String fileName = fileEntry.getName();
			if (fileEntry.isDirectory()) {
				findLogFilesInUsb(fileEntry, assetSerNo);
			}
			else if (fileName.endsWith(".xml")) {
				String fileNameAssetNo = fileName.substring(0, fileName.lastIndexOf('.'));
				if (fileNameAssetNo.indexOf('_') != -1) {
					// if post update file contains unique id
					fileNameAssetNo = fileName.substring(0, fileName.indexOf('_'));
				}
				jwslogger.printLogger(UsbFileUtil.class.getSimpleName(), "",
						"findLogFilesInUsb:fileNameAssetNo= " + fileNameAssetNo);
				if (assetSerNo.equals(fileNameAssetNo)) {
					jwslogger.printLogger(UsbFileUtil.class.getSimpleName(),
							"findLogFilesInUsb", "File= " + folder.getAbsolutePath()
									+ "\\" + fileEntry.getName());
					File postUpdateFile = new File(folder.getAbsolutePath()
							+ fileEntry.getName());
					jwslogger.printLogger(UsbFileUtil.class.getSimpleName(),
							"findLogFilesInUsb", "file found for upload: "
									+ postUpdateFile.getName());
					filesToUpload.add(postUpdateFile);
				}
			}
		}

		return filesToUpload;
	}

	/**
	 * return true if connected USB drive is a valid one
	 * @param usbDriveType
	 */
	public static boolean isValidUsbDrive(String usbDriveType) {
		return usbDriveType.toLowerCase().contains("removable")
				|| usbDriveType.toLowerCase().contains("usb drive");
	}
}
