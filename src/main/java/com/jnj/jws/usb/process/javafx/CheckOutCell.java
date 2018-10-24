/*
 * Process package
 * 
 */
package com.jnj.jws.usb.process.javafx;

import static com.jnj.jws.USBConstant.INT_ONEFIFTY;
import static com.jnj.jws.USBConstant.INT_TEN;
import static com.jnj.jws.USBConstant.INT_TWENTY;
import static com.jnj.jws.USBConstant.INT_TWO;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.jnj.jws.util.JavaWebStartLogger;
import com.jnj.jws.vo.UsbAssetVO;
import com.jnj.jws.vo.UserVO;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Pair;

/**
 * setting imageview for lock or unlock asset and popup on imageview.
 *
 * @author 555786
 * @version 1.0
 * @since 1.0
 * @serial 1.0
 */
public class CheckOutCell extends TableCell<UsbAssetVO, String> {

	/** The tbl view. */
	private TableView tblView;

	/** The jwslogger. */
	private JavaWebStartLogger jwslogger = new JavaWebStartLogger();

	/** The Constant CHECKOUT_CELL. */
	private static final String CHECKOUT_CELL = "CheckOutCell";

	/**
	 * Instantiates a new check out cell.
	 *
	 * @param tblView the tbl view
	 * @param stage the stage
	 */
	CheckOutCell(TableView tblView, Stage stage) {
		this.tblView = tblView;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javafx.scene.control.Cell#updateItem(java.lang.Object, boolean)
	 */
	@Override
	public void updateItem(String item, boolean empty) {
		if (item != null) {
			Image image;
			/**
			 * Initialise HBox
			 */
			HBox box = new HBox();
			box.setSpacing(INT_TEN);
			VBox vbox = new VBox();
			final ImageView imv = new ImageView();
			if ("unlocked".equalsIgnoreCase(item)) {
				image = new Image(this.getClass().getClassLoader()
						.getResourceAsStream("unlocked.png"));
			}
			else {
				image = new Image(this.getClass().getClassLoader()
						.getResourceAsStream("locked.png"));
				imv.addEventHandler(MouseEvent.MOUSE_CLICKED,
						new EventHandler<MouseEvent>() {

							@Override
							public void handle(MouseEvent event) {
								try {
									jsonToCheckOutObj();
								}
								catch (IOException e) {
									jwslogger.printLogger(CHECKOUT_CELL, "updateItem",
											"IOException Occurred" + e);
								}
							}
						});
			}
			imv.setImage(image);

			box.getChildren().addAll(imv, vbox);
			// SETTING ALL THE GRAPHICS COMPONENT FOR CELL
			setGraphic(box);
		}

		else {
			setContentDisplay(null);
			setGraphic(null);
			setText(null);
		}
	}

	/**
	 * Json to check out obj.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	void jsonToCheckOutObj() throws IOException {

		int selectdIndex = getTableRow().getIndex();
		ObjectMapper objectMapper = new ObjectMapper();
		String convertedJson;
		String convertedJsonUser;
		String convertedJsonContact;
		String checkoutOn;
		String ckOn = null;
		String ckBy = null;
		String checkoutinfo = null;
		UserVO userVO;
		Gson gson = new Gson();
		UsbAssetVO selectedRecord = (UsbAssetVO) tblView.getItems().get(selectdIndex);
		jwslogger.printLogger(CHECKOUT_CELL, "selected index",
				String.valueOf(selectdIndex));
		try {
			if ((selectedRecord.getCheckOutHistoryVO() != null)
					&& (selectedRecord.getCheckOutHistoryVO().getCheckoutOn() != null)) {

				DateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
				Calendar calendar = Calendar.getInstance();

				// date checkedout on
				convertedJson = gson.toJson(selectedRecord.getCheckOutHistoryVO()
						.getCheckoutOn());

				checkoutOn = objectMapper.readValue(convertedJson, String.class);

				long milliSeconds = Long.parseLong(checkoutOn);
				calendar.setTimeInMillis(milliSeconds);
				ckOn = formatter.format(calendar.getTime());

			}
			// contact email
			if ((selectedRecord.getCheckOutHistoryVO() != null)
					&& (selectedRecord.getCheckOutHistoryVO().getCheckoutInfo() != null)) {
				convertedJsonContact = gson.toJson(selectedRecord.getCheckOutHistoryVO()
						.getCheckoutInfo());

				checkoutinfo = objectMapper.readValue(convertedJsonContact, String.class);

			}

			// checkedoutby name
			if ((selectedRecord.getCheckOutHistoryVO() != null)
					&& (selectedRecord.getCheckOutHistoryVO().getCheckoutBy() != null)) {
				convertedJsonUser = gson.toJson(selectedRecord.getCheckOutHistoryVO()
						.getCheckoutBy());

				userVO = objectMapper.readValue(convertedJsonUser, UserVO.class);
				ckBy = userVO.getFirstName() + " " + userVO.getLastName();

			}
		}
		catch (IOException e) {
			throw e;
		}

		createPopup(ckBy, ckOn, checkoutinfo);

	}

	/**
	 * Creates the popup.
	 *
	 * @param username the username
	 * @param checkedoutdate the checkedoutdate
	 * @param contact the contact
	 */
	void createPopup(String username, String checkedoutdate, String contact) {// Create
																				// the
																				// custom
																				// dialog.
		Dialog<Pair<String, String>> dialog = new Dialog<>();
		// Set the button types.
		ButtonType loginButtonType = new ButtonType("OK", ButtonData.OK_DONE);
		dialog.getDialogPane().getButtonTypes()
				.addAll(loginButtonType, ButtonType.CANCEL);

		GridPane gridPane = new GridPane();
		gridPane.setHgap(INT_TEN);
		gridPane.setVgap(INT_TEN);
		gridPane.setPadding(new Insets(INT_TWENTY, INT_ONEFIFTY, INT_TEN, INT_TEN));

		Label usernameLabel = new Label();
		usernameLabel.setText("Checkedout By");
		Label usernameLabelValue = new Label();
		usernameLabelValue.setText(username);

		Label assigneddateLabel = new Label();
		assigneddateLabel.setText("Checkedout On");
		Label assigneddateLabelValue = new Label();
		if (checkedoutdate != null) {
			assigneddateLabelValue.setText(checkedoutdate.toUpperCase());
		}

		Label contactLabel = new Label();
		contactLabel.setText("Contact");
		Label contactLabelValue = new Label();
		contactLabelValue.setText(contact);

		gridPane.add(usernameLabel, 0, 1);
		gridPane.add(usernameLabelValue, INT_TWO, 1);

		gridPane.add(assigneddateLabel, 0, 0);
		gridPane.add(assigneddateLabelValue, INT_TWO, 0);

		gridPane.add(contactLabel, 0, INT_TWO);
		gridPane.add(contactLabelValue, INT_TWO, INT_TWO);

		dialog.getDialogPane().setContent(gridPane);
		dialog.setTitle("Asset Status");

		dialog.showAndWait();
	}

}