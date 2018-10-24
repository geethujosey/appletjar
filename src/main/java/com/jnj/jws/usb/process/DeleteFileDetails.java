/*
 * Process package
 * @author Cognizant
 * version 1.0
 * @since 1.0
 * 
 */
package com.jnj.jws.usb.process;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.security.AccessController;
import java.security.PrivilegedActionException;
import java.security.PrivilegedExceptionAction;

import org.apache.commons.io.FileUtils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jnj.jws.usb.process.javafx.DownloadEventLogDialog;
import com.jnj.jws.util.JavaWebStartLogger;
import com.jnj.jws.util.UsbFileUtil;
import com.jnj.jws.vo.DeleteFileRespVo;

/**
 * Java class to delete the files from USB.
 */

public class DeleteFileDetails {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The json. */
	private String json = "";

	/** The jwslogger. */
	private JavaWebStartLogger jwslogger = new JavaWebStartLogger();

	/**
	 * Method to delete files from USB.
	 *
	 * @param assetSerialNo the asset serial no
	 * @param isRemovePostUpdtFile the is remove post updt file
	 * @param isAllBinFiles the is all bin files
	 * @return the string
	 */

	@SuppressWarnings("unchecked")
	public String deleteUSBFile(final String assetSerialNo,
			final boolean isRemovePostUpdtFile, final boolean isAllBinFiles) {
		try {
			jwslogger.printLogger(DownloadEventLogDialog.class.getSimpleName(), "",
					"deleteUSBFile:isRemovePostUpdtFile=" + isRemovePostUpdtFile
							+ "isAllBinFiles= " + isAllBinFiles + "assetSerialNo:"
							+ assetSerialNo);
			AccessController
					.doPrivileged((PrivilegedExceptionAction<String>) () -> findFilesToDelete(
							assetSerialNo, isRemovePostUpdtFile, isAllBinFiles));

		}
		catch (PrivilegedActionException e) {
			jwslogger.printLogger(DownloadEventLogDialog.class.getSimpleName(), "",
					"deleteUSBFile:PrivilegedActionException:" + e);
		}

		jwslogger.printLogger(DownloadEventLogDialog.class.getSimpleName(),
				"deleteUSBFile", "Json:" + json);

		return json;
	}

	/**
	 * Find files to delete.
	 *
	 * @param assetSerialNo the asset serial no
	 * @param isRemovePostUpdtFile the is remove post updt file
	 * @param isAllBinFiles the is all bin files
	 * @return the string
	 */
	String findFilesToDelete(String assetSerialNo, boolean isRemovePostUpdtFile,
			boolean isAllBinFiles) {
		DeleteFileRespVo respVo = new DeleteFileRespVo();
		try {

			json = "";
			/**
			 * Setting Asset Serial Number
			 */
			respVo.setAssetId(assetSerialNo);
			/**
			 * Setting Delete Status
			 */
			respVo.setDeleteStatus(false);

			/**
			 * To Detect the Usb
			 */
			File usbDrivePath = UsbFileUtil.detectUSB();
			jwslogger.printLogger(DeleteFileDetails.class.getSimpleName(), "",
					"findFilesToDelete:usbDrivePath:" + usbDrivePath);
			File[] filesToDelete = null;
			/**
			 * If usbDrivePath is not null
			 */
			if (usbDrivePath != null) {
				if (isAllBinFiles) {
					jwslogger.printLogger(DeleteFileDetails.class.getSimpleName(), "",
							"findFilesToDelete:To remove all .bin files..");
					filesToDelete = findBinFilesToBeDeletedInUsb(usbDrivePath);
				}
				else {
					filesToDelete = findFilesToBeDeletedInUsb(usbDrivePath,
							assetSerialNo, isRemovePostUpdtFile);
				}
			}
			if (filesToDelete != null) {
				invokeDeleteFile(filesToDelete, respVo);
			}
		}
		catch (Exception e) {
			jwslogger.printLogger(DeleteFileDetails.class.getSimpleName(), "",
					"findFilesToDelete:Exception while deleting file form usb :" + e);
			Gson gson = new Gson();
			Type objType = new TypeToken<DeleteFileRespVo>() {
			}.getType();
			respVo.setDeleteStatus(false);
			json = gson.toJson(respVo, objType);
		}
		return json;

	}

	/**
	 * Invoke delete file.
	 *
	 * @param filesToDelete the files to delete
	 * @param respVo the resp vo
	 * @return true, if successful
	 */
	private boolean invokeDeleteFile(File[] filesToDelete, DeleteFileRespVo respVo) {
		boolean isDeleted = false;
		for (File file : filesToDelete) {
			if (file != null && file.exists()) {
				isDeleted = deleteFile(file);
			}
		}
		if (isDeleted) {
			respVo.setDeleteStatus(true);
			jwslogger.printLogger(DeleteFileDetails.class.getSimpleName(), "",
					"findFilesToDelete:isDeleted:" + isDeleted);
		}
		/**
		 * Gson conversion
		 */
		Gson gson = new Gson();
		Type objType = new TypeToken<DeleteFileRespVo>() {
		}.getType();
		/**
		 * Converting to json Using Gson
		 */
		json = gson.toJson(respVo, objType);
		return isDeleted;
	}

	/**
	 * Delete file.
	 *
	 * @param file the file
	 * @return true, if successful
	 */
	private boolean deleteFile(File file) {
		jwslogger.printLogger(DeleteFileDetails.class.getSimpleName(), "deleteFile:",
				file.getName() + "-path-" + file.getAbsolutePath());
		try {
			FileUtils.forceDelete(file);
		}
		catch (IOException e) {
			jwslogger.printLogger(DeleteFileDetails.class.getSimpleName(), "deleteFile",
					"Exception inside deleteFile-" + e);
			return false;
		}
		jwslogger.printLogger(DeleteFileDetails.class.getSimpleName(),
				"findFilesToDelete", "fileName:" + file.getName() + " is deleted.");
		return true;
	}

