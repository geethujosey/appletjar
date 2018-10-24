/*
 * 
 */
package com.jnj.jws.download.event;

import static com.jnj.jws.USBConstant.BYTE_SIZE;
import static com.jnj.jws.USBConstant.DOWNLOAD_FILE_URL;
import static com.jnj.jws.USBConstant.RESPONSE_CODE_TWOHUNDRED;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.Callable;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;

import com.jnj.jws.USBConstant;
import com.jnj.jws.util.JavaWebStartLogger;
import com.jnj.jws.vo.AssetEventLogVO;

/**
 * Class which downloads event log file to client machine.
 *
 * @author 555786
 */
public class AssetEventFileProcessor implements Callable<String> {

	/** The Constant BUFFER_SIZE. */
	private static final int BUFFER_SIZE = 12000000;

	/** The Constant FN_WRITE_FILE. */
	private static final String FN_WRITE_FILE = "writeToFile():";

	/** The Constant FN_UN_ZIP. */
	private static final String FN_UN_ZIP = "unZip():";

	/** The file ref id. */
	private String fileRefId;

	/** The folder path. */
	private String folderPath;

	/** The filesource. */
	private String filesource;

	/** The j token. */
	private String jToken;

	/** The properties. */
	private Properties properties;

	/** The filewithout extn. */
	private String filewithoutExtn;

	/** The isFolderExists constant. */
	private static final String IS_FOLDER_EXISTS = "isFolderExists";

	/** The isFolderExists constant. */
	private static final String PARENT_FILE_DIR = "parentFileDir";

	/** The jwslogger. */
	private JavaWebStartLogger jwslogger = new JavaWebStartLogger();

