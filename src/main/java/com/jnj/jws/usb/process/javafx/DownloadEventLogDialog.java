/*
 * Javafx package
 */
package com.jnj.jws.usb.process.javafx;

import static com.jnj.jws.USBConstant.DOUBLE_FIVEZERO;
import static com.jnj.jws.USBConstant.INSETS_BOTTOM_TWENTYFIVE;
import static com.jnj.jws.USBConstant.INSETS_LEFT_TWENTYFIVE;
import static com.jnj.jws.USBConstant.INSETS_RIGHT_TWENTYFIVE;
import static com.jnj.jws.USBConstant.INSETS_TOP_TWENTYFIVE;
import static com.jnj.jws.USBConstant.INT_FIFTEEN;
import static com.jnj.jws.USBConstant.INT_FIVEHUNDRED;
import static com.jnj.jws.USBConstant.INT_FOURHUNDRED;
import static com.jnj.jws.USBConstant.INT_ONEFIFTY;
import static com.jnj.jws.USBConstant.INT_SEVENHUNDRED;
import static com.jnj.jws.USBConstant.INT_TEN;
import static com.jnj.jws.USBConstant.INT_THREE;
import static com.jnj.jws.USBConstant.INT_THREEHUNDRED;
import static com.jnj.jws.USBConstant.INT_TWENTY;
import static com.jnj.jws.USBConstant.INT_TWENTYFIVE;
import static com.jnj.jws.USBConstant.INT_TWO;
import static com.jnj.jws.USBConstant.REQARGS_LENGTH_TWO;
import static com.jnj.jws.USBConstant.VGAP_GRIDPANE_TEN;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.jnj.jws.usb.process.DownloadEvent;
import com.jnj.jws.util.EncryptDecryptUtil;
import com.jnj.jws.util.JavaWebStartLogger;
import com.jnj.jws.vo.SearchEventLogVO;
import com.jnj.jws.vo.UserHeaderVO;

import javafx.concurrent.Task;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Side;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.DirectoryChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Pair;
import jiconfont.icons.GoogleMaterialDesignIcons;
import jiconfont.javafx.IconFontFX;
import jiconfont.javafx.IconNode;

/**
 * DownloadEventLogDialog class.
 * @author Cognizant
 * @version 1.0
 * @serial 1.0
 * @since 1.0
 */
@SuppressWarnings("restriction")
public class DownloadEventLogDialog extends DownloadEvent {

	/** The css. */
	private String css = null;

	/** The userheader json. */
	private String userheaderJson;

	/** The searchasset json. */
	private String searchassetJson;

	/** The task update stage. */
	private final Stage taskUpdateStage = new Stage(StageStyle.UTILITY);

	/** The i path. */
	private Button iPath = new Button();

	/** The selected directory. */
	private File selectedDirectory = null;

	/** The folder path status. */
	private boolean folderPathStatus = false;

	/** The browse button. */
	private Button browseButton = new Button("Browse");

	/** The download button. */
	private Button downloadButton = new Button("Proceed");

	/** The path. */
	private TextField path = new TextField("Folder Path to Download The Files");

	/** The dialog. */
	private Stage dialog = new Stage();

	/** The req args. */
	private String[] reqArgs = null;

	/** The jwttoken. */
	private String jwttoken = null;

	/** The userheadervo. */
	private UserHeaderVO userheadervo;

	/** The jwslogger. */
	private JavaWebStartLogger jwslogger = new JavaWebStartLogger();

	/** The Constant FN_DOWNLOAD. */
	private static final String FN_DOWNLOAD = "download";

	/** The Constant FN_DOWNLOADEVENTLOGDIALOG. */
	private static final String FN_DOWNLOADEVENTLOGDIALOG = "DownloadEventLogDialog";

	/** The Constant DOWNLOAD_EVENT_TITLE. */
	private static final String DOWNLOAD_EVENT_TITLE = "Download Event Logs";

