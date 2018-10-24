/*
 * 
 */

package com.jnj.jws.usb.process.javafx;

import static com.jnj.jws.USBConstant.ACCORDIAN_POST_UPDATE_FILE;
import static com.jnj.jws.USBConstant.ACCORDIAN_PRE_UPDATE_FILE;
import static com.jnj.jws.USBConstant.ACCORDIAN_PRE_UPDATE_FILE_MSG;
import static com.jnj.jws.USBConstant.ASSET_HASH;
import static com.jnj.jws.USBConstant.CODE_424;
import static com.jnj.jws.USBConstant.CREATE_USB_LIST_FUNC;
import static com.jnj.jws.USBConstant.DOUBLE_FIVEZERO;
import static com.jnj.jws.USBConstant.DOUBLE_TEN;
import static com.jnj.jws.USBConstant.DOUBLE_THREE;
import static com.jnj.jws.USBConstant.DOUBLE_TWO;
import static com.jnj.jws.USBConstant.DOUBLE_ZEROEIGHT;
import static com.jnj.jws.USBConstant.DOUBLE_ZEROFIFTEEN;
import static com.jnj.jws.USBConstant.DOUBLE_ZEROFIVE;
import static com.jnj.jws.USBConstant.DOUBLE_ZEROFOUR;
import static com.jnj.jws.USBConstant.DOUBLE_ZERONINE;
import static com.jnj.jws.USBConstant.DOUBLE_ZEROSEVENTEEN;
import static com.jnj.jws.USBConstant.DOUBLE_ZEROTEN;
import static com.jnj.jws.USBConstant.DOUBLE_ZEROTHIRTEEN;
import static com.jnj.jws.USBConstant.DOUBLE_ZEROTWENTY;
import static com.jnj.jws.USBConstant.ENTRIES;
import static com.jnj.jws.USBConstant.INITIALIZE_TABLE_GRID_FUNC;
import static com.jnj.jws.USBConstant.INSETS_BOTTOM_TWENTYFIVE;
import static com.jnj.jws.USBConstant.INSETS_LEFT_TWENTYFIVE;
import static com.jnj.jws.USBConstant.INSETS_RIGHT_TWENTYFIVE;
import static com.jnj.jws.USBConstant.INSETS_TOP_TWENTYFIVE;
import static com.jnj.jws.USBConstant.INT_EIGHTHUNDRED;
import static com.jnj.jws.USBConstant.INT_FIVE;
import static com.jnj.jws.USBConstant.INT_FIVEHUNDRED;
import static com.jnj.jws.USBConstant.INT_FOUR;
import static com.jnj.jws.USBConstant.INT_FOURHUNDRED;
import static com.jnj.jws.USBConstant.INT_SEVENHUNDRED;
import static com.jnj.jws.USBConstant.INT_SIX;
import static com.jnj.jws.USBConstant.INT_SIXTY;
import static com.jnj.jws.USBConstant.INT_TEN;
import static com.jnj.jws.USBConstant.INT_THOUSANDTHREEHUNDRED;
import static com.jnj.jws.USBConstant.INT_THOUSANDTWOHUNDRED;
import static com.jnj.jws.USBConstant.INT_THREE;
import static com.jnj.jws.USBConstant.INT_THREEHUNDRED;
import static com.jnj.jws.USBConstant.INT_TWENTY;
import static com.jnj.jws.USBConstant.INT_TWENTYFOUR;
import static com.jnj.jws.USBConstant.INT_TWO;
import static com.jnj.jws.USBConstant.INT_TWOHUNDRED;
import static com.jnj.jws.USBConstant.INT_ZERO;
import static com.jnj.jws.USBConstant.MISSING_POST_UPDATE_FILE;
import static com.jnj.jws.USBConstant.POST_REASON_FOR_REMOVE;
import static com.jnj.jws.USBConstant.POST_UPDATE_FILES;
import static com.jnj.jws.USBConstant.PRE_UPDATE_FILES;
import static com.jnj.jws.USBConstant.PRE_UPDATE_FILE_WARNING_LABEL;
import static com.jnj.jws.USBConstant.PRE_UPDATE_NOT_RECONCILE_WITH_SYS;
import static com.jnj.jws.USBConstant.PROCESS_OTHER_USB_WARNING;
import static com.jnj.jws.USBConstant.PROCESS_USB_FAILED_MSG;
import static com.jnj.jws.USBConstant.PROCESS_USB_FAILED_TITLE;
import static com.jnj.jws.USBConstant.PROCESS_USB_MSG_TITLE;
import static com.jnj.jws.USBConstant.REASON_FOR_REMOVE;
import static com.jnj.jws.USBConstant.REMOVE;
import static com.jnj.jws.USBConstant.REMOVE_PRE_UPDATE_HEADER;
import static com.jnj.jws.USBConstant.REQARGS_LENGTH_TWO;
import static com.jnj.jws.USBConstant.SHOWING;
import static com.jnj.jws.USBConstant.TABLE_HEADER_STYLE;
import static com.jnj.jws.USBConstant.USB_NOT_DETECTED;
import static com.jnj.jws.USBConstant.USB_RECONCILIATION_FAILED_TITLE;
import static com.jnj.jws.USBConstant.USB_UNWANTED_FILES;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Properties;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.jnj.jws.usb.process.CheckUsb;
import com.jnj.jws.usb.process.DeleteFileDetails;
import com.jnj.jws.usb.process.ProcessUsbFile;
import com.jnj.jws.util.AssetComparator;
import com.jnj.jws.util.EncryptDecryptUtil;
import com.jnj.jws.util.JavaWebStartLogger;
import com.jnj.jws.vo.DeleteFileRespVo;
import com.jnj.jws.vo.FileProcessResponseVo;
import com.jnj.jws.vo.ProcessUSBContentVO;
import com.jnj.jws.vo.ProcessUSBGenericVO;
import com.jnj.jws.vo.ProcessUsbVo;
import com.jnj.jws.vo.ReasonForRemovalVo;
import com.jnj.jws.vo.ReasonVO;
import com.jnj.jws.vo.UsbAssetVO;
import com.jnj.jws.vo.UsbErrorEnum;
import com.jnj.jws.vo.UsbErrorVo;
import com.jnj.jws.vo.UserHeaderVO;

import javafx.beans.binding.Bindings;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TitledPane;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;

/**
 * This is class to invoke java web start for Process USB and Process other's USB.
 *
 * @author 555786
 */
public class ManageUsbDialog extends CheckUsb {

	/** The css. */
	protected String css = null;

	/** The data. */
	protected ObservableList<UsbAssetVO> data;

	/** The task update stage. */
	private final Stage taskUpdateStage = new Stage(StageStyle.UTILITY);

	/** The usbtable. */
	private TableView<UsbAssetVO> usbtable = new TableView<>();

	/** The preupdate not reconcile list. */
	protected List<UsbAssetVO> preupdateNotReconcileList;

	/** The postupdate list. */
	private List<UsbAssetVO> postupdateList;

	/** The preupdate list. */
	protected List<UsbAssetVO> preupdateList;

	/** The title lable. */
	private Label titleLable = new Label();

	/** The warning lable. */
	private Label warningLable = new Label();

	/** The usb warn lable. */
	private Label usbWarnLable = new Label();

	/** The reason remove dialog. */
	private final Stage reasonRemoveDialog = new Stage();

	/** The properties. */
	private Properties properties;

	/** The error vo. */
	private UsbErrorVo errorVo;

	/** The user name. */
	private String userName;

	/** The other user name. */
	private String otherUserName;

	/** The other wwid. */
	private String otherWwid;

	/** The pagination. */
	private Pagination pagination = new Pagination();

	/** The rows per page. */
	private int rowsPerPage = INT_FIVE;

	/** The jwttoken. */
	private String jwttoken = "";

	/** The user header VO. */
	private UserHeaderVO userHeaderVO = null;