	/**
	 * initialize attributes.
	 *
	 * @param assetvo the assetvo
	 * @param properties the properties
	 * @param jwttoken the jwttoken
	 */
	public AssetEventFileProcessor(AssetEventLogVO assetvo, Properties properties,
			String jwttoken) {
		this.folderPath = assetvo.getFolderPath();
		this.properties = properties;
		this.jToken = jwttoken;
		this.filesource = assetvo.getFileSource();
		if (assetvo.getFileRefId() != null && assetvo.getFileRefId().endsWith(".zip")) {
			this.filewithoutExtn = FilenameUtils.removeExtension(assetvo.getFileRefId());
			this.fileRefId = assetvo.getFileRefId();
		}
		else {
			this.fileRefId = assetvo.getFileRefId();
			this.filewithoutExtn = assetvo.getFileRefId();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.concurrent.Callable#call()
	 */
	@Override
	public String call() {
		String successFailureCount = "";
		try {
			URL url = new URL(properties.getProperty(DOWNLOAD_FILE_URL) + fileRefId
					+ "&filesource=" + filesource);
			jwslogger.printLogger(AssetEventFileProcessor.class.getSimpleName(), "call:",
					"Thread for file ref id" + fileRefId
							+ "to download file started and URL is:" + url);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoInput(true);
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Authorization", "Bearer " + jToken);
			successFailureCount = writeToFile(conn);
		}
		catch (Exception e) {
			jwslogger.printLogger(AssetEventFileProcessor.class.getSimpleName(), "call:",
					"Exception while downloading ref id>>>>>>>>>>>>>>>>>>>>>>>>>" + e);
			return new StringBuilder().append(fileRefId).append("-failure")
					.append(successFailureCount).toString();
		}
		return new StringBuilder(fileRefId).append("-success")
				.append(successFailureCount).toString();
	}

	/**
	 * Write to file.
	 *
	 * @param con the con
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	private String writeToFile(HttpURLConnection con) throws IOException {
		jwslogger.printLogger(AssetEventFileProcessor.class.getSimpleName(),
				FN_WRITE_FILE, "Thread download file : " + fileRefId + " to folder"
						+ folderPath);
		OutputStream fileOutputStream = null;
		String successFailureCount = "";
		File file = new File(folderPath + File.separator + filewithoutExtn
				+ USBConstant.TYPE_ZIP);

		try (InputStream bif = con.getInputStream()) {
			if (con.getResponseCode() == RESPONSE_CODE_TWOHUNDRED) {
				fileOutputStream = new FileOutputStream(file);

				// =====================================
				// this is the total size of the file
				int totalSize = con.getContentLength();
				jwslogger.printLogger(AssetEventFileProcessor.class.getSimpleName(),
						FN_WRITE_FILE, "Zipfile fileRefId:" + filewithoutExtn
								+ "Total size of the file:" + totalSize);
				// variable to store total downloaded bytes
				int downloadedSize = 0;
				// =====================================

				// create a buffer...
				byte[] buffer = new byte[BUFFER_SIZE];
				int bufferLength = 0; // used to store a temporary size of the
										// buffer

				// now, read through the input buffer and write the contents to
				// the file
				while ((bufferLength = bif.read(buffer)) > 0) {
					// add the data in the buffer to the file in the file output
					// stream (the file on the sd card
					fileOutputStream.write(buffer, 0, bufferLength);
					// add up the size so we know how much is downloaded
					downloadedSize += bufferLength;
					// this is where you would do something to report the
					// progress, like this maybe
				}
				// close the output stream when done
				jwslogger.printLogger(AssetEventFileProcessor.class.getSimpleName(),
						FN_WRITE_FILE, "Closing the output stream");
				fileOutputStream.flush();
				fileOutputStream.close();
				if (downloadedSize == totalSize) {
					jwslogger.printLogger(AssetEventFileProcessor.class.getSimpleName(),
							FN_WRITE_FILE, "downloadedSize:" + downloadedSize
									+ "totalSize:" + totalSize);
				}

				successFailureCount = unZip(file, folderPath);
				deleteZipFile(file);
			}
			else {
				jwslogger.printLogger(
						AssetEventFileProcessor.class.getSimpleName(),
						FN_WRITE_FILE,
						"Failed to download file beacause of resposne code"
								+ con.getResponseCode());
			}

		}
		catch (IOException exep) {
			jwslogger.printLogger(AssetEventFileProcessor.class.getSimpleName(),
					FN_WRITE_FILE, "Exception while downloading file for fileRefId:["
							+ fileRefId + "]" + exep);
			throw exep;
		}
		finally {
			IOUtils.closeQuietly(fileOutputStream);
		}

		return successFailureCount;
	}

	/**
	 * Un zip.
	 *
	 * @param zipFile the zip file
	 * @param outputFolder the output folder
	 * @param successFullyUnzipedFiles
	 * @param totalFilesInZip
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	private String unZip(File zipFile, String outputFolder) throws IOException {
		jwslogger.printLogger(AssetEventFileProcessor.class.getSimpleName(), FN_UN_ZIP,
				"Starting UNZIPPING zip location" + zipFile.getAbsolutePath()
						+ " outputfolder" + outputFolder);
		byte[] buffer = new byte[BYTE_SIZE];
		FileOutputStream fos = null;
		ZipEntry ze;
		Integer totalFilesInZip = 0;
		Integer successFullyUnzipedFiles = 0;
		try (FileInputStream fin = new FileInputStream(zipFile.getAbsolutePath());
				ZipInputStream zis = new ZipInputStream(fin)) {
			jwslogger.printLogger(AssetEventFileProcessor.class.getSimpleName(),
					FN_UN_ZIP, "before create outputfolder" + outputFolder);
			// create output directory is not exists
			File folder = new File(outputFolder);
			boolean isFolderExists = false;
			if (!folder.exists()) {
				isFolderExists = folder.mkdir();
			}
			if (!isFolderExists) {
				jwslogger.printLogger(AssetEventFileProcessor.class.getSimpleName(),
						IS_FOLDER_EXISTS, IS_FOLDER_EXISTS);
			}

			// get the zip file content
			jwslogger.printLogger(AssetEventFileProcessor.class.getSimpleName(),
					FN_UN_ZIP, "fin" + fin.available() + "zis" + zis.available());

			// get the zipped file list entry
			while ((ze = zis.getNextEntry()) != null) {
				++totalFilesInZip;
				File newFile = null;
				jwslogger.printLogger(AssetEventFileProcessor.class.getSimpleName(),
						FN_UN_ZIP, "inside zip entry " + ze.getName());
				// get unique file name
				String fileName = retrieveUniqueUnzippedFileName(ze.getName());
				jwslogger.printLogger(AssetEventFileProcessor.class.getSimpleName(),
						FN_UN_ZIP, "unzipping file as " + fileName);

				newFile = new File(folder + File.separator + fileName);
				// create all non exists folders
				// else you will hit FileNotFoundException for compressed folder
				boolean parentFileDir = false;
				parentFileDir = new File(newFile.getParent()).mkdirs();
				if (!parentFileDir) {
					jwslogger.printLogger(AssetEventFileProcessor.class.getSimpleName(),
							PARENT_FILE_DIR, PARENT_FILE_DIR);
				}

				fos = new FileOutputStream(newFile);

				successFullyUnzipedFiles += writeUnzippedFile(buffer, fos, zis, newFile);
			}
		}
		catch (IOException ex) {
			jwslogger.printLogger(AssetEventFileProcessor.class.getSimpleName(),
					FN_WRITE_FILE, "IOEXception while unzipping" + ex);
			throw ex;
		}
		return new StringBuilder().append("-").append(totalFilesInZip.toString())
				.append("-").append(successFullyUnzipedFiles.toString()).toString();
	}

	private int writeUnzippedFile(byte[] buffer, FileOutputStream fos,
			ZipInputStream zis, File newFile) {
		int len;
		int successFullyUnzipedFiles = 0;
		try {
			while ((len = zis.read(buffer)) > 0) {
				fos.write(buffer, 0, len);
			}
			jwslogger.printLogger(AssetEventFileProcessor.class.getSimpleName(),
					FN_UN_ZIP, "Completed the UNZIp under" + newFile);
			++successFullyUnzipedFiles;
		}
		catch (IOException ex) {
			jwslogger.printLogger(AssetEventFileProcessor.class.getSimpleName(),
					FN_WRITE_FILE, "IOEXception while writing file" + ex);
		}
		finally {
			IOUtils.closeQuietly(fos);
		}
		return successFullyUnzipedFiles;
	}

	/**
	 * get unique unzipped file name
	 * @param unzippedFileName
	 * @return formatted file name
	 */
	private String retrieveUniqueUnzippedFileName(String unzippedFileName) {
		StringBuilder fileName = new StringBuilder(unzippedFileName.replace(
				USBConstant.TYPE_XML, ""));
		String[] referenceFileNameWithTimeStamp = filewithoutExtn.split("_");
		if (referenceFileNameWithTimeStamp != null) {
			if (referenceFileNameWithTimeStamp.length > 1) {
				fileName.append("_").append(referenceFileNameWithTimeStamp[1])
						.append(USBConstant.TYPE_XML);
			}
			else if (referenceFileNameWithTimeStamp.length == 1) {
				fileName.append("_").append(referenceFileNameWithTimeStamp[0])
						.append(USBConstant.TYPE_XML);
			}
		}
		return fileName.toString();
	}

	/**
	 * Delete zip file.
	 *
	 * @param file the file
	 */
	void deleteZipFile(File file) {
		boolean iszipfiledeleted = file.delete();
		jwslogger.printLogger(AssetEventFileProcessor.class.getSimpleName(),
				FN_WRITE_FILE, "iszipfiledeleted:" + iszipfiledeleted);
	}

}
