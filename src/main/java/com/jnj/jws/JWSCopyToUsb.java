/*
 * 
 */
package com.jnj.jws;

import static com.jnj.jws.USBConstant.ASSET_RECONCILIATE_URL;
import static com.jnj.jws.USBConstant.BUFFER_SIZE;
import static com.jnj.jws.USBConstant.BYTE_SIZE;
import static com.jnj.jws.USBConstant.COMPARE_ASSET_URL;
import static com.jnj.jws.USBConstant.DIALOG_VBOX_HEIGHT_FOURHUNDRED;
import static com.jnj.jws.USBConstant.DIALOG_VBOX_WIDTH_FOURHUNDRED;
import static com.jnj.jws.USBConstant.DOWNLOAD_URL;
import static com.jnj.jws.USBConstant.FAILURE_MESSAGE;
import static com.jnj.jws.USBConstant.FAILURE_TITLE;
import static com.jnj.jws.USBConstant.LOCK_ASSET_URL;
import static com.jnj.jws.USBConstant.MD5_ALGORITHM;
import static com.jnj.jws.USBConstant.META_FILE_NAME;
import static com.jnj.jws.USBConstant.META_FILE_USER_VALIDATE_MSG;
import static com.jnj.jws.USBConstant.META_FILE_VERSION_VALIDATE_NEW_MSG;
import static com.jnj.jws.USBConstant.META_FILE_VERSION_VALIDATE_TITLE;
import static com.jnj.jws.USBConstant.REQARGS_LENGTH_TWO;
import static com.jnj.jws.USBConstant.SCENE_HEIGHT_SEVENHUNDRED;
import static com.jnj.jws.USBConstant.SCENE_WIDTH_THOUSANDTWOHUNDRED;
import static com.jnj.jws.USBConstant.SECOND_ROW_INDEX;
import static com.jnj.jws.USBConstant.SPLIT_DOT_REGEX;
import static com.jnj.jws.USBConstant.STAGE_HEIGHT_FOURHUNDRED;
import static com.jnj.jws.USBConstant.STAGE_WIDTH_FOURHUNDRED;
import static com.jnj.jws.USBConstant.STANLEY_BIN;
import static com.jnj.jws.USBConstant.THIRD_ROW_INDEX;
import static com.jnj.jws.USBConstant.TYPE_BIN;
import static com.jnj.jws.USBConstant.TYPE_XML;
import static com.jnj.jws.USBConstant.USB_KEY_FILE_CHECKSUM_VALIDATE;
import static com.jnj.jws.USBConstant.USB_KEY_FILE_CHECKSUM_VALIDATE_TITLE;
import static com.jnj.jws.USBConstant.USB_LENGTH_THOUSANDTWENTYFOUR;
import static com.jnj.jws.USBConstant.USB_MULTIPLE_DETECTED;
import static com.jnj.jws.USBConstant.USB_MULTIPLE_DETECTED_TITLE;
import static com.jnj.jws.USBConstant.USB_NOT_DETECTED;
import static com.jnj.jws.USBConstant.USB_NOT_DETECTED_TITLE;
import static com.jnj.jws.USBConstant.USB_NO_SPACE;
import static com.jnj.jws.USBConstant.USB_NO_SPACE_TITLE;
import static com.jnj.jws.USBConstant.USB_POST_UPDATE_VALIDATE_MSG;
import static com.jnj.jws.USBConstant.USB_POST_UPDATE_VALIDATE_TITLE;
import static com.jnj.jws.USBConstant.USB_READ_PROTECT_MSG;
import static com.jnj.jws.USBConstant.USB_READ_PROTECT_TITLE;
import static com.jnj.jws.USBConstant.USB_RECONCILIATION_FAILED_TITLE;
import static com.jnj.jws.USBConstant.USB_UNWANTED_FILE_MSG;
import static com.jnj.jws.USBConstant.USB_WRITE_PROTECT_MSG;
import static com.jnj.jws.USBConstant.USB_WRITE_PROTECT_TITLE;
import static com.jnj.jws.USBConstant.VALIDATE_METAINFO_URL;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.stream.Stream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import javax.swing.filechooser.FileSystemView;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jnj.jws.download.event.AssetEventFileProcessor;
import com.jnj.jws.usb.process.DeleteFileDetails;
import com.jnj.jws.usb.process.javafx.CopyToUsbDialog;
import com.jnj.jws.util.EncryptDecryptUtil;
import com.jnj.jws.util.FileChecksumGenerator;
import com.jnj.jws.util.JavaWebStartLogger;
import com.jnj.jws.util.UsbFileUtil;
import com.jnj.jws.vo.AssetVersionCompareReqVO;
import com.jnj.jws.vo.AssetVersionVO;
import com.jnj.jws.vo.CopyToUSBJWSRespVo;
import com.jnj.jws.vo.DeleteFileRespVo;
import com.jnj.jws.vo.MetaInfoValidationRequestVO;
import com.jnj.jws.vo.MetaInfoValidationRespVO;
import com.jnj.jws.vo.PreUpdateDownloadResVo;
import com.jnj.jws.vo.UsbFileVo;
import com.jnj.jws.vo.UserHeaderVO;

import javafx.application.Application;
import javafx.concurrent.Task;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * JWS web start class which handles provides JavaFx Visual for copy to USB screen.
 */

@SuppressWarnings("restriction")
public class JWSCopyToUsb extends Application {

	/** The css. */
	private String css = null;

	/** The jwttoken. */
	private String jwttoken = null;

	/** The req args. */
	private String[] reqArgs = null;

	/** The userheadervo. */
	private UserHeaderVO userheadervo;

	/** The ok button. */
	private Button okButton = new Button("OK");

	/** The cancel button. */
	private Button cancelButton = new Button("Cancel");

	/** The jwslogger. */
	private JavaWebStartLogger jwslogger = new JavaWebStartLogger();

	/** The Constant STYLESHEET_CSS. */
	private static final String STYLESHEET_CSS = "stylesheet.css";

	/** The Constant FN_COPYTOUSBDIALOG. */
	private static final String FN_COPYTOUSBDIALOG = "copytoUSBDialog";

	/** The Constant PROCESS_JSON_VALUE. */
	private static final String PROCESS_JSON_VALUE = "processJsonValue";

	/** The Constant FN_CHECKFORUSB. */
	private static final String FN_CHECKFORUSB = "checkForUSB";

	/** The Constant FN_CHECKFORUSB. */
	private static final String FN_CHECKUSBDRIVE = "checkUSBDrive";

	/** The Constant FN_CREATERESPVOJSON. */
	private static final String FN_CREATERESPVOJSON = "createResponseVoJson";

	/** The Constant FN_SETERRORMSGTORESP. */
	private static final String FN_SETERRORMSGTORESP = "setErrorMsgToResponse";

	/** The Constant FN_PROCESSCOPYTOUSB. */
	private static final String FN_PROCESSCOPYTOUSB = "processCopyToUsb";

	/** The Constant FN_DOFILEDOWNLOAD. */
	private static final String FN_DOFILEDOWNLOAD = "doFileDownload";

	/** The Constant FN_VALIDATEKEYFILECHECKSUM. */
	private static final String FN_VALIDATEKEYFILECHECKSUM = "validateKeyFileChecksum";

	/** The Constant FN_DELETEFILE. */
	private static final String FN_DELETEFILE = "deleteFile";

	/** The Constant FN_DOUNZIP. */
	private static final String FN_DOUNZIP = "doUnZip";

	/** The Constant FOLDER_EXISTS. */
	private static final String FOLDER_EXISTS = "folderExists";

	/** The Constant FN_PROCESSWHENUSBCONTAINSFILES. */
	private static final String FN_PROCESSWHENUSBCONTAINSFILES = "processWhenUsbContainsFiles";

	/** The Constant FN_PROCESSWHENINVALIDSTANLEY. */
	private static final String FN_PROCESSWHENINVALIDSTANLEY = "processWhenInvalidStanleyBinInUSB";

	/** The Constant FN_CREATEMETAINFOVALIDATEREQVO. */
	private static final String FN_CREATEMETAINFOVALIDATEREQVO = "createMetaInfoValidationRequestVO";

	/** The Constant FN_LISTFILENAMESFORFOLDER. */
	private static final String FN_LISTFILENAMESFORFOLDER = "listFilesNamesForFolder";

	/** The Constant FN_INVOKEDOWNLOADFILESERVICE. */
	private static final String FN_INVOKEDOWNLOADFILESERVICE = "invokeDownloadFileService";

	/** The Constant FN_INVOKE_LOCKASSETAFTERCHECKOUT. */
	private static final String FN_INVOKE_LOCKASSETAFTERCHECKOUT = "invokeLockAssetsAfterCheckOut";

	/** The Constant FN_INVOKE_UNLOCKASSETAFTERCHECKOUT. */
	private static final String FN_INVOKE_UNLOCKASSETAFTERCHECKOUT = "invokeUnlockAssetsForCopyToUsb";

	/** The Constant FN_INVOKEASSETRECONCILIATESERVICE. */
	private static final String FN_INVOKEASSETRECONCILIATESERVICE = "invokeAssetReconciliationService";

	/** The Constant FN_INVOKECOMPAREASSETVERSION. */
	private static final String FN_INVOKECOMPAREASSETVERSION = "invokeCompareAssetVersion";

	/** The Constant FN_INVOKEVALIDATEMETAINFOFILE. */
	private static final String FN_INVOKEVALIDATEMETAINFOFILE = "invokevalidateMetaInfoFile";

	/** The Constant FN_CONFIRMATION_DIALOG. */
	private static final String FN_CONFIRMATION_DIALOG = "confirmationDialog";

	/** The Constant FN_CONFIRMATION_DIALOG. */
	private static final String FN_FINDUSBDRIVE = "findUSBDrives";

	/** The Constant MSG_GOT_JSON. */
	private static final String MSG_GOT_JSON = "got json response=";

	/** The Constant MSG_URL. */
	private static final String MSG_URL = "URL is -";

	/** The Constant CONTENT_TYPE. */
	private static final String CONTENT_TYPE = "Content-Type";

