/*
 * 
 */
package com.jnj.jws.usb.process;

import static com.jnj.jws.USBConstant.INT_TEN;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.io.FileUtils;

import com.jnj.jws.util.JavaWebStartLogger;

/**
 * http client to upload multipart file.
 */
public class Client {

	/** The Constant BUFFER_SIZE. */
	public static final int BUFFER_SIZE = 4096;

	/** The Constant MAX_CHUNK_SIZE. */
	public static final int MAX_CHUNK_SIZE = 1000 * BUFFER_SIZE; // ~12MB

	/** The Constant FILE_NAME_HEADER. */
	public static final String FILE_NAME_HEADER = "Transfer-File-Name";

	/** The Constant CLIENT_ID_HEADER. */
	public static final String CLIENT_ID_HEADER = "Transfer-Client-ID";

	/** The Constant FILE_CHUNK_HEADER. */
	public static final String FILE_CHUNK_HEADER = "Transfer-File-Chunk";

	/** The Constant FILE_CHUNK_COUNT_HEADER. */
	public static final String FILE_CHUNK_COUNT_HEADER = "Transfer-File-Chunk-Count";

	/** The jws logger. */
	private static JavaWebStartLogger jwsLogger = new JavaWebStartLogger();

	/** The Constant FN_TRANSFER_FILE. */
	private static final String FN_TRANSFER_FILE = "transferFile";

	/**
	 * Instantiates a new client.
	 */
	private Client() {

	}

	/**
	 * Transfer file.
	 *
	 * @param fileName the file name
	 * @param urlString the url string
	 * @param otherWwid the other wwid
	 * @param jToken the j token
	 * @return the string
	 */
	public static String transferFile(String fileName, String urlString,
			String otherWwid, String jToken) {
		String status = null;
		StringBuilder response = new StringBuilder();
		InputStream in = null;
		try {
			jwsLogger.printLogger(Client.class.getSimpleName(), FN_TRANSFER_FILE,
					"inside transferFile urlString=" + urlString);
			URL url = new URL(urlString);
			HttpURLConnection conn = null;
			File file = new File(fileName);
			long fileSize = file.length();
			jwsLogger.printLogger(Client.class.getSimpleName(), FN_TRANSFER_FILE,
					"file name : " + file.getName() + "file size : " + fileSize);
			in = new ByteArrayInputStream(FileUtils.readFileToByteArray(file));
			int nChunks = (int) (fileSize / MAX_CHUNK_SIZE);
			if (fileSize % MAX_CHUNK_SIZE > 0) {
				nChunks++;
			}
			long bytesRemaining = fileSize;

			String clientID = generateSecretToken();

			for (int i = 0; i < nChunks; i++) {
				int chunkSize;

				if (bytesRemaining > MAX_CHUNK_SIZE) {
					chunkSize = MAX_CHUNK_SIZE;
				}
				else {
					chunkSize = (int) bytesRemaining;
				}
				jwsLogger.printLogger(Client.class.getSimpleName(), FN_TRANSFER_FILE,
						"nChunks:" + nChunks);
				jwsLogger.printLogger(Client.class.getSimpleName(), FN_TRANSFER_FILE,
						"chunkSize:" + chunkSize + "bytesRemaining" + bytesRemaining);
				bytesRemaining -= chunkSize;
				conn = (HttpURLConnection) url.openConnection();
				setHeaderAttributes(conn, nChunks, chunkSize, clientID, otherWwid,
						jToken, file);
				conn.setRequestProperty(FILE_CHUNK_HEADER, String.valueOf(i));
				readBytes(conn, chunkSize, in);
				status = "status : " + conn.getResponseCode();
				jwsLogger.printLogger(Client.class.getSimpleName(), FN_TRANSFER_FILE,
						"status=" + status);

				jwsLogger.printLogger(Client.class.getSimpleName(), FN_TRANSFER_FILE,
						"conn.getResponseMessage():" + conn.getResponseMessage());
			}

			jwsLogger.printLogger(Client.class.getSimpleName(), FN_TRANSFER_FILE,
					"after sending chunk...");
			if (conn != null && conn.getResponseCode() == HttpServletResponse.SC_OK) {
				jwsLogger.printLogger(Client.class.getSimpleName(), FN_TRANSFER_FILE,
						"Recieved response 200...");
				BufferedReader respIn = new BufferedReader(new InputStreamReader(
						conn.getInputStream(), StandardCharsets.UTF_8));
				String inputLine;

				while ((inputLine = respIn.readLine()) != null) {
					response.append(inputLine);
				}
				respIn.close();
			}
		}
		catch (IOException e) {
			e.getStackTrace();
			jwsLogger.printLogger(Client.class.getSimpleName(), FN_TRANSFER_FILE,
					"Got Exception : " + e);

		}
		jwsLogger.printLogger(Client.class.getSimpleName(), FN_TRANSFER_FILE, "response="
				+ response.toString());

		return response.toString();
	}