	/**
	 * Method to get list of files which needs to be deleted.
	 *
	 * @param folder the folder
	 * @param assetSerNo the asset ser no
	 * @param isRemovePostUpdtFile the is remove post updt file
	 * @return the file[]
	 */
	public File[] findFilesToBeDeletedInUsb(final File folder, String assetSerNo,
			boolean isRemovePostUpdtFile) {
		jwslogger.printLogger(
				DeleteFileDetails.class.getSimpleName(),
				"",
				"inside findFilesToBeDeletedInUsb:" + assetSerNo + "path="
						+ folder.getAbsolutePath());
		File[] fileToDelete = null;

		String fileExtn = ".bin";
		if (isRemovePostUpdtFile) {
			fileExtn = ".xml";
		}
		File[] fileEntries = folder.listFiles();
		if (fileEntries == null) {
			return fileToDelete;
		}
		fileToDelete = new File[fileEntries.length];
		int i = 0;
		for (final File fileEntry : fileEntries) {
			if (fileEntry.isDirectory()) {
				jwslogger.printLogger(DeleteFileDetails.class.getSimpleName(), "",
						"findFilesToBeDeletedInUsb:fileEntry isDirectory..");
				findFilesToBeDeletedInUsb(fileEntry, assetSerNo, isRemovePostUpdtFile);
			}
			else {
				File file = findExactFileToDeleteFromUSB(fileEntry, folder, assetSerNo,
						fileExtn);
				if (file != null) {
					fileToDelete[i] = file;
					i++;
				}
			}
		}

		return fileToDelete;

	}

	/**
	 * @param fileEntry
	 * @param folder
	 * @param assetSerNo
	 * @param fileExtn
	 * @return
	 */
	private File findExactFileToDeleteFromUSB(File fileEntry, final File folder,
			String assetSerNo, String fileExtn) {
		String fileName = fileEntry.getName();
		jwslogger.printLogger(DeleteFileDetails.class.getSimpleName(),
				"findFilesToBeDeletedInUsb", "fileName= " + fileName);
		String fileNameAssetNo = null;
		if (fileName != null && fileName.contains(".")) {
			fileNameAssetNo = fileName.substring(0, fileName.lastIndexOf('.'));
			if (fileNameAssetNo.indexOf('_') != -1) {
				// if post update file contains unique id
				fileNameAssetNo = fileName.substring(0, fileName.indexOf('_'));
			}
			jwslogger.printLogger(DeleteFileDetails.class.getSimpleName(),
					"findFilesToBeDeletedInUsb", "fileNameAssetNo= " + fileNameAssetNo);
		}
		if (fileNameAssetNo != null && assetSerNo.equals(fileNameAssetNo)
				&& (fileName.endsWith(fileExtn) || fileName.endsWith(".zip"))) {
			jwslogger.printLogger(DeleteFileDetails.class.getSimpleName(), "",
					"findFilesToBeDeletedInUsb:File= " + folder.getAbsolutePath() + "\\"
							+ fileEntry.getName());
			File file = new File(folder.getAbsolutePath() + fileEntry.getName());
			jwslogger.printLogger(
					DeleteFileDetails.class.getSimpleName(),
					"",
					"findFilesToBeDeletedInUsb:file added for deletion: "
							+ file.getName());
			return file;
		}
		return null;
	}

	/**
	 * Find bin files to be deleted in usb.
	 *
	 * @param folder the folder
	 * @return find all files with .bin extension from specified folder.
	 */
	public File[] findBinFilesToBeDeletedInUsb(final File folder) {

		jwslogger.printLogger(
				DeleteFileDetails.class.getSimpleName(),
				"",
				"findFilesToBeDeletedInUsb folder argument: path="
						+ folder.getAbsolutePath());
		/**
		 * File Initialsiation
		 */
		File[] fileToDelete = null;
		File[] fileEntries = folder.listFiles();
		if (fileEntries == null) {
			return fileToDelete;
		}
		fileToDelete = new File[fileEntries.length];
		int i = 0;
		String fileExtn = ".bin";

		for (final File fileEntry : fileEntries) {
			/**
			 * Is directory available
			 */
			if (fileEntry.isDirectory()) {
				findBinFilesToBeDeletedInUsb(fileEntry);
			}
			else {
				String fileName = fileEntry.getName();
				jwslogger.printLogger(DeleteFileDetails.class.getSimpleName(), "",
						"findFilesToBeDeletedInUsb folder argument :fileName= "
								+ fileName);
				if (fileName != null && fileName.contains(".")
						&& fileName.endsWith(fileExtn)) {
					String fileNameAssetNo = fileName.substring(0,
							fileName.lastIndexOf('.'));
					jwslogger.printLogger(DeleteFileDetails.class.getSimpleName(),
							"findFilesToBeDeletedInUsb folder argument ",
							"fileNameAssetNo= " + fileNameAssetNo);
					File file = new File(folder.getAbsolutePath() + fileEntry.getName());
					jwslogger.printLogger(DeleteFileDetails.class.getSimpleName(),
							"findFilesToBeDeletedInUsb folder argument ",
							"file added for deletion: " + file.getName());
					fileToDelete[i] = file;
					i++;
				}
			}
		}

		return fileToDelete;

	}

}
