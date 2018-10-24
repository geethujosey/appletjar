/*
 * Process package
 * 
 */
package com.jnj.jws.usb.process;

import static com.jnj.jws.USBConstant.DOWNLOAD_EVENT_LOG_HELP_MSG;
import static com.jnj.jws.USBConstant.DOWNLOAD_EVENT_LOG_SUCCESS;
import static com.jnj.jws.USBConstant.DOWNLOAD_EVENT_THREADPOOL_SIZE;
import static com.jnj.jws.USBConstant.EVENTLOG_FILE_REFID_URL;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.AccessController;
import java.security.PrivilegedActionException;
import java.security.PrivilegedExceptionAction;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.apache.commons.io.IOUtils;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jnj.jws.download.event.AssetEventFileProcessor;
import com.jnj.jws.util.JavaWebStartLogger;
import com.jnj.jws.vo.AssetEventLogVO;
import com.jnj.jws.vo.EventDownloadFailureListVO;
import com.jnj.jws.vo.EventDownloadSuccessListVO;
import com.jnj.jws.vo.EventLogDownloadResVO;

/**
 * Class to handle JWS download event log to get file reference id and downloading file.
 *
 * @author 555786
 * @since 1.0
 * @version 1.0
 * @serial 1.0
 */
public class DownloadEvent {

	/** The jwslogger. */
	private JavaWebStartLogger jwslogger = new JavaWebStartLogger();

	/** The Constant FAILED_MSG. */
	private static final String FAILED_MSG = "Failed to download.. Please try again";

	/** The Constant FN_DWNLD_LOG. */
	private static final String FN_DWNLD_LOG = "downLoadEventLogFile";

	/** The final response. */
	private String finalResponse;

	/** The properties. */
	private Properties properties = null;

	/** The thread pool. */
	private ExecutorService threadPool = Executors
			.newFixedThreadPool(DOWNLOAD_EVENT_THREADPOOL_SIZE);

	/**
	 * method to invoke file reference id and download event log.
	 *
	 * @param searchjsonstring the searchjsonstring
	 * @param jwttoken the jwttoken
	 * @return the string
	 */
	public String downloadFile(final String searchjsonstring, String jwttoken) {
		try {
			jwslogger.printLogger(DownloadEvent.class.getSimpleName(),
					"downloadFile:searchjsonstring", searchjsonstring + " jwttoken"
							+ jwttoken);
			AccessController
					.doPrivileged((PrivilegedExceptionAction<String>) () -> downloadEventLog(
							searchjsonstring, jwttoken));

		}
		/**
		 * If PrivilegedActionException caught
		 */
		catch (PrivilegedActionException e) {
			jwslogger.printLogger(DownloadEvent.class.getSimpleName(), "downloadFile",
					" PrivilegedActionException while download file " + e);
		}
		/**
		 * If Any Exception caught
		 */
		catch (Exception e) {
			jwslogger.printLogger(DownloadEvent.class.getSimpleName(), "downloadFile",
					" Exception while download file " + e);
		}
		return finalResponse;
	}

	/**
	 * Download event log.
	 *
	 * @param searchjsonstring the searchjsonstring
	 * @param jwttoken the jwttoken
	 * @return the string
	 */
	String downloadEventLog(String searchjsonstring, String jwttoken) {
		try {
			List<AssetEventLogVO> assetLogList = getFileReferenceId(searchjsonstring,
					jwttoken);
			finalResponse = downLoadEventLogFile(assetLogList, properties, jwttoken);
		}
		/**
		 * If JsonParseException or JsonMappingException
		 * 
		 */
		catch (JsonParseException | JsonMappingException e) {
			jwslogger.printLogger(DownloadEvent.class.getSimpleName(), FN_DWNLD_LOG,
					" JsonParseException|JsonMappingException while downloading file "
							+ e);
			finalResponse = FAILED_MSG;
		}
		/**
		 * If IOException
		 */
		catch (IOException e) {
			jwslogger.printLogger(DownloadEvent.class.getSimpleName(), "FN_DWNLD_LOG",
					" IOException while downloading  file " + e);
			finalResponse = FAILED_MSG;
		}
		return finalResponse;

	}

