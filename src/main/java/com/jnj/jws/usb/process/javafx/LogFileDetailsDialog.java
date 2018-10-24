/*
 * Javafx package
 */
package com.jnj.jws.usb.process.javafx;

import static com.jnj.jws.USBConstant.ACC_NAME;
import static com.jnj.jws.USBConstant.APPLET_PROP;
import static com.jnj.jws.USBConstant.ASSET_SERIAL_NMBR;
import static com.jnj.jws.USBConstant.AUTHORIZATION;
import static com.jnj.jws.USBConstant.BEARER;
import static com.jnj.jws.USBConstant.CURRENT_VERSION;
import static com.jnj.jws.USBConstant.CUSTOM_BUTTON;
import static com.jnj.jws.USBConstant.DOUBLE_FIVEZERO;
import static com.jnj.jws.USBConstant.GET_SOFTWARE_VERSION;
import static com.jnj.jws.USBConstant.HTTP_ERROR;
import static com.jnj.jws.USBConstant.INSETS_BOTTOM_TWENTYFIVE;
import static com.jnj.jws.USBConstant.INSETS_LEFT_TWENTYFIVE;
import static com.jnj.jws.USBConstant.INSETS_RIGHT_TWENTYFIVE;
import static com.jnj.jws.USBConstant.INSETS_TOP_TWENTYFIVE;
import static com.jnj.jws.USBConstant.INT_EIGHT;
import static com.jnj.jws.USBConstant.INT_EIGTHHUNDRED;
import static com.jnj.jws.USBConstant.INT_FIFTEEN;
import static com.jnj.jws.USBConstant.INT_FIVE;
import static com.jnj.jws.USBConstant.INT_FIVEHUNDRED;
import static com.jnj.jws.USBConstant.INT_FORTY;
import static com.jnj.jws.USBConstant.INT_FOUR;
import static com.jnj.jws.USBConstant.INT_NINE;
import static com.jnj.jws.USBConstant.INT_NINETYFOUR;
import static com.jnj.jws.USBConstant.INT_ONEFIFTY;
import static com.jnj.jws.USBConstant.INT_ONESEVENTEEN;
import static com.jnj.jws.USBConstant.INT_ONETHIRTYFIVE;
import static com.jnj.jws.USBConstant.INT_SEVEN;
import static com.jnj.jws.USBConstant.INT_SEVENHUNDRED;
import static com.jnj.jws.USBConstant.INT_SEVENTY;
import static com.jnj.jws.USBConstant.INT_SIX;
import static com.jnj.jws.USBConstant.INT_SIXHUNDRED;
import static com.jnj.jws.USBConstant.INT_TEN;
import static com.jnj.jws.USBConstant.INT_THOUSAND;
import static com.jnj.jws.USBConstant.INT_THOUSANDTHREEHUNDRED;
import static com.jnj.jws.USBConstant.INT_THREE;
import static com.jnj.jws.USBConstant.INT_THREEHUNDRED;
import static com.jnj.jws.USBConstant.INT_TWELVE;
import static com.jnj.jws.USBConstant.INT_TWENTYFIVE;
import static com.jnj.jws.USBConstant.INT_TWO;
import static com.jnj.jws.USBConstant.INT_TWOFIFTY;
import static com.jnj.jws.USBConstant.INT_TWOHUNDRED;
import static com.jnj.jws.USBConstant.POST_UPDATE_FILES;
import static com.jnj.jws.USBConstant.POST_UPDATE_FILE_URL;
import static com.jnj.jws.USBConstant.PREVIOUS_VERSION;
import static com.jnj.jws.USBConstant.PRE_UPDATE_FILES;
import static com.jnj.jws.USBConstant.REASON_FOR_FAILURE;
import static com.jnj.jws.USBConstant.REASON_FOR_MANUAL_UPDATE;
import static com.jnj.jws.USBConstant.SERVICING_COUNTRIES;
import static com.jnj.jws.USBConstant.UPDATE_STATUS;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jnj.jws.USBConstant;
import com.jnj.jws.customui.operations.AutoCompleteComboBoxListener;
import com.jnj.jws.usb.process.DeleteFileDetails;
import com.jnj.jws.util.JavaWebStartLogger;
import com.jnj.jws.vo.AssetLocation;
import com.jnj.jws.vo.CountryVO;
import com.jnj.jws.vo.FileProcessResponseVo;
import com.jnj.jws.vo.FileUploadStatusEnum;
import com.jnj.jws.vo.MandatoryFieldsVO;
import com.jnj.jws.vo.PackageVO;
import com.jnj.jws.vo.PostUpdateFileVO;
import com.jnj.jws.vo.ProcessUSBGenericVO;
import com.jnj.jws.vo.ReasonVO;
import com.jnj.jws.vo.UpgradeStatusVO;
import com.jnj.jws.vo.UsbAssetVO;
import com.jnj.jws.vo.UserHeaderVO;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Side;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import javafx.util.StringConverter;
import jiconfont.icons.GoogleMaterialDesignIcons;
import jiconfont.javafx.IconFontFX;
import jiconfont.javafx.IconNode;

/**
 * Class to open Log file details dialog.
 *
 * @author 555786
 * @since 1.0
 * @category serial 1.0
 * @version 1.0
 */
public class LogFileDetailsDialog extends ManageUsbDialog {

	/** The css. */
	private String css = null;

	/** The dialog. */
	private final Stage dialog = new Stage();

	/** The properties. */
	private Properties properties;

	/** The table row. */
	private TableRow tableRow;

	/** The table view. */
	private TableView tableView;

	/** The data. */
	private ObservableList<UsbAssetVO> data;

	/** The error. */
	private StringBuilder error = new StringBuilder(
			"Invalid or Missing Mandatory fields ");

	/** The postupdate dialog. */
	// Post update file status starting
	private final Stage postupdateDialog = new Stage();

	/** The cancel button. */
	private Button cancelButton = new Button("Cancel");

	/** The complete button. */
	private Button completeButton = new Button("Complete");

	/** The acc fld. */
	private TextField accFld = new TextField();

	/** The asset fld. */
	private TextField assetFld = new TextField();

	/** The cv fld. */
	private TextField cvFld = new TextField();

	/** The uc fld. */
	private DatePicker ucFld;

	/** The la fld. */
	private TextField laFld = new TextField();

	/** The city fld. */
	private TextField cityFld = new TextField();

	/** The state fld. */
	private TextField stateFld = new TextField();

	/** The zip fld. */
	private TextField zipFld = new TextField();

	/** The comments fld. */
	private TextArea commentsFld = new TextArea();

	/** The manual reason cmbx. */
	private ComboBox<ReasonVO> manualReasonCmbx = new ComboBox<>();

	/** The current version cmbx. */
	private ComboBox<String> currentVersionCmbx = new ComboBox<>();

	/** The previous version cmbx. */
	private ComboBox<String> previousVersionCmbx = new ComboBox<>();

	/** The upgrade status cmbx. */
	private ComboBox<String> upgradeStatusCmbx = new ComboBox<>();

	/** The confrm checkbox. */
	private CheckBox confrmCheckbox = new CheckBox("Confirm Address *");

	/** The update op checkbox. */
	private CheckBox updateOpCheckbox = new CheckBox(
			"Updated operator manual is handed over to the hospital.");

	/** The asset button. */
	private Button assetButton = new Button();

	/** The current version button. */
	private Button currentVersionButton = new Button();

	/** The update status button. */
	private Button updateStatusButton = new Button();

	/** The completed on button. */
	private Button completedOnButton = new Button();

	/** The location address button. */
	private Button locationAddressButton = new Button();

	/** The previous version button. */
	private Button previousVersionButton = new Button();

	/** The accountname button. */
	private Button accountnameButton = new Button();

	/** The reason for manual update button. */
	private Button reasonForManualUpdateButton = new Button();

	/** The failure reason info button. */
	private Button failureReasonInfoButton = new Button();

	/** The comment button. */
	private Button commentButton = new Button();

	/** The asset lbl. */
	private Label assetLbl = new Label(ASSET_SERIAL_NMBR);

	/** The acc lbl. */
	private Label accLbl = new Label(ACC_NAME);

	/** The cv lbl. */
	private Label cvLbl = new Label(CURRENT_VERSION);

	/** The pv lbl. */
	private Label pvLbl = new Label(PREVIOUS_VERSION);

	/** The uc lbl. */
	private Label ucLbl = new Label("Upgrade Completed On *");

	/** The us lbl. */
	private Label usLbl = new Label("Upgrade Status *");

	/** The la lbl. */
	private Label laLbl = new Label("Location Address *");

	/** The uplaod lbl. */
	private Label uplaodLbl = new Label(
			"Please state the reason for uploading a new file.");

	/** The reason lbl. */
	private Label reasonLbl = new Label("Reason For Manual Update *");

	/** The comments lbl. */
	private Label commentsLbl = new Label("Comments");

	/** The reason for failure lbl. */
	private Label reasonForFailureLbl = new Label("Reason For Failure *");

	/** The required lbl. */
	private Label requiredLbl = new Label("* Represents required field");

	/** The stage. */
	private Stage stage;

	/** The usbtable. */
	private TableView<UsbAssetVO> usbtable;

	/** The j token. */
	private String jToken;

	/** The postupdate list. */
	private List<UsbAssetVO> postupdateList;

	/** The failure reason cmb. */
	private ComboBox<ReasonVO> failureReasonCmb = new ComboBox<>();

	/** The ismanual file. */
	private boolean ismanualFile = false;

	/** The file name. */
	private String fileName = null;

	/** The complete stage. */
	private final Stage completeStage = new Stage(StageStyle.UTILITY);

	/** The country cmb box. */
	private ComboBox<String> countryCmbBox = new ComboBox();

	/** The i country. */
	private Button iCountry = new Button();

	/** The country map. */
	private Map<String, String> countryMap = new HashMap<>();

	/** The version map. */
	private Map<String, String> versionMap = new HashMap<>();

	/** The status map. */
	private Map<String, String> statusMap = new HashMap<>();

	/** The userheader VO. */
	private UserHeaderVO userheaderVO;

	/** The other wwid. */
	private String otherWwid;

	/** The page number of table. */
	private int pageNrOfTable;

	/** The rows per page. */
	private int rowsPerPage;

	/** The logger. */
	private JavaWebStartLogger logger = new JavaWebStartLogger();

	/** The genericvo. */
	private ProcessUSBGenericVO genericvo;

	/** The madatory VO. */
	private MandatoryFieldsVO madatoryVO = new MandatoryFieldsVO();

	/** The pattern. */
	private String pattern = "dd-MMM-yyyy";

	/** The date formatter. */
	private DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(pattern);

	/** The current version menu. */
	private MenuItem currentVersionMenu = new MenuItem(
			"Current Version of the software is auto-populated.");

	/** The previous version menu. */
	private MenuItem previousVersionMenu = new MenuItem(
			"Previous Version of software is auto-populated");

	/** The upgradestatus menu. */
	private MenuItem upgradestatusMenu = new MenuItem("Upgrade Status is auto-populated.");

	/** The status list. */
	private List<String> statusList = new ArrayList<>();

	/** The Constant CONTENT_TYPE. */
	private static final String CONTENT_TYPE = "Content-Type";

	/** The Constant APPLICATION_JSON. */
	private static final String APPLICATION_JSON = "application/json";