	/** The Constant APPLCTN_JSON. */
	private static final String APPLCTN_JSON = "application/json";

	/** The Constant AUTHORIZN. */
	private static final String AUTHORIZN = "Authorization";

	/** The Constant BEARER. */
	private static final String BEARER = "Bearer ";

	/** The Constant SUCCESS. */
	private static final String SUCCESS = "SUCCESS";

	/** The Constant ERROR. */
	private static final String ERROR = "error";

	/** The isFolderExists constant. */
	private static final String PARENT_FILE_DIR = "parentFileDir";

	/*
	 * (non-Javadoc)
	 * 
	 * @see javafx.application.Application#start(javafx.stage.Stage)
	 */
	@Override
	public void start(final Stage stage) {
		css = this.getClass().getClassLoader().getResource(STYLESHEET_CSS)
				.toExternalForm();
		if (getParameters() != null) {
			Object[] objectArray = getParameters().getUnnamed().toArray();
			reqArgs = Arrays.copyOf(objectArray, objectArray.length, String[].class);
		}
		jwslogger.printLogger(JWSCopyToUsb.class.getSimpleName(), "start", "reqArgs[1]="
				+ reqArgs[1]);
		List<String> assetVersionCheckLst = new ArrayList<>();
		String asetSerNoArrayJson = reqArgs[1];
		Gson gson = new Gson();
		String[] asserNoArray = null;
		if (asetSerNoArrayJson != null) {
			asserNoArray = gson.fromJson(asetSerNoArrayJson, String[].class);
		}
		if (asserNoArray != null) {
			assetVersionCheckLst.addAll(Arrays.asList(asserNoArray));
		}
		copytoUSBDialog(stage, assetVersionCheckLst);
	}

	/**
	 * Copyto USB dialog.
	 *
	 * @param stage the stage
	 * @param asssetSrNo the assset sr no
	 */
	public void copytoUSBDialog(final Stage stage, final List<String> asssetSrNo) {
		final double wndwWidth = 300.0d;
		Label updateLabel = new Label("Running tasks...");
		updateLabel.setPrefWidth(wndwWidth);
		ProgressIndicator progress = new ProgressIndicator();

		progress.setPrefWidth(wndwWidth);
		VBox vbox = new VBox();
		Scene scene = new Scene(vbox, SCENE_WIDTH_THOUSANDTWOHUNDRED,
				SCENE_HEIGHT_SEVENHUNDRED);
		scene.getStylesheets().add(css);
		vbox.getChildren().addAll(progress);
		vbox.setAlignment(Pos.CENTER);
		stage.setTitle("Copying to USB");
		stage.setScene(scene);
		stage.setHeight(STAGE_HEIGHT_FOURHUNDRED);
		stage.setWidth(STAGE_WIDTH_FOURHUNDRED);
		stage.show();

		if (reqArgs[0] != null) {
			EncryptDecryptUtil edutil = new EncryptDecryptUtil();
			String useheaderjson = edutil.parseJWT(reqArgs[0]);
			ObjectMapper mapper = new ObjectMapper();
			try {
				if (useheaderjson != null) {
					userheadervo = mapper.readValue(useheaderjson, UserHeaderVO.class);
					jwslogger.printLogger(JWSCopyToUsb.class.getSimpleName(),
							FN_COPYTOUSBDIALOG,
							"JWSCopyToUsb:username:" + userheadervo.getFirstName());
				}
				else {
					jwslogger.printLogger(JWSCopyToUsb.class.getSimpleName(),
							FN_COPYTOUSBDIALOG,
							"JWSCopyToUsb:Unable to derive userheader ");
				}

			}
			catch (JsonParseException | JsonMappingException e) {
				jwslogger.printLogger(JWSCopyToUsb.class.getSimpleName(),
						FN_COPYTOUSBDIALOG, "JsonParseException | JsonMappingException:"
								+ e);

			}
			catch (IOException e) {
				jwslogger.printLogger(JWSCopyToUsb.class.getSimpleName(),
						FN_COPYTOUSBDIALOG, "IOException :" + e);
			}
		}

		// setting the jwttoken
		if (reqArgs.length > REQARGS_LENGTH_TWO) {
			jwttoken = reqArgs[REQARGS_LENGTH_TWO];
			jwslogger.printLogger(JWSCopyToUsb.class.getSimpleName(), FN_COPYTOUSBDIALOG,
					"JWSCopyToUsb:JWT TOKEN:[ " + jwttoken + " ]");
		}

		final Task<String> longTask = new Task<String>() {
			private String processUsb = null;

			@Override
			protected String call() throws Exception {
				if (asssetSrNo != null) {
					// code for validating USB and Copy to USB
					processUsb = checkForUSB(asssetSrNo);
				}
				return processUsb;
			}
		};
		longTask.setOnFailed(t -> {
			CopyToUsbDialog cusbDialog = new CopyToUsbDialog();
			cusbDialog.createErrorDialog(stage, FAILURE_TITLE, FAILURE_MESSAGE,
					userheadervo);
			stage.hide();
			longTask.cancel();
		});

		longTask.setOnSucceeded(t -> {
			stage.hide();
			jwslogger.printLogger(JWSCopyToUsb.class.getSimpleName(), FN_COPYTOUSBDIALOG,
					"JWSCopyToUsb:Process USB:Task completed:[" + longTask.getValue()
							+ "]");
			processJsonValue(stage, longTask.getValue());
			jwslogger.printLogger(JWSCopyToUsb.class.getSimpleName(), FN_COPYTOUSBDIALOG,
					"JWSCopyToUsb:Process USB:Task completed:[" + longTask.getValue()
							+ "]");

		});
		progress.progressProperty().bind(longTask.progressProperty());
		updateLabel.textProperty().bind(longTask.messageProperty());
		stage.show();
		new Thread(longTask).start();
	}

	/**
	 * Process json value.
	 *
	 * @param stage the stage
	 * @param json the json
	 */
	public void processJsonValue(final Stage stage, String json) {
		CopyToUsbDialog cusbDialog = new CopyToUsbDialog();
		if (!json.isEmpty()) {

			CopyToUSBJWSRespVo ctousb = null;
			ObjectMapper mapper = new ObjectMapper();
			try {
				jwslogger.printLogger(JWSCopyToUsb.class.getSimpleName(),
						PROCESS_JSON_VALUE, "Process USB Json value:[" + json + "]");

				ctousb = mapper.readValue(json, CopyToUSBJWSRespVo.class);

				if (ctousb.isError()) {
					handleErrorDialog(stage, ctousb, cusbDialog);
				}
				else {
					cusbDialog.createSuccessDialog(stage, ctousb, userheadervo);
				}

			}
			catch (JsonParseException e1) {
				cusbDialog.createErrorDialog(stage, FAILURE_TITLE, FAILURE_MESSAGE,
						userheadervo);
				jwslogger.printLogger(JWSCopyToUsb.class.getSimpleName(),
						PROCESS_JSON_VALUE,
						"JWSCopyToUsb:Process USB Json value: json parse exception" + e1);
			}
			catch (JsonMappingException e1) {
				cusbDialog.createErrorDialog(stage, FAILURE_TITLE, FAILURE_MESSAGE,
						userheadervo);
				jwslogger.printLogger(JWSCopyToUsb.class.getSimpleName(),
						PROCESS_JSON_VALUE,
						"JWSCopyToUsb:Process USB Json value: JsonMappingException" + e1);
			}
			catch (IOException e1) {
				cusbDialog.createErrorDialog(stage, FAILURE_TITLE, FAILURE_MESSAGE,
						userheadervo);
				jwslogger.printLogger(JWSCopyToUsb.class.getSimpleName(),
						PROCESS_JSON_VALUE,
						"JWSCopyToUsb:Process USB Json value:IOException " + e1);
			}
		}

	}

	/**
	 * Handle error dialog.
	 *
	 * @param stage the stage
	 * @param ctousb the ctousb
	 * @param cusbDialog the cusb dialog
	 */
	private void handleErrorDialog(final Stage stage, CopyToUSBJWSRespVo ctousb,
			CopyToUsbDialog cusbDialog) {

		if (ctousb.isToConfirm()) {
			confirmationDialog(stage, ctousb.getTitle(), ctousb.getErrorMessage(),
					userheadervo, ctousb.getUsbSerNumList(), ctousb.getAssetSerNumList());
		}
		else {
			if (ctousb.getTitle() != null) {
				cusbDialog.createErrorDialog(stage, ctousb.getTitle(),
						ctousb.getErrorMessage(), userheadervo);
			}
			else {
				cusbDialog.createErrorDialog(stage, "Error while copying to USB",
						ctousb.getErrorMessage(), userheadervo);
			}
		}

	}

