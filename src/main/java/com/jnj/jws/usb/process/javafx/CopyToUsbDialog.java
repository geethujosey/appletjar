/*
 * Javafx Package
 */
package com.jnj.jws.usb.process.javafx;

import static com.jnj.jws.USBConstant.DOUBLE_ONETHREE;
import static com.jnj.jws.USBConstant.DOUBLE_TEN;
import static com.jnj.jws.USBConstant.DOUBLE_TWO;
import static com.jnj.jws.USBConstant.DOUBLE_ZEROFOUR;
import static com.jnj.jws.USBConstant.DOUBLE_ZEROSIX;
import static com.jnj.jws.USBConstant.FOOTER_MSG;
import static com.jnj.jws.USBConstant.HEADER_MSG;
import static com.jnj.jws.USBConstant.HGAP_GRIDPANE_TEN;
import static com.jnj.jws.USBConstant.INSETS_BOTTOM_TWENTYFIVE;
import static com.jnj.jws.USBConstant.INSETS_LEFT_TWENTYFIVE;
import static com.jnj.jws.USBConstant.INSETS_RIGHT_TWENTYFIVE;
import static com.jnj.jws.USBConstant.INSETS_TOP_TWENTYFIVE;
import static com.jnj.jws.USBConstant.INT_FIVE;
import static com.jnj.jws.USBConstant.INT_FIVEHUNDRED;
import static com.jnj.jws.USBConstant.INT_SIXHUNDRED;
import static com.jnj.jws.USBConstant.INT_TEN;
import static com.jnj.jws.USBConstant.INT_THIRTY;
import static com.jnj.jws.USBConstant.INT_THREE;
import static com.jnj.jws.USBConstant.INT_TWO;
import static com.jnj.jws.USBConstant.INT_ZERO;
import static com.jnj.jws.USBConstant.SCENE_HEIGHT_SEVENHUNDRED;
import static com.jnj.jws.USBConstant.SCENE_WIDTH_THOUSANDTWOHUNDRED;
import static com.jnj.jws.USBConstant.STAGE_HEIGHT_FOURHUNDRED;
import static com.jnj.jws.USBConstant.STAGE_WIDTH_FOURHUNDRED;
import static com.jnj.jws.USBConstant.VGAP_GRIDPANE_TEN;
import static com.jnj.jws.USBConstant.VGAP_GRIDPANE_TWELVE;

import java.util.Collections;
import java.util.List;

import com.jnj.jws.util.JavaWebStartLogger;
import com.jnj.jws.vo.CopyToUSBJWSRespVo;
import com.jnj.jws.vo.CopytoUsbTableVo;
import com.jnj.jws.vo.UserHeaderVO;

import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 * The Class CopyToUsbDialog.
 * @author Cognizant
 * @version 1.0
 * @serial 1.0
 * @since 1.0
 */
public class CopyToUsbDialog {

	/** The css. */
	private String css = null;

	/** The ok button. */
	private Button okButton = new Button("OK");

	/** The jwslogger. */
	private JavaWebStartLogger jwslogger = new JavaWebStartLogger();

	/** The data. */
	private final ObservableList<CopytoUsbTableVo> data = FXCollections
			.observableArrayList();

	/** The dialog. */
	private Stage dialog;

	/** The usb warn lable. */
	private Label usbWarnLable = new Label();

	/** The Constant USB_UNWANTED_FILE_MSG. */
	private static final String USB_UNWANTED_FILE_MSG = "USB drive contains unwanted files.";

	/** The rows per page. */
	private int copyPerPage = INT_FIVE;

	/** The pagination. */
	private Pagination copyToUsbPagination = new Pagination();

	/** The page upper limit. */
	private int pageUpperLimit;

	/** The page lower limit. */
	private int pageLowerLimit = 1;

	/** The table page info. */
	private Label tablePageInfo = new Label();