	/**
	 * Instantiates a new log file details dialog.
	 *
	 * @param fpvo the fpvo
	 * @param stage the stage
	 * @param genericVO the generic VO
	 * @param pageNumberOfTable the page number of table
	 * @param rowsPerPage2 the rows per page 2
	 */
	LogFileDetailsDialog(FileProcessResponseVo fpvo, final Stage stage,
			ProcessUSBGenericVO genericVO, int pageNumberOfTable, int rowsPerPage2) {
		/**
		 * Initialize values
		 */
		initializeValues(fpvo, stage, genericVO, pageNumberOfTable, rowsPerPage2);
	}

	/**
	 * Initialize values.
	 *
	 * @param fpvo the fpvo
	 * @param stage the stage
	 * @param genericVO the generic VO
	 * @param pageNumberOfTable the page number of table
	 * @param rowsPerPage2 the rows per page 2
	 */
	final void initializeValues(FileProcessResponseVo fpvo, final Stage stage,
			ProcessUSBGenericVO genericVO, int pageNumberOfTable, int rowsPerPage2) {
		initialize(genericVO, rowsPerPage2, pageNumberOfTable, stage);
		/**
		 * Setting the country combobox
		 */
		countryCombobox(genericvo.getJwtToken());
		/**
		 * Setting Asset Number
		 */
		if (fpvo.getAssetNumber() != null) {

			assetFld = new TextField(fpvo.getAssetNumber());
			fileName = fpvo.getFileName();
		}
		else {
			assetFld.setPromptText(ASSET_SERIAL_NMBR);
		}

		assetFld.setEditable(false);
		/**
		 * Setting Account Name
		 */
		if (fpvo.getAccountName() != null) {

			accFld = new TextField(fpvo.getAccountName());
		}
		else {
			accFld.setPromptText(ACC_NAME);

		}
		accFld.setEditable(false);
		/**
		 * Setting the version
		 */
		getVersion(jToken);
		/**
		 * Setting the current version
		 */
		currentVersion(fpvo);
		/**
		 * setting the previous version
		 */
		previousVersion(fpvo);
		/**
		 * Setting the Upgrade status
		 */
		upgradeStatus(fpvo);
		/**
		 * init the date picker
		 */
		ucFld = new DatePicker();
		ucFld.setEditable(false);
		/**
		 * Method to disable future dates for update completed on
		 */
		disableFutureDatesUpdateCompletedOn();
		/**
		 * Method to format date
		 */
		dateFormatterUpdateCompletedOn();
		/**
		 * Populate Location Details
		 */
		populateLocationDetails(fpvo);
		/**
		 * Prompting the comment box
		 */
		commentsFld.setPromptText("Type your comments here…");
		commentsFld.setWrapText(true);
		ucFld.setPromptText("--/--/----");
		/**
		 * Setting Reason in combobox
		 */
		manualReasonDropdown();
		/**
		 * Validation Listeners
		 */
		validationChangeListners();
		/**
		 * Styling I Button
		 */
		styleIButton(fpvo);
		/**
		 * Setting I button
		 */
		iButtonMessage(fpvo);
		/**
		 * Setting Grid pane Alignment
		 */
		gridPaneAlignment(fpvo);
		/**
		 * Checking the mandate fields
		 */
		checkTheMandateFields(madatoryVO);
	}

	private void initialize(ProcessUSBGenericVO genericVO, int rowsPerPage2,
			int pageNumberOfTable, Stage stge) {
		/**
		 * initModality
		 */
		completeStage.initModality(Modality.APPLICATION_MODAL);
		/**
		 * initOwner
		 */
		completeStage.initOwner(dialog);
		postupdateDialog.initOwner(stage);
		/**
		 * Setting Title
		 */
		dialog.setTitle("Log File Details");
		dialog.initModality(Modality.APPLICATION_MODAL);
		dialog.initOwner(stage);
		dialog.setResizable(false);
		/**
		 * Setting the title
		 */
		postupdateDialog.setTitle("Process Post-Update File");
		postupdateDialog.initModality(Modality.APPLICATION_MODAL);
		/**
		 * getting the tablerow
		 */
		tableRow = genericVO.getRow();

		/**
		 * getting the tableview
		 */
		tableView = genericVO.getTv();
		/**
		 * getting jwttoken
		 */
		this.jToken = genericVO.getJwtToken();
		/**
		 * Setting style sheet
		 */
		css = this.getClass().getClassLoader().getResource("stylesheet.css")
				.toExternalForm();
		this.stage = stge;
		/**
		 * Getting the values from VO
		 */
		this.postupdateList = genericVO.getPostupdateList();
		this.data = genericVO.getDatav();
		this.preupdateList = genericVO.getPreupdateList();
		this.usbtable = genericVO.getUsbassetTableview();
		this.userheaderVO = genericVO.getUserHeaderVO();
		this.otherWwid = genericVO.getOtherWwid();
		this.pageNrOfTable = pageNumberOfTable;
		this.rowsPerPage = rowsPerPage2;
		this.genericvo = genericVO;
	}

	/**
	 * Previous version.
	 *
	 * @param fpvo the fpvo
	 */
	void previousVersion(FileProcessResponseVo fpvo) {
		/**
		 * Getting the previous version
		 */
		if (fpvo.getPreviousVersion() != null) {
			logger.printLogger(LogFileDetailsDialog.class.getSimpleName(), "",
					"fpvo.getPreviousVersion()" + fpvo.getPreviousVersion());
			/**
			 * Checking the validate previous version
			 */
			boolean isvalid = validatePreviousVersion(fpvo.getPreviousVersion());
			if (isvalid) {
				/**
				 * Setting the previous version combobox
				 */
				previousVersionCmbx.setValue(fpvo.getPreviousVersion());
				madatoryVO.setPreviousVersionCombobox(true);
				previousVersionCmbx.setEditable(false);
				previousVersionCmbx.setDisable(true);
				previousVersionCmbx.setPrefWidth(INT_ONEFIFTY);
			}
			else {
				previousVersionMenu
						.setText("Please select the Previous Version of software from the drop down list.");
				previousVersionCombobox(genericvo.getJwtToken());
			}
		}
		else {
			previousVersionMenu
					.setText("Please select the Previous Version of software from the drop down list.");
			previousVersionCombobox(genericvo.getJwtToken());
		}
	}

	/**
	 * Current version.
	 *
	 * @param fpvo the fpvo
	 */
	void currentVersion(FileProcessResponseVo fpvo) {
		/**
		 * Checking the validate current version
		 */
		if (fpvo.getCurrentVersion() != null) {
			/**
			 * Checking the validate Current version
			 */
			boolean isvalid = validateCurrentVersion(fpvo.getCurrentVersion());
			logger.printLogger(LogFileDetailsDialog.class.getSimpleName(), "",
					"fpvo.getCurrentVersion()" + fpvo.getCurrentVersion());
			if (isvalid) {
				currentVersionCmbx.setValue(fpvo.getCurrentVersion());
				/**
				 * Setting the current version Combobox
				 */
				madatoryVO.setCurrentVersionCombobox(true);
				currentVersionCmbx.setEditable(false);
				currentVersionCmbx.setDisable(true);
				currentVersionCmbx.setPrefWidth(INT_ONEFIFTY);
			}
			else {
				currentVersionCombobox(genericvo.getJwtToken());
				currentVersionMenu
						.setText("Please select the Current Version of software from the drop down list.");
			}
		}
		else {
			currentVersionMenu
					.setText("Please select the Current Version of software from the drop down list.");
			currentVersionCombobox(genericvo.getJwtToken());
		}
	}

	/**
	 * Upgrade status.
	 *
	 * @param fpvo the fpvo
	 */
	void upgradeStatus(FileProcessResponseVo fpvo) {
		if (statusList.isEmpty()) {
			getUpgradeStatus(jToken);
		}
		if (fpvo.getUpdateStatus() != null) {
			boolean isvalid = validateStatus(fpvo.getUpdateStatus().getStatusName());
			if (isvalid) {
				upgradeStatusCmbx.setValue(fpvo.getUpdateStatus().getStatusName());
				madatoryVO.setUpdatestatus(true);
				upgradeStatusCmbx.setDisable(true);
				if (FileUploadStatusEnum.SUCCESS.getStatusCode().equalsIgnoreCase(
						fpvo.getUpdateStatus().getStatusCode())
						|| FileUploadStatusEnum.UPTODATE.getStatusCode()
								.equalsIgnoreCase(fpvo.getUpdateStatus().getStatusCode())) {
					madatoryVO.setNoreasonCombobox(true);
					upgradeStatusCmbx.setEditable(false);
					upgradeStatusCmbx.setDisable(true);
					upgradeStatusCmbx.setPrefWidth(INT_ONEFIFTY);
					disableFailureReason();
				}
				else {
					reasonForFailureLbl.setVisible(true);
					failureReasonCmb.setVisible(true);
					failureReasonInfoButton.setVisible(true);
				}
			}
			else {
				upgradestatusMenu
						.setText("Please select the Upgrade Status from the drop down list.");
				upgradeStatusCombobox();
				disableFailureReason();
			}
		}
		else {
			upgradestatusMenu
					.setText("Please select the Upgrade Status from the drop down list.");
			upgradeStatusCombobox();
			disableFailureReason();
		}
	}

	/**
	 * Populate location details.
	 *
	 * @param fpvo the fpvo
	 */
	void populateLocationDetails(FileProcessResponseVo fpvo) {
		if (fpvo.getLocationAddress() != null) {
			/**
			 * Checking the street null or not
			 */
			if (fpvo.getLocationAddress().getStreet() != null) {

				madatoryVO.setStreetaddress(true);

				laFld = new TextField(fpvo.getLocationAddress().getStreet());
			}
			else {
				madatoryVO.setStreetaddress(false);
				laFld.setPromptText("Street");
			}
			/**
			 * Checking the city null or not
			 */

			if (fpvo.getLocationAddress().getCity() != null) {
				cityFld = new TextField(fpvo.getLocationAddress().getCity());
				madatoryVO.setCity(true);
			}
			else {
				cityFld.setPromptText("City");
				madatoryVO.setCity(false);
			}
			/**
			 * Checking the state null or not
			 */

			if (fpvo.getLocationAddress().getState() != null) {
				stateFld = new TextField(fpvo.getLocationAddress().getState());
			}
			else {
				stateFld.setPromptText("State/Province");
			}
			/**
			 * Checking the country null or not
			 */

			if (fpvo.getLocationAddress().getCountry() != null) {
				boolean isvalid = validateCountry(fpvo.getLocationAddress().getCountry());
				if (isvalid) {
					countryCmbBox.setValue(fpvo.getLocationAddress().getCountry());
					madatoryVO.setCountry(true);
				}
			}
			else {
				madatoryVO.setCountry(false);
				countryCombobox(jToken);
			}
			/**
			 * Checking the zipcode null or not
			 */

			if (fpvo.getLocationAddress().getZipCode() != null) {
				zipFld = new TextField(fpvo.getLocationAddress().getZipCode());
			}
			else {
				zipFld.setPromptText("Zip/Postal Code");
			}
		}
		/**
		 * Setting the character length
		 */
		setCharaterLength();
	}

	/**
	 * Sets the charater length.
	 */
	private void setCharaterLength() {
		cityFld.addEventFilter(KeyEvent.KEY_TYPED,
				maxLength(USBConstant.CITY_FLD_MAX_LENGTH));
		stateFld.addEventFilter(KeyEvent.KEY_TYPED,
				maxLength(USBConstant.STATE_FLD_MAX_LENGTH));
		laFld.addEventFilter(KeyEvent.KEY_TYPED,
				maxLength(USBConstant.STREET_FLD_MAX_LENGTH));
		zipFld.addEventFilter(KeyEvent.KEY_TYPED,
				maxLength(USBConstant.ZIP_FLD_MAX_LENGTH));
		commentsFld.addEventFilter(KeyEvent.KEY_TYPED,
				maxLengthTextArea(USBConstant.COMMENTS_FLD_MAX_LENGTH));
	}

