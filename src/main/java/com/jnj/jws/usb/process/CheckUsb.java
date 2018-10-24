/*
 * 
 */
package com.jnj.jws.usb.process;

import static com.jnj.jws.USBConstant.META_INFO_FILE_NAME;
import static com.jnj.jws.USBConstant.USB_RECONCLT_URL;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.AccessController;
import java.security.PrivilegedActionException;
import java.security.PrivilegedExceptionAction;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.swing.filechooser.FileSystemView;

import com.google.gson.Gson;
import com.jnj.jws.util.JavaWebStartLogger;
import com.jnj.jws.util.UsbFileUtil;
import com.jnj.jws.vo.ErrorVo;
import com.jnj.jws.vo.UsbErrorEnum;
import com.jnj.jws.vo.UsbErrorVo;
import com.jnj.jws.vo.UsbFileVo;

/**
 * The Class CheckUsb.
 */
public class CheckUsb {

	/** The usb json. */
	private String usbJson = "";

	/** The jwslogger. */
	private JavaWebStartLogger jwslogger = new JavaWebStartLogger();

	/**
	 * Check USB content.
	 *
	 * @param wwid the wwid
	 * @param jwttoken the jwttoken
	 * @return the string
	 */
	@SuppressWarnings("unchecked")
	public String checkUSBContent(final String wwid, final String jwttoken) {

		jwslogger.printLogger(CheckUsb.class.getSimpleName(), "",
				"inside checkUSBContent:=wwid[" + wwid + "]jwttoken:[" + jwttoken + "]");
		try {
			AccessController
					.doPrivileged((PrivilegedExceptionAction<String>) () -> detectUSBandReconcileFile(
							wwid, jwttoken));
		}
		catch (PrivilegedActionException e) {
			usbJson = e.getMessage();
			jwslogger.printLogger(CheckUsb.class.getSimpleName(), "",
					"checkUSBContent:PrivilegedActionException while in checkUSBContent :"
							+ e);
		}
		return usbJson;
	}

	/**
	 * Detect US band reconcile file.
	 *
	 * @param wwid the wwid
	 * @param jwttoken the jwttoken
	 * @return the string
	 */
	String detectUSBandReconcileFile(final String wwid, final String jwttoken) {
		try {
			usbJson = "";
			File[] f = File.listRoots();
			usbJson = reconcileUSB(f, wwid, jwttoken);
		}
		catch (Exception var2) {
			usbJson = var2.getMessage();
			jwslogger.printLogger(CheckUsb.class.getSimpleName(), "",
					"detectUSBandReconcileFile:Exception while reconciling file form usb :"
							+ var2);
		}
		jwslogger.printLogger(CheckUsb.class.getSimpleName(),
				"detectUSBandReconcileFile", "usbJson:" + usbJson);
		return usbJson;
	}

	/**
	 * Reconcile USB.
	 *
	 * @param f the f
	 * @param wwid the wwid
	 * @param jwttoken the jwttoken
	 * @return the string
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	private String reconcileUSB(File[] f, String wwid, String jwttoken)
			throws IOException {
		List<String> usbFileNames;
		boolean usbDetected = false;
		FileSystemView fsv = FileSystemView.getFileSystemView();
		for (int i = 0; i < f.length; i++) {
			String displayName = fsv.getSystemDisplayName(f[i]);
			jwslogger.printLogger(CheckUsb.class.getSimpleName(), "",
					"reconcileUSB:displayName=" + displayName);
			String fileType = fsv.getSystemTypeDescription(f[i]);
			jwslogger.printLogger(
					CheckUsb.class.getSimpleName(),
					"detectUSBandReconcileFile",
					"connected fileType=" + fileType + " isDrive=" + fsv.isDrive(f[i])
							+ " canRead= " + f[i].canRead() + "canWrite="
							+ f[i].canWrite());
			if (fsv.isDrive(f[i]) && f[i].canRead() && f[i].canWrite()
					&& UsbFileUtil.isValidUsbDrive(fileType)) {
				// removable drive detected.
				jwslogger.printLogger(CheckUsb.class.getSimpleName(), "",
						"reconcileUSB:removable drive detected");
				// read file names
				usbDetected = true;
				usbFileNames = listFilesNamesForFolder(f[i]);
				jwslogger.printLogger(CheckUsb.class.getSimpleName(), "",
						"reconcileUSB:Read files- usbFiles" + usbFileNames.size());
				if (!usbFileNames.isEmpty()) {
					usbJson = doFilesReconciliation(wwid, usbFileNames, jwttoken);
				}
				else {
					return usbJson;
				}
			}
		}
		if (!usbDetected) {
			// show msg- USB drive Not detected
			usbJson = setUsbErrorCode(UsbErrorEnum.CODE_421.getType());

		}

		return usbJson;
	}

	/**
	 * List files names for folder.
	 *
	 * @param folder the folder
	 * @return the list
	 */
	public List<String> listFilesNamesForFolder(final File folder) {
		List<String> usbFiles = new ArrayList<>();
		File[] fileEntries = folder.listFiles();
		if (fileEntries != null) {
			for (final File fileEntry : fileEntries) {

				if (fileEntry.isDirectory()) {
					listFilesNamesForFolder(fileEntry);
				}
				else {
					addUsbFiles(folder, fileEntry, usbFiles);
				}
			}
		}
		return usbFiles;
	}

