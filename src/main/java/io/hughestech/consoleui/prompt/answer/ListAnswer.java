package io.hughestech.consoleui.prompt.answer;

import java.util.HashSet;

import io.hughestech.consoleui.elements.ConfirmChoice.ConfirmationValue;

/**
 * Result of a list choice. Holds the id of the selected item.
 * <p>
 * Created by Andreas Wegmann on 03.02.16.
 */
public class ListAnswer implements Answer {

	String selectedId;

	/**
	 * Returns the ID of the selected item.
	 *
	 * @return id of selected item
	 */
	public String getSelectedId() {
		return selectedId;
	}

	/**
	 * Default constructor.
	 *
	 * @param selectedId
	 *            id of selected item.
	 */
	public ListAnswer(String selectedId) {
		this.selectedId = selectedId;
	}

	@Override
	public String value() {
		return this.selectedId;
	}

	@Override
	public HashSet<String> list() {
		return null;
	}

	@Override
	public ConfirmationValue confirmation() {
		return null;
	}

	@Override
	public String toString() {
		return "ListResult{" + "selectedId='" + selectedId + '\'' + '}';
	}

}