	/**
	 * Date formatter update completed on.
	 */
	private void dateFormatterUpdateCompletedOn() {
		ucFld.setConverter(new StringConverter<LocalDate>() {
			@Override
			public String toString(LocalDate date) {
				if (date != null) {
					return dateFormatter.format(date).toUpperCase();
				}
				return "";
			}

			@Override
			public LocalDate fromString(String string) {
				if (string != null && !string.isEmpty()) {
					return LocalDate.parse(string, dateFormatter);
				}
				else {
					return null;
				}
			}
		});
	}

	/**
	 * To string.
	 *
	 * @param date the date
	 * @return the string
	 */
	String toString(LocalDate date) {
		if (date != null) {
			return dateFormatter.format(date).toUpperCase();
		}
		else {
			return "";
		}
	}

	/**
	 * From string.
	 *
	 * @param string the string
	 * @return the local date
	 */
	LocalDate fromString(String string) {
		if (string != null && !string.isEmpty()) {
			return LocalDate.parse(string, dateFormatter);
		}
		else {
			return null;
		}
	}

	/**
	 * Disable future dates update completed on.
	 */
	private void disableFutureDatesUpdateCompletedOn() {
		Callback<DatePicker, DateCell> dayCellFactory = new Callback<DatePicker, DateCell>() {
			public DateCell call(final DatePicker datePicker) {
				return new DateCell() {
					@Override
					public void updateItem(LocalDate item, boolean empty) {
						super.updateItem(item, empty);
						DayOfWeek day = DayOfWeek.from(item);
						if (day == DayOfWeek.SATURDAY || day == DayOfWeek.SUNDAY) {
							this.setTextFill(Color.BLUE);
						}
						if (item.isAfter(LocalDate.now())) {
							this.setDisable(true);
						}
					}
				};
			}
		};
		/**
		 * Set the day cell factory to the date picker
		 */
		ucFld.setDayCellFactory(dayCellFactory);

	}

	/**
	 * Manual reason dropdown.
	 */
	private void manualReasonDropdown() {

		ObjectMapper objectmapper = new ObjectMapper();
		List<ReasonVO> resonFor = null;
		try {
			logger.printLogger(LogFileDetailsDialog.class.getSimpleName(),
					"manualReasonDropdown:", "jToken:" + jToken);
			/**
			 * To populate Manual Reason
			 */
			String reasonJson = getReasons(jToken, REASON_FOR_MANUAL_UPDATE);// to
																				// populate
																				// manual
																				// reason
			resonFor = objectmapper.readValue(reasonJson,
					new TypeReference<List<ReasonVO>>() {
					});
			/**
			 * Adding reasons in manual reason box
			 */
			manualReasonCmbx.getItems().addAll(resonFor);
			manualReasonCmbx.setPrefWidth(INT_THREEHUNDRED);
		}
		catch (Exception e) {
			logger.printLogger(LogFileDetailsDialog.class.getSimpleName(),
					"manualReasonDropdown", "reason for manual:Exception:" + e);
		}

	}

	/**
	 * Grid pane alignment.
	 *
	 * @param fpvo the fpvo
	 */
	private void gridPaneAlignment(FileProcessResponseVo fpvo) {
		GridPane gridpane = new GridPane();
		gridpane.setAlignment(Pos.CENTER);
		gridpane.setHgap(INT_TWENTYFIVE);
		gridpane.setVgap(INT_TEN);
		gridpane.setPadding(new Insets(INSETS_TOP_TWENTYFIVE, INSETS_RIGHT_TWENTYFIVE,
				INSETS_BOTTOM_TWENTYFIVE, INSETS_LEFT_TWENTYFIVE));

		/**
		 * Adding Asset Button in grid pane
		 */
		gridpane.add(assetButton, INT_TWO, 0);
		/**
		 * Adding Asset Button in grid pane
		 */

		gridpane.add(currentVersionButton, INT_TWO, 1);
		/**
		 * Adding Current Version Button in grid pane
		 */

		gridpane.add(updateStatusButton, INT_FIVE, INT_TWO);
		/**
		 * Adding Updated status Button in grid pane
		 */

		gridpane.add(completedOnButton, INT_TWO, INT_THREE);
		/**
		 * Adding Completed on Button in grid pane
		 */

		gridpane.add(locationAddressButton, INT_TWO, INT_FOUR);
		/**
		 * Adding Location Address in grid pane
		 */

		gridpane.add(previousVersionButton, INT_TWO, INT_TWO);
		/**
		 * Adding Asset Name in grid pane
		 */

		gridpane.add(accountnameButton, INT_FIVE, 1);
		gridpane.add(assetLbl, 0, 0);
		gridpane.add(assetFld, 1, 0);
		gridpane.add(cvLbl, 0, 1);
		gridpane.add(currentVersionCmbx, 1, 1);
		gridpane.add(pvLbl, 0, INT_TWO);
		gridpane.add(previousVersionCmbx, 1, INT_TWO);
		gridpane.add(ucLbl, 0, INT_THREE);
		gridpane.add(ucFld, 1, INT_THREE);
		gridpane.add(laLbl, 0, INT_FOUR);
		gridpane.add(laFld, 1, INT_FOUR);
		gridpane.add(cityFld, 1, INT_FIVE);
		gridpane.add(stateFld, 1, INT_SIX);
		gridpane.add(countryCmbBox, 1, INT_SEVEN);
		gridpane.add(zipFld, 1, INT_EIGHT);
		gridpane.add(confrmCheckbox, 1, INT_NINE);
		gridpane.add(requiredLbl, 0, INT_TWELVE, INT_TWO, 1);
		requiredLbl.getStyleClass().add("redText");

		gridpane.add(accLbl, INT_THREE, 1);
		gridpane.add(accFld, INT_FOUR, 1);
		gridpane.add(usLbl, INT_THREE, INT_TWO);
		gridpane.add(upgradeStatusCmbx, INT_FOUR, INT_TWO);

		reasonDropdownDesigner(gridpane, fpvo);

		gridpane.add(updateOpCheckbox, INT_THREE, INT_NINE, INT_TWO, 1);
		HBox hbox = new HBox(INT_TEN);
		hbox.getChildren().addAll(cancelButton, completeButton);
		hbox.setAlignment(Pos.CENTER);
		VBox dialogVbox = new VBox();
		dialogVbox.getChildren().addAll(gridpane, hbox);

		dialogBox(dialogVbox);

	}