	/**
	 * Sets the header attributes.
	 *
	 * @param conn the conn
	 * @param nChunks the n chunks
	 * @param chunkSize the chunk size
	 * @param clientID the client ID
	 * @param otherWwid the other wwid
	 * @param jToken the j token
	 * @param file the file
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	static void setHeaderAttributes(HttpURLConnection conn, int nChunks, long chunkSize,
			String clientID, String otherWwid, String jToken, File file)
			throws IOException {
		conn.setRequestMethod("PUT");
		conn.setDoOutput(true);
		conn.setDoInput(true);
		conn.setUseCaches(false);
		conn.setRequestProperty("Content-Type", "application/octet-stream");
		conn.setRequestProperty("Content-Length", String.valueOf(chunkSize));
		conn.setRequestProperty("Authorization", "Bearer " + jToken);
		conn.setRequestProperty(FILE_CHUNK_COUNT_HEADER, String.valueOf(nChunks));
		conn.setRequestProperty(FILE_NAME_HEADER, file.getName());
		conn.setRequestProperty(CLIENT_ID_HEADER, clientID);
		jwsLogger.printLogger(Client.class.getSimpleName(), FN_TRANSFER_FILE,
				"Transfer-Client-ID" + clientID);
		if (otherWwid != null && !otherWwid.isEmpty()) {
			conn.setRequestProperty("OTHER_USER_ID", otherWwid);
			jwsLogger.printLogger(Client.class.getSimpleName(), FN_TRANSFER_FILE,
					"otherWwid" + otherWwid);

		}
		else {
			conn.setRequestProperty("OTHER_USER_ID", "");
		}
		jwsLogger.printLogger(Client.class.getSimpleName(), FN_TRANSFER_FILE, "chunkSize"
				+ chunkSize);
	}

	/**
	 * Read bytes.
	 *
	 * @param conn the conn
	 * @param chunkSize the chunk size
	 * @param in the in
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	static void readBytes(HttpURLConnection conn, int chunkSize, InputStream in)
			throws IOException {
		jwsLogger.printLogger(Client.class.getSimpleName(), "inside readBytes",
				"chunkSize" + chunkSize);
		byte[] buf = new byte[BUFFER_SIZE];
		OutputStream out = conn.getOutputStream();
		int bytesRead = 0;
		while (bytesRead < chunkSize) {
			int read = in.read(buf);
			if (read == -1) {
				break;
			}
			else if (read > 0) {
				bytesRead += read;
				out.write(buf, 0, read);
			}
		}
		out.flush();
		out.close();
		if (null != in) {
			in.close();
		}
		if (conn.getResponseCode() != HttpServletResponse.SC_OK) {
			jwsLogger.printLogger(
					Client.class.getSimpleName(),
					FN_TRANSFER_FILE,
					"Response code Not OK conn.getResponseCode()"
							+ conn.getResponseCode() + "conn.getresponsemessage()"
							+ conn.getResponseMessage());
		}
		else {
			jwsLogger.printLogger(Client.class.getSimpleName(), FN_TRANSFER_FILE,
					"Response success message:" + conn.getResponseMessage());
		}
		jwsLogger.printLogger(Client.class.getSimpleName(), "end of readbytes", "");
	}

	/**
	 * Generate secret token.
	 *
	 * @return the string
	 */
	static String generateSecretToken() {
		SecureRandom secRandom = new SecureRandom();
		byte[] result = new byte[INT_TEN];
		secRandom.nextBytes(result);
		return Hex.encodeHexString(result);
	}
}
