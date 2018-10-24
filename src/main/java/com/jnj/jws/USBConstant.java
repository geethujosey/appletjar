/*
 * 
 */
package com.jnj.jws;

/**
 * Class to define constant variables for JWS.
 *
 * @author 555786
 */
public final class USBConstant {

	/** The Constant POST_UPDATE_FILE_URL. */
	public static final String POST_UPDATE_FILE_URL = "usb.update.postupdatefiles.url";

	/** The Constant SERVICING_COUNTRIES. */
	public static final String SERVICING_COUNTRIES = "usb.process.getservicingcountries.url";

	/** The Constant UPDATE_STATUS. */
	public static final String UPDATE_STATUS = "usb.process.getupdatestatus.url";

	/** The Constant UPGRADE_TYPE. */
	public static final String UPGRADE_TYPE = "usb.process.getupgradetype.url";

	/** The Constant SOFTWARE_VERSIONS. */
	public static final String SOFTWARE_VERSIONS = "usb.process.getsoftwareversions.url";

	/** The Constant ASSET_SERIALNUMBERS. */
	public static final String ASSET_SERIALNUMBERS = "usb.process.getassetserialnumbers.url";

	/** The Constant ACCOUNT_NAMES. */
	public static final String ACCOUNT_NAMES = "usb.process.getassetaccountnames.url";

	/** The Constant ACCOUNT_NUMBERS. */
	public static final String ACCOUNT_NUMBERS = "usb.process.getassetaccountnumbers.url";

	/** The Constant CHECK_ASSET_AVAILABLE. */
	public static final String CHECK_ASSET_AVAILABLE = "asset.event.log.genesis.isAssetAvailable.url";

	/** The Constant EVENTLOG_FILE_REFID_URL. */
	public static final String EVENTLOG_FILE_REFID_URL = "asset.event.log.genesis.filerefid.url";

	/** The Constant DOWNLOAD_FILE_URL. */
	public static final String DOWNLOAD_FILE_URL = "axeda.downloadfile.url";

	/** The Constant USB_RECONCLT_URL. */
	public static final String USB_RECONCLT_URL = "usb.upload.reconciliation.url";

	/** The Constant PROCESS_USB_URL. */
	public static final String PROCESS_USB_URL = "usb.process.file.url";

	/** The Constant DOWNLOAD_URL. */
	public static final String DOWNLOAD_URL = "usb.copy.download.url";

	/** The Constant COMPARE_ASSET_URL. */
	public static final String COMPARE_ASSET_URL = "usb.copy.compare.asset.url";

	/** The Constant VALIDATE_METAINFO_URL. */
	public static final String VALIDATE_METAINFO_URL = "usb.copy.validate.metainfo.url";

	/** The Constant ASSET_RECONCILIATE_URL. */
	public static final String ASSET_RECONCILIATE_URL = "usb.copy.reconcilition.url";

	/** The Constant COPY_USB_UNLOCK_ASSET_URL. */
	public static final String COPY_USB_UNLOCK_ASSET_URL = "usb.copy.unlock.asset.url";

	/** The Constant LOCK_ASSET_URL. */
	public static final String LOCK_ASSET_URL = "usb.copy.lockasset.url";

	/** The Constant REASON_FOR_FAILURE. */
	public static final String REASON_FOR_FAILURE = "usb.update.failure.reason.url";

	/** The Constant REASON_FOR_MANUAL_UPDATE. */
	public static final String REASON_FOR_MANUAL_UPDATE = "usb.update.manual.reason.url";

	/** The Constant REASON_FOR_REMOVE. */
	public static final String REASON_FOR_REMOVE = "usb.update.reason.url";

	/** The Constant POST_REASON_FOR_REMOVE. */
	public static final String POST_REASON_FOR_REMOVE = "usb.update.reason.post.url";

	/** The Constant GET_SOFTWARE_VERSION. */
	public static final String GET_SOFTWARE_VERSION = "usb.common.get.software.version";