	/** The preupdate file dialog. */
	private final Stage preupdateFileDialog = new Stage();

	/** The fx error dialog. */
	private JavaFxDefaultErrorDialog fxErrorDialog = new JavaFxDefaultErrorDialog();

	/** The page upper limit. */
	private int pageUpperLimit;

	/** The page lower limit. */
	private int pageLowerLimit = 1;

	/** The table page info. */
	private Label tablePageInfo = new Label();

	/** The page number of table. */
	private int pageNumberOfTable;

	/** The accordion text. */
	private Label accordionText = new Label();

	/** The reason failure status. */
	private boolean reasonFailureStatus = false;

	/** The pcvo. */
	private ProcessUSBContentVO pcvo = new ProcessUSBContentVO();

	/** The jwslogger. */
	private JavaWebStartLogger jwslogger = new JavaWebStartLogger();

	/** The generic vo. */
	private ProcessUSBGenericVO genericVo = new ProcessUSBGenericVO();

	/** The remove button. */
	private final Button removeButton = new Button(REMOVE);

	/** The cancel button. */
	private Button cancelButton = new Button("Cancel");

	/**
	 * Method to create the grid/table view for the USB files.
	 *
	 * @param stage the stage
	 * @param args the args
	 */
	public void createUsbListGrid(final Stage stage, String[] args) {
		preupdateFileDialog.initOwner(stage);
		preupdateFileDialog.setTitle("Pre update File Details");
		preupdateFileDialog.initModality(Modality.APPLICATION_MODAL);
		try {
			extractArguments(args, stage);
		}
		catch (Exception ex) {
			jwslogger.printLogger(ManageUsbDialog.class.getSimpleName(),
					CREATE_USB_LIST_FUNC, "Exception while createUsbListGrid" + ex);

			fxErrorDialog.createErrorDialog(stage, PROCESS_USB_FAILED_TITLE,
					PROCESS_USB_FAILED_MSG, userName, false);
		}

	}

	/**
	 * Extract arguments.
	 *
	 * @param args the args
	 * @param stage the stage
	 * @throws JsonMappingException the json mapping exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	void extractArguments(String[] args, Stage stage) throws JsonMappingException,
			IOException {
		String wwid = null;
		if (null != args && args.length > REQARGS_LENGTH_TWO) {
			wwid = args[REQARGS_LENGTH_TWO];
			otherWwid = wwid;
			genericVo.setOtherWwid(otherWwid);
		}
		if (null != args && args.length > 0) {
			jwttoken = args[0];
			genericVo.setJwtToken(jwttoken);
		}
		if (null != args && args.length > 1) {
			EncryptDecryptUtil edutil = new EncryptDecryptUtil();
			String useheaderjson = edutil.parseJWT(args[1]);
			ObjectMapper mapper = new ObjectMapper();
			if (useheaderjson != null) {
				userHeaderVO = mapper.readValue(useheaderjson, UserHeaderVO.class);
				genericVo.setUserHeaderVO(userHeaderVO);
			}
		}
		if (userHeaderVO != null) {
			userName = userHeaderVO.getFirstName();
			genericVo.setUsername(userName);
			jwslogger.printLogger(ManageUsbDialog.class.getSimpleName(),
					CREATE_USB_LIST_FUNC, "userName" + userName);
		}
		else {
			jwslogger.printLogger(ManageUsbDialog.class.getSimpleName(),
					CREATE_USB_LIST_FUNC, "userName" + userName);
		}

		String reconciledJson = checkUSBContent(wwid, jwttoken);
		jwslogger.printLogger(ManageUsbDialog.class.getSimpleName(),
				CREATE_USB_LIST_FUNC, "reconciledJson" + userName);

		processUSBContentValidation(reconciledJson, stage);
	}

	/**
	 * Method to initialize table view Grid Manage USB.
	 *
	 * @param stage the stage
	 * @param name the name
	 * @param othersWwid the others wwid
	 * @param errorVo2 the error vo 2
	 * @param i the i
	 */
	void initializeTableViewGrid(final Stage stage, String othersWwid, UsbErrorVo errorVo2) {
		taskUpdateStage.initModality(Modality.APPLICATION_MODAL);
		taskUpdateStage.initOwner(stage);
		css = this.getClass().getClassLoader().getResource("stylesheet.css")
				.toExternalForm();
		HBox hbox1 = new HBox(INT_TWENTY);
		hbox1.getStyleClass().add("redBox");
		final Label otherUserLabel = new Label("  You are reading " + otherUserName
				+ PROCESS_OTHER_USB_WARNING);
		otherUserLabel.setWrapText(true);
		hbox1.getChildren().addAll(otherUserLabel);
		stage.setTitle("Process USB Stick");
		// add columns to USB table.
		addColumnsToUSBTable(stage);

		final HBox hb = new HBox();
		final VBox vbox = new VBox();
		hb.setSpacing(INT_THREE);
		hb.setAlignment(Pos.CENTER);
		usbWarnLable.setText(USB_UNWANTED_FILES);
		usbWarnLable.getStyleClass().add("warningRed");
		usbWarnLable.setPrefWidth(INT_THOUSANDTWOHUNDRED);
		vbox.setSpacing(INT_FIVE);
		vbox.setPadding(new Insets(INT_TEN, 0, 0, INT_TEN));
		AnchorPane anchor = new AnchorPane();
		AnchorPane.setTopAnchor(pagination, DOUBLE_TEN);
		AnchorPane.setRightAnchor(pagination, DOUBLE_TEN);
		AnchorPane.setBottomAnchor(pagination, DOUBLE_TEN);
		AnchorPane.setLeftAnchor(pagination, DOUBLE_TEN);
		anchor.getChildren().addAll(pagination);
		TitledPane t1 = new TitledPane(titleLable.getText(), accordionText);
		t1.setGraphic(new ImageView(new Image(this.getClass().getClassLoader()
				.getResourceAsStream("infowhite.png"), 0, INT_TWENTYFOUR, true, true)));
		t1.setContentDisplay(ContentDisplay.RIGHT);
		Accordion accordion = new Accordion();
		accordion.getPanes().addAll(t1);
		AnchorPane pageinfo = new AnchorPane();
		AnchorPane.setTopAnchor(tablePageInfo, DOUBLE_TEN);
		AnchorPane.setRightAnchor(tablePageInfo, DOUBLE_TEN);
		AnchorPane.setBottomAnchor(tablePageInfo, DOUBLE_TEN);
		AnchorPane.setLeftAnchor(tablePageInfo, 0.0);
		pageinfo.getChildren().addAll(tablePageInfo);

		if (othersWwid != null && !othersWwid.isEmpty()) {
			if (errorVo2 != null && errorVo2.getValue().equals(CODE_424)) {
				jwslogger.printLogger(ManageUsbDialog.class.getSimpleName(),
						INITIALIZE_TABLE_GRID_FUNC, errorVo2.getValue() + "==CODE 424");

				vbox.getChildren().addAll(hbox1, usbWarnLable, warningLable, accordion,
						usbtable, hb, pageinfo, anchor);
			}
			else {
				vbox.getChildren().addAll(hbox1, warningLable, accordion, usbtable, hb,
						pageinfo, anchor);
			}
		}
		else {
			if (errorVo2 != null && errorVo2.getValue().equals(CODE_424)) {
				jwslogger.printLogger(ManageUsbDialog.class.getSimpleName(),
						INITIALIZE_TABLE_GRID_FUNC, errorVo2.getValue() + "==CODE 424");

				vbox.getChildren().addAll(usbWarnLable, warningLable, accordion,
						usbtable, hb, pageinfo, anchor);
			}
			else {
				vbox.getChildren().addAll(warningLable, accordion, usbtable, hb,
						pageinfo, anchor);
			}
		}
		ScrollPane scrollPane = new ScrollPane();
		scrollPane.setContent(vbox);
		scrollPane.setStyle("-fx-border-color: white;");
		vbox.setMaxSize(1400, INT_EIGHTHUNDRED);
		Scene scene = new Scene(scrollPane, 1400, INT_SEVENHUNDRED);
		scene.getStylesheets().add(css);
		stage.setScene(scene);
		stage.setHeight(INT_SEVENHUNDRED);
		stage.setWidth(INT_THOUSANDTHREEHUNDRED);
		stage.show();
	}

