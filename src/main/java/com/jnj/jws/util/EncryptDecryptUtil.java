/*
 * 
 */
package com.jnj.jws.util;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

import javax.servlet.ServletException;
import javax.xml.bind.DatatypeConverter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

/**
 * Class to decrypt the content.
 */
public class EncryptDecryptUtil {

	/** The jwslogger. */
	private JavaWebStartLogger jwslogger = new JavaWebStartLogger();

	/** The Constant SECRET_KEY. */
	private static final String SECRET_KEY = "54321MedGFDDFStyyueiDConnect";

	/**
	 * This method has dependency with JwtTokenGenerator in genesis-core module currently
	 * the encryted userheaderjson is set in setIssuer method.
	 *
	 * @param encrytedtext the encrytedtext
	 * @return the string
	 */
	public String parseJWT(String encrytedtext) {
		try {
			String encodedKey = Base64.getEncoder().encodeToString(
					SECRET_KEY.getBytes(StandardCharsets.UTF_8));
			Claims claims = Jwts.parser()
					.setSigningKey(DatatypeConverter.parseBase64Binary(encodedKey))
					.parseClaimsJws(encrytedtext).getBody();
			return claims.getIssuer();
		}
		catch (Exception e) {
			jwslogger.printLogger(EncryptDecryptUtil.class.getSimpleName(), "",
					"parseJWT:" + e);
			return null;
		}

	}

}