	/** The Constant PRE_UPDATE_NOT_RECONCILE_WITH_SYS. */
	public static final String PRE_UPDATE_NOT_RECONCILE_WITH_SYS = "PRENOTRECSYS";

	/** The Constant POST_UPDATE_FILES. */
	public static final String POST_UPDATE_FILES = "POSTUPDATEFILES";

	/** The Constant PRE_UPDATE_FILES. */
	public static final String PRE_UPDATE_FILES = "PREUPDATEFILES";

	/** The Constant GENESIS_APPLICATION_EXCEPTION. */
	public static final String GENESIS_APPLICATION_EXCEPTION = " Application exception  Occured:  ";

	/** The Constant JSON_PARSE_EXCEPTION. */
	public static final String JSON_PARSE_EXCEPTION = " JsonParseException   Occured:  ";

	/** The Constant JSON_MAPPING_EXCEPTION. */
	public static final String JSON_MAPPING_EXCEPTION = " JsonMappingException   Occured:  ";

	/** The Constant IO_EXCEPTION. */
	public static final String IO_EXCEPTION = " IOException   Occured:  ";

	/** The Constant META_INFO_FILE_NAME. */
	public static final String META_INFO_FILE_NAME = "softwaretrxinfo.rxi";

	/** The Constant USB_UNWANTED_FILES. */
	public static final String USB_UNWANTED_FILES = "The USB Stick contains files which are not used by Med Device Connect.\n Please keep only pre-update and post-update files in the USB Stick.";

	/** The Constant MISSING_POST_UPDATE_FILE. */
	public static final String MISSING_POST_UPDATE_FILE = "No Post-update files found in the USB stick to process.Please verify and try again.";

	/** The Constant PROCESS_USB_MSG_TITLE. */
	public static final String PROCESS_USB_MSG_TITLE = "Process USB Stick";

	/** The Constant PROCESS_USB_FAILED_MSG. */
	public static final String PROCESS_USB_FAILED_MSG = "Process post update files failed.";

	/** The Constant PROCESS_USB_FAILED_TITLE. */
	public static final String PROCESS_USB_FAILED_TITLE = "Process USB Failed";

	/** The Constant PRE_UPDATE_FILE_WARNING_LABEL. */
	public static final String PRE_UPDATE_FILE_WARNING_LABEL = "According to the system, you have the pre-update file(s), listed below, that were not assigned to you. Please click the \"Remove\" button.\nYou cannot receive any additional pre-update file(s) until these files are removed from USB.";

	/** The Constant PROCESS_OTHER_USB_WARNING. */
	public static final String PROCESS_OTHER_USB_WARNING = "'s USB Stick.";

	/** The Constant USB_NOT_DETECTED. */
	public static final String USB_NOT_DETECTED = "USB Stick is not detected. Please remove and reconnect the USB Stick and try again.";

	/** The Constant USB_NO_SPACE_TITLE. */
	public static final String USB_NO_SPACE_TITLE = "No enough space in USB Stick";

	/** The Constant USB_NOT_DETECTED_TITLE. */
	public static final String USB_NOT_DETECTED_TITLE = "USB Stick not detected.";

	/** The Constant USB_READ_PROTECT_TITLE. */
	public static final String USB_READ_PROTECT_TITLE = "USB Read Protected";

	/** The Constant USB_WRITE_PROTECT_TITLE. */
	public static final String USB_WRITE_PROTECT_TITLE = "USB Write Protected";

	/** The Constant USB_UNWANTED_FILE_TITLE. */
	public static final String USB_UNWANTED_FILE_TITLE = "Unwanted Files in USB";

	/** The Constant USB_POST_UPDATE_VALIDATE_TITLE. */
	public static final String USB_POST_UPDATE_VALIDATE_TITLE = "Post Update Files in USB Stick.";

	/** The Constant USB_KEY_FILE_CHECKSUM_VALIDATE_TITLE. */
	public static final String USB_KEY_FILE_CHECKSUM_VALIDATE_TITLE = "Failure";