	/**
	 * Instantiates a new download event log dialog.
	 *
	 * @param args the args
	 */
	public DownloadEventLogDialog(String[] args) {
		reqArgs = Arrays.copyOf(args, args.length, String[].class);
		if (reqArgs[1] != null && reqArgs.length > 1) {
			ObjectMapper mapper = new ObjectMapper();
			try {
				EncryptDecryptUtil edutil = new EncryptDecryptUtil();
				String useheaderjson = edutil.parseJWT(reqArgs[1]);
				if (useheaderjson != null) {
					userheadervo = mapper.readValue(useheaderjson, UserHeaderVO.class);
					jwslogger.printLogger(DownloadEventLogDialog.class.getSimpleName(),
							FN_DOWNLOADEVENTLOGDIALOG,
							"username:" + userheadervo.getFirstName());

				}
				else {
					jwslogger.printLogger(DownloadEventLogDialog.class.getSimpleName(),
							FN_DOWNLOADEVENTLOGDIALOG, "Unable to derive userheadervo");
				}

			}
			catch (JsonParseException | JsonMappingException e) {
				jwslogger.printLogger(DownloadEventLogDialog.class.getSimpleName(),
						FN_DOWNLOADEVENTLOGDIALOG,
						"JsonException while mapping UserHeaderVo" + e);

			}
			catch (IOException e) {
				jwslogger.printLogger(DownloadEventLogDialog.class.getSimpleName(),
						FN_DOWNLOADEVENTLOGDIALOG,
						"IOException while mapping UserHeaderVo" + e);
			}
		}
		/**
		 * Getting jws token
		 */
		if (reqArgs.length > 0) {
			jwttoken = reqArgs[0];
			jwslogger.printLogger(DownloadEventLogDialog.class.getSimpleName(),
					FN_DOWNLOADEVENTLOGDIALOG, "JWT Token=" + jwttoken);
		}
		if (reqArgs.length > 1) {
			userheaderJson = reqArgs[1];
			jwslogger.printLogger(DownloadEventLogDialog.class.getSimpleName(),
					FN_DOWNLOADEVENTLOGDIALOG, "userheaderJson=" + userheaderJson);
		}
		if (reqArgs.length > REQARGS_LENGTH_TWO) {
			Gson gson = new Gson();
			searchassetJson = gson.fromJson(reqArgs[REQARGS_LENGTH_TWO], String.class);
			jwslogger.printLogger(DownloadEventLogDialog.class.getSimpleName(),
					FN_DOWNLOADEVENTLOGDIALOG, "searchassetJson=" + searchassetJson);
		}
	}

	/**
	 * Creates the usb list grid.
	 *
	 * @param stage the stage
	 */
	public void createUsbListGrid(final Stage stage) {
		initializeTableViewGrid(stage);
	}

	/**
	 * Method to initialize table view Grid Downloadeventlog.
	 *
	 * @param stage the stage
	 */
	void initializeTableViewGrid(final Stage stage) {
		css = this.getClass().getClassLoader().getResource("stylesheet.css")
				.toExternalForm();
		stage.setTitle("JavaFX Welcome");
		/**
		 * Initialise Vbox
		 */
		VBox dialogVbox = new VBox(INT_FIVEHUNDRED);
		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(INT_TWENTYFIVE);
		grid.setVgap(VGAP_GRIDPANE_TEN);
		grid.setPadding(new Insets(INSETS_TOP_TWENTYFIVE, INSETS_RIGHT_TWENTYFIVE,
				INSETS_BOTTOM_TWENTYFIVE, INSETS_LEFT_TWENTYFIVE));
		// grid.autosize()
		/**
		 * Icon Font initialising
		 */
		IconFontFX.register(GoogleMaterialDesignIcons.getIconFont());
		Label accLbl = new Label("Please choose a directory and click on Proceed Button");
		/**
		 * Checking the mandatefields(Validation)
		 */
		checkTheMandateFields(folderPathStatus);
		/**
		 * Setting Info Button Style
		 */
		iButtonStyle();
		/**
		 * Setting Info Button Message
		 */
		iButtonMessage();
		/**
		 * Setting Download Path in UI
		 */
		downloadPathTextfield();

		grid.add(accLbl, 0, 0, INT_THREE, 1);
		grid.add(path, 0, 1);
		grid.add(browseButton, 1, 1);
		grid.add(iPath, INT_TWO, 1);
		grid.add(downloadButton, 0, INT_TWO, INT_TWO, 1);
		downloadButton.setDisable(true);
		/**
		 * Validation check Listners
		 */
		validationChangeListners(stage);

		dialogVbox.getChildren().add(grid);
		Scene dialogScene = new Scene(dialogVbox, INT_SEVENHUNDRED, INT_THREEHUNDRED);
		dialogScene.getStylesheets().add(css);
		dialog.setScene(dialogScene);
		dialog.setTitle(DOWNLOAD_EVENT_TITLE);
		dialog.show();

	}