	/**
	 * Creates the success dialog.
	 *
	 * @param stage the stage
	 * @param ctousb the ctousb
	 * @param userheadervo the userheadervo
	 */
	public void createSuccessDialog(final Stage stage, CopyToUSBJWSRespVo ctousb,
			UserHeaderVO userheadervo) {
		jwslogger.printLogger(CopyToUsbDialog.class.getSimpleName(),
				"createSuccessDialog", "userheadervo : " + userheadervo.getFirstName());
		/**
		 * Creating Hbox
		 */
		final HBox hb = new HBox();
		final VBox vbox = new VBox();
		/**
		 * Creating Table view
		 */
		final TableView<CopytoUsbTableVo> resulttable = new TableView<>();
		usbWarnLable
				.setText("USB drive contain unwanted files\nplease remove the unwanted files.");
		usbWarnLable.getStyleClass().add("warningRed");
		usbWarnLable.setPrefWidth(SCENE_WIDTH_THOUSANDTWOHUNDRED);
		dialog = initializeDialogue(stage);
		css = this.getClass().getClassLoader().getResource("stylesheet.css")
				.toExternalForm();
		/**
		 * Initialising Header Label
		 */
		final Label headerLabel = new Label(HEADER_MSG);
		/**
		 * Initialising Footer Label
		 */
		final Label footerLabel = new Label(FOOTER_MSG);

		// add columns to table.
		addColumnsToResultTable(resulttable);

		hb.setSpacing(INT_THREE);
		hb.setAlignment(Pos.CENTER);
		/**
		 * Initializing Grid pane
		 */
		GridPane grid = new GridPane();
		grid.setHgap(HGAP_GRIDPANE_TEN);
		grid.setVgap(VGAP_GRIDPANE_TWELVE);
		hb.getChildren().addAll(okButton);

		List<String> assetSrNrList = ctousb.getAssetSerNumList();
		String availableVersion = ctousb.getAvailableVersion();
		jwslogger.printLogger(CopyToUsbDialog.class.getSimpleName(), "",
				"createSuccessDialog:assetSrNrList length" + assetSrNrList);
		/**
		 * Populating value object to Grid
		 */
		populateResultValueToGrid(assetSrNrList, availableVersion);

		describePaginationSize(resulttable);
		// initialize page info
		AnchorPane pageinfo = initializePageinfoAnchorPane();

		AnchorPane anchor = initializeAnchorPane();

		vbox.setSpacing(INT_FIVE);
		vbox.setPadding(new Insets(INT_TEN, 0, 0, INT_TEN));
		if (ctousb.isWarning()
				&& ctousb.getWarningMessage().equals(USB_UNWANTED_FILE_MSG)) {
			vbox.getChildren().addAll(headerLabel, usbWarnLable, resulttable, pageinfo,
					anchor, footerLabel, hb);
		}
		else {
			vbox.getChildren().addAll(headerLabel, resulttable, pageinfo, anchor,
					footerLabel, hb);
		}
		vbox.setMaxSize(INT_SIXHUNDRED, INT_SIXHUNDRED);
		Scene scene = new Scene(vbox, SCENE_WIDTH_THOUSANDTWOHUNDRED,
				SCENE_HEIGHT_SEVENHUNDRED);
		scene.getStylesheets().add(css);
		dialog.setScene(scene);
		dialog.setHeight(INT_FIVEHUNDRED);
		dialog.setWidth(INT_SIXHUNDRED);
		dialog.show();
		okButton.setOnAction(new EventHandler<ActionEvent>() {

			public void handle(ActionEvent t) {
				dialog.hide();
				stage.hide();
			}
		});

	}

	private AnchorPane initializePageinfoAnchorPane() {
		AnchorPane pageinfo = new AnchorPane();
		AnchorPane.setTopAnchor(tablePageInfo, DOUBLE_TEN);
		AnchorPane.setRightAnchor(tablePageInfo, DOUBLE_TEN);
		AnchorPane.setBottomAnchor(tablePageInfo, DOUBLE_TEN);
		AnchorPane.setLeftAnchor(tablePageInfo, 0.0);
		pageinfo.getChildren().addAll(tablePageInfo);
		return pageinfo;
	}

	private AnchorPane initializeAnchorPane() {
		AnchorPane anchor = new AnchorPane();
		AnchorPane.setTopAnchor(copyToUsbPagination, DOUBLE_TEN);
		AnchorPane.setRightAnchor(copyToUsbPagination, DOUBLE_TEN);
		AnchorPane.setBottomAnchor(copyToUsbPagination, DOUBLE_TEN);
		AnchorPane.setLeftAnchor(copyToUsbPagination, DOUBLE_TEN);
		anchor.getChildren().addAll(copyToUsbPagination);
		return anchor;
	}