	/** The Constant FAILURE_TITLE. */
	public static final String FAILURE_TITLE = "Failure";

	/** The Constant FAILURE_MESSAGE. */
	public static final String FAILURE_MESSAGE = "Software download failed. Please try again.";

	/** The Constant META_FILE_VERSION_VALIDATE_TITLE. */
	public static final String META_FILE_VERSION_VALIDATE_TITLE = "Version mismatch";

	/** The Constant USB_RECONCILIATION_FAILED_TITLE. */
	public static final String USB_RECONCILIATION_FAILED_TITLE = "USB Reconciliation Failed";

	/** The Constant USB_MULTIPLE_DETECTED_TITLE. */
	public static final String USB_MULTIPLE_DETECTED_TITLE = "Multiple USB Stick";

	/** The Constant USB_MULTIPLE_DETECTED. */
	public static final String USB_MULTIPLE_DETECTED = "More than one USB Stick detected. Please connect only a single valid USB for copying software upgrade files. ";

	/** The Constant USB_WRITE_PROTECT_MSG. */
	public static final String USB_WRITE_PROTECT_MSG = "Files cannot be written to the USB Stick. Please use the JNJ supplied USB Stick and try again.";

	/** The Constant USB_READ_PROTECT_MSG. */
	public static final String USB_READ_PROTECT_MSG = "Files cannot be read from the USB Stick. Please use the JNJ supplied USB Stick and try again.";

	/** The Constant USB_NO_SPACE. */
	public static final String USB_NO_SPACE = "There is no enough space for copying files. Please clear data from USB stick and try again.";

	/** The Constant USB_UNWANTED_FILE_MSG. */
	public static final String USB_UNWANTED_FILE_MSG = "The USB Stick contains files which are not used by Med Device Connect.";

	/** The Constant USB_POST_UPDATE_VALIDATE_MSG. */
	public static final String USB_POST_UPDATE_VALIDATE_MSG = "The USB stick contains post-update files. Please process post-update files first before checking out new assets.";

	/** The Constant USB_KEY_FILE_CHECKSUM_VALIDATE. */
	public static final String USB_KEY_FILE_CHECKSUM_VALIDATE = "Asset key file validation failed after copying to USB";

	/** The Constant META_FILE_USER_VALIDATE_MSG. */
	public static final String META_FILE_USER_VALIDATE_MSG = "The Software upgrade file has been already downloaded by a different user.";

	/** The Constant META_FILE_VERSION_VALIDATE_MSG. */
	public static final String META_FILE_VERSION_VALIDATE_MSG = "The version of Software Upgrade File in the USB Stick does not match with the available version for the selected asset(s). You must process or remove the software on the USB stick before you can download a new version of the software.";

	/** The Constant META_FILE_VERSION_VALIDATE_NEW_MSG. */
	public static final String META_FILE_VERSION_VALIDATE_NEW_MSG = "The version of Software Upgrade File in the USB Stick does not match with the available version for the selected asset(s). The software package for the country has been reassigned. You must remove the software on the USB stick before you can download a new version of the software. Do you want to proceed?";

	/** The Constant FOOTER_MSG. */
	public static final String FOOTER_MSG = "The assets will be checked out until you upload the post update log files. This must be completed \n within 8 weeks or your access to the system will be blocked.";

	/** The Constant HEADER_MSG. */
	public static final String HEADER_MSG = "Software for the requested assets has been copied to the USB Stick.";

	/** The Constant TYPE_XML. */
	public static final String TYPE_XML = ".xml";

	/** The Constant TYPE_BIN. */
	public static final String TYPE_BIN = ".bin";

	/** The Constant TYPE_ZIP. */
	public static final String TYPE_ZIP = ".zip";

	/** The Constant EMPTY_STR. */
	public static final String EMPTY_STR = "";

