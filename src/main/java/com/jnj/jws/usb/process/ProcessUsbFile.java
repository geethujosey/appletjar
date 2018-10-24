/*
 * 
 */
package com.jnj.jws.usb.process;

import static com.jnj.jws.USBConstant.BYTE_SIZE;
import static com.jnj.jws.USBConstant.PROCESS_USB_URL;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.AccessController;
import java.security.PrivilegedActionException;
import java.security.PrivilegedExceptionAction;
import java.util.List;
import java.util.Properties;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.commons.io.IOUtils;

import com.jnj.jws.USBConstant;
import com.jnj.jws.util.JavaWebStartLogger;
import com.jnj.jws.util.UsbFileUtil;

/**
 * The Class ProcessUsbFile.
 *
 * @author 431262 invoked when click on process button
 */
public class ProcessUsbFile {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The details. */
	private String details = "OK";

	/** The jwslogger. */
	private JavaWebStartLogger jwslogger = new JavaWebStartLogger();

	/**
	 * Process USB file.
	 *
	 * @param assetSerNo the asset ser no
	 * @param otherWwid the other wwid
	 * @param jToken the j token
	 * @return the string
	 */

	public String processUSBFile(final String assetSerNo, final String otherWwid,
			final String jToken) {

		jwslogger.printLogger(ProcessUsbFile.class.getSimpleName(), "",
				"inside ProcessUsbFile for assetSerNo:" + assetSerNo + " otherWwid["
						+ otherWwid + "]jwttoken:[" + jToken + "] ");
		try {
			AccessController
					.doPrivileged((PrivilegedExceptionAction<String>) () -> processusb(
							assetSerNo, otherWwid, jToken));
		}
		catch (PrivilegedActionException e) {
			jwslogger.printLogger(ProcessUsbFile.class.getSimpleName(), "",
					"PrivilegedActionException while process USb" + e);
			details = e.getMessage();
		}

		return details;
	}

	/**
	 * Processusb.
	 *
	 * @param assetSerNo the asset ser no
	 * @param otherWwid the other wwid
	 * @param jToken the j token
	 * @return the string
	 */
	String processusb(final String assetSerNo, final String otherWwid, final String jToken) {

		details = "OK";
		File usbDrivePath = UsbFileUtil.detectUSB();

		List<File> filesToUpload = UsbFileUtil
				.findLogFilesInUsb(usbDrivePath, assetSerNo);
		if (!filesToUpload.isEmpty()) {

			Properties properties = new Properties();
			InputStream input = getClass().getClassLoader().getResourceAsStream(
					"applet.properties");
			try {
				properties.load(input);

				String urlString = properties.getProperty(PROCESS_USB_URL);
				// zip file before upload.
				String zipFilePath = doZipFile(filesToUpload, assetSerNo);
				if (zipFilePath != null) {
					details = Client.transferFile(zipFilePath, urlString, otherWwid,
							jToken);
				}
			}
			catch (IOException e) {
				jwslogger.printLogger(ProcessUsbFile.class.getSimpleName(), "",
						"IOException while loading properties file." + e);
			}
			catch (Exception er) {
				jwslogger.printLogger(ProcessUsbFile.class.getSimpleName(), "",
						"Exception while process USB." + er);
			}
		}
		return null;

	}

	/**
	 * Do zip file.
	 *
	 * @param postUpdateFiles the post update file
	 * @param assetSerialNumber assetSerialNumber
	 * @return the string
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	private String doZipFile(List<File> postUpdateFiles, String assetSerialNumber)
			throws IOException {

		String zipFileName = new StringBuilder(assetSerialNumber).append(
				USBConstant.TYPE_ZIP).toString();

		FileOutputStream fos = new FileOutputStream(zipFileName);
		ZipOutputStream zos = new ZipOutputStream(fos);

		for (File postUpdateFile : postUpdateFiles) {
			byte[] buffer = new byte[BYTE_SIZE];

			try (FileInputStream in = new FileInputStream(
					postUpdateFile.getAbsolutePath())) {

				ZipEntry ze = new ZipEntry(postUpdateFile.getName());
				zos.putNextEntry(ze);
				int len;
				while ((len = in.read(buffer)) > 0) {
					zos.write(buffer, 0, len);
				}

				jwslogger.printLogger(ProcessUsbFile.class.getSimpleName(), "doZipFile",
						"Done");
			}
			catch (IOException ex) {
				jwslogger.printLogger(ProcessUsbFile.class.getSimpleName(), "doZipFile",
						"IOException while process USB." + ex);
			}
		}

		IOUtils.closeQuietly(zos);
		IOUtils.closeQuietly(fos);

		return zipFileName;

	}

}
