/*
 * 
 */
package com.jnj.jws.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * Common logger class to log JWS related logging info.
 *
 * @author 555786
 */
public class JavaWebStartLogger {

	/** The logger. */
	private Logger logger = null;

	/** The properties. */
	private Properties properties;

	/**
	 * Instantiates a new java web start logger.
	 */
	public JavaWebStartLogger() {
		enableLogger();
	}

	/**
	 * Prints the logger.
	 *
	 * @param className the class name
	 * @param funcName the func name
	 * @param content the content
	 */
	public void printLogger(String className, String funcName, String content) {
		if (logger != null) {
			System.out.println(className + ":" + funcName + ":" + content);
		}
	}

	/**
	 * Enable logger.
	 */
	public final void enableLogger() {
		properties = new Properties();
		InputStream input = getClass().getClassLoader().getResourceAsStream(
				"applet.properties");
		try {
			properties.load(input);
		}
		catch (IOException e) {
			printLogger(this.getClass().getSimpleName(), "enableLogger",
					"IOException:Unable to load properties file" + e);
		}
		String isEnable = properties.getProperty("logger.enable");
		if (isEnable != null && "true".equals(isEnable)) {
			logger = Logger.getLogger("jwsconsole");
			Handler handler = new ConsoleHandler();
			handler.setFormatter(new SimpleFormatter());
			logger.addHandler(handler);
		}
	}
}