	/**
	 * Download path textfield.
	 */
	private void downloadPathTextfield() {
		Color colorGrey = Color.web("#5e8775");
		path.setStyle("-fx-text-inner-color: grey;");
		path.setEditable(false);
		path.setPrefWidth(INT_FOURHUNDRED);
		IconNode iconNodeIpath = new IconNode(GoogleMaterialDesignIcons.INFO_OUTLINE);
		iconNodeIpath.setIconSize(INT_FIFTEEN);
		iconNodeIpath.setFill(colorGrey);
		iPath.setGraphic(iconNodeIpath);

	}

	/**
	 * I button style.
	 */
	private void iButtonStyle() {
		iPath.getStyleClass().add("custom-button");

	}

	/**
	 * Status check for validation.
	 *
	 * @return the search event log VO
	 */
	private SearchEventLogVO statusCheckForValidation() {
		SearchEventLogVO searcheventlogvo = null;
		ObjectMapper mapper = new ObjectMapper();
		try {
			searcheventlogvo = mapper.readValue(searchassetJson, SearchEventLogVO.class);
		}
		catch (IOException e) {
			jwslogger.printLogger(DownloadEventLogDialog.class.getSimpleName(),
					"statusCheckForValidation",
					"IOException inside statusCheckForValidation:" + e);
		}
		return searcheventlogvo;
	}

	/**
	 * Validation change listners.
	 *
	 * @param stage the stage
	 */
	private void validationChangeListners(Stage stage) {
		/**
		 * Browse Button on Action Listner
		 */
		browseButton.setOnAction(event -> {
			DirectoryChooser directoryChooser = new DirectoryChooser();
			selectedDirectory = directoryChooser.showDialog(dialog);

			if (selectedDirectory == null) {
				folderPathStatus = false;
				checkTheMandateFields(folderPathStatus);
				path.setText("Folder Path to Download The Files");
			}
			else {
				folderPathStatus = true;
				checkTheMandateFields(folderPathStatus);
				path.setText(selectedDirectory.getAbsolutePath());
			}
		});

		downloadButton.setOnAction(event -> {
			SearchEventLogVO searchlogvo = statusCheckForValidation();
			searchlogvo.setUserRoleIds(userheadervo.getUserRoleIds());
			if (path != null && !path.getText().isEmpty()) {
				searchlogvo.setFolderPath(path.getText());
			}

			try {
				runDownloadTask(searchlogvo, stage);
			}
			catch (Exception js) {
				jwslogger.printLogger(DownloadEventLogDialog.class.getSimpleName(),
						"validationChangeListners", "Exception while runtask" + js);

			}
		});

	}

	/**
	 * Check the mandate fields.
	 *
	 * @param folderPath the folder path
	 */
	private void checkTheMandateFields(boolean folderPath) {
		jwslogger.printLogger(DownloadEventLogDialog.class.getSimpleName(),
				"checkTheMandateFields", "folderPath" + folderPath);

		if (folderPath) {
			if (selectedDirectory.getAbsolutePath().length() > 0) {
				downloadButton.setDisable(false);
			}
			else {
				downloadButton.setDisable(true);
			}

		}
		else {
			downloadButton.setDisable(true);
		}
	}

	/**
	 * I button message.
	 */
	private void iButtonMessage() {

		MenuItem fileSelectionTypeMenu = new MenuItem(
				"Please select the Folder Path to download the files");

		final ContextMenu contextMenufileSelection = new ContextMenu();
		contextMenufileSelection.getItems().addAll(fileSelectionTypeMenu);
		iPath.setContextMenu(contextMenufileSelection);

		iPath.setOnAction(e -> contextMenufileSelection.show(iPath, Side.RIGHT, 0, 0));

	}