	/** The Constant SPLIT_DOT_REGEX. */
	public static final String SPLIT_DOT_REGEX = "\\.";

	/** The Constant STANLEY_BIN. */
	public static final String STANLEY_BIN = "stanley.bin";

	/** The Constant META_FILE_NAME. */
	public static final String META_FILE_NAME = "softwaretrxinfo.rxi";

	/** The Constant MD5_ALGORITHM. */
	public static final String MD5_ALGORITHM = "MD5";

	/** The Constant ACCORDIAN_POST_UPDATE_FILE. */
	public static final String ACCORDIAN_POST_UPDATE_FILE = "The Post-Update files table contains the files in the USB Stick which are available for processing. ‘Asset Serial Number’ represents the unique serial number of the GEN11 generator.  ‘Software Name’ represents the name of the software in the USB stick. ‘Model’ represents the model of the generator. ‘Version Number’ represents the version of the software installed in the GEN11 generator. 'Check out Completed On' represents the date & time of the asset check out. ‘Asset Status’ represents the checkout status of the GEN11 generator. 'Process' button against each file helps to process the file from the USB stick.";

	/** The Constant ACCORDIAN_PRE_UPDATE_FILE. */
	public static final String ACCORDIAN_PRE_UPDATE_FILE = "The Pre-Update files table contains the files in the USB Stick which are not assigned to the user. ‘Asset Serial Number’ represents the unique serial number of the GEN11 generator. ‘Software Name’ represents the name of the software in the USB stick. ‘Model’ represents the model of the generator. ‘Version Number’ represents the version of the software installed in the GEN11 generator. ‘Asset Status’ represents the checkout status of the GEN11 generator. 'Remove' button against each file helps to remove the file from the USB stick.";

	/** The Constant ACCORDIAN_PRE_UPDATE_FILE_MSG. */
	public static final String ACCORDIAN_PRE_UPDATE_FILE_MSG = "The table lists the Pre-Update files in the USB stick. ‘Asset Serial Number’ represents the unique serial number of the GEN11 generator. ‘Software Name’ represents the name of the software in the USB stick. ‘Model’ represents the model of the generator. ‘Version Number’ represents the version of the software installed in the GEN11 generator. ‘Asset Status’ represents the checkout status of the GEN11 generator. 'Remove' button against each file helps to remove the file from the USB stick.";

	/** The Constant BUFFER_SIZE. */
	public static final int BUFFER_SIZE = 4096;

	/** The Constant MAX_CHUNK_SIZE. */
	public static final int MAX_CHUNK_SIZE = 1000 * BUFFER_SIZE; // ~12MB

	/** The Constant FILE_NAME_HEADER. */
	public static final String FILE_NAME_HEADER = "Transfer-File-Name";

	/** The Constant CLIENT_ID_HEADER. */
	public static final String CLIENT_ID_HEADER = "Transfer-Client-ID";

	/** The Constant FILE_CHUNK_HEADER. */
	public static final String FILE_CHUNK_HEADER = "Transfer-File-Chunk";

	/** The Constant FILE_CHUNK_COUNT_HEADER. */
	public static final String FILE_CHUNK_COUNT_HEADER = "Transfer-File-Chunk-Count";

	/** The Constant DOWNLOAD_EVENT_LOG_SUCCESS. */
	public static final String DOWNLOAD_EVENT_LOG_SUCCESS = "Requested Event log file(s) have been downloaded to the folder that you have specified.";

	/** The Constant DOWNLOAD_EVENT_LOG_HELP_MSG. */
	public static final String DOWNLOAD_EVENT_LOG_HELP_MSG = "For further information, please contact the Helpdesk. \n\nHelpdesk Number : 1800 2536 2456 \nHelpdesk Email Address : mdcsupport@its.jnj.com \nPlease contact the Helpdesk through email after support hours. ";

	/** The Constant ASSET_SERIAL_NMBR. */
	public static final String ASSET_SERIAL_NMBR = "Asset Serial Number";