	/**
	 * Check navigation to read USB.
	 *
	 * @param stage the stage
	 * @param ctousb the ctousb
	 * @param cusbDialog the cusb dialog
	 */
	void checkNavigationToReadUSB(final Stage stage, CopyToUSBJWSRespVo ctousb,
			CopyToUsbDialog cusbDialog) {
		if (ctousb.isPostUpdateFile()) {
			cusbDialog.createErrorDialog(stage, ctousb.getTitle(),
					ctousb.getErrorMessage(), userheadervo);
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

	/**
	 * Check for USB.
	 *
	 * @param assetVersionCheckLst the asset version check lst
	 * @return the string
	 */
	public String checkForUSB(List<String> assetVersionCheckLst) {
		jwslogger.printLogger(JWSCopyToUsb.class.getSimpleName(), FN_CHECKFORUSB,
				"Asset list[" + assetVersionCheckLst + "]");
		String json = "";

		CopyToUSBJWSRespVo jwsRespVo = new CopyToUSBJWSRespVo();

		try {

			File[] usbDrives = findUSBDrives();
			List<File> validUSBDrives = new ArrayList<>();
			if (usbDrives != null && usbDrives.length > 1) {
				// do not consider net setter like devices as USB.
				validUSBDrives = checkForMultipleWritableUSB(usbDrives);
			}
			json = checkUSBDrive(jwsRespVo, validUSBDrives);
			if (!json.isEmpty()) {
				return json;
			}
			File usbFolder = null;
			jwslogger.printLogger(JWSCopyToUsb.class.getSimpleName(), FN_CHECKFORUSB,
					"validUSBDrives size " + validUSBDrives.size());
			if (!validUSBDrives.isEmpty()) {
				usbFolder = validUSBDrives.get(0);
			}
			if (usbFolder != null) {
				String errorJson = validateUSB(usbFolder, jwsRespVo);
				if (!errorJson.isEmpty()) {
					return errorJson;
				}
				json = validateFiles(usbFolder, jwsRespVo, assetVersionCheckLst);
			}
		}
		catch (Exception e) {
			jwslogger.printLogger(JWSCopyToUsb.class.getSimpleName(), FN_CHECKFORUSB,
					"Exception inside checkForUSB:" + e);
			json = "JWSCopyToUsb:checkForUSB:EXCEPTION inside jwsCopyToUsb";
		}
		return json;
	}

	/**
	 * Check multiple USB.
	 *
	 * @param usbDrives the usb drives
	 * @param jwsRespVo the jws resp vo
	 * @param validUSBDrives
	 * @param usbFolder the usb folder
	 * @return the string
	 */
	public String checkUSBDrive(CopyToUSBJWSRespVo jwsRespVo, List<File> validUSBDrives) {
		jwslogger.printLogger(JWSCopyToUsb.class.getSimpleName(), FN_CHECKUSBDRIVE,
				"inside checkUSBDrive.");

		jwslogger.printLogger(JWSCopyToUsb.class.getSimpleName(), FN_CHECKUSBDRIVE,
				"validUSBDrives size " + validUSBDrives.size());
		if (validUSBDrives.size() > 1) {
			// multiple USB drive found.
			jwslogger.printLogger(JWSCopyToUsb.class.getSimpleName(), FN_CHECKUSBDRIVE,
					"Found Multiple writable USB.");
			return setErrorMsgToResponse(USB_MULTIPLE_DETECTED, jwsRespVo,
					USB_MULTIPLE_DETECTED_TITLE);
		}

		jwslogger.printLogger(JWSCopyToUsb.class.getSimpleName(), FN_CHECKUSBDRIVE,
				" Multiple writable USB NOT FOUND.");

		boolean usbDetected = false;
		if (validUSBDrives.size() == 1) {
			usbDetected = true;
		}

		if (!usbDetected) {
			// send error message usb not detected.
			return setErrorMsgToResponse(USB_NOT_DETECTED, jwsRespVo,
					USB_NOT_DETECTED_TITLE);
		}
		return "";
	}

	/**
	 * @param usbDrives
	 * @return
	 */
	private List<File> checkForMultipleWritableUSB(File[] usbDrives) {
		List<File> validUSBDrives = new ArrayList<>();
		for (File usbDrv : usbDrives) {
			if (usbDrv != null && usbDrv.canWrite()) {
				jwslogger.printLogger(JWSCopyToUsb.class.getSimpleName(),
						"checkForMultipleWritableUSB", usbDrv.getName()
								+ " usbDrv.canWrite()= " + usbDrv.canWrite());
				validUSBDrives.add(usbDrv);
			}
		}
		return validUSBDrives;
	}

	/**
	 * Validate files.
	 *
	 * @param usbFolder the usb folder
	 * @param jwsRespVo the jws resp vo
	 * @param assetVersionCheckLst the asset version check lst
	 * @return the string
	 * @throws NoSuchAlgorithmException the no such algorithm exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	private String validateFiles(File usbFolder, CopyToUSBJWSRespVo jwsRespVo,
			List<String> assetVersionCheckLst) throws NoSuchAlgorithmException,
			IOException {
		UsbFileVo usbFileVo = null;
		if (!jwsRespVo.isError()) {
			// usb not write protected. check for files
			usbFileVo = listFilesNamesForFolder(usbFolder);
			// if usb contains unwanted files.
			setUnwantedFilesWarning(usbFileVo, jwsRespVo);
			if (usbFileVo.isContainsXmlFile()) {
				jwsRespVo.setPostUpdateFile(true);
				jwslogger.printLogger(JWSCopyToUsb.class.getSimpleName(), FN_CHECKFORUSB,
						"Found xml Files");
				// show error message for xml log files.
				return setErrorMsgToResponse(USB_POST_UPDATE_VALIDATE_MSG, jwsRespVo,
						USB_POST_UPDATE_VALIDATE_TITLE);
			}
		}
		String json = "";
		if (usbFileVo != null) {
			json = versionCheckBeforeCopyToUSB(usbFileVo, jwsRespVo, usbFolder,
					assetVersionCheckLst);
		}
		return json;
	}

	/**
	 * Version check before copy to USB.
	 *
	 * @param usbFileVo the usb file vo
	 * @param jwsRespVo the jws resp vo
	 * @param usbFolder the usb folder
	 * @param assetVersionCheckLst the asset version check lst
	 * @return the string
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws NoSuchAlgorithmException the no such algorithm exception
	 */
	private String versionCheckBeforeCopyToUSB(UsbFileVo usbFileVo,
			CopyToUSBJWSRespVo jwsRespVo, File usbFolder,
			List<String> assetVersionCheckLst) throws IOException,
			NoSuchAlgorithmException {
		String json = "";
		// validate asset version are same.
		AssetVersionVO assetVersionVo = invokeCompareAssetVersion(assetVersionCheckLst,
				usbFileVo.getExistngAssetSerNumLst());
		jwslogger.printLogger(JWSCopyToUsb.class.getSimpleName(), FN_CHECKFORUSB,
				"invokeCompareAssetVersion COMPLETE");

		if (assetVersionVo != null) {
			if (assetVersionVo.getStatus() != null
					&& !assetVersionVo.getMessage().isEmpty()
					&& !SUCCESS.equalsIgnoreCase(assetVersionVo.getStatus())) {
				jwslogger.printLogger(
						JWSCopyToUsb.class.getSimpleName(),
						FN_CHECKFORUSB,
						"message from invokeCompareAssetVersion-"
								+ assetVersionVo.getMessage());

				return setErrorMsgToResponse(assetVersionVo.getMessage(), jwsRespVo,
						META_FILE_VERSION_VALIDATE_TITLE);
			}

			json = processCopyToUsb(assetVersionVo, usbFileVo, usbFolder);
		}
		return json;

	}

	/**
	 * Sets the unwanted files warning.
	 *
	 * @param usbFileVo the usb file vo
	 * @param jwsRespVo the jws resp vo
	 */
	private void setUnwantedFilesWarning(UsbFileVo usbFileVo, CopyToUSBJWSRespVo jwsRespVo) {
		if (usbFileVo.getUnwantedFileNames() != null
				&& !usbFileVo.getUnwantedFileNames().isEmpty()) {
			jwslogger.printLogger(JWSCopyToUsb.class.getSimpleName(), FN_CHECKFORUSB,
					"Found unwantedFileNames" + usbFileVo.getUnwantedFileNames().size());
			// show warning message for unwanted files.
			// return setErrorMsgToResponse(USB_UNWANTED_FILE_MSG, jwsRespVo)
			jwsRespVo.setWarning(true);
			jwsRespVo.setWarningMessage(USB_UNWANTED_FILE_MSG);
		}
	}

	/**
	 * Find USB drives.
	 *
	 * @return the file[]
	 */
	private File[] findUSBDrives() {
		File[] usbResult = { null, null, null };
		// boolean usbDetected=false
		FileSystemView fsv = FileSystemView.getFileSystemView();
		File[] f = File.listRoots();
		int j = 0;
		// File usbFolder
		for (int i = 0; i < f.length; i++) {
			String displayName = fsv.getSystemDisplayName(f[i]);
			jwslogger.printLogger(JWSCopyToUsb.class.getSimpleName(), FN_FINDUSBDRIVE,
					"displayName=[" + displayName + "]");
			String fileType = fsv.getSystemTypeDescription(f[i]);
			jwslogger.printLogger(
					JWSCopyToUsb.class.getSimpleName(),
					FN_FINDUSBDRIVE,
					"fileType=" + fileType + " isDrive=" + fsv.isDrive(f[i])
							+ " canRead= " + f[i].canRead() + "canWrite="
							+ f[i].canWrite());

			if (fsv.isDrive(f[i]) && UsbFileUtil.isValidUsbDrive(fileType)) {
				// usb detected
				// usbFolder = f[i]
				usbResult[j] = f[i];
				j++;
			}
		}
		return usbResult;
	}

	/**
	 * Creates the response vo json.
	 *
	 * @param respVo the resp vo
	 * @return the string
	 */
	private String createResponseVoJson(CopyToUSBJWSRespVo respVo) {
		jwslogger.printLogger(JWSCopyToUsb.class.getSimpleName(), FN_CREATERESPVOJSON,
				"inside createResponseVoJson");
		Gson gson = new Gson();
		return gson.toJson(respVo);
	}

	/**
	 * Sets the error msg to response.
	 *
	 * @param errormessage the errormessage
	 * @param jwsRespVo the jws resp vo
	 * @param title the title
	 * @return the string
	 */
	private String setErrorMsgToResponse(String errormessage,
			CopyToUSBJWSRespVo jwsRespVo, String title) {
		jwslogger.printLogger(JWSCopyToUsb.class.getSimpleName(), FN_SETERRORMSGTORESP,
				" with title-" + title);
		jwsRespVo.setError(true);
		jwsRespVo.setErrorMessage(errormessage);
		jwsRespVo.setTitle(title);
		return createResponseVoJson(jwsRespVo);
	}

	/**
	 * Process copy to usb.
	 *
	 * @param assetVersionVo the asset version vo
	 * @param usbFileVo the usb file vo
	 * @param usbFolder the usb folder
	 * @return the string
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws NoSuchAlgorithmException the no such algorithm exception
	 */
	private String processCopyToUsb(AssetVersionVO assetVersionVo, UsbFileVo usbFileVo,
			File usbFolder) throws IOException, NoSuchAlgorithmException {
		jwslogger.printLogger(JWSCopyToUsb.class.getSimpleName(), FN_PROCESSCOPYTOUSB,
				"usbFolder= " + usbFolder);
		String message = SUCCESS;
		CopyToUSBJWSRespVo jwsRespVo = new CopyToUSBJWSRespVo();

		if (checkWhetherUsbContainsAnyValidFiles(usbFileVo)) {
			jwslogger.printLogger(JWSCopyToUsb.class.getSimpleName(),
					FN_PROCESSCOPYTOUSB, "USB contains Valid Files");
			// check for USB reconciliation here
			if (usbFileVo.getUsbFileNames() == null) {
				usbFileVo.setUsbFileNames(new ArrayList<>());
			}
			message = invokeAssetReconciliationService(usbFileVo.getUsbFileNames());

			// if No reconciliation error, then >>> EXISTS FLOW
			if (SUCCESS.equalsIgnoreCase(message)) {
				jwslogger.printLogger(JWSCopyToUsb.class.getSimpleName(),
						FN_PROCESSCOPYTOUSB, "reconciliation SUCCESS.");
				if (usbFileVo.isStanlelExists() && usbFileVo.isMetaFileExists()) {
					jwslogger.printLogger(JWSCopyToUsb.class.getSimpleName(),
							FN_PROCESSCOPYTOUSB, "Stanley & MetaFile Exists.");
					processWhenUsbContainsFiles(assetVersionVo, usbFolder, jwsRespVo,
							usbFileVo);
				}
				else {
					// REMOVE stanley.bin and/or meta-info file.
					processWhenStanleyOrMetaMissing(assetVersionVo, usbFileVo, usbFolder,
							jwsRespVo);
				}
			}
			else {
				jwslogger.printLogger(JWSCopyToUsb.class.getSimpleName(),
						FN_PROCESSCOPYTOUSB, "USB reconciliation FAILED.");
				// show reconciliation error message.
				jwsRespVo.setTitle(USB_RECONCILIATION_FAILED_TITLE);
				jwsRespVo.setError(true);
				jwsRespVo.setErrorMessage(message);
			}

		}
		else {
			// NOT EXISTS flow here.
			jwslogger.printLogger(JWSCopyToUsb.class.getSimpleName(),
					FN_PROCESSCOPYTOUSB, "NO VALID FILES FOUND in USB");
			// check for USB reconciliation here
			message = invokeAssetReconciliationService(new ArrayList<>());
			if (!SUCCESS.equalsIgnoreCase(message)) {
				jwslogger.printLogger(JWSCopyToUsb.class.getSimpleName(),
						FN_PROCESSCOPYTOUSB,
						"USB reconciliation FAILED. NO Valid Files in USB");
				// show reconciliation error message.
				jwsRespVo.setTitle(USB_RECONCILIATION_FAILED_TITLE);
				jwsRespVo.setError(true);
				jwsRespVo.setErrorMessage(message);
			}
			else {
				// invoke pre-update file DOWNLOAD SERVICE here.
				jwslogger.printLogger(JWSCopyToUsb.class.getSimpleName(),
						FN_PROCESSCOPYTOUSB, "USB is Empty.");
				doFileDownload(assetVersionVo, usbFolder, jwsRespVo, false, usbFileVo);
			}
		}

		// convert response to json
		Gson gson = new Gson();
		return gson.toJson(jwsRespVo);
	}

	/**
	 * Process when stanley or meta missing.
	 *
	 * @param assetVersionVo the asset version vo
	 * @param usbFileVo the usb file vo
	 * @param usbFolder the usb folder
	 * @param jwsRespVo the jws resp vo
	 * @throws NoSuchAlgorithmException the no such algorithm exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	private void processWhenStanleyOrMetaMissing(AssetVersionVO assetVersionVo,
			UsbFileVo usbFileVo, File usbFolder, CopyToUSBJWSRespVo jwsRespVo)
			throws NoSuchAlgorithmException, IOException {

		// REMOVE stanley.bin and/or meta-info file.
		jwslogger.printLogger(JWSCopyToUsb.class.getSimpleName(), FN_PROCESSCOPYTOUSB,
				"Either Stanley or MetaFile missing in USB.");
		if (usbFileVo.isStanlelExists()) {
			jwslogger.printLogger(JWSCopyToUsb.class.getSimpleName(),
					FN_PROCESSCOPYTOUSB, "Removing Stanley");
			File stanleyFile = getStanleyBinFile(usbFolder);
			deleteFile(stanleyFile);
		}
		doFileDownload(assetVersionVo, usbFolder, jwsRespVo, false, usbFileVo);

	}

	/**
	 * Check whether usb contains any valid files.
	 *
	 * @param usbFileVo the usb file vo
	 * @return true, if successful
	 */
	private boolean checkWhetherUsbContainsAnyValidFiles(UsbFileVo usbFileVo) {
		boolean contains = false;
		if ((usbFileVo.getUsbFileNames() != null && !usbFileVo.getUsbFileNames()
				.isEmpty())
				|| (usbFileVo.isStanlelExists() || usbFileVo.isMetaFileExists())) {
			contains = true;
		}
		return contains;
	}

	/**
	 * Gets the stanley bin file.
	 *
	 * @param usbFolder the usb folder
	 * @return the stanley bin file
	 */
	private File getStanleyBinFile(File usbFolder) {
		return new File(usbFolder.getAbsolutePath() + "\\" + STANLEY_BIN);
	}

	/**
	 * Do file download.
	 *
	 * @param assetVersionVo the asset version vo
	 * @param usbFolder the usb folder
	 * @param jwsRespVo the jws resp vo
	 * @param isOnlyKeyFile the is only key file
	 * @param usbFileVo the usb file vo
	 * @throws IOException if USB is EMPTY
	 * @throws NoSuchAlgorithmException the no such algorithm exception
	 */
	private void doFileDownload(AssetVersionVO assetVersionVo, File usbFolder,
			CopyToUSBJWSRespVo jwsRespVo, boolean isOnlyKeyFile, UsbFileVo usbFileVo)
			throws IOException, NoSuchAlgorithmException {
		jwslogger.printLogger(JWSCopyToUsb.class.getSimpleName(), FN_DOFILEDOWNLOAD,
				" download started.");
		String zipFileName = usbFolder.getAbsolutePath() + "\\" + "pre-update.zip";

		if (isOnlyKeyFile) {
			assetVersionVo.setOnlyKeyFiles(true);
		}
		PreUpdateDownloadResVo vo = invokeDownloadFileService(assetVersionVo, zipFileName);
		String keyFileChecksum = null;
		if (vo.isError()) {
			jwsRespVo.setTitle(FAILURE_TITLE);
			jwsRespVo.setError(true);
			jwsRespVo.setErrorMessage(vo.getErrorString());
		}
		else {
			keyFileChecksum = vo.getCheckSumStr();
		}

		if (keyFileChecksum != null && !keyFileChecksum.isEmpty()) {

			doUnZip(zipFileName, usbFolder.getAbsolutePath());

			boolean validationSuccess = validateKeyFileChecksum(keyFileChecksum,
					usbFolder.getAbsolutePath());

			if (validationSuccess) {
				jwslogger.printLogger(JWSCopyToUsb.class.getSimpleName(),
						FN_DOFILEDOWNLOAD,
						" Key file validation success after downloading files.");

				deleteFile(new File(zipFileName));

				jwsRespVo.setAvailableVersion(assetVersionVo.getAvailableVersion());

				// all asset numbers in USB
				List<String> assetList = findKeyFileAssets(usbFolder);

				// remove already existing key files from assetlist
				List<String> keyFilesUsbList = new ArrayList<>(assetList);
				jwslogger.printLogger(JWSCopyToUsb.class.getSimpleName(),
						FN_DOFILEDOWNLOAD, " Key file in USb size=" + assetList.size());

				if (usbFileVo.isContainsAssetKeyFile()
						&& usbFileVo.getExistngAssetSerNumLst() != null) {
					jwslogger.printLogger(JWSCopyToUsb.class.getSimpleName(),
							FN_DOFILEDOWNLOAD,
							" usb key file size before start processing:"
									+ usbFileVo.getUsbFileNames().size());

					keyFilesUsbList.removeAll(usbFileVo.getExistngAssetSerNumLst());
				}
				jwslogger.printLogger(JWSCopyToUsb.class.getSimpleName(),
						FN_DOFILEDOWNLOAD,
						" AFTER removing already existing Key files in USb;size="
								+ keyFilesUsbList.size());
				jwsRespVo.setAssetSerNumList(keyFilesUsbList);

				// invoke lock service
				assetVersionVo.setAssetSerialNumberList(keyFilesUsbList);
				invokeLockAssetsAfterCheckOut(assetVersionVo);
			}
			else {
				jwslogger.printLogger(JWSCopyToUsb.class.getSimpleName(),
						FN_DOFILEDOWNLOAD, " Key file checksum Validation Failed.");

				deleteFile(new File(zipFileName));
				// should we remove all key files??
				jwsRespVo.setError(true);
				jwsRespVo.setErrorMessage(USB_KEY_FILE_CHECKSUM_VALIDATE);
				jwsRespVo.setTitle(USB_KEY_FILE_CHECKSUM_VALIDATE_TITLE);
			}
		}

	}

	/**
	 * Validate key file checksum.
	 *
	 * @param checksumLst the checksum lst
	 * @param filePath the file path
	 * @return true, if successful
	 * @throws NoSuchAlgorithmException the no such algorithm exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	private boolean validateKeyFileChecksum(String checksumLst, String filePath)
			throws NoSuchAlgorithmException, IOException {
		boolean success = true;
		jwslogger.printLogger(JWSCopyToUsb.class.getSimpleName(),
				FN_VALIDATEKEYFILECHECKSUM, " inside validatekeyFileChecksum:");

		if (checksumLst.contains(",")) {
			String[] fileChecksumArray = checksumLst.split(",");
			for (String fChecksum : fileChecksumArray) {
				if (!doValidateChecksumKeyfile(fChecksum, filePath)) {
					success = false;
					break;
				}
			}
		}
		return success;
	}

	/**
	 * Do validate checksum keyfile.
	 *
	 * @param fChecksum the f checksum
	 * @param filePath the file path
	 * @return true, if successful
	 * @throws NoSuchAlgorithmException the no such algorithm exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	private boolean doValidateChecksumKeyfile(String fChecksum, String filePath)
			throws NoSuchAlgorithmException, IOException {
		boolean success = true;
		String[] splitArray = fChecksum.split("=");
		if (splitArray.length > 1) {
			String fileName = splitArray[0].trim();
			String serverChecksum = splitArray[1].trim();
			File keyFile = new File(filePath + "\\" + fileName);
			String calculatedChecksum = calculateCheckSumFile(keyFile);
			jwslogger.printLogger(JWSCopyToUsb.class.getSimpleName(),
					FN_VALIDATEKEYFILECHECKSUM, "fileName=" + fileName
							+ " calculatedChecksum=" + calculatedChecksum
							+ " serverChecksum=" + serverChecksum);

			if (!calculatedChecksum.equals(serverChecksum)) {
				success = false;
				// delete invalid key file from usb
				deleteFile(keyFile);
			}
		}
		return success;
	}

	/**
	 * Calculate check sum file.
	 *
	 * @param file the file
	 * @return the string
	 * @throws NoSuchAlgorithmException the no such algorithm exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	private String calculateCheckSumFile(File file) throws NoSuchAlgorithmException,
			IOException {
		jwslogger.printLogger(JWSCopyToUsb.class.getSimpleName(),
				"calculateCheckSumFile",
				" inside calculateCheckSumFile for" + file.getAbsolutePath());

		MessageDigest md = FileChecksumGenerator.getMessageDigest(MD5_ALGORITHM);
		return FileChecksumGenerator.getFileChecksum(md, file);
	}

	/**
	 * Delete file.
	 *
	 * @param file the file
	 * @return true, if successful
	 */
	private boolean deleteFile(File file) {
		jwslogger
				.printLogger(
						JWSCopyToUsb.class.getSimpleName(),
						FN_DELETEFILE,
						" inside deleteFile" + file.getName() + "-path-"
								+ file.getAbsolutePath());

		try {
			FileUtils.forceDelete(file);
		}
		catch (IOException e) {
			jwslogger.printLogger(JWSCopyToUsb.class.getSimpleName(), FN_DELETEFILE,
					"IOException inside deleteFile" + e);
			return false;
		}
		jwslogger.printLogger(JWSCopyToUsb.class.getSimpleName(), FN_DELETEFILE,
				" deleteFile" + file.getName() + " is deleted!");

		return true;
	}

	/**
	 * Do un zip.
	 *
	 * @param zipFile the zip file
	 * @param outputFolder the output folder
	 */
	private void doUnZip(String zipFile, String outputFolder) {
		jwslogger.printLogger(JWSCopyToUsb.class.getSimpleName(), FN_DOUNZIP,
				" Starting unzipping the file" + zipFile + outputFolder);

		byte[] buffer = new byte[BYTE_SIZE];
		// ZipInputStream zis = null
		FileOutputStream fos = null;
		try (ZipInputStream zis = new ZipInputStream(new FileInputStream(zipFile))) {

			// create output directory is not exists
			File folder = new File(outputFolder);
			boolean isFolderExists = false;
			if (!folder.exists()) {
				isFolderExists = folder.mkdir();
			}
			if (!isFolderExists) {
				jwslogger.printLogger(JWSCopyToUsb.class.getSimpleName(), FOLDER_EXISTS,
						FOLDER_EXISTS);
			}

			// get the zip file content
			// zis = new ZipInputStream(new FileInputStream(zipFile))
			// get the zipped file list entry
			ZipEntry ze = zis.getNextEntry();

			while (ze != null) {

				String fileName = ze.getName();
				jwslogger.printLogger(JWSCopyToUsb.class.getSimpleName(), FN_DOUNZIP,
						" fileName=" + fileName);

				File newFile = new File(outputFolder + File.separator + fileName);

				// create all non exists folders
				// else you will hit FileNotFoundException for compressed folder
				boolean parentFileDir = false;
				parentFileDir = new File(newFile.getParent()).mkdirs();
				if (!parentFileDir) {
					jwslogger.printLogger(AssetEventFileProcessor.class.getSimpleName(),
							PARENT_FILE_DIR, PARENT_FILE_DIR);
				}

				fos = new FileOutputStream(newFile);

				int len;
				while ((len = zis.read(buffer)) > 0) {
					fos.write(buffer, 0, len);
				}

				fos.close();
				ze = zis.getNextEntry();
			}

		}
		catch (IOException ex) {
			jwslogger.printLogger(JWSCopyToUsb.class.getSimpleName(), FN_DOUNZIP,
					" Error while unzipping the file" + ex);

		}
		finally {
			IOUtils.closeQuietly(fos);
			// IOUtils.closeQuietly(zis)
		}
	}

	/**
	 * STANLEY BIN EXISTS FLOW.
	 *
	 * @param assetVersionVo the asset version vo
	 * @param usbFolder the usb folder
	 * @param jwsRespVo the jws resp vo
	 * @param usbFileVo the usb file vo
	 * @throws NoSuchAlgorithmException @
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	private void processWhenUsbContainsFiles(AssetVersionVO assetVersionVo,
			File usbFolder, CopyToUSBJWSRespVo jwsRespVo, UsbFileVo usbFileVo)
			throws NoSuchAlgorithmException, IOException {
		// validate existing metainfo file
		jwslogger.printLogger(JWSCopyToUsb.class.getSimpleName(),
				FN_PROCESSWHENUSBCONTAINSFILES, " inside processWhenUsbContainsFiles:");

		MetaInfoValidationRequestVO metaInfoValidationReqVO = createMetaInfoValidationRequestVO(
				assetVersionVo, usbFolder);
		if (metaInfoValidationReqVO.getFileContent().isEmpty()) {
			jwslogger.printLogger(JWSCopyToUsb.class.getSimpleName(),
					FN_PROCESSWHENUSBCONTAINSFILES, " Meta Info File is EMPTY..");
			processWhenInvalidStanleyBinInUSB(assetVersionVo, usbFolder, jwsRespVo,
					usbFileVo);
		}
		else {
			MetaInfoValidationRespVO metaValidateRespVo = invokevalidateMetaInfoFile(metaInfoValidationReqVO);
			if (metaValidateRespVo != null) {
				//
				if (metaValidateRespVo.isCheckSum()
						&& metaValidateRespVo.isDownloadedUser()
						&& metaValidateRespVo.isSoftwareVersion()) {
					// success
					jwslogger.printLogger(JWSCopyToUsb.class.getSimpleName(),
							FN_PROCESSWHENUSBCONTAINSFILES,
							" META_FILE validation Success");

					// invoke service to download key files
					doFileDownload(assetVersionVo, usbFolder, jwsRespVo, true, usbFileVo);
				}
				else {
					processOnMetaFileValidationError(metaValidateRespVo, assetVersionVo,
							usbFolder, jwsRespVo, usbFileVo);
				}
			}
		}
	}

	/**
	 * Process on meta file validation error.
	 *
	 * @param metaValidateRespVo the meta validate resp vo
	 * @param assetVersionVo the asset version vo
	 * @param usbFolder the usb folder
	 * @param jwsRespVo the jws resp vo
	 * @param usbFileVo the usb file vo
	 * @throws NoSuchAlgorithmException the no such algorithm exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	private void processOnMetaFileValidationError(
			MetaInfoValidationRespVO metaValidateRespVo, AssetVersionVO assetVersionVo,
			File usbFolder, CopyToUSBJWSRespVo jwsRespVo, UsbFileVo usbFileVo)
			throws NoSuchAlgorithmException, IOException {

		jwsRespVo.setUsbSerNumList(usbFileVo.getExistngAssetSerNumLst());
		if (!metaValidateRespVo.isDownloadedUser()) {
			jwslogger
					.printLogger(JWSCopyToUsb.class.getSimpleName(),
							FN_PROCESSWHENUSBCONTAINSFILES,
							"META_FILE validation -user mismatch");

			// SHOW ERROR MESSAGE TO USER.
			jwsRespVo.setTitle(FAILURE_TITLE);
			jwsRespVo.setError(true);
			jwsRespVo.setErrorMessage(META_FILE_USER_VALIDATE_MSG);
		}
		else if (!metaValidateRespVo.isCheckSum()) {
			// remove stanley bin from usb and download stanley,meta and key
			// files
			jwslogger.printLogger(JWSCopyToUsb.class.getSimpleName(),
					FN_PROCESSWHENUSBCONTAINSFILES,
					"META_FILE validation -CHECKSUM mismatch");

			processWhenInvalidStanleyBinInUSB(assetVersionVo, usbFolder, jwsRespVo,
					usbFileVo);

		}
		else if (!metaValidateRespVo.isSoftwareVersion()) {
			// version mis match error.
			jwslogger.printLogger(JWSCopyToUsb.class.getSimpleName(),
					FN_PROCESSWHENUSBCONTAINSFILES,
					"check whether key files exists in USB");

			if (usbFileVo.isContainsAssetKeyFile()) {
				jwslogger.printLogger(JWSCopyToUsb.class.getSimpleName(),
						FN_PROCESSWHENUSBCONTAINSFILES,
						"Asset key files exists in USB...");

				/**
				 * the software package has been reassigned. System should ask for
				 * confirmation before removing existing files from USB.
				 */
				jwsRespVo.setTitle(META_FILE_VERSION_VALIDATE_TITLE);
				jwsRespVo.setError(true);
				jwsRespVo.setErrorMessage(META_FILE_VERSION_VALIDATE_NEW_MSG);
				jwsRespVo.setToConfirm(true);
				jwslogger.printLogger(
						JWSCopyToUsb.class.getSimpleName(),
						FN_PROCESSWHENUSBCONTAINSFILES,
						"usbFileVo.getExistngAssetSerNumLst()"
								+ usbFileVo.getExistngAssetSerNumLst());

				jwsRespVo.setUsbSerNumList(usbFileVo.getExistngAssetSerNumLst());
				jwsRespVo.setAssetSerNumList(assetVersionVo.getAssetSerialNumberList());
				jwslogger.printLogger(JWSCopyToUsb.class.getSimpleName(),
						FN_PROCESSWHENUSBCONTAINSFILES, "jwsRespVo.getAssetSerNumList()="
								+ jwsRespVo.getAssetSerNumList());

				/*
				 * // SHOW ERROR MESSAGE TO USER.
				 * jwsRespVo.setTitle(META_FILE_VERSION_VALIDATE_TITLE)
				 * jwsRespVo.setError(true)
				 * jwsRespVo.setErrorMessage(META_FILE_VERSION_VALIDATE_MSG)
				 */
			}
			else {
				jwslogger.printLogger(JWSCopyToUsb.class.getSimpleName(),
						FN_PROCESSWHENUSBCONTAINSFILES,
						"Asset key files Not exists in USB...");

				// remove existing stanley bin and download from server.
				processWhenInvalidStanleyBinInUSB(assetVersionVo, usbFolder, jwsRespVo,
						usbFileVo);
			}
		}

	}

	/**
	 * Process when invalid stanley bin in USB.
	 *
	 * @param assetVersionVo the asset version vo
	 * @param usbFolder the usb folder
	 * @param jwsRespVo the jws resp vo
	 * @param usbFileVo the usb file vo
	 * @throws NoSuchAlgorithmException the no such algorithm exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	private void processWhenInvalidStanleyBinInUSB(AssetVersionVO assetVersionVo,
			File usbFolder, CopyToUSBJWSRespVo jwsRespVo, UsbFileVo usbFileVo)
			throws NoSuchAlgorithmException, IOException {
		jwslogger
				.printLogger(JWSCopyToUsb.class.getSimpleName(),
						FN_PROCESSWHENINVALIDSTANLEY,
						"inside processWhenInvalidStanleyBinInUSB:");

		File stanleyFile = getStanleyBinFile(usbFolder);
		deleteFile(stanleyFile);
		jwslogger.printLogger(JWSCopyToUsb.class.getSimpleName(),
				FN_PROCESSWHENINVALIDSTANLEY,
				"Download Stanley.bin now.along with key files");

		doFileDownload(assetVersionVo, usbFolder, jwsRespVo, false, usbFileVo);
	}

	/**
	 * Creates the meta info validation request VO.
	 *
	 * @param assetVersionVo the asset version vo
	 * @param folder the folder
	 * @return the meta info validation request VO
	 * @throws NoSuchAlgorithmException the no such algorithm exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	// create meta-info validate request vo
	private MetaInfoValidationRequestVO createMetaInfoValidationRequestVO(
			AssetVersionVO assetVersionVo, File folder) throws NoSuchAlgorithmException,
			IOException {
		jwslogger.printLogger(JWSCopyToUsb.class.getSimpleName(),
				FN_CREATEMETAINFOVALIDATEREQVO,
				"inside createMetaInfoValidationRequestVO:");

		File metaFile = new File(folder.getAbsolutePath() + "\\" + META_FILE_NAME);
		String content = readFileContent(metaFile.getAbsolutePath());
		jwslogger.printLogger(JWSCopyToUsb.class.getSimpleName(),
				FN_CREATEMETAINFOVALIDATEREQVO, "meta-file content:" + content);

		// set metafile content
		MetaInfoValidationRequestVO metaInfoValidationRequestVO = new MetaInfoValidationRequestVO();
		metaInfoValidationRequestVO.setFileContent(content);
		// set version
		metaInfoValidationRequestVO.setSoftwareVersion(assetVersionVo
				.getAvailableVersion());

		//
		File stanleyFile = new File(folder.getAbsolutePath() + "\\" + STANLEY_BIN);
		MessageDigest md = FileChecksumGenerator.getMessageDigest(MD5_ALGORITHM);
		String checksum = FileChecksumGenerator.getFileChecksum(md, stanleyFile);
		metaInfoValidationRequestVO.setCheckSum(checksum);

		return metaInfoValidationRequestVO;
	}

	/**
	 * Read file content.
	 *
	 * @param filePath the file path
	 * @return the string
	 */
	private String readFileContent(String filePath) {
		StringBuilder content = new StringBuilder();
		List<String> list = new ArrayList<>();
		try (Stream<String> stream = Files.lines(Paths.get(filePath))) {

			// list =stream.collect( Collectors.toList())
			stream.forEach(str -> content.append(str + "\n"));
		}
		catch (IOException e) {
			jwslogger.printLogger(JWSCopyToUsb.class.getSimpleName(), "readFileContent",
					" IOException inside readFileContent" + e);
		}

		list.forEach(str -> content.append(str + "\n"));
		return content.toString();
	}

	/**
	 * VALIDATE WRITE PERMISSION & SIZE.
	 *
	 * @param folder the folder
	 * @param jwsRespVo the jws resp vo
	 * @return the string
	 */
	private String validateUSB(File folder, CopyToUSBJWSRespVo jwsRespVo) {
		String returnValue = "";
		jwslogger.printLogger(
				JWSCopyToUsb.class.getSimpleName(),
				"validateUSB",
				"usable space in USB:" + folder.getUsableSpace() + " Free space="
						+ folder.getFreeSpace() + " Total Space="
						+ folder.getTotalSpace());
		if (!folder.canWrite()) {
			returnValue = setErrorMsgToResponse(USB_WRITE_PROTECT_MSG, jwsRespVo,
					USB_WRITE_PROTECT_TITLE);
		}
		else if (!folder.canRead()) {
			returnValue = setErrorMsgToResponse(USB_READ_PROTECT_MSG, jwsRespVo,
					USB_READ_PROTECT_TITLE);
		}
		else if (folder.getUsableSpace() < USB_LENGTH_THOUSANDTWENTYFOUR) { // no enough
																			// space in
																			// USB
			returnValue = setErrorMsgToResponse(USB_NO_SPACE, jwsRespVo,
					USB_NO_SPACE_TITLE);
		}

		return returnValue;
	}

	/**
	 * List files names for folder.
	 *
	 * @param folder the folder
	 * @return Find files from USB
	 */
	private UsbFileVo listFilesNamesForFolder(final File folder) {
		jwslogger.printLogger(JWSCopyToUsb.class.getSimpleName(),
				FN_LISTFILENAMESFORFOLDER, "inside listFilesNamesForFolder:" + folder);
		if (folder != null) {
			File[] fileEntries = folder.listFiles();
			if (fileEntries != null) {
				return getFileEntries(fileEntries, folder);
			}
		}
		return new UsbFileVo();
	}

	private UsbFileVo getFileEntries(File[] fileEntries, File folder) {
		List<String> usbFiles = new ArrayList<>();
		List<String> unwantedFileNames = new ArrayList<>();
		UsbFileVo usbFileVo = new UsbFileVo();
		for (final File fileEntry : fileEntries) {
			if (fileEntry.isDirectory()) {
				listFilesNamesForFolder(fileEntry);
			}
			else {
				jwslogger.printLogger(JWSCopyToUsb.class.getSimpleName(),
						FN_LISTFILENAMESFORFOLDER, "File= " + folder.getAbsolutePath()
								+ "\\" + fileEntry.getName());
				doListFilesForFolder(fileEntry, usbFiles, usbFileVo, unwantedFileNames);
			}
		}
		if (!unwantedFileNames.isEmpty()) {
			usbFileVo.setUnwantedFileNames(unwantedFileNames);
		}
		if (!usbFiles.isEmpty()) {
			usbFileVo.setUsbFileNames(usbFiles);
		}
		return usbFileVo;
	}

	/**
	 * Do list files for folder.
	 *
	 * @param fileEntry the file entry
	 * @param usbFiles the usb files
	 * @param usbFileVo the usb file vo
	 * @param unwantedFileNames the unwanted file names
	 */
	private void doListFilesForFolder(File fileEntry, List<String> usbFiles,
			UsbFileVo usbFileVo, List<String> unwantedFileNames) {
		String fileName = fileEntry.getName();
		if (fileName.endsWith(".bin") && !STANLEY_BIN.equalsIgnoreCase(fileName)) {
			jwslogger.printLogger(JWSCopyToUsb.class.getSimpleName(),
					FN_LISTFILENAMESFORFOLDER, "Found key files in USB.");
			addBinFilesToUsbFileVo(fileName, usbFiles, usbFileVo);
		}
		else if (fileName.endsWith(TYPE_XML)) {
			jwslogger.printLogger(JWSCopyToUsb.class.getSimpleName(),
					FN_LISTFILENAMESFORFOLDER, "Found xml files in USB.");
			usbFileVo.setContainsXmlFile(true);
		}
		else if (!META_FILE_NAME.equalsIgnoreCase(fileName)
				&& !STANLEY_BIN.equalsIgnoreCase(fileName)) {
			jwslogger.printLogger(JWSCopyToUsb.class.getSimpleName(),
					FN_LISTFILENAMESFORFOLDER, "Found unwanted files in USB.");
			unwantedFileNames.add(fileName);
		}

		if (STANLEY_BIN.equalsIgnoreCase(fileName)) {
			usbFileVo.setStanlelExists(true);
		}
		else if (META_FILE_NAME.equalsIgnoreCase(fileName)) {
			usbFileVo.setMetaFileExists(true);
		}

	}

	/**
	 * Adds the bin files to usb file vo.
	 *
	 * @param fileName the file name
	 * @param usbFiles the usb files
	 * @param usbFileVo the usb file vo
	 */
	private void addBinFilesToUsbFileVo(String fileName, List<String> usbFiles,
			UsbFileVo usbFileVo) {

		jwslogger.printLogger(JWSCopyToUsb.class.getSimpleName(),
				FN_LISTFILENAMESFORFOLDER, "Found key files in USB.");
		usbFiles.add(fileName);
		usbFileVo.setContainsAssetKeyFile(true);
		if (usbFileVo.getExistngAssetSerNumLst() == null) {
			usbFileVo.setExistngAssetSerNumLst(new ArrayList<>());
		}
		usbFileVo.getExistngAssetSerNumLst().add(
				fileName.substring(0, fileName.lastIndexOf('.')));

		jwslogger.printLogger(JWSCopyToUsb.class.getSimpleName(),
				FN_LISTFILENAMESFORFOLDER, "usbFileVo.getExistngAssetSerNumLst()="
						+ usbFileVo.getExistngAssetSerNumLst());

	}

	/**
	 * Find key file assets.
	 *
	 * @param usbFolder the usb folder
	 * @return Find asset numbers for which key file exists in USB
	 */
	private List<String> findKeyFileAssets(File usbFolder) {
		jwslogger.printLogger(JWSCopyToUsb.class.getSimpleName(), "findKeyFileAssets",
				"inside findKeyFileAssets:");
		List<String> assetSerNumList = new ArrayList<>();

		File[] fileEntries = usbFolder.listFiles();
		if (fileEntries == null) {
			return assetSerNumList;
		}

		for (final File fileEntry : fileEntries) {

			if (fileEntry.isDirectory()) {
				findKeyFileAssets(fileEntry);
			}
			else {
				jwslogger.printLogger(JWSCopyToUsb.class.getSimpleName(),
						"findKeyFileAssets", "File= " + usbFolder.getAbsolutePath()
								+ "\\" + fileEntry.getName());
				doFindKeyFileAssets(fileEntry, assetSerNumList);
			}
		}
		return assetSerNumList;
	}

	/**
	 * Do find key file assets.
	 *
	 * @param fileEntry the file entry
	 * @param assetSerNumList the asset ser num list
	 */
	private void doFindKeyFileAssets(File fileEntry, List<String> assetSerNumList) {
		String fileName = fileEntry.getName();
		if (fileName.endsWith(TYPE_BIN) && !STANLEY_BIN.equalsIgnoreCase(fileName)) {
			String[] fileNames = fileName.split(SPLIT_DOT_REGEX);

			if (fileNames.length > 0) {
				assetSerNumList.add(fileNames[0]);
			}
		}
	}

	/**
	 * Gets the url from properties.
	 *
	 * @param key the key
	 * @return the url from properties
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	String getUrlFromProperties(String key) throws IOException {
		Properties properties = new Properties();
		InputStream input = getClass().getClassLoader().getResourceAsStream(
				"applet.properties");
		properties.load(input);
		return properties.getProperty(key);
	}

	/*----- REST SERVICE CALLS--------------------------------------------------------*/

	/**
	 * Invoke download file service.
	 *
	 * @param assetVersionVo the asset version vo
	 * @param zipFileName the zip file name
	 * @return the pre update download res vo
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	// downloadPreUpdateFile
	private PreUpdateDownloadResVo invokeDownloadFileService(
			AssetVersionVO assetVersionVo, String zipFileName) throws IOException {
		jwslogger.printLogger(JWSCopyToUsb.class.getSimpleName(),
				FN_INVOKEDOWNLOADFILESERVICE, "inside invokeDownloadFileService  :");

		String urlString = getUrlFromProperties(DOWNLOAD_URL);

		jwslogger.printLogger(JWSCopyToUsb.class.getSimpleName(),
				FN_INVOKEDOWNLOADFILESERVICE, MSG_URL + urlString);

		HttpURLConnection conn = getConnection(urlString);
		DataOutputStream out = new DataOutputStream(conn.getOutputStream());
		Gson gson = new Gson();
		String json = gson.toJson(assetVersionVo);
		// String jjwid = "jjwid=" + userheadervo.getUserWwid()
		jwslogger.printLogger(JWSCopyToUsb.class.getSimpleName(),
				FN_INVOKEDOWNLOADFILESERVICE, "AssetVersionVO-" + json);
		// out.writeBytes(jjwid)
		out.writeBytes(json);
		out.flush();
		out.close();
		InputStream inputStream;
		FileOutputStream output;
		if (conn.getHeaderField(ERROR) == null) {
			inputStream = conn.getInputStream();
			byte[] buffer = new byte[BUFFER_SIZE];
			int bufferLength; // used to store a temporary size of the buffer
			output = new FileOutputStream(zipFileName);
			// now, read through the input buffer and write the contents to the
			// file
			while ((bufferLength = conn.getInputStream().read(buffer)) > 0) {
				// add the data in the buffer to the file in the file output
				// stream (the file on the sd card
				output.write(buffer, 0, bufferLength);
				// add up the size so we know how much is downloaded
				// this is where you would do something to report the progress,
				// like this maybe
			}
			output.close();
			inputStream.close();
		}

		String respStr;
		PreUpdateDownloadResVo vo = new PreUpdateDownloadResVo();
		if (conn.getHeaderField(ERROR) != null) {
			respStr = conn.getHeaderField(ERROR);
			vo.setError(true);
			vo.setErrorString(respStr);
			jwslogger.printLogger(JWSCopyToUsb.class.getSimpleName(),
					FN_INVOKEDOWNLOADFILESERVICE, "Error message from server:" + respStr);
		}
		else if (conn.getHeaderField("checksum") != null) {
			respStr = conn.getHeaderField("checksum");
			vo.setCheckSumStr(respStr);
			jwslogger.printLogger(JWSCopyToUsb.class.getSimpleName(),
					FN_INVOKEDOWNLOADFILESERVICE, "checksumStr:" + respStr);
		}

		return vo;
	}

	// compareAssetVersion

	/**
	 * Invoke compare asset version.
	 *
	 * @param assetSerialNumberList the asset serial number list
	 * @param usbAssetNumLst the usb asset num lst
	 * @return the asset version VO
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	private AssetVersionVO invokeCompareAssetVersion(List<String> assetSerialNumberList,
			List<String> usbAssetNumLst) throws IOException {
		jwslogger.printLogger(JWSCopyToUsb.class.getSimpleName(),
				FN_INVOKECOMPAREASSETVERSION, "inside invokeCompareAssetVersion  :");
		// String urlString = "http://localhost:8080/compareAssetVersion"
		String urlString = getUrlFromProperties(COMPARE_ASSET_URL);
		AssetVersionVO respVo = new AssetVersionVO();
		jwslogger.printLogger(JWSCopyToUsb.class.getSimpleName(),
				FN_INVOKECOMPAREASSETVERSION, MSG_URL + urlString);
		HttpURLConnection conn = getConnection(urlString);
		DataOutputStream out = new DataOutputStream(conn.getOutputStream());
		AssetVersionCompareReqVO assetVersionCompareReqVO = new AssetVersionCompareReqVO();
		assetVersionCompareReqVO.setAssetSerialNumberList(assetSerialNumberList);
		assetVersionCompareReqVO.setUsbAssetSerNumList(usbAssetNumLst);
		Gson gson = new Gson();
		String json = gson.toJson(assetVersionCompareReqVO);

		jwslogger.printLogger(JWSCopyToUsb.class.getSimpleName(),
				FN_INVOKECOMPAREASSETVERSION, "compareAssetVersion-json-" + json);
		out.writeBytes(json);
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

		jwslogger.printLogger(JWSCopyToUsb.class.getSimpleName(),
				FN_INVOKECOMPAREASSETVERSION, " response = " + response.toString());

		if (!response.toString().isEmpty()) {
			Gson gsonOut = new Gson();
			respVo = gsonOut.fromJson(response.toString(), AssetVersionVO.class);
		}
		return respVo;

	}

	/**
	 * Invokevalidate meta info file.
	 *
	 * @param vo the vo
	 * @return the meta info validation resp VO
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	private MetaInfoValidationRespVO invokevalidateMetaInfoFile(
			MetaInfoValidationRequestVO vo) throws IOException {
		jwslogger.printLogger(JWSCopyToUsb.class.getSimpleName(),
				FN_INVOKEVALIDATEMETAINFOFILE, "inside invokevalidateMetaInfoFile");
		// String urlString = "http://localhost:8080/validateMetaInfoFile"
		String urlString = getUrlFromProperties(VALIDATE_METAINFO_URL);
		MetaInfoValidationRespVO respVo = new MetaInfoValidationRespVO();
		jwslogger.printLogger(JWSCopyToUsb.class.getSimpleName(),
				FN_INVOKEVALIDATEMETAINFOFILE, MSG_URL + urlString);

		HttpURLConnection conn = getConnection(urlString);
		DataOutputStream out = new DataOutputStream(conn.getOutputStream());
		Gson gson = new Gson();
		String json = gson.toJson(vo);
		jwslogger.printLogger(JWSCopyToUsb.class.getSimpleName(),
				FN_INVOKEVALIDATEMETAINFOFILE, "compareAssetVersion-json-" + json);
		out.writeBytes(json);
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

		jwslogger.printLogger(JWSCopyToUsb.class.getSimpleName(),
				FN_INVOKEVALIDATEMETAINFOFILE, "json response=" + response.toString());

		if (!response.toString().isEmpty()) {
			Gson gsonOut = new Gson();
			respVo = gsonOut
					.fromJson(response.toString(), MetaInfoValidationRespVO.class);
		}
		return respVo;

	}

	/**
	 * Invoke asset reconciliation service.
	 *
	 * @param usbFilesList the usb files list
	 * @return the string
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	// assetReconciliationForCopyToUsb
	private String invokeAssetReconciliationService(List<String> usbFilesList)
			throws IOException {

		jwslogger.printLogger(JWSCopyToUsb.class.getSimpleName(),
				FN_INVOKEASSETRECONCILIATESERVICE, " usbFilesList.size() : "
						+ usbFilesList.size());

		// String urlString =
		// "http://localhost:8080/assetReconciliationForCopyToUsb"
		String urlString = getUrlFromProperties(ASSET_RECONCILIATE_URL);

		jwslogger.printLogger(JWSCopyToUsb.class.getSimpleName(),
				FN_INVOKEASSETRECONCILIATESERVICE, MSG_URL + urlString);

		HttpURLConnection conn = getConnection(urlString);

		DataOutputStream out = new DataOutputStream(conn.getOutputStream());
		Type listType = new TypeToken<List<String>>() {
		}.getType();
		Gson gson = new Gson();
		String json = gson.toJson(usbFilesList, listType);
		jwslogger.printLogger(JWSCopyToUsb.class.getSimpleName(),
				FN_INVOKEASSETRECONCILIATESERVICE, "-json-" + json);
		out.writeBytes(json);
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

		jwslogger.printLogger(JWSCopyToUsb.class.getSimpleName(),
				FN_INVOKEASSETRECONCILIATESERVICE, MSG_GOT_JSON + response.toString());

		return response.toString();
	}

	/**
	 * Invoke lock assets after check out.
	 *
	 * @param assetVersionVO the asset version VO
	 * @return true, if successful
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	// lockAssetsAfterCheckOut
	private boolean invokeLockAssetsAfterCheckOut(AssetVersionVO assetVersionVO)
			throws IOException {
		jwslogger.printLogger(JWSCopyToUsb.class.getSimpleName(),
				FN_INVOKE_LOCKASSETAFTERCHECKOUT,
				"inside invokeLockAssetsAfterCheckOut  :");
		// String urlString = "http://localhost:8080/lockAssetsAfterCheckOut"
		String urlString = getUrlFromProperties(LOCK_ASSET_URL);

		jwslogger.printLogger(JWSCopyToUsb.class.getSimpleName(),
				FN_INVOKE_LOCKASSETAFTERCHECKOUT, MSG_URL + urlString);

		HttpURLConnection conn = getConnection(urlString);
		DataOutputStream out = new DataOutputStream(conn.getOutputStream());
		Gson gson = new Gson();
		String json = gson.toJson(assetVersionVO);
		jwslogger.printLogger(JWSCopyToUsb.class.getSimpleName(),
				FN_INVOKE_LOCKASSETAFTERCHECKOUT, "invokeLockAssetsAfterCheckOut-json-"
						+ json);
		out.writeBytes(json);
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

		jwslogger.printLogger(JWSCopyToUsb.class.getSimpleName(),
				FN_INVOKE_LOCKASSETAFTERCHECKOUT, MSG_GOT_JSON + response.toString());
		return gson.fromJson(response.toString(), Boolean.class);
	}

	/**
	 * Invoke unlock assets for copy to usb.
	 *
	 * @param assetSerNumList the asset ser num list
	 * @return the string
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	// unlockAssetsForCopyToUsb
	private String invokeUnlockAssetsForCopyToUsb(List<String> assetSerNumList)
			throws IOException {
		jwslogger.printLogger(JWSCopyToUsb.class.getSimpleName(),
				FN_INVOKE_UNLOCKASSETAFTERCHECKOUT,
				"inside invokeUnlockAssetsForCopyToUsb  : " + assetSerNumList.size());
		String urlString = getUrlFromProperties(USBConstant.COPY_USB_UNLOCK_ASSET_URL);

		jwslogger.printLogger(JWSCopyToUsb.class.getSimpleName(),
				FN_INVOKE_UNLOCKASSETAFTERCHECKOUT, MSG_URL + urlString);
		HttpURLConnection conn = getConnection(urlString);
		DataOutputStream out = new DataOutputStream(conn.getOutputStream());
		Gson gson = new Gson();
		Type listType = new TypeToken<List<String>>() {
		}.getType();
		String json = gson.toJson(assetSerNumList, listType);
		jwslogger.printLogger(JWSCopyToUsb.class.getSimpleName(),
				FN_INVOKE_UNLOCKASSETAFTERCHECKOUT,
				"invokeUnlockAssetsForCopyToUsb-json-" + json);
		out.writeBytes(json);
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
		jwslogger.printLogger(JWSCopyToUsb.class.getSimpleName(),
				FN_INVOKE_UNLOCKASSETAFTERCHECKOUT, MSG_GOT_JSON + response.toString());

		return response.toString();
	}

	/**
	 * Confirmation dialog.
	 *
	 * @param stage the stage
	 * @param title the title
	 * @param msg the msg
	 * @param userheadervo the userheadervo
	 * @param usbAssetSrNoLst the usb asset sr no lst
	 * @param asssetSrNo the assset sr no
	 */
	public void confirmationDialog(final Stage stage, String title, String msg,
			UserHeaderVO userheadervo, List<String> usbAssetSrNoLst,
			List<String> asssetSrNo) {
		jwslogger.printLogger(JWSCopyToUsb.class.getSimpleName(), FN_CONFIRMATION_DIALOG,
				"usbAssetSrNoLst" + usbAssetSrNoLst);

		jwslogger.printLogger(JWSCopyToUsb.class.getSimpleName(), FN_CONFIRMATION_DIALOG,
				"asssetSrNo" + asssetSrNo);

		Stage dialog = new Stage();
		dialog.setTitle(title);
		dialog.initModality(Modality.APPLICATION_MODAL);
		dialog.initOwner(stage);
		css = this.getClass().getClassLoader().getResource(STYLESHEET_CSS)
				.toExternalForm();
		VBox dialogVbox = new VBox();
		dialogVbox.setMaxSize(STAGE_WIDTH_FOURHUNDRED, STAGE_HEIGHT_FOURHUNDRED);
		CopyToUsbDialog cusbDialog = new CopyToUsbDialog();
		GridPane gridpane = cusbDialog.getGridPane(msg, userheadervo);
		gridpane.add(okButton, 1, SECOND_ROW_INDEX);
		gridpane.add(cancelButton, 1, THIRD_ROW_INDEX);

		dialogVbox.getChildren().addAll(gridpane);
		Scene dialogScene = new Scene(dialogVbox, STAGE_WIDTH_FOURHUNDRED,
				STAGE_HEIGHT_FOURHUNDRED);
		dialogScene.getStylesheets().add(css);
		dialog.setScene(dialogScene);
		dialog.setHeight(STAGE_HEIGHT_FOURHUNDRED);
		dialog.setWidth(STAGE_WIDTH_FOURHUNDRED);
		dialog.show();

		okButton.setOnAction(t -> {
			dialog.hide();
			onClickOKButton(stage, usbAssetSrNoLst, asssetSrNo);

		});

		cancelButton.setOnAction(t -> {
			dialog.hide();
			stage.hide();
		});

	}

	/**
	 * Delete on click OK button.
	 *
	 * @return the delete file resp vo
	 */
	private DeleteFileRespVo deleteOnClickOKButton() {
		DeleteFileDetails df = new DeleteFileDetails();
		DeleteFileRespVo deleteResponseVo = new DeleteFileRespVo();
		// remove all bin files from usb
		String deletedJson = df.deleteUSBFile("", false, true);
		ObjectMapper mapper = new ObjectMapper();
		try {
			deleteResponseVo = mapper.readValue(deletedJson, DeleteFileRespVo.class);
		}
		catch (JsonParseException | JsonMappingException e) {
			jwslogger.printLogger(JWSCopyToUsb.class.getSimpleName(),
					FN_CONFIRMATION_DIALOG, "JsonParseException | JsonMappingException:"
							+ e);

		}
		catch (IOException e) {
			jwslogger.printLogger(JWSCopyToUsb.class.getSimpleName(),
					FN_CONFIRMATION_DIALOG, "IOException :" + e);

		}
		return deleteResponseVo;

	}

	/**
	 * On click OK button.
	 *
	 * @param stage the stage
	 * @param usbAssetSrNoLst the usb asset sr no lst
	 * @param asssetSrNo the assset sr no
	 */
	private void onClickOKButton(final Stage stage, List<String> usbAssetSrNoLst,
			List<String> asssetSrNo) {
		StringBuilder assetIds = new StringBuilder();
		DeleteFileRespVo deleteResponseVo = deleteOnClickOKButton();
		if (!deleteResponseVo.isDeleteStatus()) {
			assetIds.append(deleteResponseVo.getAssetId()).append(",");
		}
		if (deleteResponseVo.isDeleteStatus()) {
			// invoke unlock asset service here.
			try {
				invokeUnlockAssetsForCopyToUsb(usbAssetSrNoLst);
			}
			catch (IOException e) {
				jwslogger.printLogger(JWSCopyToUsb.class.getSimpleName(),
						FN_CONFIRMATION_DIALOG,
						"Exception while invoking UnlockAssetsForCopyToUsb:" + e);
			}
			asssetSrNo.addAll(usbAssetSrNoLst);
			copytoUSBDialog(stage, asssetSrNo);
		}
		else {
			deleteFailureDialog(
					stage,
					"File Deletion Failed",
					"Bin file deletion through system is failed for the assets "
							+ assetIds
							+ "Please remove bin finles in USB stick manually and try once again by click Copy to USB Button",
					userheadervo);
		}
	}

	/**
	 * Delete failure dialog.
	 *
	 * @param stage the stage
	 * @param title the title
	 * @param msg the msg
	 * @param userheadervo the userheadervo
	 */
	public void deleteFailureDialog(final Stage stage, String title, String msg,
			UserHeaderVO userheadervo) {
		Stage failureDialog = new Stage();
		failureDialog.setTitle(title);
		failureDialog.initModality(Modality.APPLICATION_MODAL);
		failureDialog.initOwner(stage);
		css = this.getClass().getClassLoader().getResource(STYLESHEET_CSS)
				.toExternalForm();
		VBox dialogVbox = new VBox();
		dialogVbox.setMaxSize(STAGE_WIDTH_FOURHUNDRED, STAGE_HEIGHT_FOURHUNDRED);
		CopyToUsbDialog cusbDialog = new CopyToUsbDialog();
		GridPane gridpane = cusbDialog.getGridPane(msg, userheadervo);
		gridpane.add(okButton, 1, SECOND_ROW_INDEX);
		dialogVbox.getChildren().addAll(gridpane);
		Scene dialogScene = new Scene(dialogVbox, DIALOG_VBOX_WIDTH_FOURHUNDRED,
				DIALOG_VBOX_HEIGHT_FOURHUNDRED);
		dialogScene.getStylesheets().add(css);
		failureDialog.setScene(dialogScene);
		failureDialog.setHeight(STAGE_HEIGHT_FOURHUNDRED);
		failureDialog.setWidth(STAGE_WIDTH_FOURHUNDRED);
		failureDialog.show();
		cancelButton.setOnAction(t -> {
			failureDialog.hide();
			stage.hide();
		});
	}

	private HttpURLConnection getConnection(String urlString) throws IOException {
		URL url = new URL(urlString);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setDoOutput(true);
		conn.setDoInput(true);
		conn.setUseCaches(false);
		conn.setRequestMethod("POST");
		conn.setRequestProperty(CONTENT_TYPE, APPLCTN_JSON);
		conn.setRequestProperty(AUTHORIZN, BEARER + jwttoken);
		return conn;
	}

}
