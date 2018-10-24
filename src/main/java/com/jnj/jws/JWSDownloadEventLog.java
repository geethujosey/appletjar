/*
 * 
 */

package com.jnj.jws;

import java.util.Arrays;

import com.jnj.jws.usb.process.javafx.DownloadEventLogDialog;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * JWS web start class which handles provides JavaFx Visual for Manage USB screen.
 */

@SuppressWarnings("restriction")
public class JWSDownloadEventLog extends Application {

	/** The req args. */
	private String[] reqArgs = null;

	/*
	 * (non-Javadoc)
	 * 
	 * @see javafx.application.Application#start(javafx.stage.Stage)
	 */
	@Override
	public void start(final Stage stage) throws Exception {
		if (getParameters() != null) {
			Object[] objectArray = getParameters().getUnnamed().toArray();
			reqArgs = Arrays.copyOf(objectArray, objectArray.length, String[].class);
			DownloadEventLogDialog deld = new DownloadEventLogDialog(reqArgs);
			deld.createUsbListGrid(stage);
		}
	}

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		Application.launch(args);
	}
}