	/**
	 * @param stage
	 */
	private void addColumnsToUSBTable(final Stage stage) {
		TableColumn assetCol = new TableColumn("Asset Serial Number");
		assetCol.setSortType(TableColumn.SortType.ASCENDING);
		assetCol.setCellValueFactory(new PropertyValueFactory<UsbAssetVO, String>(
				"assetSerialNumber"));
		TableColumn swCol = new TableColumn("Software Name");
		swCol.setCellValueFactory(new PropertyValueFactory<UsbAssetVO, String>(
				"softwareName"));
		TableColumn modelCol = new TableColumn("Model");
		modelCol.setCellValueFactory(new PropertyValueFactory<UsbAssetVO, String>("model"));
		TableColumn versionNoCol = new TableColumn("Version Number");
		versionNoCol.setCellValueFactory(new PropertyValueFactory<UsbAssetVO, String>(
				"versionNumber"));
		TableColumn coCol = new TableColumn("Checkout Completed On");
		coCol.setCellValueFactory(new Callback<CellDataFeatures<UsbAssetVO, String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<UsbAssetVO, String> param) {
				return checkoutInfo(param);
			}
		});
		TableColumn assetStatusCol = new TableColumn("Asset Status");
		assetStatusCol.setCellValueFactory(new PropertyValueFactory<UsbAssetVO, String>(
				"assetStatus"));
		assetStatusCol
				.setCellFactory(new Callback<TableColumn<UsbAssetVO, String>, TableCell<UsbAssetVO, String>>() {
					public TableCell<UsbAssetVO, String> call(
							TableColumn<UsbAssetVO, String> p) {
						return new CheckOutCell(usbtable, stage);
					}
				});
		assetCol.prefWidthProperty().bind(
				usbtable.widthProperty().multiply(DOUBLE_ZEROSEVENTEEN));
		assetCol.setResizable(false);
		versionNoCol.prefWidthProperty().bind(
				usbtable.widthProperty().multiply(DOUBLE_ZEROFIFTEEN));
		versionNoCol.setResizable(false);
		modelCol.prefWidthProperty().bind(
				usbtable.widthProperty().multiply(DOUBLE_ZEROTEN));
		modelCol.setResizable(false);
		swCol.prefWidthProperty().bind(
				usbtable.widthProperty().multiply(DOUBLE_ZEROFIFTEEN));
		swCol.setResizable(false);
		coCol.prefWidthProperty().bind(
				usbtable.widthProperty().multiply(DOUBLE_ZEROTWENTY));
		coCol.setResizable(false);
		assetStatusCol.prefWidthProperty().bind(
				usbtable.widthProperty().multiply(DOUBLE_ZEROTHIRTEEN));
		assetStatusCol.setResizable(false);

		usbtable.getColumns().addAll(assetCol, swCol, modelCol, versionNoCol, coCol,
				assetStatusCol);

	}

	/**
	 * Checkout info.
	 *
	 * @param param the param
	 * @return the read only object wrapper
	 */
	ReadOnlyObjectWrapper<String> checkoutInfo(CellDataFeatures<UsbAssetVO, String> param) {
		ObjectMapper objectMapper = new ObjectMapper();
		String convertedJson = null;
		String checkoutOn = null;
		Gson gson = new Gson();
		DateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
		Calendar calendar = Calendar.getInstance();
		try {
			if ((param.getValue().getCheckOutHistoryVO() != null)
					&& (param.getValue().getCheckOutHistoryVO().getCheckoutOn() != null)) {
				convertedJson = gson.toJson(param.getValue().getCheckOutHistoryVO()
						.getCheckoutOn());
				checkoutOn = objectMapper.readValue(convertedJson, String.class);
				long milliSeconds = Long.parseLong(checkoutOn);
				calendar.setTimeInMillis(milliSeconds);

				checkoutOn = formatter.format(calendar.getTime());
			}
		}
		catch (Exception e) {
			jwslogger.printLogger(ManageUsbDialog.class.getSimpleName(),
					INITIALIZE_TABLE_GRID_FUNC, "Exception" + e);
		}
		String finalCheckOutValue;
		if (checkoutOn == null) {
			finalCheckOutValue = "";
		}
		else {
			finalCheckOutValue = checkoutOn.toUpperCase();
		}
		return new ReadOnlyObjectWrapper<>(finalCheckOutValue);
	}

	/**
	 * File process response json.
	 *
	 * @param processUsbJsonValue the process usb json value
	 * @return the file process response vo
	 */
	FileProcessResponseVo fileProcessResponseJson(String processUsbJsonValue) {
		ObjectMapper mapper = new ObjectMapper();
		FileProcessResponseVo fpvo = null;
		try {
			jwslogger.printLogger(ManageUsbDialog.class.getSimpleName(),
					"fileProcessResponseJson:: ", processUsbJsonValue);
			fpvo = mapper.readValue(processUsbJsonValue, FileProcessResponseVo.class);
		}
		catch (IOException e) {
			jwslogger.printLogger(ManageUsbDialog.class.getSimpleName(),
					"fileProcessResponseJson:", "Exception while fileProcessResponseJson"
							+ e);
		}
		return fpvo;
	}

	/**
	 * Seperate json data.
	 *
	 * @param dataObj the data obj
	 * @return the list
	 */
	List<UsbAssetVO> seperateJsonData(Object dataObj) {
		List<UsbAssetVO> objList = null;
		Gson gson = new Gson();
		String convertedJson = gson.toJson(dataObj);
		jwslogger.printLogger(ManageUsbDialog.class.getSimpleName(),
				"seperateJsonData:convertedJSON:: ", gson.toJson(dataObj));
		ObjectMapper mapper1 = new ObjectMapper();
		try {
			objList = mapper1.readValue(convertedJson,
					new TypeReference<List<UsbAssetVO>>() {
					});
		}
		catch (Exception ioe) {
			jwslogger.printLogger(ManageUsbDialog.class.getSimpleName(),
					"seperateJsonData", "Exception while converting json " + ioe);
		}

		return objList;
	}

	/**
	 * Common method to process preupdate not reconciled with system Removal and post
	 * update file process.
	 *
	 * @param objList the obj list
	 * @param fileStatusText the file status text
	 * @param stage the stage
	 * @param isDirect the is direct
	 * @param pageNumber the page number
	 * @param genericVO the generic VO
	 */
	protected void populateDataItems(List<UsbAssetVO> objList, String fileStatusText,
			final Stage stage, int pageNumber, ProcessUSBGenericVO genericVO) {
		jwslogger.printLogger(ManageUsbDialog.class.getSimpleName(),
				"Starting poulatedataitems for < ", fileStatusText + ">");
		TableColumn processCol = new TableColumn("");
		processCol.setCellValueFactory(new PropertyValueFactory<UsbAssetVO, String>(""));
		processCol.setResizable(false);
		TableColumn removeCol = new TableColumn("");
		removeCol.setCellValueFactory(new PropertyValueFactory<UsbAssetVO, String>(""));
		removeCol.setResizable(false);
		int i = 0;
		data = FXCollections.observableArrayList();
		jwslogger.printLogger(ManageUsbDialog.class.getSimpleName(),
				"populateDataItems:Asset object list size: ",
				String.valueOf(objList.size()));
		AssetComparator assetComparator = new AssetComparator();
		objList.sort(assetComparator);
		for (UsbAssetVO usbassetvo : objList) {
			data.add(usbassetvo);
			jwslogger.printLogger(ManageUsbDialog.class.getSimpleName(),
					"Assest serial nmbr: ", +i + " : "
							+ data.get(i).getAssetSerialNumber());
			i++;
		}
		usbtable = new TableView<>();
		userHeaderVO = genericVO.getUserHeaderVO();
		if (fileStatusText.equals(PRE_UPDATE_NOT_RECONCILE_WITH_SYS) && !data.isEmpty()) {
			preupdateNotReconciled(stage, pageNumber, genericVO, removeCol);
		}
		else if (fileStatusText.equals(POST_UPDATE_FILES) && !data.isEmpty()) {
			postUpdateFile(stage, pageNumber, genericVO, processCol);
		}
		else if (fileStatusText.equals(PRE_UPDATE_FILES) && !data.isEmpty()) {
			preUpdateReconciled(stage, pageNumber, genericVO, removeCol);
		}
		else {
			stage.close();
		}
	}

	/**
	 * Preupdate not reconciled.
	 *
	 * @param objList the obj list
	 * @param fileStatusText the file status text
	 * @param stage the stage
	 * @param isDirect the is direct
	 * @param pageNumber the page number
	 * @param genericVO the generic VO
	 * @param removeCol the remove col
	 */
	void preupdateNotReconciled(final Stage stage, int pageNumber,
			ProcessUSBGenericVO genericVO, TableColumn removeCol) {
		errorVo = genericVO.getUsbErrorVo();
		userName = genericVO.getUsername();
		jwttoken = genericVO.getJwtToken();
		titleLable.setText("PRE-UPDATE FILES");
		titleLable.getStyleClass().add(TABLE_HEADER_STYLE);
		titleLable.setPrefWidth(INT_THOUSANDTWOHUNDRED);
		otherWwid = genericVO.getOtherWwid();
		accordionText.setText(ACCORDIAN_PRE_UPDATE_FILE);
		accordionText.setMaxWidth(INT_THOUSANDTWOHUNDRED);
		accordionText.setWrapText(true);
		warningLable.setText(PRE_UPDATE_FILE_WARNING_LABEL);
		warningLable.getStyleClass().add("warningRed");
		warningLable.setPrefWidth(INT_THOUSANDTWOHUNDRED);
		otherUserName = genericVO.getOtherUserName();
		removeCol
				.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<UsbAssetVO, Boolean>, ObservableValue<Boolean>>() {
					public ObservableValue<Boolean> call(
							TableColumn.CellDataFeatures<UsbAssetVO, Boolean> p) {
						return new SimpleBooleanProperty(p.getValue() != null);
					}
				});

		removeCol
				.setCellFactory(new Callback<TableColumn<UsbAssetVO, Boolean>, TableCell<UsbAssetVO, Boolean>>() {
					public TableCell<UsbAssetVO, Boolean> call(
							TableColumn<UsbAssetVO, Boolean> p) {
						return new RemoveButtonCell(usbtable, stage, false);
					}
				});
		removeCol.setSortable(false);
		removeCol.prefWidthProperty().bind(
				usbtable.widthProperty().multiply(DOUBLE_ZERONINE));
		removeCol.setResizable(false);
		drawPagination(pageNumber);
		initializeTableViewGrid(stage, otherWwid, errorVo);
		usbtable.getColumns().add(removeCol);
	}

	/**
	 * Post update file.
	 *
	 * @param objList the obj list
	 * @param fileStatusText the file status text
	 * @param stage the stage
	 * @param isDirect the is direct
	 * @param pageNumber the page number
	 * @param genericVO the generic VO
	 * @param processCol the process col
	 */
	void postUpdateFile(final Stage stage, int pageNumber, ProcessUSBGenericVO genericVO,
			TableColumn processCol) {

		jwslogger.printLogger(
				ManageUsbDialog.class.getSimpleName(),
				"populateDataItems",
				"Pre update file reconcile size:pcvo.getPreupdatefiles(): "
						+ pcvo.getPreupdatefiles());
		postupdateList = data;
		errorVo = genericVO.getUsbErrorVo();
		userName = genericVO.getUsername();
		otherWwid = genericVO.getOtherWwid();
		jwttoken = genericVO.getJwtToken();
		preupdateList = genericVO.getPreupdateList();
		titleLable.setText("POST-UPDATE FILES");
		titleLable.getStyleClass().add(TABLE_HEADER_STYLE);
		titleLable.setPrefWidth(INT_THOUSANDTWOHUNDRED);
		otherUserName = genericVO.getOtherUserName();
		accordionText.setText(ACCORDIAN_POST_UPDATE_FILE);
		accordionText.setMaxWidth(INT_THOUSANDTWOHUNDRED);
		accordionText.setWrapText(true);
		warningLable.setText("");
		warningLable.getStyleClass().add("nobox");

		processCol
				.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<UsbAssetVO, Boolean>, ObservableValue<Boolean>>() {

					public ObservableValue<Boolean> call(
							TableColumn.CellDataFeatures<UsbAssetVO, Boolean> p) {
						return new SimpleBooleanProperty(p.getValue() != null);
					}
				});

		processCol
				.setCellFactory(new Callback<TableColumn<UsbAssetVO, Boolean>, TableCell<UsbAssetVO, Boolean>>() {
					public TableCell<UsbAssetVO, Boolean> call(
							TableColumn<UsbAssetVO, Boolean> p) {
						return new ButtonCell(usbtable, stage);
					}
				});

		rowsPerPage = INT_FIVE;
		processCol.setSortable(false);
		processCol.prefWidthProperty().bind(
				usbtable.widthProperty().multiply(DOUBLE_ZERONINE));
		processCol.setResizable(false);
		drawPagination(pageNumber);

		initializeTableViewGrid(stage, otherWwid, genericVO.getUsbErrorVo());
		usbtable.getColumns().add(processCol);

	}

	/**
	 * Pre update reconciled.
	 *
	 * @param objList the obj list
	 * @param fileStatusText the file status text
	 * @param stage the stage
	 * @param isDirect the is direct
	 * @param pageNumber the page number
	 * @param genericVO the generic VO
	 * @param removeCol the remove col
	 */
	void preUpdateReconciled(final Stage stage, int pageNumber,
			ProcessUSBGenericVO genericVO, TableColumn removeCol) {
		preupdateList = data;
		errorVo = genericVO.getUsbErrorVo();
		userName = genericVO.getUsername();
		jwttoken = genericVO.getJwtToken();
		titleLable.setText("PRE-UPDATE FILES");
		titleLable.getStyleClass().add(TABLE_HEADER_STYLE);
		titleLable.setPrefWidth(INT_THOUSANDTWOHUNDRED);
		otherWwid = genericVO.getOtherWwid();
		accordionText.setText(ACCORDIAN_PRE_UPDATE_FILE_MSG);
		accordionText.setMaxWidth(INT_THOUSANDTWOHUNDRED);
		accordionText.setWrapText(true);
		otherUserName = genericVO.getOtherUserName();
		warningLable.setText("");
		warningLable.getStyleClass().add("nobox");
		removeCol
				.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<UsbAssetVO, Boolean>, ObservableValue<Boolean>>() {

					public ObservableValue<Boolean> call(
							TableColumn.CellDataFeatures<UsbAssetVO, Boolean> p) {
						return new SimpleBooleanProperty(p.getValue() != null);
					}
				});
		removeCol
				.setCellFactory(new Callback<TableColumn<UsbAssetVO, Boolean>, TableCell<UsbAssetVO, Boolean>>() {
					public TableCell<UsbAssetVO, Boolean> call(
							TableColumn<UsbAssetVO, Boolean> p) {
						return new RemoveButtonPreupdateCell(usbtable, stage, true,
								genericVO);
					}
				});
		rowsPerPage = INT_FIVE;
		removeCol.setSortable(false);
		removeCol.prefWidthProperty().bind(
				usbtable.widthProperty().multiply(DOUBLE_ZERONINE));
		removeCol.setResizable(false);
		drawPagination(pageNumber);

		initializeTableViewGrid(stage, otherWwid, genericVO.getUsbErrorVo());
		usbtable.getColumns().add(removeCol);

	}

	/**
	 * Draw pagination.
	 *
	 * @param pageNumber the page number
	 * @param stage the stage
	 * @param removeCol the remove col
	 */
	void drawPagination(int pageNumber) {
		if (data.size() >= rowsPerPage) {
			rowsPerPage = INT_FIVE;
		}
		else {
			rowsPerPage = data.size();
		}
		// TO determine number of pages
		int pageSize;
		if ((data.size() % rowsPerPage) != 0) {
			pageSize = (data.size() / rowsPerPage) + 1;
		}
		else {
			pageSize = data.size() / rowsPerPage;
		}

		pagination = new Pagination(pageSize, pageNumber);
		pagination.setPageFactory(new Callback<Integer, Node>() {
			public Node call(Integer pageIndex) {
				if (pageIndex > data.size() / rowsPerPage) {
					return null;
				}
				else {
					pageLowerLimit = 1 + ((pageIndex) * rowsPerPage);
					if ((rowsPerPage + ((pageIndex) * rowsPerPage)) > data.size()) {
						pageUpperLimit = data.size();
					}
					else {
						pageUpperLimit = rowsPerPage + ((pageIndex) * rowsPerPage);
					}
					tablePageInfo.setText(SHOWING + pageLowerLimit + " to "
							+ pageUpperLimit + " of " + data.size() + " " + ENTRIES);
					pageNumberOfTable = pageIndex;
					return createPage(pageIndex, data, usbtable);
				}
			}
		});
	}

	/**
	 * Creates the page.
	 *
	 * @param pageIndex the page index
	 * @param data2 the data 2
	 * @param usbtable2 the usbtable 2
	 * @return the v box
	 */
	private VBox createPage(int pageIndex, ObservableList<UsbAssetVO> data2,
			TableView<UsbAssetVO> usbtable2) {
		jwslogger.printLogger(ManageUsbDialog.class.getSimpleName(),
				"Inside Create Page Function", "");
		int fromIndex = pageIndex * rowsPerPage;
		int toIndex = Math.min(fromIndex + rowsPerPage, data2.size());
		usbtable2.setItems(FXCollections.observableArrayList(data2.subList(fromIndex,
				toIndex)));
		// Changes For Table Row Fit(Removing Empty Rows) ----START
		ObservableList list = FXCollections.observableArrayList(data2.subList(fromIndex,
				toIndex));
		jwslogger
				.printLogger(ManageUsbDialog.class.getSimpleName(),
						"Inside Pagination Data Size", +list.size() + "  Page Index "
								+ pageIndex);
		usbtable2.setFixedCellSize(INT_SIXTY);
		usbtable2.getSortOrder().add(usbtable2.getColumns().get(INT_ZERO));
		float addingSpace;
		if (list.size() == INT_FIVE) {
			addingSpace = (float) ((list.size() / DOUBLE_THREE) - DOUBLE_ZEROEIGHT);
		}
		else if (list.size() == INT_FOUR) {
			addingSpace = (float) ((list.size() / DOUBLE_THREE) - DOUBLE_ZEROFIVE);
		}
		else if (list.size() == INT_THREE) {
			addingSpace = (float) ((list.size() / DOUBLE_THREE) - DOUBLE_ZEROFOUR);
		}
		else if (list.size() == INT_TWO) {
			addingSpace = (float) ((list.size() / DOUBLE_TWO) - DOUBLE_ZEROFOUR);
		}
		else {
			addingSpace = (float) (list.size() - DOUBLE_ZEROFOUR);
		}

		usbtable2.prefHeightProperty().bind(
				usbtable2.fixedCellSizeProperty().multiply(
						Bindings.size(usbtable2.getItems()).add(addingSpace)));
		usbtable2.minHeightProperty().bind(usbtable2.prefHeightProperty());
		usbtable2.maxHeightProperty().bind(usbtable2.prefHeightProperty());

		// Changes For Table Row Fit ----END

		return new VBox();
	}

	/**
	 * method to invoke process USB file.
	 *
	 * @param assetSnr the asset snr
	 * @return the string
	 */

	String usbFileProcessing(String assetSnr) {
		jwslogger.printLogger(ManageUsbDialog.class.getSimpleName(),
				"Selected rows serial number:", assetSnr);
		ProcessUsbFile pusb = new ProcessUsbFile();
		String json = pusb.processUSBFile(assetSnr, otherWwid, jwttoken);
		jwslogger.printLogger(ManageUsbDialog.class.getSimpleName(),
				"Process USB Json:[", json + "]");
		return json;
	}

	/**
	 * The Class ButtonCell.
	 */
	// Inner class which creates Process button inside the tableview
	private class ButtonCell extends TableCell<UsbAssetVO, Boolean> {

		/** The process button. */
		private Button processButton = new Button("Process");

		/**
		 * Instantiates a new button cell.
		 *
		 * @param tblView the tbl view
		 * @param stage the stage
		 */
		ButtonCell(final TableView tblView, final Stage stage) {

			processButton.setOnAction(new EventHandler<ActionEvent>() {

				public void handle(ActionEvent t) {
					int selectdIndex = getTableRow().getIndex();
					UsbAssetVO selectedRecord = (UsbAssetVO) tblView.getItems().get(
							selectdIndex);
					runTask(getTableRow(), selectedRecord, stage, tblView);

				}
			});

		}

		/**
		 * places an process button in the row only if the row is not empty.
		 *
		 * @param item the item
		 * @param empty the empty
		 */
		@Override
		protected void updateItem(Boolean item, boolean checkIsEmpty) {
			super.updateItem(item, checkIsEmpty);
			if (!checkIsEmpty) {
				setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
				setGraphic(processButton);
			}
			else {
				setGraphic(null);
				setText(null);
				setContentDisplay(null);
			}
		}

		/**
		 * Method to create thread to process the file and displays the progress bar until
		 * completes and navigates to log file details screen.
		 *
		 * @param tableRow the table row
		 * @param assetSnr the asset snr
		 * @param stage the stage
		 * @param tblView the tbl view
		 * @return the string
		 */
		private String runTask(final TableRow tableRow, final UsbAssetVO assetSnr,
				final Stage stage, final TableView tblView) {

			final double wndwWidth = 300.0d;
			Label updateLabel = new Label("Running tasks...");
			updateLabel.setPrefWidth(wndwWidth);
			ProgressIndicator progress = new ProgressIndicator();

			progress.setPrefWidth(wndwWidth);

			VBox updatePane = new VBox();
			updatePane.setPadding(new Insets(INT_TEN));
			updatePane.setSpacing(DOUBLE_FIVEZERO);
			updatePane.getChildren().addAll(updateLabel, progress);

			taskUpdateStage.setTitle("Processing Asset: "
					+ assetSnr.getAssetSerialNumber());
			taskUpdateStage.setScene(new Scene(updatePane));

			taskUpdateStage.show();

			final Task<String> longTask = new Task<String>() {
				private String processUsb = null;

				@Override
				protected String call() throws Exception {
					processUsb = usbFileProcessing(assetSnr.getAssetSerialNumber());
					jwslogger.printLogger(ManageUsbDialog.class.getSimpleName(),
							"runTask: processUsb: ", processUsb);
					return processUsb;
				}
			};
			longTask.setOnFailed(new EventHandler<WorkerStateEvent>() {
				@Override
				public void handle(WorkerStateEvent t) {
					taskUpdateStage.hide();
					fxErrorDialog.createErrorDialog(stage, PROCESS_USB_FAILED_TITLE,
							PROCESS_USB_FAILED_MSG, userName, true);
					longTask.cancel();
				}
			});

			longTask.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
				@Override
				public void handle(WorkerStateEvent t) {
					taskUpdateStage.hide();
					jwslogger.printLogger(ManageUsbDialog.class.getSimpleName(),
							"setOnSucceeded:longTask.getValue(): ", longTask.getValue());

					invokeLogFileDetails(longTask, stage, tableRow, tblView, assetSnr);
					longTask.cancel();

				}
			});
			progress.progressProperty().bind(longTask.progressProperty());
			updateLabel.textProperty().bind(longTask.messageProperty());
			taskUpdateStage.show();
			new Thread(longTask).start();
			return longTask.getValue();
		}

	}

	/**
	 * Invoke log file details.
	 *
	 * @param longTask the long task
	 * @param stage the stage
	 * @param tableRow the table row
	 * @param tblView the tbl view
	 * @param assetSnr the asset snr
	 */
	void invokeLogFileDetails(Task<String> longTask, Stage stage, TableRow tableRow,
			TableView tblView, UsbAssetVO assetSnr) {
		if (longTask.getValue() != null) {
			FileProcessResponseVo fvpo = fileProcessResponseJson(longTask.getValue());
			if (fvpo == null) {
				fxErrorDialog.createErrorDialog(stage, PROCESS_USB_FAILED_TITLE,
						PROCESS_USB_FAILED_MSG, userName, true);
			}
			if (fvpo != null && fvpo.getErrorMessage() != null) {
				fxErrorDialog.createErrorDialog(stage, USB_RECONCILIATION_FAILED_TITLE,
						fvpo.getErrorMessage(), userName, true);
			}
			else {
				UsbAssetVO selectedRecord = assetSnr;
				genericVo.setPostupdateList(postupdateList);
				genericVo.setPreupdateList(preupdateList);
				genericVo.setPreupdateNotReconcileList(preupdateNotReconcileList);
				genericVo.setSelectedRecord(selectedRecord);
				genericVo.setDatav(data);
				genericVo.setRow(tableRow);
				genericVo.setTv(tblView);
				genericVo.setUsbErrorVo(errorVo);
				genericVo.setUsbassetTableview(usbtable);
				genericVo.setJwtToken(jwttoken);
				genericVo.setUserHeaderVO(userHeaderVO);
				genericVo.setUsername(userName);
				genericVo.setOtherWwid(otherWwid);
				genericVo.setOtherUserName(otherUserName);
				new LogFileDetailsDialog(fvpo, stage, genericVo, pageNumberOfTable,
						rowsPerPage);
			}
		}
		else {
			fxErrorDialog.createErrorDialog(stage, PROCESS_USB_FAILED_TITLE,
					PROCESS_USB_FAILED_MSG, userName, true);
		}
	}

	// Inner class which creates Remove button not reconciled with system inside
	/**
	 * The Class RemoveButtonCell.
	 */
	// the tableview
	private class RemoveButtonCell extends TableCell<UsbAssetVO, Boolean> {

		/** The remove button. */
		private final Button removeButton = new Button(REMOVE);

		/**
		 * Instantiates a new removes the button cell.
		 *
		 * @param tblView the tbl view
		 * @param stage the stage
		 * @param isPreupdateFile the is preupdate file
		 */
		RemoveButtonCell(final TableView tblView, final Stage stage,
				boolean isPreupdateFile) {
			removeButton.setOnAction(new EventHandler<ActionEvent>() {
				public void handle(ActionEvent t) {
					removePreupdateNotReconciled(tblView, stage, isPreupdateFile);
				}
			});
		}

		/**
		 * places an remove button for pre update file in the row only if the row is not
		 * empty.
		 *
		 * @param item the item
		 * @param empty the empty
		 */
		@Override
		protected void updateItem(Boolean item, boolean isEmpty) {
			super.updateItem(item, isEmpty);
			if (!isEmpty) {
				setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
				setGraphic(removeButton);
			}
			else {
				setContentDisplay(null);
				setText(null);
				setGraphic(null);
			}
		}

		/**
		 * Removes the preupdate not reconciled.
		 *
		 * @param tblView the tbl view
		 * @param stage the stage
		 * @param isPreupdateFile the is preupdate file
		 */
		final void removePreupdateNotReconciled(final TableView tblView,
				final Stage stage, boolean isPreupdateFile) {

			String message = null;
			int selectdIndex = getTableRow().getIndex();
			UsbAssetVO selectedRecord = (UsbAssetVO) tblView.getItems().get(selectdIndex);
			try {
				DeleteFileDetails dfd = new DeleteFileDetails();
				String deleteJson = dfd.deleteUSBFile(
						selectedRecord.getAssetSerialNumber(), false, false);
				ObjectMapper obj = new ObjectMapper();
				DeleteFileRespVo deletefileresVo = obj.readValue(deleteJson,
						DeleteFileRespVo.class);

				if (deletefileresVo != null && deletefileresVo.isDeleteStatus()) {
					message = ASSET_HASH
							+ selectedRecord.getAssetSerialNumber()
							+ " pre-update file has been successfully removed from USB stick.";
					showPostUpdateDialog(message, REMOVE_PRE_UPDATE_HEADER);
					data.remove(selectedRecord);
					preupdateNotReconcileList.remove(selectedRecord);

				}
				else {
					message = ASSET_HASH + selectedRecord.getAssetSerialNumber()
							+ " pre-update file removal from USB stick failed.";
					showPostUpdateDialog(message, REMOVE_PRE_UPDATE_HEADER);
				}
				if (preupdateNotReconcileList.isEmpty()) {
					usbtable.getColumns().remove(INT_SIX); // removed process post
															// update files
					populateDataItems(postupdateList, POST_UPDATE_FILES, stage, 0,
							genericVo);
				}
				else {
					populateDataItems(preupdateNotReconcileList,
							PRE_UPDATE_NOT_RECONCILE_WITH_SYS, stage, pageNumberOfTable,
							genericVo);
				}
			}
			catch (Exception e) {
				jwslogger.printLogger(ManageUsbDialog.class.getSimpleName(),
						"PremovePreupdateNotReconciled",
						"Exception while removing not reconciled pre update file from USB"
								+ e);
				message = ASSET_HASH + selectedRecord.getAssetSerialNumber()
						+ " pre-update file removal from USB stick failed.";
				showPostUpdateDialog(message, REMOVE_PRE_UPDATE_HEADER);
			}
		}
	}

	// Inner class which creates remove button fro preupdate fiel reconciled
	/**
	 * The Class RemoveButtonPreupdateCell.
	 */
	// with system inside the tableview
	private class RemoveButtonPreupdateCell extends TableCell<UsbAssetVO, Boolean> {

		/** The remove button. */
		private final Button removeButton = new Button(REMOVE);

		/**
		 * Instantiates a new removes the button preupdate cell.
		 *
		 * @param tblView the tbl view
		 * @param stage the stage
		 * @param isPreupdateFile the is preupdate file
		 * @param genericVO the generic VO
		 */
		RemoveButtonPreupdateCell(final TableView tblView, final Stage stage,
				boolean isPreupdateFile, final ProcessUSBGenericVO genericVO) {
			removeButton.setOnAction(new EventHandler<ActionEvent>() {
				public void handle(ActionEvent t) {
					int selectdIndex = getTableRow().getIndex();
					UsbAssetVO selectedRecord = (UsbAssetVO) tblView.getItems().get(
							selectdIndex);
					removeItem(selectedRecord.getAssetSerialNumber(), selectedRecord,
							stage, genericVO);
				}
			});
		}

		/**
		 * places an remove button reconciled pre update in the row only if the row is not
		 * empty.
		 *
		 * @param item the item
		 * @param empty the empty
		 */
		@Override
		protected void updateItem(Boolean item, boolean empty) {
			super.updateItem(item, empty);
			if (!empty) {
				setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
				setGraphic(removeButton);
			}
			else {
				setContentDisplay(null);
				setGraphic(null);
				setText(null);
			}
		}
	}

	/**
	 * Removes the item.
	 *
	 * @param assetId the asset id
	 * @param selectedRecord the selected record
	 * @param stage the stage
	 * @param genericVO the generic VO
	 */
	public void removeItem(String assetId, UsbAssetVO selectedRecord, Stage stage,
			ProcessUSBGenericVO genericVO) {
		reasonForRemoveDialog(selectedRecord, assetId, stage, genericVO);
	}

	/**
	 * Reason for remove dialog.
	 *
	 * @param selectedRecord the selected record
	 * @param assetNr the asset nr
	 * @param stage the stage
	 * @param genericVO the generic VO
	 */
	private void reasonForRemoveDialog(UsbAssetVO selectedRecord, String assetNr,
			Stage stage, ProcessUSBGenericVO genericVO) {
		ComboBox<ReasonVO> reason;
		List<ReasonVO> resonFor = null;
		ObjectMapper objectmapper = new ObjectMapper();
		try {
			String json = getReasonsForRemoval(genericVO.getJwtToken());
			resonFor = objectmapper.readValue(json, new TypeReference<List<ReasonVO>>() {
			});
		}
		catch (Exception e) {
			jwslogger.printLogger(ManageUsbDialog.class.getSimpleName(),
					"reasonForRemoveDialog", "Exception while reasonForRemoveDialog" + e);

		}
		reason = new ComboBox<>();
		reason.getItems().addAll(resonFor);
		reason.setPromptText("Select Reason");
		reason.setPrefWidth(INT_FIVEHUNDRED);
		reason.valueProperty().addListener(new ChangeListener<ReasonVO>() {

			@Override
			public void changed(ObservableValue<? extends ReasonVO> observable,
					ReasonVO oldValue, ReasonVO newValue) {
				removeButtonHandler(reason, assetNr, selectedRecord, stage, genericVO);
			}
		});
		cancelButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent t) {
				reasonRemoveDialog.close();
			}
		});
		if (reasonFailureStatus) {
			removeButton.setDisable(false);
		}
		else {
			removeButton.setDisable(true);
		}
		reconciledRemovalDialog(genericVO.getUsername(), reason);
	}

	/**
	 * Reconciled removal dialog.
	 *
	 * @param uName the u name
	 * @param reason the reason
	 */
	void reconciledRemovalDialog(String uName, ComboBox<ReasonVO> reason) {
		VBox dialogVbox = new VBox();
		reasonRemoveDialog.setTitle(REMOVE_PRE_UPDATE_HEADER);
		Label label1 = new Label(
				"Are you sure you want to remove the Pre-Update Files from the USB Stick?");
		Label label2 = new Label("Reason For Remove *");
		Label label3 = new Label("Dear " + uName + ",");
		HBox hbox1 = new HBox(INT_FIVE);
		GridPane gridpane = new GridPane();
		gridpane.setAlignment(Pos.TOP_CENTER);
		gridpane.setHgap(INT_TEN);
		gridpane.setVgap(INT_TEN);
		gridpane.add(label3, 1, 1);
		gridpane.add(label1, 1, INT_TWO);
		gridpane.add(label2, 1, INT_THREE);
		gridpane.add(reason, 1, INT_FOUR);
		gridpane.add(new Label(), 1, INT_FIVE);
		hbox1.getChildren().addAll(removeButton, cancelButton);
		hbox1.setAlignment(Pos.CENTER);
		dialogVbox.getChildren().addAll(gridpane, hbox1);
		Scene dialogScene = new Scene(dialogVbox, INT_FOURHUNDRED, INT_EIGHTHUNDRED);
		dialogScene.getStylesheets().add(css);
		reasonRemoveDialog.setScene(dialogScene);
		reasonRemoveDialog.setHeight(INT_FOURHUNDRED);
		reasonRemoveDialog.setWidth(INT_EIGHTHUNDRED);
		reasonRemoveDialog.show();
	}

	/**
	 * Removes the button handler.
	 *
	 * @param reason the reason
	 * @param assetNr the asset nr
	 * @param selectedRecord the selected record
	 * @param stage the stage
	 * @param genericVO the generic VO
	 */
	void removeButtonHandler(ComboBox<ReasonVO> reason, String assetNr,
			UsbAssetVO selectedRecord, Stage stage, ProcessUSBGenericVO genericVO) {
		removeButton.setDisable(false);
		removeButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent t) {
				invokeDeleteFile(reason, assetNr);
				repopulateDataGrid(selectedRecord, stage, genericVO);
			}
		});
	}

	/**
	 * Invoke delete file.
	 *
	 * @param reason the reason
	 * @param assetNr the asset nr
	 */
	private void invokeDeleteFile(ComboBox<ReasonVO> reason, String assetNr) {
		try {
			String response = postReasonForRemoval(reason.getValue(), assetNr, jwttoken);
			String[] postUpdateRes = response.split("-");
			int resposnecode = 0;
			if (postUpdateRes[0] != null) {
				resposnecode = Integer.parseInt(postUpdateRes[0]);
			}
			String message;
			jwslogger.printLogger(ManageUsbDialog.class.getSimpleName(),
					"reasonForRemoveDialog:resposnecode", String.valueOf(resposnecode));
			if (resposnecode == INT_TWOHUNDRED) {
				DeleteFileDetails dfd = new DeleteFileDetails();
				dfd.deleteUSBFile(assetNr, false, false);

				message = ASSET_HASH
						+ assetNr
						+ " pre-update file has been successfully removed from USB stick.";
				showPostUpdateDialog(message, REMOVE_PRE_UPDATE_HEADER);
			}
			else {
				message = ASSET_HASH + assetNr
						+ " pre-update file removal has been failed.";
				showPostUpdateDialog(message, REMOVE_PRE_UPDATE_HEADER);
			}
		}
		catch (Exception e) {
			jwslogger.printLogger(ManageUsbDialog.class.getSimpleName(),
					"invokeDeleteFile", "Exception while delete file:" + e);
		}
	}

	/**
	 * Repopulate data grid.
	 *
	 * @param selectedRecord the selected record
	 * @param stage the stage
	 * @param genericVO the generic VO
	 */
	private void repopulateDataGrid(UsbAssetVO selectedRecord, Stage stage,
			ProcessUSBGenericVO genericVO) {
		data.remove(selectedRecord);
		preupdateList = data;
		if (preupdateList.isEmpty()) {
			reasonRemoveDialog.close();
			stage.close();
		}
		else {
			int pageNumber;
			if ((preupdateList.size() % rowsPerPage) == 0) {
				pageNumber = pageNumberOfTable - 1;
			}
			else {
				pageNumber = pageNumberOfTable;
			}
			jwslogger.printLogger(ManageUsbDialog.class.getSimpleName(),
					"reasonForRemoveDialog:Size of Pre upadte file = ",
					String.valueOf(preupdateList.size()));
			jwslogger.printLogger(ManageUsbDialog.class.getSimpleName(),
					"reasonForRemoveDialog:Size of DATA =", String.valueOf(data.size()));

			populateDataItems(preupdateList, PRE_UPDATE_FILES, stage, pageNumber,
					genericVO);
		}
		reasonRemoveDialog.close();
	}

	/**
	 * Post reason for removal.
	 *
	 * @param reason the reason
	 * @param assetId the asset id
	 * @param jToken the j token
	 * @return the string
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public String postReasonForRemoval(ReasonVO reason, String assetId, String jToken)
			throws IOException {
		properties = new Properties();
		InputStream input = getClass().getClassLoader().getResourceAsStream(
				"applet.properties");
		properties.load(input);
		String urlString = properties.getProperty(POST_REASON_FOR_REMOVE);

		try {
			URL url = new URL(urlString);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setUseCaches(false);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/json");
			conn.setRequestProperty("Authorization", "Bearer " + jToken);

			ReasonForRemovalVo pufo = new ReasonForRemovalVo();
			pufo.setAssetSerialNmbrs(Arrays.asList(assetId));
			pufo.setReason(reason);
			DataOutputStream out = new DataOutputStream(conn.getOutputStream());

			ObjectMapper objectmapper = new ObjectMapper();
			String pufojson = objectmapper.writeValueAsString(pufo);
			jwslogger.printLogger(ManageUsbDialog.class.getSimpleName(),
					"postReasonForRemoval:Final Json For Reason-", pufojson);
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
			jwslogger.printLogger(ManageUsbDialog.class.getSimpleName(),
					"conn.getResponseCode() - conn.getResponseMessage()",
					conn.getResponseCode() + "-" + conn.getResponseMessage());
			return conn.getResponseCode() + "-" + conn.getResponseMessage();
		}
		catch (IOException e) {
			throw e;
		}

	}

	/**
	 * Gets the reasons for removal.
	 *
	 * @param jToken the j token
	 * @return the reasons for removal
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public String getReasonsForRemoval(String jToken) throws IOException {

		properties = new Properties();
		InputStream input = getClass().getClassLoader().getResourceAsStream(
				"applet.properties");
		properties.load(input);
		String urlString = properties.getProperty(REASON_FOR_REMOVE);
		try {
			URL url = new URL(urlString);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Authorization", "Bearer " + jToken);
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
			throw e;
		}
	}

	/**
	 * Show post update dialog.
	 *
	 * @param message the message
	 * @param title the title
	 */
	void showPostUpdateDialog(String message, String title) {
		VBox dialogVbox = new VBox(INT_TWOHUNDRED);
		Label postUpdateLbl;
		final Button closeButton = new Button("Close");
		postUpdateLbl = new Label(message);
		GridPane gridpane = new GridPane();
		gridpane.setAlignment(Pos.CENTER);
		gridpane.setHgap(INT_TEN);
		gridpane.setVgap(INT_TEN);
		gridpane.setPadding(new Insets(INSETS_TOP_TWENTYFIVE, INSETS_RIGHT_TWENTYFIVE,
				INSETS_BOTTOM_TWENTYFIVE, INSETS_LEFT_TWENTYFIVE));
		gridpane.add(postUpdateLbl, 0, 0);
		gridpane.add(closeButton, 1, 1);
		dialogVbox.getChildren().add(gridpane);
		Scene dialogScene = new Scene(dialogVbox, INT_THREEHUNDRED, INT_EIGHTHUNDRED);
		dialogScene.getStylesheets().add(css);
		preupdateFileDialog.setTitle(title);
		preupdateFileDialog.setScene(dialogScene);
		preupdateFileDialog.setHeight(INT_TWOHUNDRED);
		preupdateFileDialog.setWidth(INT_EIGHTHUNDRED);
		preupdateFileDialog.show();
		closeButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent t) {
				preupdateFileDialog.hide();
			}
		});
	}

	/**
	 * Method to validate reconciled USB content and show the process USB grid.
	 *
	 * @param json the json
	 * @param stage the stage
	 */

	private void processUSBContentValidation(String json, final Stage stage) {
		ProcessUsbVo pvo = null;
		try {
			if (json != null && !json.isEmpty()) {
				ObjectMapper mapper = new ObjectMapper();
				pvo = mapper.readValue(json, ProcessUsbVo.class);
				if (pvo == null) {
					fxErrorDialog.createErrorDialog(stage, PROCESS_USB_FAILED_TITLE,
							PROCESS_USB_FAILED_MSG, userName, false);
				}
				else {
					populateDataList(pvo);
					validateDataList(stage, pvo);
				}
			}
			else {
				fxErrorDialog.createErrorDialog(stage, PROCESS_USB_MSG_TITLE,
						MISSING_POST_UPDATE_FILE, userName, false);
			}
		}
		catch (Exception ioe) {
			jwslogger.printLogger(ManageUsbDialog.class.getSimpleName(),
					"processUSBContentValidation",
					"Exception while processUSBContentValidation" + ioe);
			fxErrorDialog.createErrorDialog(stage, PROCESS_USB_FAILED_TITLE,
					PROCESS_USB_FAILED_MSG, userName, false);
		}
	}

	/**
	 * Populate data list.
	 *
	 * @param pvo the pvo
	 * @param stage the stage
	 */
	void populateDataList(ProcessUsbVo pvo) {

		if (pvo != null
				&& pvo.getPreUpdateFilesNotReconcileWithSystem().getData() != null) {
			preupdateNotReconcileList = seperateJsonData(pvo
					.getPreUpdateFilesNotReconcileWithSystem().getData());
			genericVo.setPreupdateNotReconcileList(preupdateNotReconcileList);
			jwslogger.printLogger(ManageUsbDialog.class.getSimpleName(),
					"Pre Update Files Not with System Object:", String.valueOf(pvo
							.getPreUpdateFilesNotReconcileWithSystem().getData()));
		}
		if (pvo != null && pvo.getPostUpdateFiles().getData() != null) {
			postupdateList = seperateJsonData(pvo.getPostUpdateFiles().getData());
			genericVo.setPostupdateList(postupdateList);
			jwslogger.printLogger(ManageUsbDialog.class.getSimpleName(),
					"Post Update Files Object:",
					String.valueOf(pvo.getPostUpdateFiles().getData()));
		}
		if (pvo != null && pvo.getPreUpdateFilesReconcileWithSystem().getData() != null) {
			preupdateList = seperateJsonData(pvo.getPreUpdateFilesReconcileWithSystem()
					.getData());
			genericVo.setPreupdateList(preupdateList);
			jwslogger.printLogger(ManageUsbDialog.class.getSimpleName(),
					"PreUpdate Files Object after Reconcile:",
					String.valueOf(pvo.getPreUpdateFilesReconcileWithSystem().getData()));
		}

	}

	/**
	 * Validate data list.
	 *
	 * @param stage the stage
	 * @param pvo the pvo
	 */
	void validateDataList(Stage stage, ProcessUsbVo pvo) {
		errorVo = pvo.getUsbErrorVo();
		if (errorVo != null && errorVo.getValue().equals(UsbErrorEnum.CODE_421.getType())) {
			fxErrorDialog.createErrorDialog(stage, "USB Stick not detected",
					USB_NOT_DETECTED, userName, false);
		}
		else if (postupdateList == null || postupdateList.isEmpty()) {
			fxErrorDialog.createErrorDialog(stage, PROCESS_USB_MSG_TITLE,
					MISSING_POST_UPDATE_FILE, userName, false);
		}
		else {
			setDataListToContentVO(stage, pvo);
		}
	}

	/**
	 * Sets the data list to content VO.
	 *
	 * @param stage the stage
	 * @param pvo the pvo
	 */
	void setDataListToContentVO(Stage stage, ProcessUsbVo pvo) {
		genericVo.setPostupdateList(postupdateList);
		genericVo.setPreupdateList(preupdateList);
		genericVo.setPreupdateNotReconcileList(preupdateNotReconcileList);
		genericVo.setUsbErrorVo(errorVo);
		pcvo.setPreupdateNotreconcilefileList(preupdateNotReconcileList);
		if (null != preupdateNotReconcileList && !preupdateNotReconcileList.isEmpty()) {
			otherUserName = pvo.getName();
			genericVo.setOtherUserName(otherUserName);
			populateDataItems(preupdateNotReconcileList,
					PRE_UPDATE_NOT_RECONCILE_WITH_SYS, stage, 0, genericVo);
		}
		else if (null != postupdateList && !postupdateList.isEmpty()) {
			otherUserName = pvo.getName();
			genericVo.setOtherUserName(otherUserName);
			populateDataItems(postupdateList, POST_UPDATE_FILES, stage, 0, genericVo);
		}
		else if (null != preupdateList && !preupdateList.isEmpty()) {
			otherUserName = pvo.getName();
			genericVo.setOtherUserName(otherUserName);
			populateDataItems(preupdateList, PRE_UPDATE_FILES, stage, 0, genericVo);
		}
		else {
			fxErrorDialog.createErrorDialog(stage, PROCESS_USB_MSG_TITLE,
					MISSING_POST_UPDATE_FILE, userName, false);
		}
	}
}