	void dialogBox(VBox dialogVbox) {
		Scene dialogScene = new Scene(dialogVbox, INT_THOUSAND, INT_SEVENHUNDRED);
		dialogScene.getStylesheets().add(css);
		dialog.setScene(dialogScene);
		dialog.setHeight(INT_SEVENHUNDRED);
		dialog.setWidth(INT_THOUSANDTHREEHUNDRED);

		dialogScene.addEventFilter(MouseEvent.MOUSE_PRESSED,
				new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent mouseEvent) {
						boolean isvalidationSuccess;
						if (countryCmbBox.getValue() != null) {
							isvalidationSuccess = validateCountry(String
									.valueOf(countryCmbBox.getValue()));
						}
						else {
							isvalidationSuccess = validateCountry("");
						}
						if (isvalidationSuccess) {
							madatoryVO.setCountry(true);
						}
						else {
							madatoryVO.setCountry(false);
						}
						checkTheMandateFields(madatoryVO);
					}
				});
		dialog.show();
		/**
		 * Setting cancel button Onaction
		 */
		cancelButton.setOnAction(new EventHandler<ActionEvent>() {

			public void handle(ActionEvent t) {
				dialog.close();
			}

		});
	}

	/**
	 * Reason dropdown designer.
	 *
	 * @param gridpane the gridpane
	 * @param fpvo the fpvo
	 */
	void reasonDropdownDesigner(GridPane gridpane, FileProcessResponseVo fpvo) {
		if (fpvo.isManualFile()) {
			ismanualFile = true;
			gridpane.add(reasonLbl, INT_THREE, INT_FIVE);
			gridpane.add(manualReasonCmbx, INT_FOUR, INT_FIVE);
			gridpane.add(reasonForManualUpdateButton, INT_FIVE, INT_FIVE);
			manualReasonCmbx.setVisible(true);
		}
		else {
			manualReasonCmbx.setVisible(false);
		}

		ObjectMapper objectmapper = new ObjectMapper();
		List<ReasonVO> resonFor = null;
		try {
			logger.printLogger(LogFileDetailsDialog.class.getSimpleName(),
					"gridPaneAlignment:jToken", jToken);
			/**
			 * To populate Failure reason
			 */
			String reasonJson = getReasons(jToken, REASON_FOR_FAILURE);// to
																		// populate
																		// failure
																		// reason
			resonFor = objectmapper.readValue(reasonJson,
					new TypeReference<List<ReasonVO>>() {
					});
			failureReasonCmb.getItems().addAll(resonFor);
			failureReasonCmb.setPrefWidth(INT_THREEHUNDRED);

		}
		catch (Exception e) {
			logger.printLogger(LogFileDetailsDialog.class.getSimpleName(),
					"reasonDropdownDesigner",
					"Exception while reason for removal failure:" + e);
		}
		if (fpvo.isManualFile() || isFailedStatus(fpvo)) {
			gridpane.add(uplaodLbl, INT_THREE, INT_THREE, INT_TWO, 1);
			uplaodLbl.getStyleClass().add("greenBox1");
			uplaodLbl.setPrefWidth(INT_FIVEHUNDRED);
			gridpane.add(reasonForFailureLbl, INT_THREE, INT_FOUR);
			gridpane.add(failureReasonCmb, INT_FOUR, INT_FOUR);
			gridpane.add(failureReasonInfoButton, INT_FIVE, INT_FOUR);
		}
		if (showComments(fpvo)) {
			gridpane.add(commentsLbl, INT_THREE, INT_SIX);
			gridpane.add(commentsFld, INT_FOUR, INT_SIX, 1, INT_THREE);
			gridpane.add(commentButton, INT_FIVE, INT_SIX, 1, INT_THREE);
			commentsFld.setPrefHeight(INT_FORTY);
			commentsFld.setPrefWidth(INT_FORTY);
		}
	}

	/**
	 * Checks if is failed status.
	 *
	 * @param fpvo the fpvo
	 * @return true, if is failed status
	 */
	boolean isFailedStatus(FileProcessResponseVo fpvo) {
		boolean failedStatus = false;
		if (fpvo.getUpdateStatus() != null
				&& (FileUploadStatusEnum.FAILED.getStatusCode().equals(
						fpvo.getUpdateStatus().getStatusCode())
						|| FileUploadStatusEnum.IN_COMPLETE.getStatusCode().equals(
								fpvo.getUpdateStatus().getStatusCode()) || FileUploadStatusEnum.IN_PROGRESS
						.getStatusCode().equals(fpvo.getUpdateStatus().getStatusCode()))) {
			failedStatus = true;
		}
		return failedStatus;
	}

	/**
	 * Show comments.
	 *
	 * @param fpvo the fpvo
	 * @return true, if successful
	 */
	boolean showComments(FileProcessResponseVo fpvo) {
		boolean comments = false;
		if (isFailedStatus(fpvo) || fpvo.isManualFile()) {
			comments = true;
		}
		return comments;
	}

	/**
	 * Menu item asset.
	 */
	void menuItemAsset() {
		/**
		 * Adding menuitem for serialnumber
		 */
		MenuItem assetserialNumberMenu = new MenuItem(
				"Asset Serial Number is auto-populated.");

		final ContextMenu contextMenuAssetSerialNumber = new ContextMenu();
		contextMenuAssetSerialNumber.getItems().addAll(assetserialNumberMenu);
		assetButton.setContextMenu(contextMenuAssetSerialNumber);
		/**
		 * Asset Button click action
		 */
		assetButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				contextMenuAssetSerialNumber.show(assetButton, Side.RIGHT, 0, 0);
			}
		});
	}

	/**
	 * Menu item sw version.
	 */
	void menuItemSwVersion() {
		/**
		 * Adding menuitem for current version
		 */
		final ContextMenu contextMenuCurrentVersion = new ContextMenu();
		contextMenuCurrentVersion.getItems().addAll(currentVersionMenu);
		currentVersionButton.setContextMenu(contextMenuCurrentVersion);
		/***
		 * Current Version setonaction
		 */
		currentVersionButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				contextMenuCurrentVersion.show(currentVersionButton, Side.RIGHT, 0, 0);
			}
		});
	}

	/**
	 * Menu item upgrade status.
	 */
	void menuItemUpgradeStatus() {
		/**
		 * Adding menuitem for Upgrade Status
		 */
		final ContextMenu contextMenuUpdatestatus = new ContextMenu();
		contextMenuUpdatestatus.getItems().addAll(upgradestatusMenu);
		updateStatusButton.setContextMenu(contextMenuUpdatestatus);
		/***
		 * Update Status setonaction
		 */
		updateStatusButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				contextMenuUpdatestatus.show(updateStatusButton, Side.RIGHT, 0, 0);
			}
		});
	}

	/**
	 * Menu item upgrade completion date.
	 */
	void menuItemUpgradeCompletionDate() {
		/**
		 * Adding menuitem for Completion date
		 */
		MenuItem updatecompletiondateMenu = new MenuItem(
				"Please select software upgrade completion date.");
		final ContextMenu contextMenuUpdateCompletionDate = new ContextMenu();
		contextMenuUpdateCompletionDate.getItems().addAll(updatecompletiondateMenu);
		completedOnButton.setContextMenu(contextMenuUpdateCompletionDate);
		/***
		 * Completed on setonaction
		 */
		completedOnButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				contextMenuUpdateCompletionDate.show(completedOnButton, Side.RIGHT, 0, 0);
			}
		});
	}

	/**
	 * Menu item location address.
	 */
	void menuItemLocationAddress() {
		/**
		 * Adding menuitem for Location Address
		 */
		MenuItem locationAddressMenu = new MenuItem(
				"Please enter the Address details of GEN11 generator");
		final ContextMenu contextMenulocationAddress = new ContextMenu();
		contextMenulocationAddress.getItems().addAll(locationAddressMenu);
		locationAddressButton.setContextMenu(contextMenulocationAddress);
		/***
		 * Location Address Button setonaction
		 */
		locationAddressButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				contextMenulocationAddress.show(locationAddressButton, Side.RIGHT, 0, 0);
			}
		});

	}

	/**
	 * Menu item previous version.
	 */
	void menuItemPreviousVersion() {
		/**
		 * Adding menuitem for previous version
		 */
		final ContextMenu contextMenuPreviousVersion = new ContextMenu();
		contextMenuPreviousVersion.getItems().addAll(previousVersionMenu);
		previousVersionButton.setContextMenu(contextMenuPreviousVersion);
		/***
		 * Previous Version setonaction
		 */
		previousVersionButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				contextMenuPreviousVersion.show(previousVersionButton, Side.RIGHT, 0, 0);
			}
		});
	}

	/**
	 * Menu item account name.
	 */
	void menuItemAccountName() {
		/**
		 * Adding menuitem for Account Name
		 */
		MenuItem accountnameButtonMenu = new MenuItem("Account Name is auto-populated");
		final ContextMenu contextMenuAccountnameButton = new ContextMenu();
		contextMenuAccountnameButton.getItems().addAll(accountnameButtonMenu);
		accountnameButton.setContextMenu(contextMenuAccountnameButton);
		/***
		 * Account Name setonaction
		 */
		accountnameButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				contextMenuAccountnameButton.show(accountnameButton, Side.RIGHT, 0, 0);
			}
		});
	}

	/**
	 * Menu item failure reason.
	 *
	 * @param fpvo the fpvo
	 */
	void menuItemFailureReason() {
		MenuItem failureReasonMenu = new MenuItem(
				"Please select the Reason for failure from the drop down list.");
		final ContextMenu contextMenuFailureReason = new ContextMenu();
		contextMenuFailureReason.getItems().addAll(failureReasonMenu);
		failureReasonInfoButton.setContextMenu(contextMenuFailureReason);
		/***
		 * Failure reason Button setonaction
		 */
		failureReasonInfoButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				contextMenuFailureReason.show(failureReasonInfoButton, Side.RIGHT, 0, 0);
			}
		});
		MenuItem commentMenu = new MenuItem("Please enter your Comments.");
		final ContextMenu contextMenuComment = new ContextMenu();
		contextMenuComment.getItems().addAll(commentMenu);
		commentButton.setContextMenu(contextMenuComment);
		/***
		 * Comment Button setonaction
		 */
		commentButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				contextMenuComment.show(commentButton, Side.RIGHT, 0, 0);
			}
		});
	}

	/**
	 * Menu item manual reason.
	 *
	 * @param fpvo the fpvo
	 */
	void menuItemManualReason(FileProcessResponseVo fpvo) {

		if (fpvo.isManualFile()) {
			MenuItem reasonForManualUpdateButtonMenu = new MenuItem(
					"Please select the Reason for manual update from the drop down list.");
			final ContextMenu contextMenuReasonForManualUpdateButton = new ContextMenu();
			contextMenuReasonForManualUpdateButton.getItems().addAll(
					reasonForManualUpdateButtonMenu);
			reasonForManualUpdateButton
					.setContextMenu(contextMenuReasonForManualUpdateButton);
			reasonForManualUpdateButton.setOnAction(new EventHandler<ActionEvent>() {
				public void handle(ActionEvent e) {
					contextMenuReasonForManualUpdateButton.show(
							reasonForManualUpdateButton, Side.RIGHT, 0, 0);
				}
			});
			MenuItem commentMenu = new MenuItem("Please enter your Comments.");
			final ContextMenu contextMenuComment = new ContextMenu();
			contextMenuComment.getItems().addAll(commentMenu);
			commentButton.setContextMenu(contextMenuComment);
			commentButton.setOnAction(new EventHandler<ActionEvent>() {
				public void handle(ActionEvent e) {
					contextMenuComment.show(commentButton, Side.RIGHT, 0, 0);
				}
			});
		}
	}

	/**
	 * I button message.
	 *
	 * @param fpvo the fpvo
	 */
	private void iButtonMessage(FileProcessResponseVo fpvo) {
		menuItemAsset();
		menuItemSwVersion();
		menuItemUpgradeStatus();
		menuItemUpgradeCompletionDate();
		menuItemLocationAddress();
		menuItemPreviousVersion();
		menuItemAccountName();
		menuItemFailureReason();
		menuItemManualReason(fpvo);
	}

	/**
	 * Style I button.
	 *
	 * @param fpvo the fpvo
	 */
	void styleIButton(FileProcessResponseVo fpvo) {
		assetButton.getStyleClass().add(CUSTOM_BUTTON);
		currentVersionButton.getStyleClass().add(CUSTOM_BUTTON);
		updateStatusButton.getStyleClass().add(CUSTOM_BUTTON);
		completedOnButton.getStyleClass().add(CUSTOM_BUTTON);
		locationAddressButton.getStyleClass().add(CUSTOM_BUTTON);
		previousVersionButton.getStyleClass().add(CUSTOM_BUTTON);
		accountnameButton.getStyleClass().add(CUSTOM_BUTTON);
		reasonForManualUpdateButton.getStyleClass().add(CUSTOM_BUTTON);
		commentButton.getStyleClass().add(CUSTOM_BUTTON);
		failureReasonInfoButton.getStyleClass().add(CUSTOM_BUTTON);
		IconFontFX.register(GoogleMaterialDesignIcons.getIconFont());
		Color colorGrey = Color.rgb(INT_NINETYFOUR, INT_ONETHIRTYFIVE, INT_ONESEVENTEEN);
		IconNode iconNode = new IconNode(GoogleMaterialDesignIcons.INFO_OUTLINE);
		iconNode.setIconSize(INT_FIFTEEN);
		iconNode.setFill(colorGrey);
		assetButton.setGraphic(iconNode);
		IconNode iconNodecurrentversion = new IconNode(
				GoogleMaterialDesignIcons.INFO_OUTLINE);
		iconNodecurrentversion.setIconSize(INT_FIFTEEN);
		iconNodecurrentversion.setFill(colorGrey);
		currentVersionButton.setGraphic(iconNodecurrentversion);
		IconNode iconNodeupdatestatus = new IconNode(
				GoogleMaterialDesignIcons.INFO_OUTLINE);
		iconNodeupdatestatus.setIconSize(INT_FIFTEEN);
		iconNodeupdatestatus.setFill(colorGrey);
		updateStatusButton.setGraphic(iconNodeupdatestatus);
		IconNode iconNodecompletedon = new IconNode(
				GoogleMaterialDesignIcons.INFO_OUTLINE);
		iconNodecompletedon.setIconSize(INT_FIFTEEN);
		iconNodecompletedon.setFill(colorGrey);
		completedOnButton.setGraphic(iconNodecompletedon);
		IconNode iconNodelocationaddress = new IconNode(
				GoogleMaterialDesignIcons.INFO_OUTLINE);
		iconNodelocationaddress.setIconSize(INT_FIFTEEN);
		iconNodelocationaddress.setFill(colorGrey);
		locationAddressButton.setGraphic(iconNodelocationaddress);
		IconNode iconNodepreviousversion = new IconNode(
				GoogleMaterialDesignIcons.INFO_OUTLINE);
		iconNodepreviousversion.setIconSize(INT_FIFTEEN);
		iconNodepreviousversion.setFill(colorGrey);
		previousVersionButton.setGraphic(iconNodepreviousversion);
		IconNode iconNodeaccountname = new IconNode(
				GoogleMaterialDesignIcons.INFO_OUTLINE);
		iconNodeaccountname.setIconSize(INT_FIFTEEN);
		iconNodeaccountname.setFill(colorGrey);
		accountnameButton.setGraphic(iconNodeaccountname);
		if (fpvo.isManualFile()) {
			IconNode iconNodeupdate = new IconNode(GoogleMaterialDesignIcons.INFO_OUTLINE);
			iconNodeupdate.setIconSize(INT_FIFTEEN);
			iconNodeupdate.setFill(colorGrey);
			reasonForManualUpdateButton.setGraphic(iconNodeupdate);
			IconNode iconNodecomment = new IconNode(
					GoogleMaterialDesignIcons.INFO_OUTLINE);
			iconNodecomment.setIconSize(INT_FIFTEEN);
			iconNodecomment.setFill(colorGrey);
			commentButton.setGraphic(iconNodecomment);
		}
		IconNode iconfailureReason = new IconNode(GoogleMaterialDesignIcons.INFO_OUTLINE);
		iconfailureReason.setIconSize(INT_FIFTEEN);
		iconfailureReason.setFill(colorGrey);
		failureReasonInfoButton.setGraphic(iconfailureReason);
		IconNode iconNodecomment = new IconNode(GoogleMaterialDesignIcons.INFO_OUTLINE);
		iconNodecomment.setIconSize(INT_FIFTEEN);
		iconNodecomment.setFill(colorGrey);
		commentButton.setGraphic(iconNodecomment);
	}

	/**
	 * Confirm checkbox change listener.
	 */
	void confirmCheckboxChangeListener() {
		confrmCheckbox.selectedProperty().addListener(new ChangeListener<Boolean>() {
			public void changed(ObservableValue<? extends Boolean> ov, Boolean oldValue,
					Boolean newValue) {
				if (confrmCheckbox.isSelected()) {
					madatoryVO.setConfirmAddress(true);
					checkTheMandateFields(madatoryVO);
				}
				else {
					madatoryVO.setConfirmAddress(false);
					checkTheMandateFields(madatoryVO);
				}
			}
		});

	}

	/**
	 * Location address change listener.
	 */
	void locationAddressChangeListener() {
		laFld.textProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> observable,
					String oldValue, String newValue) {
				if (newValue.length() > INT_SEVENTY) {
					laFld.setText(oldValue);
				}
				madatoryVO.setStreetaddress(true);
				checkTheMandateFields(madatoryVO);
			}
		});
	}

	/**
	 * City change listener.
	 */
	void cityChangeListener() {
		cityFld.textProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> observable,
					String oldValue, String newValue) {
				if (newValue.length() > USBConstant.CITY_FLD_MAX_LENGTH) {
					cityFld.setText(oldValue);
				}
				madatoryVO.setCity(true);
				checkTheMandateFields(madatoryVO);
			}
		});
	}

	/**
	 * Comments change listener.
	 */
	void commentsChangeListener() {
		commentsFld.textProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> observable,
					String oldValue, String newValue) {
				if (newValue.length() > USBConstant.COMMENTS_FLD_MAX_LENGTH) {
					commentsFld.setText(oldValue);
				}
			}
		});
	}

	/**
	 * Country change listener.
	 */
	void countryChangeListener() {
		countryCmbBox.valueProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> observable,
					String oldValue, String newValue) {
				countryBoxListener(oldValue, newValue);
			}
		});
	}

	/**
	 * Current version change listener.
	 */
	void currentVersionChangeListener() {
		currentVersionCmbx.valueProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> observable,
					String oldValue, String newValue) {
				currentVersionBoxListener(oldValue, newValue);
			}
		});
	}

	/**
	 * Previous version change listener.
	 */
	void previousVersionChangeListener() {
		previousVersionCmbx.valueProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> observable,
					String oldValue, String newValue) {
				previousVersionBoxListener(oldValue, newValue);
			}
		});
	}

	/**
	 * State change listener.
	 */
	void stateChangeListener() {
		stateFld.textProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> observable,
					String oldValue, String newValue) {
				if (newValue.length() > USBConstant.STATE_FLD_MAX_LENGTH) {
					stateFld.setText(oldValue);
				}
			}
		});
	}

	/**
	 * Manual change listener.
	 */
	void manualChangeListener() {
		manualReasonCmbx.getSelectionModel().selectedItemProperty()
				.addListener(new ChangeListener<ReasonVO>() {

					@Override
					public void changed(ObservableValue<? extends ReasonVO> observable,
							ReasonVO oldValue, ReasonVO newValue) {

						if (!String.valueOf(oldValue).equalsIgnoreCase(
								String.valueOf(newValue))) {

							madatoryVO.setReasonForManulaUpdate(true);
							checkTheMandateFields(madatoryVO);
						}
						else {
							madatoryVO.setReasonForManulaUpdate(false);
							checkTheMandateFields(madatoryVO);
						}
					}
				});
	}

	/**
	 * Failure reason change listener.
	 */
	void failureReasonChangeListener() {
		failureReasonCmb.valueProperty().addListener(new ChangeListener<ReasonVO>() {
			@Override
			public void changed(ObservableValue<? extends ReasonVO> observable,
					ReasonVO oldValue, ReasonVO newValue) {
				if (!String.valueOf(oldValue).equalsIgnoreCase(String.valueOf(newValue))) {
					madatoryVO.setFailureReasonBoolean(true);
					checkTheMandateFields(madatoryVO);
				}
				else {
					madatoryVO.setFailureReasonBoolean(false);
					checkTheMandateFields(madatoryVO);
				}
			}
		});
	}

	/**
	 * Update completion date change listener.
	 */
	void updateCompletionDateChangeListener() {
		ucFld.setOnAction(event -> {
			madatoryVO.setUpdatecompletedon(true);
			checkTheMandateFields(madatoryVO);
		});
	}

	/**
	 * Zip code date change listener.
	 */
	void zipCodeDateChangeListener() {
		zipFld.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable,
					String oldValue, String newValue) {
				if (newValue.matches("\\d*")) {
					zipFld.setText(newValue);
					checkTheMandateFields(madatoryVO);
				}
				else {
					zipFld.setText(oldValue);
					checkTheMandateFields(madatoryVO);
				}
				if (newValue.length() > USBConstant.ZIP_FLD_MAX_LENGTH) {
					zipFld.setText(oldValue);
				}
			}
		});
	}

	/**
	 * Upgrade status change listener.
	 */
	void upgradeStatusChangeListener() {
		upgradeStatusCmbx.valueProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable,
					String oldValue, String newValue) {
				upgradeStatusBoxListener(oldValue, newValue);
			}
		});
	}

	/**
	 * Validation change listners.
	 */
	private void validationChangeListners() {
		upgradeStatusChangeListener();
		confirmCheckboxChangeListener();
		locationAddressChangeListener();
		cityChangeListener();
		countryChangeListener();
		stateChangeListener();
		manualChangeListener();
		failureReasonChangeListener();
		updateCompletionDateChangeListener();
		zipCodeDateChangeListener();
		commentsChangeListener();
		previousVersionChangeListener();
		currentVersionChangeListener();
	}

	/**
	 * Country box listener.
	 *
	 * @param oldValue the old value
	 * @param newValue the new value
	 */
	void countryBoxListener(String oldValue, String newValue) {
		if (!newValue.equalsIgnoreCase(oldValue)) {
			madatoryVO.setCountry(true);
			boolean isvalidationSuccess;
			if (countryCmbBox.getValue() != null) {
				isvalidationSuccess = validateCountry(String.valueOf(countryCmbBox
						.getValue()));
			}
			else {
				isvalidationSuccess = validateCountry("");
			}
			if (isvalidationSuccess) {
				madatoryVO.setCountry(true);
				checkTheMandateFields(madatoryVO);
			}
			else {
				countryCmbBox.setValue("");
				countryCmbBox.getItems().clear();
				countryCombobox(genericvo.getJwtToken());
				madatoryVO.setCountry(false);
				checkTheMandateFields(madatoryVO);
			}
		}
		else {
			madatoryVO.setCountry(false);
			checkTheMandateFields(madatoryVO);
		}
	}

	/**
	 * Upgrade status box listener.
	 *
	 * @param oldValue the old value
	 * @param newValue the new value
	 */
	void upgradeStatusBoxListener(String oldValue, String newValue) {
		if (!newValue.equalsIgnoreCase(oldValue)) {
			madatoryVO.setUpdatestatus(true);
			boolean isvalidationSuccess;
			if (upgradeStatusCmbx.getValue() != null) {
				isvalidationSuccess = validateStatus(String.valueOf(upgradeStatusCmbx
						.getValue()));
			}
			else {
				isvalidationSuccess = validateStatus("");
			}
			if (isvalidationSuccess) {
				madatoryVO.setUpdatestatus(true);
				enableFailureDropDown(newValue);
				checkTheMandateFields(madatoryVO);
			}
			else {
				disableFailureReason();
				disableCommentsFld();
				upgradeStatusCmbx.setValue("");
				upgradeStatusCmbx.getItems().clear();
				upgradeStatusCombobox();
				madatoryVO.setUpdatestatus(false);
				checkTheMandateFields(madatoryVO);
			}
		}
		else {
			upgradeStatusCmbx.setValue("");
			upgradeStatusCmbx.getItems().clear();
			upgradeStatusCombobox();
			disableFailureReason();
			disableCommentsFld();
			madatoryVO.setUpdatestatus(false);
			checkTheMandateFields(madatoryVO);
		}

	}

	/**
	 * Enable failure drop down.
	 *
	 * @param newValue the new value
	 */
	void enableFailureDropDown(String newValue) {
		if (newValue != null
				&& (FileUploadStatusEnum.FAILED.getStatusVal().equals(newValue)
						|| FileUploadStatusEnum.IN_COMPLETE.getStatusVal().equals(
								newValue) || FileUploadStatusEnum.IN_PROGRESS
						.getStatusVal().equals(newValue))) {
			failureReasonCmb.setVisible(true);
			reasonForFailureLbl.setVisible(true);
			failureReasonInfoButton.setVisible(true);
			commentsFld.setVisible(true);
			commentsLbl.setVisible(true);
			commentButton.setVisible(true);
			uplaodLbl.setVisible(true);
			if (failureReasonCmb.getValue() != null) {
				madatoryVO.setFailureReasonBoolean(true);
			}
			else {
				madatoryVO.setFailureReasonBoolean(false);
			}
		}
		else {
			madatoryVO.setFailureReasonBoolean(false);
			disableFailureReason();
			disableCommentsFld();
		}
	}

	/**
	 * Disable failure reason.
	 */
	void disableFailureReason() {
		failureReasonCmb.setVisible(false);
		reasonForFailureLbl.setVisible(false);
		failureReasonInfoButton.setVisible(false);
	}

	/**
	 * Disable comments fld.
	 */
	void disableCommentsFld() {
		if (!ismanualFile) {
			commentsFld.setVisible(false);
			commentsLbl.setVisible(false);
			commentButton.setVisible(false);
			uplaodLbl.setVisible(false);
		}
	}

	/**
	 * Current version box listener.
	 *
	 * @param oldValue the old value
	 * @param newValue the new value
	 */
	void currentVersionBoxListener(String oldValue, String newValue) {
		if (!newValue.equalsIgnoreCase(oldValue)) {
			madatoryVO.setCurrentVersionCombobox(true);
			boolean isvalidationSuccess;
			if (currentVersionCmbx.getValue() != null) {
				isvalidationSuccess = validateCurrentVersion(String
						.valueOf(currentVersionCmbx.getValue()));
			}
			else {
				isvalidationSuccess = validateCurrentVersion("");
			}
			if (isvalidationSuccess) {
				madatoryVO.setCurrentVersionCombobox(true);
				checkTheMandateFields(madatoryVO);
			}
			else {
				currentVersionCmbx.setValue("");
				currentVersionCmbx.getItems().clear();
				currentVersionCombobox(genericvo.getJwtToken());
				madatoryVO.setCurrentVersionCombobox(false);
				checkTheMandateFields(madatoryVO);
			}
		}
		else {
			madatoryVO.setCurrentVersionCombobox(false);
			checkTheMandateFields(madatoryVO);
		}
	}

	/**
	 * Previous version box listener.
	 *
	 * @param oldValue the old value
	 * @param newValue the new value
	 */
	void previousVersionBoxListener(String oldValue, String newValue) {
		if (!newValue.equalsIgnoreCase(oldValue)) {
			madatoryVO.setPreviousVersionCombobox(true);
			boolean isvalidationSuccess;
			if (previousVersionCmbx.getValue() != null) {
				isvalidationSuccess = validatePreviousVersion(String
						.valueOf(previousVersionCmbx.getValue()));
			}
			else {
				isvalidationSuccess = validatePreviousVersion("");
			}
			if (isvalidationSuccess) {
				madatoryVO.setPreviousVersionCombobox(true);
				checkTheMandateFields(madatoryVO);
			}
			else {
				previousVersionCmbx.setValue("");
				previousVersionCmbx.getItems().clear();
				previousVersionCombobox(genericvo.getJwtToken());
				madatoryVO.setPreviousVersionCombobox(false);
				checkTheMandateFields(madatoryVO);
			}
		}
		else {
			madatoryVO.setPreviousVersionCombobox(false);
			checkTheMandateFields(madatoryVO);
		}
	}

	/**
	 * Check the mandate fields.
	 *
	 * @param madatoryVO the madatory VO
	 */
	private void checkTheMandateFields(MandatoryFieldsVO madatoryVO) {
		logger.printLogger(
				LogFileDetailsDialog.class.getSimpleName(),
				"checkTheMandateFields:",
				"[madatoryVO.isUpdatestatus():" + madatoryVO.isUpdatestatus()
						+ "][madatoryVO.isConfirmAddress() :"
						+ madatoryVO.isConfirmAddress()
						+ "][madatoryVO.isStreetaddress():"
						+ madatoryVO.isStreetaddress() + "][madatoryVO.isCity():"
						+ madatoryVO.isCity() + "][madatoryVO.isCountry() :"
						+ madatoryVO.isCountry() + "][madatoryVO.isUpdatecompletedon():"
						+ madatoryVO.isUpdatecompletedon()
						+ "][madatoryVO.isPreviousVersionCombobox():"
						+ madatoryVO.isPreviousVersionCombobox()
						+ "][madatoryVO.isCurrentVersionCombobox():"
						+ madatoryVO.isCurrentVersionCombobox() + "]");
		if (isMandatory1Exists(madatoryVO.isUpdatestatus(),
				madatoryVO.isConfirmAddress(), madatoryVO.isUpdatecompletedon())
				&& isMandatory2Exists(madatoryVO.isCity(), madatoryVO.isCountry(),
						madatoryVO.isStreetaddress())
				&& isMandatory3Exists(madatoryVO.isPreviousVersionCombobox(),
						madatoryVO.isCurrentVersionCombobox())) {
			validateValues();
		}
		else {
			completeButton.setDisable(true);
		}
		createPromptText();
	}

	/**
	 * Validate values.
	 */
	void validateValues() {
		if ((laFld.getText().length() > 0) && (cityFld.getText().length() > 0)
				&& (ucFld.getValue() != null) && isAllDropdownSelected()) {
			logger.printLogger(
					LogFileDetailsDialog.class.getSimpleName(),
					"checkTheMandateFields:",
					"madatoryVO.isFailureReasonBoolean()"
							+ madatoryVO.isFailureReasonBoolean()
							+ " madatoryVO.isReasonForManulaUpdate():"
							+ madatoryVO.isReasonForManulaUpdate()
							+ "madatoryVO.isNoreasonCombobox():"
							+ madatoryVO.isNoreasonCombobox());
			enableCompleteButton();
		}
		else {
			completeButton.setDisable(true);
		}
	}

	/**
	 * Enable complete button.
	 */
	void enableCompleteButton() {
		logger.printLogger(LogFileDetailsDialog.class.getSimpleName(),
				"enableCompleteButton:", "failureReasonCmb.isVisible()"
						+ failureReasonCmb.isVisible() + " manualReasonCmbx.isVisible()"
						+ manualReasonCmbx.isVisible());
		if (failureReasonCmb.isVisible() && manualReasonCmbx.isVisible()) {
			if (madatoryVO.isFailureReasonBoolean()
					&& madatoryVO.isReasonForManulaUpdate()) {
				checkButtonVisibility();
			}
			else {
				completeButton.setDisable(true);
			}
		}
		else if (failureReasonCmb.isVisible()) {
			if (failureReasonCmb.getValue() != null) {
				checkButtonVisibility();
			}
			else {
				completeButton.setDisable(true);
			}
		}
		else if (manualReasonCmbx.isVisible()) {
			if (manualReasonCmbx.getValue() != null) {
				checkButtonVisibility();
			}
			else {
				completeButton.setDisable(true);
			}
		}
		else {
			checkButtonVisibility();
		}
	}

	/**
	 * Checks if is all dropdown selected.
	 *
	 * @return true, if is all dropdown selected
	 */
	boolean isAllDropdownSelected() {
		boolean isTrue = false;
		if (previousVersionCmbx.getValue() != null && currentVersionCmbx != null
				&& upgradeStatusCmbx.getValue() != null
				&& (countryCmbBox.getValue() != null)) {
			isTrue = true;
		}
		logger.printLogger(LogFileDetailsDialog.class.getSimpleName(),
				"isAllDropdownSelected:", String.valueOf(isTrue));
		return isTrue;

	}

	/**
	 * Checks if is mandatory 3 exists.
	 *
	 * @param cv the cv
	 * @param pv the pv
	 * @return true, if is mandatory 3 exists
	 */
	boolean isMandatory3Exists(boolean cv, boolean pv) {
		boolean isTrue = false;
		if (cv && pv) {
			isTrue = true;
		}
		return isTrue;

	}

	/**
	 * Checks if is mandatory 2 exists.
	 *
	 * @param city the city
	 * @param country the country
	 * @param streetaddress the streetaddress
	 * @return true, if is mandatory 2 exists
	 */
	boolean isMandatory2Exists(boolean city, boolean country, boolean streetaddress) {
		boolean isTrue = false;
		if (city && country && streetaddress) {
			isTrue = true;
		}
		return isTrue;

	}

	/**
	 * Checks if is mandatory 1 exists.
	 *
	 * @param updatestatus the updatestatus
	 * @param confirmAddress the confirm address
	 * @param completedOn the completed on
	 * @return true, if is mandatory 1 exists
	 */
	boolean isMandatory1Exists(boolean updatestatus, boolean confirmAddress,
			boolean completedOn) {
		boolean isTrue = false;
		if (updatestatus && confirmAddress && completedOn) {
			isTrue = true;
		}
		return isTrue;

	}

	/**
	 * Creates the prompt text.
	 */
	void createPromptText() {
		assetFld.setPromptText(ASSET_SERIAL_NMBR);
		accFld.setPromptText(ACC_NAME);
		currentVersionCmbx.setPromptText(CURRENT_VERSION);
		upgradeStatusCmbx.setPromptText("Upgrade Status ");
		previousVersionCmbx.setPromptText(PREVIOUS_VERSION);
		laFld.setPromptText("Street");
		cityFld.setPromptText("City");
		stateFld.setPromptText("State/Province");
		countryCmbBox.setPromptText("Country");
		zipFld.setPromptText("Zip/Postal Code");
		commentsFld.setPromptText("Type your comments here…");
		failureReasonCmb.setPromptText("Select the Reason");
		manualReasonCmbx.setPromptText("Select the Reason");
	}

	/**
	 * Check button visibility.
	 */
	void checkButtonVisibility() {
		completeButton.setDisable(false);
		completeButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent t) {
				runCompleteTask();
			}
		});
	}

	/**
	 * Show post update dialog.
	 *
	 * @param response the response
	 * @param assetNr the asset nr
	 * @param stage the stage
	 * @param jToken the j token
	 * @param uName the u name
	 */
	void viewPostUpdateDialog(String response, String assetNr) {
		VBox dialogVbox = new VBox(INT_TWOHUNDRED);
		Label postUpdateLbl;
		final Button closeButton = new Button("Close");
		String[] postUpdateRes = response.split("-");
		int resposnecode = 0;
		if (postUpdateRes[0] != null) {
			resposnecode = Integer.parseInt(postUpdateRes[0]);
		}
		logger.printLogger(LogFileDetailsDialog.class.getSimpleName(),
				"viewPostUpdateDialog:resposnecode", String.valueOf(resposnecode));
		if (resposnecode == INT_TWOHUNDRED) {
			postUpdateLbl = new Label("Asset #" + assetNr
					+ " post-update file has been successfully processed.");
		}
		else {
			postupdateDialog.setTitle("Process Post-Update File");
			postUpdateLbl = new Label("Asset #" + assetNr
					+ " post-update file processing failed.");
		}
		GridPane gridpane = new GridPane();
		gridpane.setAlignment(Pos.CENTER);
		gridpane.setHgap(INT_TEN);
		gridpane.setVgap(INT_TEN);
		gridpane.setPadding(new Insets(INSETS_TOP_TWENTYFIVE, INSETS_RIGHT_TWENTYFIVE,
				INSETS_BOTTOM_TWENTYFIVE, INSETS_LEFT_TWENTYFIVE));
		gridpane.add(postUpdateLbl, 0, 0);
		gridpane.add(closeButton, 1, 1);
		dialogVbox.getChildren().add(gridpane);
		Scene dialogScene = new Scene(dialogVbox, INT_THREEHUNDRED, INT_EIGTHHUNDRED);
		dialogScene.getStylesheets().add(css);
		postupdateDialog.setScene(dialogScene);
		postupdateDialog.setHeight(INT_TWOHUNDRED);
		postupdateDialog.setWidth(INT_SIXHUNDRED);
		postupdateDialog.show();
		closeButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent t) {
				callbackDataGrid();
			}
		});
	}

	/**
	 * Callback data grid.
	 */
	void callbackDataGrid() {
		postupdateDialog.close();
		dialog.close();
		if (genericvo.getPostupdateList() != null
				&& genericvo.getPostupdateList().isEmpty()) {
			logger.printLogger(LogFileDetailsDialog.class.getSimpleName(),
					"genericvo.getPostupdateList() :",
					String.valueOf(genericvo.getPostupdateList().size()));
			usbtable.getColumns().remove(INT_SIX);
			populateDataItems(genericvo.getPreupdateList(), PRE_UPDATE_FILES, stage, 0,
					genericvo);
		}
		else {
			int pageNumber;
			if ((postupdateList.size() % rowsPerPage) == 0) {
				pageNumber = pageNrOfTable - 1;
			}
			else {
				pageNumber = pageNrOfTable;
			}

			populateDataItems(genericvo.getPostupdateList(), POST_UPDATE_FILES, stage,
					pageNumber, genericvo);
		}

	}

	/**
	 * Update post update file.
	 *
	 * @return the string
	 */
	protected String updatePostUpdateFile() {
		PostUpdateFileVO pufvo = new PostUpdateFileVO();
		pufvo.setAccountName(accFld.getText());
		pufvo.setAssetNumber(assetFld.getText());
		pufvo.setComments(commentsFld.getText());
		int temp = ucFld.getValue().getMonthValue() - 1;
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DAY_OF_MONTH, ucFld.getValue().getDayOfMonth());
		cal.set(Calendar.MONTH, temp);
		cal.set(Calendar.YEAR, ucFld.getValue().getYear());
		Date dateUsingCal = new java.util.Date(cal.getTimeInMillis());
		pufvo.setCompletedOn(dateUsingCal);
		logger.printLogger(LogFileDetailsDialog.class.getSimpleName(),
				"Current version:", cvFld.getText());
		pufvo.setCurrentVersion(currentVersionCmbx.getValue());
		AssetLocation locationAddress = new AssetLocation();
		logger.printLogger(LogFileDetailsDialog.class.getSimpleName(), "city",
				cityFld.getText());
		locationAddress.setCity(cityFld.getText());
		logger.printLogger(LogFileDetailsDialog.class.getSimpleName(),
				"location Address", laFld.getText());
		locationAddress.setStreet(laFld.getText());
		logger.printLogger(LogFileDetailsDialog.class.getSimpleName(), "country",
				countryCmbBox.getValue());
		if (countryCmbBox.getValue() != null) {
			locationAddress.setCountry(String.valueOf(countryCmbBox.getValue()));
		}

		if (zipFld.getText() != null && !zipFld.getText().isEmpty()) {
			locationAddress.setZipCode(zipFld.getText());
			locationAddress.setPostalCode(zipFld.getText());
			logger.printLogger(LogFileDetailsDialog.class.getSimpleName(), "zipcode",
					zipFld.getText());
		}
		if (stateFld.getText() != null) {
			locationAddress.setState(stateFld.getText());
		}
		pufvo.setLocationAddress(locationAddress);
		pufvo.setPreviousVersion(previousVersionCmbx.getValue());
		logger.printLogger(LogFileDetailsDialog.class.getSimpleName(), "processedby",
				userheaderVO.getUserWwid());
		pufvo.setProcessedBy(userheaderVO.getUserWwid());
		UpgradeStatusVO upgradestatusvo = new UpgradeStatusVO();
		logger.printLogger(LogFileDetailsDialog.class.getSimpleName(), "ismanualFile",
				String.valueOf(ismanualFile));
		if (ismanualFile) {
			pufvo.setManualFile(true);
			upgradestatusvo.setStatusName(upgradeStatusCmbx.getValue());
		}
		else {
			upgradestatusvo.setStatusName(upgradeStatusCmbx.getValue());
		}

		if (ismanualFile && manualReasonCmbx.getValue() != null) {
			pufvo.setReasonManualUpdate(manualReasonCmbx.getValue());
		}

		pufvo.setUpdateStatus(upgradestatusvo);
		pufvo.setFileName(fileName);
		if (failureReasonCmb.getValue() != null) {
			pufvo.setReasonFailure(failureReasonCmb.getValue());
		}
		pufvo.setProcessUsb(true);
		pufvo.setHandedOver(updateOpCheckbox.isSelected());
		pufvo.setOtherUserWwid(otherWwid);
		logger.printLogger(LogFileDetailsDialog.class.getSimpleName(), "otherWwid",
				otherWwid);
		String response = null;
		try {
			response = updatePostupdateFiles(pufvo);
			logger.printLogger(LogFileDetailsDialog.class.getSimpleName(),
					"updatePostupdateFile:Success", response);
		}
		catch (IOException e) {
			logger.printLogger(LogFileDetailsDialog.class.getSimpleName(),
					"updatePostupdateFile", "IOException while updatePostUpdateFile: "
							+ e);
			response = HTTP_ERROR;
		}
		return response;
	}

	/**
	 * Update postupdate files.
	 *
	 * @param pufo the pufo
	 * @return the string
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	private String updatePostupdateFiles(PostUpdateFileVO pufo) throws IOException {
		properties = new Properties();
		HttpURLConnection conn = null;
		try {
			InputStream input = getClass().getClassLoader().getResourceAsStream(
					APPLET_PROP);
			properties.load(input);
			String urlString = properties.getProperty(POST_UPDATE_FILE_URL);
			logger.printLogger(LogFileDetailsDialog.class.getSimpleName(),
					"updatePostupdateFiles URL is -", urlString);
			URL url = new URL(urlString);
			conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setUseCaches(false);
			conn.setRequestMethod("POST");
			conn.setRequestProperty(CONTENT_TYPE, APPLICATION_JSON);
			conn.setRequestProperty(AUTHORIZATION, BEARER + jToken);
			conn.setRequestProperty("jjwid", userheaderVO.getUserWwid());
			DataOutputStream out = new DataOutputStream(conn.getOutputStream());

			ObjectMapper objectmapper = new ObjectMapper();
			String pufojson = objectmapper.writeValueAsString(pufo);
			logger.printLogger(LogFileDetailsDialog.class.getSimpleName(), "pufojson ",
					pufojson);
			out.writeBytes(pufojson);
			out.flush();
			out.close();

			BufferedReader in = new BufferedReader(new InputStreamReader(
					conn.getInputStream(), StandardCharsets.UTF_8));
			String inputLine;
			StringBuilder response = new StringBuilder();
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
			logger.printLogger(LogFileDetailsDialog.class.getSimpleName(),
					"conn.getResponseCode()-conn.getResponseMessage()",
					conn.getResponseCode() + "-" + conn.getResponseMessage());
			return conn.getResponseCode() + "-" + conn.getResponseMessage();
		}
		catch (IOException e) {
			logger.printLogger(LogFileDetailsDialog.class.getSimpleName(),
					"updatePostupdateFiles", "IOException while updatePostupdateFiles: "
							+ e);
			return "400-Failure";
		}
	}

	/**
	 * Gets the reasons.
	 *
	 * @param jToken the j token
	 * @param urlforreason the urlforreason
	 * @return the reasons
	 */
	String getReasons(String jToken, String urlforreason) {
		try {
			properties = new Properties();
			InputStream input = getClass().getClassLoader().getResourceAsStream(
					APPLET_PROP);
			properties.load(input);
			String urlString = properties.getProperty(urlforreason);

			URL url = new URL(urlString);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty(AUTHORIZATION, BEARER + jToken);
			BufferedReader in = new BufferedReader(new InputStreamReader(
					conn.getInputStream(), StandardCharsets.UTF_8));
			String inputLine;
			StringBuilder response = new StringBuilder();
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
			return response.toString();
		}
		catch (IOException e) {
			logger.printLogger(LogFileDetailsDialog.class.getSimpleName(), "getReasons",
					"IOException while getReasons : " + e);
			return "";
		}

	}

	/**
	 * Method to create thread to process the file and displays the progress bar until
	 * post file update completes.
	 *
	 * @return the string
	 */
	private String runCompleteTask() {

		final double wndwWidth = 300.0d;
		Label updateLabel = new Label("Running tasks...");
		updateLabel.setPrefWidth(wndwWidth);
		ProgressIndicator progress = new ProgressIndicator();

		progress.setPrefWidth(wndwWidth);

		VBox updatePane = new VBox();
		updatePane.setPadding(new Insets(INT_TEN));
		updatePane.setSpacing(DOUBLE_FIVEZERO);
		updatePane.getChildren().addAll(updateLabel, progress);
		/**
		 * Setting the title
		 */
		completeStage.setTitle("Processing Post update file");
		completeStage.setScene(new Scene(updatePane));

		completeStage.show();

		final Task<String> longTask = new Task<String>() {
			private String completeTask = null;

			@Override
			protected String call() throws Exception {
				completeTask = updatePostUpdateFile();
				return completeTask;
			}
		};
		int selectdIndex = tableRow.getIndex();
		UsbAssetVO selectedRecord = (UsbAssetVO) tableView.getItems().get(selectdIndex);
		longTask.setOnFailed(new EventHandler<WorkerStateEvent>() {
			@Override
			public void handle(WorkerStateEvent t) {
				completeStage.hide();
				longTask.cancel();
				viewPostUpdateDialog(HTTP_ERROR, selectedRecord.getAssetSerialNumber());
			}
		});

		longTask.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
			@Override
			public void handle(WorkerStateEvent t) {
				completeStage.hide();
				longTask.cancel();
				if (longTask.getValue() == null) {
					viewPostUpdateDialog(HTTP_ERROR,
							selectedRecord.getAssetSerialNumber());
				}
				else {
					deletePostUpdateFiles(longTask.getValue());
				}

			}
		});
		progress.progressProperty().bind(longTask.progressProperty());
		updateLabel.textProperty().bind(longTask.messageProperty());

		new Thread(longTask).start();
		logger.printLogger(LogFileDetailsDialog.class.getSimpleName(),
				"longTask.getValue()", longTask.getValue());
		return longTask.getValue();
	}

	/**
	 * Delete post update files.
	 *
	 * @param response the response
	 */
	void deletePostUpdateFiles(String response) {

		String[] postUpdateRes = response.split("-");
		int resposnecode = 0;
		if (postUpdateRes[0] != null) {
			resposnecode = Integer.parseInt(postUpdateRes[0]);
		}
		int selectdIndex = tableRow.getIndex();
		UsbAssetVO selectedRecord = (UsbAssetVO) tableView.getItems().get(selectdIndex);
		if (resposnecode != INT_TWOHUNDRED) {
			viewPostUpdateDialog("400-Postupdate Failed",
					selectedRecord.getAssetSerialNumber());
		}
		else {
			try {
				/**
				 * Delete file from USB
				 */
				DeleteFileDetails dfusb = new DeleteFileDetails();
				dfusb.deleteUSBFile(selectedRecord.getAssetSerialNumber(), true, false);
			}
			catch (Exception e) {
				logger.printLogger(LogFileDetailsDialog.class.getSimpleName(),
						"deletePostUpdateFiles", " Exception while delete file form USB:"
								+ e);
				dialog.close();
				viewPostUpdateDialog("400-Postupdate Failed",
						selectedRecord.getAssetSerialNumber());
			}
			if (genericvo.getPostupdateList() != null
					&& !genericvo.getPostupdateList().isEmpty()) {
				logger.printLogger(LogFileDetailsDialog.class.getSimpleName(),
						"deletePostUpdateFiles:postupdateList",
						String.valueOf(postupdateList.size()));
				genericvo.getPostupdateList().remove(selectedRecord);
			}
			else {
				logger.printLogger(LogFileDetailsDialog.class.getSimpleName(),
						"deletePostUpdateFiles:postupdateList", "postupdateList is Empty");
			}
			data.remove(selectedRecord);
			usbtable.setItems(data);
			viewPostUpdateDialog(response, selectedRecord.getAssetSerialNumber());
			dialog.close();
		}
	}

	/**
	 * Gets the countries.
	 *
	 * @param jwttoken the jwttoken
	 * @return the countries
	 */
	protected List<String> getCountries(String jwttoken) {
		logger.printLogger(LogFileDetailsDialog.class.getSimpleName(),
				"inside getCountries", "");
		properties = new Properties();
		InputStream input = getClass().getClassLoader().getResourceAsStream(APPLET_PROP);
		List<CountryVO> objList = new ArrayList<>();
		List<String> countryList = new ArrayList<>();
		HttpURLConnection urlConnForCountry = null;
		try {
			properties.load(input);
			String urlString = properties.getProperty(SERVICING_COUNTRIES);
			logger.printLogger(LogFileDetailsDialog.class.getSimpleName(),
					"getCountries URL is -", urlString + "jtoken:" + jwttoken);
			URL countryUrl = new URL(urlString);
			urlConnForCountry = (HttpURLConnection) countryUrl.openConnection();
			urlConnForCountry.setDoOutput(true);
			urlConnForCountry.setDoInput(true);
			urlConnForCountry.setUseCaches(false);
			urlConnForCountry.setRequestMethod("GET");
			urlConnForCountry.setRequestProperty(CONTENT_TYPE, APPLICATION_JSON);
			urlConnForCountry.setRequestProperty(AUTHORIZATION, BEARER + jwttoken);
			urlConnForCountry.setDoOutput(true);
		}
		catch (IOException ioe) {
			logger.printLogger(LogFileDetailsDialog.class.getSimpleName(),
					"getCountries", " IOException while reading applet.properties:" + ioe);
		}
		if (urlConnForCountry != null) {
			try (BufferedReader in = new BufferedReader(new InputStreamReader(
					urlConnForCountry.getInputStream(), StandardCharsets.UTF_8))) {

				String inputLine;
				StringBuilder response = new StringBuilder();
				while ((inputLine = in.readLine()) != null) {
					response.append(inputLine);
				}
				ObjectMapper mapper = new ObjectMapper();
				objList = mapper.readValue(response.toString(),
						new TypeReference<List<CountryVO>>() {
						});

				for (CountryVO countryvo : objList) {
					countryMap.put(countryvo.getName(), countryvo.getCountryCode());
					countryList.add(countryvo.getName());
				}
				logger.printLogger(LogFileDetailsDialog.class.getSimpleName(),
						"Completed getting servicing countries and total is:",
						String.valueOf(objList.size()));
			}
			catch (IOException e) {
				logger.printLogger(LogFileDetailsDialog.class.getSimpleName(),
						"getCountries", " IOException while getCountries:" + e);
				return countryList;
			}
		}
		return countryList;

	}

	/**
	 * Gets the version.
	 *
	 * @param jwttoken the jwttoken
	 * @return the version
	 */
	protected List<String> getVersion(String jwttoken) {
		logger.printLogger(LogFileDetailsDialog.class.getSimpleName(),
				"inside getVersion", "");
		properties = new Properties();
		InputStream input = getClass().getClassLoader().getResourceAsStream(APPLET_PROP);
		List<PackageVO> objList = new ArrayList<>();
		List<String> versionList = new ArrayList<>();
		HttpURLConnection conn = null;
		try {
			properties.load(input);
			String urlString = properties.getProperty(GET_SOFTWARE_VERSION);
			logger.printLogger(LogFileDetailsDialog.class.getSimpleName(),
					"getVersion URL is -", urlString + "jwttoken:" + jwttoken);
			URL url = new URL(urlString);
			conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setUseCaches(false);
			conn.setRequestMethod("GET");
			conn.setRequestProperty(CONTENT_TYPE, APPLICATION_JSON);
			conn.setRequestProperty(AUTHORIZATION, BEARER + jwttoken);
			conn.setDoOutput(true);
		}
		catch (IOException ioe) {
			logger.printLogger(LogFileDetailsDialog.class.getSimpleName(), "getVersion",
					" IOException while reading applet.properties for getting url:" + ioe);
		}
		if (conn != null) {
			try (BufferedReader in = new BufferedReader(new InputStreamReader(
					conn.getInputStream(), StandardCharsets.UTF_8))) {

				String inputLine;
				StringBuilder response = new StringBuilder();
				while ((inputLine = in.readLine()) != null) {
					response.append(inputLine);
				}
				ObjectMapper mapper = new ObjectMapper();
				objList = mapper.readValue(response.toString(),
						new TypeReference<List<PackageVO>>() {
						});

				for (PackageVO packageVO : objList) {
					versionMap
							.put(packageVO.getVersionName(), packageVO.getVersionName());
					versionList.add(packageVO.getVersionName());
				}
				logger.printLogger(LogFileDetailsDialog.class.getSimpleName(),
						"Completed getting software version and total is:",
						String.valueOf(objList.size()));
			}
			catch (IOException e) {
				logger.printLogger(LogFileDetailsDialog.class.getSimpleName(),
						"getVersion", " IOException while getVersion:" + e);
				return versionList;
			}
		}
		return versionList;

	}

	/**
	 * Upgrade status combobox.
	 */
	private void upgradeStatusCombobox() {
		/**
		 * Setting Prompt text for Upgrade Status
		 */
		upgradeStatusCmbx.setPromptText("Upgrade Status");
		if (statusList.isEmpty()) {
			upgradeStatusCmbx.getItems().addAll(getUpgradeStatus(jToken));
		}
		else {
			upgradeStatusCmbx.getItems().addAll(statusList);
		}
		/**
		 * Setting Upgrade status combobox
		 */
		new AutoCompleteComboBoxListener<String>(upgradeStatusCmbx);
		upgradeStatusCmbx.setEditable(true);
	}

	/**
	 * Gets the upgrade status.
	 *
	 * @param jwttoken the jwttoken
	 * @return the upgrade status
	 */
	private List<String> getUpgradeStatus(String jwttoken) {
		InputStream input = getClass().getClassLoader().getResourceAsStream(APPLET_PROP);
		List<UpgradeStatusVO> upgradeStatusList = new ArrayList<>();
		HttpURLConnection statusConn = null;
		try {
			properties.load(input);
			String urlString = properties.getProperty(UPDATE_STATUS);
			logger.printLogger(LogFileDetailsDialog.class.getSimpleName(),
					"getUpgradeStatus URL is -", urlString + "jwttoken:" + jwttoken);
			URL url = new URL(urlString);
			statusConn = (HttpURLConnection) url.openConnection();
			statusConn.setDoOutput(true);
			statusConn.setDoInput(true);
			statusConn.setUseCaches(false);
			statusConn.setRequestMethod("GET");
			statusConn.setRequestProperty(CONTENT_TYPE, APPLICATION_JSON);
			statusConn.setRequestProperty(AUTHORIZATION, BEARER + jwttoken);
			statusConn.setDoOutput(true);
		}
		catch (IOException ioe) {
			logger.printLogger(LogFileDetailsDialog.class.getSimpleName(),
					"upgradeStatusList",
					" IOException while reading applet.properties for getting url:" + ioe);
		}
		if (statusConn != null) {
			try (BufferedReader in = new BufferedReader(new InputStreamReader(
					statusConn.getInputStream(), StandardCharsets.UTF_8))) {

				String inputLine;
				StringBuilder response = new StringBuilder();
				while ((inputLine = in.readLine()) != null) {
					response.append(inputLine);
				}
				ObjectMapper mapper = new ObjectMapper();
				upgradeStatusList = mapper.readValue(response.toString(),
						new TypeReference<List<UpgradeStatusVO>>() {
						});

				for (UpgradeStatusVO upgradeStatusVO : upgradeStatusList) {
					statusMap.put(upgradeStatusVO.getStatusName(),
							upgradeStatusVO.getStatusName());
					statusList.add(upgradeStatusVO.getStatusName());
				}
				logger.printLogger(LogFileDetailsDialog.class.getSimpleName(),
						"Completed getting getUpgradeStatus and total is:",
						String.valueOf(statusList.size()));
			}
			catch (IOException e) {
				logger.printLogger(LogFileDetailsDialog.class.getSimpleName(),
						"upgradeStatusList", " IOException while getVersion:" + e);
				return statusList;
			}
		}
		return statusList;
	}

	/**
	 * Previous version combobox.
	 *
	 * @param jwttoken the jwttoken
	 */
	private void previousVersionCombobox(String jwttoken) {
		/**
		 * Setting Prompt text for previous version
		 */
		previousVersionCmbx.setPromptText("Previous Version");
		previousVersionCmbx.getItems().addAll(getVersion(jwttoken));
		previousVersionCmbx.setEditable(true);
		/**
		 * Setting Previous Version Box combobox
		 */
		new AutoCompleteComboBoxListener<String>(previousVersionCmbx);
	}

	/**
	 * Current version combobox.
	 *
	 * @param jwttoken the jwttoken
	 */
	private void currentVersionCombobox(String jwttoken) {
		/**
		 * Setting Prompt text for current version
		 */
		currentVersionCmbx.setPromptText("Current Version");
		currentVersionCmbx.getItems().addAll(getVersion(jwttoken));
		/**
		 * Setting Current Version Box combobox
		 */
		new AutoCompleteComboBoxListener<String>(currentVersionCmbx);
		currentVersionCmbx.setEditable(true);
	}

	/**
	 * Country combobox.
	 *
	 * @param jwttoken the jwttoken
	 */
	private void countryCombobox(String jwttoken) {
		/**
		 * Setting Prompt Text for country
		 */
		countryCmbBox.setPromptText("Country");
		countryCmbBox.getItems().addAll(getCountries(jwttoken));
		countryCmbBox.setPrefWidth(INT_TWOFIFTY);
		countryCmbBox.setEditable(true);
		/**
		 * Setting Country combobox
		 */
		new AutoCompleteComboBoxListener<CountryVO>(countryCmbBox);
		/**
		 * Setting Icon node
		 */
		IconNode iconNodecountry = new IconNode(GoogleMaterialDesignIcons.INFO_OUTLINE);
		iconNodecountry.setIconSize(INT_FIFTEEN);
		iconNodecountry.setFill(Color.GREEN);
		iCountry.setGraphic(iconNodecountry);

	}

	/**
	 * Validate country.
	 *
	 * @param selectedCountry the selected country
	 * @return true, if successful
	 */
	private boolean validateCountry(String selectedCountry) {
		logger.printLogger(LogFileDetailsDialog.class.getSimpleName(), "validateCountry",
				"");

		boolean isError = true;
		if (countryMap != null && countryMap.get(selectedCountry) == null) {
			logger.printLogger(LogFileDetailsDialog.class.getSimpleName(),
					"Invalid or missing validateCountry error true", "");
			isError = false;
			error.append(" Country ");
		}
		return isError;
	}

	/**
	 * Validate status.
	 *
	 * @param selectedStatus the selected status
	 * @return true, if successful
	 */
	private boolean validateStatus(String selectedStatus) {
		logger.printLogger(LogFileDetailsDialog.class.getSimpleName(), "validateStatus",
				"");

		boolean isError = true;
		if (statusMap != null && statusMap.get(selectedStatus) == null) {
			logger.printLogger(LogFileDetailsDialog.class.getSimpleName(),
					"Invalid or missing validateStatus error true", "");
			isError = false;
			error.append(" Upgrade Status ");
		}
		return isError;
	}

	/**
	 * Validate current version.
	 *
	 * @param selectedVersion the selected version
	 * @return true, if successful
	 */
	private boolean validateCurrentVersion(String selectedVersion) {
		logger.printLogger(LogFileDetailsDialog.class.getSimpleName(),
				"validateCurrentVersion", "");
		boolean isError = true;
		if (versionMap != null && versionMap.get(selectedVersion) == null) {
			logger.printLogger(LogFileDetailsDialog.class.getSimpleName(),
					"Invalid or missing validateCurrentVersion error true", "");
			isError = false;
			error.append(" Current Version ");
		}
		return isError;
	}

	/**
	 * Validate previous version.
	 *
	 * @param selectedVersion the selected version
	 * @return true, if successful
	 */
	private boolean validatePreviousVersion(String selectedVersion) {
		logger.printLogger(LogFileDetailsDialog.class.getSimpleName(),
				"validatePreviousVersion", "");
		boolean isError = true;
		if (versionMap != null && versionMap.get(selectedVersion) == null) {
			logger.printLogger(LogFileDetailsDialog.class.getSimpleName(),
					"Invalid or missing validatePreviousVersion error true", "");
			isError = false;
			error.append(" Previous Version ");
		}
		return isError;
	}

	/**
	 * Max length.
	 *
	 * @param i the i
	 * @return the event handler
	 */
	EventHandler<KeyEvent> maxLength(final Integer i) {
		return new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent arg0) {

				TextField tx = (TextField) arg0.getSource();
				if (tx.getText().length() >= i) {
					arg0.consume();
				}
			}
		};
	}

	/**
	 * Max length text area.
	 *
	 * @param i the i
	 * @return the event handler
	 */
	EventHandler<KeyEvent> maxLengthTextArea(final Integer i) {
		return new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent arg0) {
				TextArea tx = (TextArea) arg0.getSource();
				if (tx.getText().length() >= i) {
					arg0.consume();
				}
			}
		};
	}

}
