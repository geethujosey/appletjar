/*
 * 
 */
package com.jnj.jws.usb.process.javafx;

import static com.jnj.jws.USBConstant.INSETS_BOTTOM_TWENTYFIVE;
import static com.jnj.jws.USBConstant.INSETS_LEFT_TWENTYFIVE;
import static com.jnj.jws.USBConstant.INSETS_RIGHT_TWENTYFIVE;
import static com.jnj.jws.USBConstant.INSETS_TOP_TWENTYFIVE;
import static com.jnj.jws.USBConstant.INT_FOURHUNDRED;
import static com.jnj.jws.USBConstant.INT_TEN;
import static com.jnj.jws.USBConstant.INT_THREE;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * The Class JavaFxDefaultErrorDialog.
 * @author Cognizant
 * @version 1.0
 * @serial 1.0
 * @since 1.0
 */
public class JavaFxDefaultErrorDialog {

	/** The dialog. */
	private Stage dialog;

	/** The ok button. */
	private Button okButton = new Button("OK");

	/**
	 * Creates the error dialog.
	 *
	 * @param stage the stage
	 * @param title the title
	 * @param msg the msg
	 * @param name the name
	 * @param fromProcess the from process
	 */
	public void createErrorDialog(final Stage stage, String title, String msg,
			String name, boolean fromProcess) {
		dialog = new Stage();
		dialog.setTitle(title);
		dialog.initModality(Modality.APPLICATION_MODAL);
		dialog.initOwner(stage);
		String css = this.getClass().getClassLoader().getResource("stylesheet.css")
				.toExternalForm();
		/**
		 * Initialising Vbox
		 */
		VBox dialogVbox = new VBox();
		dialogVbox.setMaxSize(INT_FOURHUNDRED, INT_FOURHUNDRED);
		final ImageView imv = new ImageView();
		final Image image2 = new Image(this.getClass().getClassLoader()
				.getResourceAsStream("info.png"));
		imv.setImage(image2);
		/**
		 * Initialising GridPane
		 */
		GridPane gridpane = new GridPane();
		gridpane.setAlignment(Pos.CENTER);
		gridpane.setHgap(INT_TEN);
		gridpane.setVgap(INT_TEN);
		gridpane.setPadding(new Insets(INSETS_TOP_TWENTYFIVE, INSETS_RIGHT_TWENTYFIVE,
				INSETS_BOTTOM_TWENTYFIVE, INSETS_LEFT_TWENTYFIVE));
		if (name != null && !name.isEmpty()) {
			Label username = new Label("Dear " + name + ",");
			gridpane.add(username, 1, 0);
		}
		Label msgLbl = new Label(msg);
		msgLbl.setWrapText(true);
		gridpane.add(imv, 0, 0);
		gridpane.add(msgLbl, 1, 1);
		gridpane.add(okButton, 1, INT_THREE);
		dialogVbox.getChildren().addAll(gridpane);
		Scene dialogScene = new Scene(dialogVbox, INT_FOURHUNDRED, INT_FOURHUNDRED);
		dialogScene.getStylesheets().add(css);
		dialog.setScene(dialogScene);
		dialog.setHeight(INT_FOURHUNDRED);
		dialog.setWidth(INT_FOURHUNDRED);
		dialog.show();

		okButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent t) {
				dialog.hide();
				if (!fromProcess) {
					stage.hide();
				}
			}
		});

	}
}
