/*
 * 
 */
package com.jnj.jws.customui.operations;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.control.ComboBox;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

/**
 * This class used for auto complete operation for dropdown search in JWS.
 *
 * @author 555786
 * @param <T> the generic type
 */
public class AutoCompleteComboBoxListener<T> implements EventHandler<KeyEvent> {

	/** The combo box. */
	private ComboBox comboBox;

	/** The data. */
	private ObservableList<T> data;

	/** The move caret to pos. */
	private boolean moveCaretToPos = false;

	/** The caret pos. */
	private int caretPos;

	/**
	 * initialize combobox.
	 *
	 * @param comboBox the combo box
	 */
	public AutoCompleteComboBoxListener(final ComboBox comboBox) {
		this.comboBox = comboBox;
		data = comboBox.getItems();

		this.comboBox.setEditable(true);
		this.comboBox.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent t) {
				comboBox.hide();
			}
		});
		this.comboBox.setOnKeyReleased(AutoCompleteComboBoxListener.this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javafx.event.EventHandler#handle(javafx.event.Event)
	 */
	@Override
	public void handle(KeyEvent event) {

		if (event.getCode() == KeyCode.UP) {
			caretPos = -1;
			moveCaret(comboBox.getEditor().getText().length());
			return;
		}
		else if (event.getCode() == KeyCode.DOWN) {
			if (!comboBox.isShowing()) {
				comboBox.show();
			}
			caretPos = -1;
			moveCaret(comboBox.getEditor().getText().length());
			return;
		}
		else if (event.getCode() == KeyCode.BACK_SPACE
				|| event.getCode() == KeyCode.DELETE) {
			moveCaretToPos = true;
			caretPos = comboBox.getEditor().getCaretPosition();
		}

		condition1(event);
		create();
	}

	/**
	 * Creates the.
	 */
	void create() {
		ObservableList list = FXCollections.observableArrayList();
		for (int i = 0; i < data.size(); i++) {
			if (data.get(i)
					.toString()
					.toLowerCase()
					.startsWith(
							AutoCompleteComboBoxListener.this.comboBox.getEditor()
									.getText().toLowerCase())) {
				list.add(data.get(i));
			}
		}
		String t = comboBox.getEditor().getText();

		comboBox.setItems(list);
		comboBox.getEditor().setText(t);
		if (!moveCaretToPos) {
			caretPos = -1;
		}
		moveCaret(t.length());
		if (!list.isEmpty()) {
			comboBox.show();
		}
	}

	/**
	 * Move caret.
	 *
	 * @param textLength the text length
	 */
	private void moveCaret(int textLength) {
		if (caretPos == -1) {
			comboBox.getEditor().positionCaret(textLength);
		}
		else {
			comboBox.getEditor().positionCaret(caretPos);
		}
		moveCaretToPos = false;
	}

	/**
	 * Condition 1.
	 *
	 * @param event the event
	 */
	private void condition1(KeyEvent event) {
		if (event.getCode() == KeyCode.RIGHT || event.getCode() == KeyCode.LEFT
				|| event.getCode() == KeyCode.TAB) {
			return;
		}
		if (event.isControlDown() || event.getCode() == KeyCode.HOME
				|| event.getCode() == KeyCode.END) {
			return;
		}
	}

}