/*
 * 
 */
package com.jnj.jws.util;

import static com.jnj.jws.USBConstant.BYTE_SIZE;
import static com.jnj.jws.USBConstant.INT_SIXTEEN;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * This class can be used to calculate checksum of any file.
 *
 */

public class FileChecksumGenerator {

	/**
	 * Instantiates a new file checksum generator.
	 */
	private FileChecksumGenerator() {

	}

	/**
	 * Gets the file checksum.
	 *
	 * @param digest the digest
	 * @param file the file
	 * @return the file checksum
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static String getFileChecksum(MessageDigest digest, File file)
			throws IOException {

		FileInputStream fis = new FileInputStream(file);

		byte[] byteArray = new byte[BYTE_SIZE];
		int bytesCount;

		// Read file data and update in message digest
		while ((bytesCount = fis.read(byteArray)) != -1) {
			digest.update(byteArray, 0, bytesCount);
		}

		fis.close();

		// Get the hash's bytes
		byte[] bytes = digest.digest();

		// Convert decimal format bytes to hexadecimal format
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < bytes.length; i++) {
			sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, INT_SIXTEEN).substring(
					1));
		}

		// return complete hash
		return sb.toString();
	}

	/**
	 * Gets the message digest.
	 *
	 * @param algrthm the algrthm
	 * @return the message digest
	 * @throws NoSuchAlgorithmException the no such algorithm exception
	 */
	public static MessageDigest getMessageDigest(String algrthm)
			throws NoSuchAlgorithmException {
		return MessageDigest.getInstance(algrthm);
	}

}