	/**
	 * Gets the file reference id.
	 *
	 * @param searchVo the search vo
	 * @param jwttoken the jwttoken
	 * @return the file reference id
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	private List<AssetEventLogVO> getFileReferenceId(String searchVo, String jwttoken)
			throws IOException {
		properties = new Properties();
		InputStream input = getClass().getClassLoader().getResourceAsStream(
				"applet.properties");
		DataOutputStream out = null;
		HttpURLConnection conn = null;

		properties.load(input);
		jwslogger.printLogger(DownloadEvent.class.getSimpleName(),
				"getFileReferenceId:file Ref URL:",
				properties.getProperty(EVENTLOG_FILE_REFID_URL));
		URL url = new URL(properties.getProperty(EVENTLOG_FILE_REFID_URL));
		conn = (HttpURLConnection) url.openConnection();
		conn.setDoOutput(true);
		conn.setDoInput(true);
		conn.setRequestMethod("POST");
		conn.setRequestProperty("Content-Type", "application/json");
		conn.setRequestProperty("Authorization", "Bearer " + jwttoken);
		conn.setDoOutput(true);
		out = new DataOutputStream(conn.getOutputStream());
		out.writeBytes(searchVo);
		try (BufferedReader in = new BufferedReader(new InputStreamReader(
				conn.getInputStream(), StandardCharsets.UTF_8))) {

			String inputLine;
			StringBuilder response = new StringBuilder();
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			ObjectMapper mapper = new ObjectMapper();
			List<AssetEventLogVO> objList = mapper.readValue(response.toString(),
					new TypeReference<List<AssetEventLogVO>>() {
					});

			jwslogger.printLogger(DownloadEvent.class.getSimpleName(),
					"getFileReferenceId", "Completed getting File Reference id ....");
			return objList;
		}
		finally {
			IOUtils.closeQuietly(out);
		}
	}

	/**
	 * Down load event log file.
	 *
	 * @param assetEventLogList the asset event log list
	 * @param properties the properties
	 * @param jwttoken the jwttoken
	 * @return the string
	 */
	private String downLoadEventLogFile(List<AssetEventLogVO> assetEventLogList,
			Properties properties, String jwttoken) {
		jwslogger.printLogger(DownloadEvent.class.getSimpleName(), FN_DWNLD_LOG,
				"Started downLoadEventLogFile() ....");
		boolean isThreadpool = false;
		List<EventDownloadFailureListVO> failureList = new ArrayList<>();
		List<EventDownloadSuccessListVO> successlist = new ArrayList<>();
		int totalfilecount = 0;
		StringBuilder response = new StringBuilder();
		EventLogDownloadResVO responseVO = new EventLogDownloadResVO();
		try {
			List<Future<String>> resultList = new ArrayList<>();
			if (!assetEventLogList.isEmpty()) {
				totalfilecount = assetEventLogList.size();
				jwslogger.printLogger(DownloadEvent.class.getSimpleName(), FN_DWNLD_LOG,
						"fileRefIds  not empty() ...." + assetEventLogList.size());
				isThreadpool = true;
				for (AssetEventLogVO aelvo : assetEventLogList) {
					jwslogger.printLogger(
							DownloadEvent.class.getSimpleName(),
							FN_DWNLD_LOG,
							"Going to submit downLoadEventLogFile() to Thread ....for file reference id"
									+ aelvo.getFileRefId() + "Folder Path:"
									+ aelvo.getFolderPath());
					Future<String> fileContent = threadPool
							.submit(new AssetEventFileProcessor(aelvo, properties,
									jwttoken));
					resultList.add(fileContent);
				}

			}
			else {
				jwslogger.printLogger(DownloadEvent.class.getSimpleName(), FN_DWNLD_LOG,
						"fileRefIds empty() ....");
				response.append("Event log file(s) not found for the selected criteria.");
				return response.toString();
			}
			processTaskList(resultList, failureList, successlist);
			return getfinalResponse(responseVO, failureList, successlist, totalfilecount);
		}
		catch (Exception ee) {
			jwslogger.printLogger(DownloadEvent.class.getSimpleName(), FN_DWNLD_LOG,
					"Error occured Thread ExecutionException while downloading file "
							+ ee);
			response.append("Failed to download file.Please try again.");
			return response.toString();
		}
		finally {
			if (isThreadpool) {
				threadPool.shutdown();
			}
		}

	}

