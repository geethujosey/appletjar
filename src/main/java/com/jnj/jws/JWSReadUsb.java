/*
 * 
 */

package com.jnj.jws;

import java.util.Arrays;

import com.jnj.jws.usb.process.javafx.ManageUsbDialog;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * JWS web start class which handles provides JavaFx Visual for Manage USB screen.
 */

@SuppressWarnings("restriction")
public class JWSReadUsb extends Application {

	/** The req args. */
	private String[] reqArgs = null;

	/*
	 * (non-Javadoc)
	 * 
	 * @see javafx.application.Application#start(javafx.stage.Stage)
	 */
	@Override
	@SuppressWarnings("unchecked")
	public void start(final Stage stage) throws Exception {
		if (getParameters() != null) {
			Object[] objectArray = getParameters().getUnnamed().toArray();
			reqArgs = Arrays.copyOf(objectArray, objectArray.length, String[].class);
			ManageUsbDialog musbd = new ManageUsbDialog();
			musbd.createUsbListGrid(stage, reqArgs);
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