	/** The Constant REMOVE_PRE_UPDATE_HEADER. */
	public static final String REMOVE_PRE_UPDATE_HEADER = "Remove Pre-Update File";

	/** The Constant MANAGE_USB_DIALOG. */
	public static final String MANAGE_USB_DIALOG = "ManageUsbDialog";

	/** The Constant ASSET_HASH. */
	public static final String ASSET_HASH = "Asset #";

	/** The Constant REMOVE. */
	public static final String REMOVE = "Remove";

	/** The Constant SHOWING. */
	public static final String SHOWING = "Showing ";

	/** The Constant ENTRIES. */
	public static final String ENTRIES = "entries";

	/** The Constant CREATE_USB_LIST_FUNC. */
	public static final String CREATE_USB_LIST_FUNC = "createUsbListGrid";

	/** The Constant INITIALIZE_TABLE_GRID_FUNC. */
	public static final String INITIALIZE_TABLE_GRID_FUNC = "initializeTableViewGrid";

	/** The Constant TABLE_HEADER_STYLE. */
	public static final String TABLE_HEADER_STYLE = "tableheader";

	/** The Constant CODE_424. */
	public static final String CODE_424 = "CODE 424";

	/** The Constant HTTP_ERROR. */
	public static final String HTTP_ERROR = "400-failure";

	/** The Constant ACC_NAME. */
	public static final String ACC_NAME = "Account Name";

	/** The Constant AUTHORIZATION. */
	public static final String AUTHORIZATION = "Authorization";

	/** The Constant BEARER. */
	public static final String BEARER = "Bearer ";

	/** The Constant CURRENT_VERSION. */
	public static final String CURRENT_VERSION = "Current Version *";

	/** The Constant CUSTOM_BUTTON. */
	public static final String CUSTOM_BUTTON = "custom-button";

	/** The Constant PREVIOUS_VERSION. */
	public static final String PREVIOUS_VERSION = "Previous Version *";

	/** The Constant LOG_FILE_DETAILS. */
	public static final String LOG_FILE_DETAILS = "LogFileDetailsDialog:";

	/** The Constant APPLET_PROP. */
	public static final String APPLET_PROP = "applet.properties";

	/** The Constant STATE_FLD_MAX_LENGTH. */
	public static final int STATE_FLD_MAX_LENGTH = 30;

	/** The Constant CITY_FLD_MAX_LENGTH. */
	public static final int CITY_FLD_MAX_LENGTH = 25;

	/** The Constant STREET_FLD_MAX_LENGTH. */
	public static final int STREET_FLD_MAX_LENGTH = 70;

	/** The Constant ZIP_FLD_MAX_LENGTH. */
	public static final int ZIP_FLD_MAX_LENGTH = 16;

	/** The Constant COMMENTS_FLD_MAX_LENGTH. */
	public static final int COMMENTS_FLD_MAX_LENGTH = 512;

	/** The Constant STRING_ARRAY_SIZE_FOUR. */
	public static final int STRING_ARRAY_SIZE_FOUR = 4;

	/** The Constant SCENE_WIDTH_THOUSANDTWOHUNDRED. */
	public static final int SCENE_WIDTH_THOUSANDTWOHUNDRED = 1200;

	/** The Constant SCENE_HEIGHT_SEVENHUNDRED. */
	public static final int SCENE_HEIGHT_SEVENHUNDRED = 700;

	/** The Constant STAGE_WIDTH_FOURHUNDRED. */
	public static final int STAGE_WIDTH_FOURHUNDRED = 400;

	/** The Constant STAGE_HEIGHT_FOURHUNDRED. */
	public static final int STAGE_HEIGHT_FOURHUNDRED = 400;

	/** The Constant REQARGS_LENGTH_TWO. */
	public static final int REQARGS_LENGTH_TWO = 2;

	/** The Constant BYTE_SIZE. */
	public static final int BYTE_SIZE = 1024;