	/**
	 * Adds the usb files.
	 *
	 * @param folder the folder
	 * @param fileEntry the file entry
	 * @param usbFiles the usb files
	 */
	public void addUsbFiles(File folder, File fileEntry, List<String> usbFiles) {

		jwslogger.printLogger(CheckUsb.class.getSimpleName(), "",
				"listFilesNamesForFolder:File= " + folder.getAbsolutePath() + "\\"
						+ fileEntry.getName());
		String fileName = fileEntry.getName();

		if (!"stanley.bin".equalsIgnoreCase(fileName)
				&& !META_INFO_FILE_NAME.equalsIgnoreCase(fileName)) {
			usbFiles.add(fileName);
		}
	}

	/**
	 * Sets the usb error code.
	 *
	 * @param errorCodeType the error code type
	 * @return the string
	 */
	String setUsbErrorCode(String errorCodeType) {
		ErrorVo vo = new ErrorVo();
		UsbErrorVo usbErrVo = new UsbErrorVo();
		usbErrVo.setValue(errorCodeType);
		usbErrVo.setType("USB ERROR");
		vo.setError(usbErrVo);
		Gson gson = new Gson();
		return gson.toJson(vo);
	}

	/**
	 * Do files reconciliation.
	 *
	 * @param wwid the wwid
	 * @param usbFileNames the usb file names
	 * @param jwttoken the jwttoken
	 * @return the string
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	private String doFilesReconciliation(String wwid, List<String> usbFileNames,
			String jwttoken) throws IOException {
		jwslogger.printLogger(CheckUsb.class.getSimpleName(), "",
				"inside doFilesReconciliation:=wwid[" + wwid + "]jwttoken:[" + jwttoken
						+ "]");
		Properties properties = new Properties();
		InputStream input = getClass().getClassLoader().getResourceAsStream(
				"applet.properties");
		properties.load(input);
		String urlString = properties.getProperty(USB_RECONCLT_URL);
		jwslogger.printLogger(CheckUsb.class.getSimpleName(), "",
				"doFilesReconciliation:usb reconciliation URL is -" + urlString);
		URL url = new URL(urlString);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setDoOutput(true);
		conn.setDoInput(true);
		conn.setUseCaches(false);
		conn.setRequestMethod("POST");
		conn.setRequestProperty("Content-Type", "application/json");
		conn.setRequestProperty("Authorization", "Bearer " + jwttoken);

		DataOutputStream out = new DataOutputStream(conn.getOutputStream());
		UsbFileVo usbFileVo = new UsbFileVo();
		if (wwid != null && !wwid.isEmpty()) {
			jwslogger.printLogger(CheckUsb.class.getSimpleName(), "",
					"doFilesReconciliation:otherusb-true");
			usbFileVo.setOtherWwid(wwid);
			usbFileVo.setOthersUsb(true);
		}
		usbFileVo.setUsbFileNames(usbFileNames);
		Gson gson = new Gson();
		String json = gson.toJson(usbFileVo);
		jwslogger.printLogger(CheckUsb.class.getSimpleName(), "",
				"doFilesReconciliation:usbFileVo" + json);
		// out.writeBytes(json)
		out.write(json.getBytes("UTF-8"));
		out.flush();
		out.close();

		BufferedReader in = new BufferedReader(new InputStreamReader(
				conn.getInputStream(), StandardCharsets.UTF_8));
		String inputLine;
		StringBuilder response = new StringBuilder();
		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
		jwslogger.printLogger(CheckUsb.class.getSimpleName(), "",
				"doFilesReconciliation:response" + response.toString());
		return response.toString();

	}

}