	/**
	 * Populating value object to Grid
	 */
	private void populateResultValueToGrid(List<String> assetSrNrList,
			String availableVersion) {
		Collections.sort(assetSrNrList);
		for (String assetSrNr : assetSrNrList) {
			jwslogger.printLogger(CopyToUsbDialog.class.getSimpleName(), "",
					"createSuccessDialog:assetSrNrList " + assetSrNr + "availableVersion"
							+ availableVersion);
			CopytoUsbTableVo vo = new CopytoUsbTableVo();
			vo.setAssetSerialNmbr(assetSrNr);
			vo.setAvailableVersion(availableVersion);
			data.add(vo);
		}
	}

	/**
	 * @param stage
	 * @return
	 */
	private Stage initializeDialogue(final Stage stage) {
		dialog = new Stage();
		dialog.setTitle("Requested Assets");
		dialog.initModality(Modality.APPLICATION_MODAL);
		dialog.initOwner(stage);
		dialog.setResizable(false);
		return dialog;
	}

	/**
	 * @param resulttable
	 */
	private void addColumnsToResultTable(final TableView<CopytoUsbTableVo> resulttable) {
		/**
		 * Creating Table Column for asset serial number
		 */
		TableColumn assetCol = new TableColumn("Asset Serial Number");
		assetCol.setCellValueFactory(new PropertyValueFactory<CopytoUsbTableVo, String>(
				"assetSerialNmbr"));
		assetCol.setSortType(TableColumn.SortType.ASCENDING);
		resulttable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		/**
		 * Creating Table column for Available software
		 */
		TableColumn aswCol = new TableColumn("Available Software");
		aswCol.setCellValueFactory(new PropertyValueFactory<CopytoUsbTableVo, String>(
				"availableVersion"));
		resulttable.getColumns().addAll(assetCol, aswCol);
	}

	/**
	 * Describe pagination size.
	 *
	 * @param resulttable the resulttable
	 */
	private void describePaginationSize(TableView<CopytoUsbTableVo> resulttable) {
		if (data.size() >= copyPerPage) {
			copyPerPage = INT_FIVE;
		}
		else {
			copyPerPage = data.size();
		}
		// TO determine number of pages
		int pageSize;
		if ((data.size() % copyPerPage) != 0) {
			pageSize = (data.size() / copyPerPage) + 1;
		}
		else {
			pageSize = data.size() / copyPerPage;
		}

		setPagination(pageSize, resulttable);
	}