	/** The Constant USB_LENGTH_THOUSANDTWENTYFOUR. */
	public static final int USB_LENGTH_THOUSANDTWENTYFOUR = 1024;

	/** The Constant HGAP_GRIDPANE_TEN. */
	public static final int HGAP_GRIDPANE_TEN = 10;

	/** The Constant VGAP_GRIDPANE_TWELVE. */
	public static final int VGAP_GRIDPANE_TWELVE = 12;

	/** The Constant VGAP_GRIDPANE_TEN. */
	public static final int VGAP_GRIDPANE_TEN = 10;

	/** The Constant INSETS_TOP_TWENTYFIVE. */
	public static final int INSETS_TOP_TWENTYFIVE = 25;

	/** The Constant INSETS_RIGHT_TWENTYFIVE. */
	public static final int INSETS_RIGHT_TWENTYFIVE = 25;

	/** The Constant INSETS_BOTTOM_TWENTYFIVE. */
	public static final int INSETS_BOTTOM_TWENTYFIVE = 25;

	/** The Constant INSETS_LEFT_TWENTYFIVE. */
	public static final int INSETS_LEFT_TWENTYFIVE = 25;

	/** The Constant SECOND_ROW_INDEX. */
	public static final int SECOND_ROW_INDEX = 2;

	/** The Constant THIRD_ROW_INDEX. */
	public static final int THIRD_ROW_INDEX = 3;

	/** The Constant DIALOG_VBOX_HEIGHT_FOURHUNDRED. */
	public static final int DIALOG_VBOX_HEIGHT_FOURHUNDRED = 400;

	/** The Constant DIALOG_VBOX_WIDTH_FOURHUNDRED. */
	public static final int DIALOG_VBOX_WIDTH_FOURHUNDRED = 400;

	/** The Constant RESPONSE_CODE_TWOHUNDRED. */
	public static final int RESPONSE_CODE_TWOHUNDRED = 200;

	/** The Constant DOWNLOAD_EVENT_THREADPOOL_SIZE. */
	public static final int DOWNLOAD_EVENT_THREADPOOL_SIZE = 5;

	/** The Constant INT_TEN. */
	public static final int INT_TEN = 10;

	/** The Constant INT_ONEFIFTY. */
	public static final int INT_ONEFIFTY = 150;

	/** The Constant INT_TWENTY. */
	public static final int INT_TWENTY = 20;

	/** The Constant INT_SIXTEEN. */
	public static final int INT_SIXTEEN = 16;

	/** The Constant INT_TWO. */
	public static final int INT_TWO = 2;

	/** The Constant DOUBLE_TWO. */
	public static final double DOUBLE_TWO = 2.0;

	/** The Constant INT_SIXTY. */
	public static final int INT_SIXTY = 60;

	/** The Constant INT_HUNDRED. */
	public static final int INT_HUNDRED = 100;

	/** The Constant INT_FIVE. */
	public static final int INT_FIVE = 5;

	/** The Constant INT_ZERO. */
	public static final int INT_ZERO = 0;

	/** The Constant INT_THREE. */
	public static final int INT_THREE = 3;

	/** The Constant DOUBLE_THREE. */
	public static final double DOUBLE_THREE = 3;

	/** The Constant DOUBLE_TEN. */
	public static final double DOUBLE_TEN = 10.0;

	/** The Constant INT_SIXHUNDRED. */
	public static final int INT_SIXHUNDRED = 600;

	/** The Constant INT_FIVEHUNDRED. */
	public static final int INT_FIVEHUNDRED = 500;

	/** The Constant INT_THIRTY. */
	public static final int INT_THIRTY = 30;

	/** The Constant DOUBLE_ZEROFOUR. */
	public static final double DOUBLE_ZEROFOUR = 0.4;

	/** The Constant DOUBLE_ZEROSIX. */
	public static final double DOUBLE_ZEROSIX = 0.6;

	/** The Constant DOUBLE_ZEROEIGHT. */
	public static final double DOUBLE_ZEROEIGHT = 0.8;