	/**
	 * Run download task.
	 *
	 * @param searchlogvo the searchlogvo
	 * @param stage the stage
	 * @return the string
	 */
	private String runDownloadTask(SearchEventLogVO searchlogvo, Stage stage) {
		taskUpdateStage.initModality(Modality.APPLICATION_MODAL);
		taskUpdateStage.initOwner(stage);
		final double wndwWidth = 300.0d;
		taskUpdateStage.setTitle(DOWNLOAD_EVENT_TITLE);
		Label updateLabel = new Label("Running tasks...");
		updateLabel.setPrefWidth(wndwWidth);
		ProgressIndicator progress = new ProgressIndicator();
		progress.setPrefWidth(wndwWidth);
		VBox updatePane = new VBox();
		updatePane.setPadding(new Insets(INT_TEN));
		updatePane.setSpacing(DOUBLE_FIVEZERO);
		updatePane.getChildren().addAll(updateLabel, progress);
		taskUpdateStage.setScene(new Scene(updatePane));
		taskUpdateStage.show();
		final Task<String> longTask = new Task<String>() {
			private String downloadEventLog = null;

			@Override
			protected String call() throws Exception {
				downloadEventLog = download(searchlogvo);
				return downloadEventLog;
			}
		};
		longTask.setOnFailed(t -> {

			downloadCompleteDialog("Download Failed");
			dialog.hide();
			taskUpdateStage.hide();
			longTask.cancel();
		});
		longTask.setOnSucceeded(t -> {
			taskUpdateStage.hide();
			if (longTask.getValue() != null && !longTask.getValue().isEmpty()) {
				downloadCompleteDialog(longTask.getValue());

			}
			else {
				downloadCompleteDialog("Download Failed.Please try again");

			}
			longTask.cancel();
		});
		progress.progressProperty().bind(longTask.progressProperty());
		updateLabel.textProperty().bind(longTask.messageProperty());

		taskUpdateStage.show();
		new Thread(longTask).start();
		return longTask.getValue();
	}

	/**
	 * Download.
	 *
	 * @param searchlogvo the searchlogvo
	 * @return the string
	 */
	private String download(SearchEventLogVO searchlogvo) {
		String searchEventLog = null;
		ObjectMapper mapper = new ObjectMapper();
		String searchEventLogjsonString = null;
		try {
			if (searchlogvo != null) {
				searchEventLogjsonString = mapper.writeValueAsString(searchlogvo);
				jwslogger.printLogger(DownloadEventLogDialog.class.getSimpleName(),
						FN_DOWNLOAD, "CREATED JSON" + searchEventLogjsonString);

				searchEventLog = downloadFile(searchEventLogjsonString, jwttoken);
				jwslogger.printLogger(DownloadEventLogDialog.class.getSimpleName(),
						FN_DOWNLOAD, "FINAL RESPONSE" + searchEventLog);
			}
		}
		catch (Exception e) {
			jwslogger.printLogger(DownloadEventLogDialog.class.getSimpleName(),
					FN_DOWNLOAD, "Exception inside  download:" + e);
		}

		return searchEventLog;

	}

	/**
	 * Download complete dialog.
	 *
	 * @param text the text
	 */
	private void downloadCompleteDialog(String text) {
		css = this.getClass().getClassLoader().getResource("stylesheet.css")
				.toExternalForm();
		Dialog<Pair<String, String>> downloaddialog = new Dialog<>();
		downloaddialog.setTitle(DOWNLOAD_EVENT_TITLE);
		ButtonType loginButtonType = new ButtonType("OK", ButtonData.OK_DONE);
		downloaddialog.getDialogPane().getButtonTypes().addAll(loginButtonType);
		GridPane gridPane = new GridPane();
		gridPane.setHgap(INT_TEN);
		gridPane.setVgap(INT_TEN);
		gridPane.setPadding(new Insets(INT_TWENTY, INT_ONEFIFTY, INT_TEN, INT_TEN));
		Label usernameLabel = new Label();
		Label textLabel = new Label();
		if ((userheadervo != null) && (userheadervo.getFirstName() != null)) {
			usernameLabel.setText("Dear " + userheadervo.getFirstName() + ",");
		}
		textLabel.setText(text);
		gridPane.add(usernameLabel, 0, 0);
		gridPane.add(textLabel, 0, 1);

		downloaddialog.getDialogPane().setContent(gridPane);
		downloaddialog.getDialogPane().getStylesheets().add(css);
		/* Optional<Pair<String, String>> result */
		downloaddialog.showAndWait();
		dialog.hide();
	}
}