	/**
	 * Creates the page.
	 *
	 * @param pageIndex the page index
	 * @param data2 the data 2
	 * @param usbtable2 the usbtable 2
	 * @return the v box
	 */
	private VBox createPage(int pageIndex, ObservableList<CopytoUsbTableVo> data2,
			TableView<CopytoUsbTableVo> usbtable2) {
		jwslogger.printLogger(CopyToUsbDialog.class.getSimpleName(), "",
				"Inside Create Page Function ");
		int fromIndex = pageIndex * copyPerPage;
		int toIndex = Math.min(fromIndex + copyPerPage, data2.size());
		usbtable2.setItems(FXCollections.observableArrayList(data2.subList(fromIndex,
				toIndex)));

		// Changes For Table Row Fit(Removing Empty Rows) ----START
		ObservableList list = FXCollections.observableArrayList(data2.subList(fromIndex,
				toIndex));
		jwslogger.printLogger(CopyToUsbDialog.class.getSimpleName(), "",
				" Inside Pagination Data Size =  " + list.size() + "  Page Index "
						+ pageIndex);

		usbtable2.setFixedCellSize(INT_THIRTY);
		usbtable2.getSortOrder().add(usbtable2.getColumns().get(INT_ZERO));
		float addingSpace;

		if (list.size() > INT_THREE) {
			addingSpace = (float) ((list.size() / DOUBLE_TWO) - DOUBLE_ZEROFOUR);
		}
		else if (list.size() == INT_TWO) {
			addingSpace = (float) (list.size() - DOUBLE_ZEROFOUR);
		}
		else if (list.size() == 1) {
			addingSpace = (float) (list.size() + DOUBLE_ZEROSIX);
		}
		else {
			addingSpace = (float) (list.size() - DOUBLE_ONETHREE);
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
	 * Creates the error dialog.
	 *
	 * @param stage the stage
	 * @param title the title
	 * @param msg the msg
	 * @param userheadervo the userheadervo
	 */
	public void createErrorDialog(final Stage stage, String title, String msg,
			UserHeaderVO userheadervo) {
		jwslogger.printLogger(CopyToUsbDialog.class.getSimpleName(), "createErrorDialog",
				"userheadervo : " + userheadervo.getFirstName());
		dialog = new Stage();
		dialog.setTitle(title);
		dialog.initModality(Modality.APPLICATION_MODAL);
		dialog.initOwner(stage);
		css = this.getClass().getClassLoader().getResource("stylesheet.css")
				.toExternalForm();
		VBox dialogVbox = new VBox();
		dialogVbox.setMaxSize(STAGE_WIDTH_FOURHUNDRED, STAGE_HEIGHT_FOURHUNDRED);
		GridPane gridpane = getGridPane(msg, userheadervo);
		gridpane.add(okButton, 1, INT_THREE);
		dialogVbox.getChildren().addAll(gridpane);
		Scene dialogScene = new Scene(dialogVbox, STAGE_WIDTH_FOURHUNDRED,
				STAGE_HEIGHT_FOURHUNDRED);
		dialogScene.getStylesheets().add(css);
		dialog.setScene(dialogScene);
		dialog.setHeight(STAGE_HEIGHT_FOURHUNDRED);
		dialog.setWidth(STAGE_WIDTH_FOURHUNDRED);
		dialog.show();

		okButton.setOnAction(new EventHandler<ActionEvent>() {

			public void handle(ActionEvent t) {
				dialog.hide();
				stage.hide();
			}
		});

	}

	/**
	 * Sets the pagination.
	 *
	 * @param pageSize the page size
	 * @param resulttable the resulttable
	 */
	private void setPagination(int pageSize, TableView<CopytoUsbTableVo> resulttable) {
		copyToUsbPagination = new Pagination(pageSize, 0);
		copyToUsbPagination.setPageFactory(new Callback<Integer, Node>() {
			public Node call(Integer pageIndex) {
				if (pageIndex > data.size() / copyPerPage) {
					return null;
				}
				else {
					pageLowerLimit = 1 + ((pageIndex) * copyPerPage);
					if ((copyPerPage + ((pageIndex) * copyPerPage)) > data.size()) {
						pageUpperLimit = data.size();
					}
					else {
						pageUpperLimit = copyPerPage + ((pageIndex) * copyPerPage);
					}
					tablePageInfo.setText("Showing " + pageLowerLimit + " to "
							+ pageUpperLimit + " of " + data.size());
					return createPage(pageIndex, data, resulttable);
				}
			}
		});
	}

	/**
	 * 
	 * @param msg
	 * @param userheadervo
	 * @return
	 */
	public GridPane getGridPane(String msg, UserHeaderVO userheadervo) {
		final ImageView imv = new ImageView();
		final Image image2 = new Image(this.getClass().getClassLoader()
				.getResourceAsStream("info.png"));
		imv.setImage(image2);
		GridPane gridpane = new GridPane();
		gridpane.setAlignment(Pos.CENTER);
		gridpane.setHgap(HGAP_GRIDPANE_TEN);
		gridpane.setVgap(VGAP_GRIDPANE_TEN);
		gridpane.setPadding(new Insets(INSETS_TOP_TWENTYFIVE, INSETS_RIGHT_TWENTYFIVE,
				INSETS_BOTTOM_TWENTYFIVE, INSETS_LEFT_TWENTYFIVE));
		if (userheadervo.getFullName() == null) {
			userheadervo.setFullName("");
		}
		Label username = new Label("Dear " + userheadervo.getFirstName() + ",");
		Label msgLbl = new Label(msg);
		msgLbl.setWrapText(true);

		gridpane.add(imv, 0, 0);
		gridpane.add(username, 1, 0);
		gridpane.add(msgLbl, 1, 1);
		return gridpane;
	}

}