	/** The Constant DOUBLE_ZERONINE. */
	public static final double DOUBLE_ZERONINE = 0.09;

	/** The Constant DOUBLE_ZEROFIVE. */
	public static final double DOUBLE_ZEROFIVE = 0.5;

	/** The Constant DOUBLE_FIVEZERO. */
	public static final double DOUBLE_FIVEZERO = 5.0d;

	/** The Constant DOUBLE_ZEROFIFTEEN. */
	public static final double DOUBLE_ZEROFIFTEEN = 0.15;

	/** The Constant DOUBLE_ZEROSEVENTEEN. */
	public static final double DOUBLE_ZEROSEVENTEEN = 0.17;

	/** The Constant DOUBLE_ZEROTHIRTEEN. */
	public static final double DOUBLE_ZEROTHIRTEEN = 0.13;

	/** The Constant DOUBLE_ZEROTWENTY. */
	public static final double DOUBLE_ZEROTWENTY = 0.20;

	/** The Constant DOUBLE_ZEROTWELVE. */
	public static final double DOUBLE_ZEROTEN = 0.10;

	/** The Constant INT_TWENTYFIVE. */
	public static final int INT_TWENTYFIVE = 25;

	/** The Constant INT_THOUSANDTWOHUNDRED. */
	public static final int INT_THOUSANDTWOHUNDRED = 1200;

	/** The Constant DOUBLE_ONETHREE. */
	public static final double DOUBLE_ONETHREE = 1.3;

	/** The Constant INT_SEVENHUNDRED. */
	public static final int INT_SEVENHUNDRED = 700;

	/** The Constant INT_THREEHUNDRED. */
	public static final int INT_THREEHUNDRED = 300;

	/** The Constant INT_FOURHUNDRED. */
	public static final int INT_FOURHUNDRED = 400;

	/** The Constant INT_FIFTEEN. */
	public static final int INT_FIFTEEN = 15;

	/** The Constant INT_FOUR. */
	public static final int INT_FOUR = 4;

	/** The Constant INT_SIX. */
	public static final int INT_SIX = 6;

	/** The Constant INT_SEVEN. */
	public static final int INT_SEVEN = 7;

	/** The Constant INT_EIGHT. */
	public static final int INT_EIGHT = 8;

	/** The Constant INT_TWENTYFOUR. */
	public static final int INT_TWENTYFOUR = 24;

	/** The Constant INT_NINE. */
	public static final int INT_NINE = 9;

	/** The Constant INT_TWELVE. */
	public static final int INT_TWELVE = 12;

	/** The Constant INT_THOUSAND. */
	public static final int INT_THOUSAND = 1000;

	/** The Constant INT_EIGHTHUNDRED. */
	public static final int INT_EIGHTHUNDRED = 800;

	/** The Constant INT_THOUSANDTHREEHUNDRED. */
	public static final int INT_THOUSANDTHREEHUNDRED = 1300;

	/** The Constant INT_FORTY. */
	public static final int INT_FORTY = 40;

	/** The Constant INT_NINETYFOUR. */
	public static final int INT_NINETYFOUR = 94;

	/** The Constant INT_ONETHIRTYFIVE. */
	public static final int INT_ONETHIRTYFIVE = 135;

	/** The Constant INT_ONESEVENTEEN. */
	public static final int INT_ONESEVENTEEN = 117;

	/** The Constant INT_SEVENTY. */
	public static final int INT_SEVENTY = 70;

	/** The Constant INT_TWOHUNDRED. */
	public static final int INT_TWOHUNDRED = 200;

	/** The Constant INT_EIGTHHUNDRED. */
	public static final int INT_EIGTHHUNDRED = 800;

	/** The Constant INT_TWOFIFTY. */
	public static final int INT_TWOFIFTY = 250;

	/**
	 * Instantiates a new USB constant.
	 */
	private USBConstant() {

	}

}