	/**
	 * Gets the final response.
	 *
	 * @param responseVO the response VO
	 * @param failureList the failure list
	 * @param successlist the successlist
	 * @param totalfilecount the totalfilecount
	 * @return the final response
	 */
	String getfinalResponse(EventLogDownloadResVO responseVO,
			List<EventDownloadFailureListVO> failureList,
			List<EventDownloadSuccessListVO> successlist, int totalfilecount) {
		StringBuilder response = new StringBuilder();
		if (!failureList.isEmpty()) {
			/**
			 * Setting failure list
			 */
			responseVO.setFailureVOList(failureList);
			jwslogger.printLogger(DownloadEvent.class.getSimpleName(), FN_DWNLD_LOG,
					"Download Event log Failure list:" + failureList.size());
		}
		if (!successlist.isEmpty()) {
			/**
			 * Setting Success list
			 */
			responseVO.setSuccessVOList(successlist);
			jwslogger.printLogger(DownloadEvent.class.getSimpleName(), FN_DWNLD_LOG,
					"Download Event log success list:" + successlist.size());
		}
		response.append("       You have downloaded ")
				.append(retrieveTotalDownloadedFileCount(successlist))
				.append(" of ")
				.append(retrieveTotalFileCount(successlist, totalfilecount))
				.append(" available event log file(s) to the folder that you have specified.");
		if (!failureList.isEmpty()) {
			response.append(DOWNLOAD_EVENT_LOG_HELP_MSG);
		}
		jwslogger.printLogger(DownloadEvent.class.getSimpleName(), FN_DWNLD_LOG,
				"finalResponse for download event log" + response.toString());
		return response.toString();
	}

	private Object retrieveTotalFileCount(List<EventDownloadSuccessListVO> successList,
			int totalZipFileCount) {
		Integer totalCount = 0;
		for (EventDownloadSuccessListVO successVO : successList) {
			totalCount += Integer.parseInt(successVO.getCountOfTotalFilesInZip());
		}
		if (totalCount == 0) {
			totalCount = totalZipFileCount;
		}
		return totalCount.toString();
	}

	private String retrieveTotalDownloadedFileCount(
			List<EventDownloadSuccessListVO> successList) {
		Integer totalCount = 0;
		for (EventDownloadSuccessListVO successVO : successList) {
			totalCount += Integer.parseInt(successVO.getCountOfDownloadedFiles());
		}
		return totalCount.toString();
	}

	/**
	 * Process task list.
	 *
	 * @param resultList the result list
	 * @param failureList the failure list
	 * @param successlist the successlist
	 */
	void processTaskList(List<Future<String>> resultList,
			List<EventDownloadFailureListVO> failureList,
			List<EventDownloadSuccessListVO> successlist) {
		EventDownloadFailureListVO failureVO = new EventDownloadFailureListVO();
		EventDownloadSuccessListVO successVO = new EventDownloadSuccessListVO();
		int processedcount = 0;
		try {
			for (Future<String> future : resultList) {
				processedcount++;
				jwslogger.printLogger(DownloadEvent.class.getSimpleName(), FN_DWNLD_LOG,
						"Future result is - " + " - " + future.get()
								+ "; And Task done is " + future.isDone());
				String ft;
				if (future.get() == null) {
					ft = "0";
				}
				else {
					ft = future.get();
				}
				jwslogger.printLogger(DownloadEvent.class.getSimpleName(), FN_DWNLD_LOG,
						"[Total Files:" + resultList.size() + "] [Processed Files:"
								+ processedcount + "]");
				jwslogger.printLogger(
						DownloadEvent.class.getSimpleName(),
						FN_DWNLD_LOG,
						"ft" + " - " + ft + "; And Task cancelled "
								+ future.isCancelled());
				String[] result = ft.split("-");
				if ("failure".equals(result[1])) {
					failureVO = new EventDownloadFailureListVO();
					failureVO.setFailureRefId(result[0]);
					failureVO.setFailureDesc("File content not found");
					failureList.add(failureVO);
					jwslogger.printLogger(DownloadEvent.class.getSimpleName(),
							FN_DWNLD_LOG, "file content not found : " + result[0]);
				}
				else {
					successVO = new EventDownloadSuccessListVO();
					successVO.setFileRefId(result[0]);
					successVO.setDescription(DOWNLOAD_EVENT_LOG_SUCCESS);
					successVO.setCountOfTotalFilesInZip(result[2]);
					successVO.setCountOfDownloadedFiles(result[3]);
					successlist.add(successVO);
					jwslogger.printLogger(DownloadEvent.class.getSimpleName(),
							FN_DWNLD_LOG, "file content found : " + result[0]);
				}
			}
		}

		/**
		 * if Exception caught
		 */

		catch (Exception ee) {
			jwslogger.printLogger(DownloadEvent.class.getSimpleName(), FN_DWNLD_LOG,
					"Error occured Thread ExecutionException while downloading file "
							+ ee);
			finalResponse = "Failed to download file.Please try again.";
		}
	}

}
