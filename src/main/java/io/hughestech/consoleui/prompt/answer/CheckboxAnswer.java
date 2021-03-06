package io.hughestech.consoleui.prompt.answer;

import java.util.HashSet;

import io.hughestech.consoleui.elements.ConfirmChoice.ConfirmationValue;

/**
 * Result of a checkbox choice. CheckboxResult contains a {@link java.util.Set}
 * with the IDs of the selected checkbox items.
 * <p>
 * User: Andreas Wegmann Date: 03.02.16
 */
public class CheckboxAnswer implements Answer {
	HashSet<String> selectedIds;

	/**
	 * Default Constructor.
	 * 
	 * @param selectedIds
	 *            Selected IDs.
	 */
	public CheckboxAnswer(HashSet<String> selectedIds) {
		this.selectedIds = selectedIds;
	}

	/**
	 * Returns the set with the IDs of selected checkbox items.
	 *
	 * @return set with IDs
	 */
	public HashSet<String> getSelectedIds() {
		return selectedIds;
	}

	@Override
	public String value() {
		return null;
	}

	@Override
	public HashSet<String> list() {
		return this.selectedIds;
	}

	@Override
	public ConfirmationValue confirmation() {
		return null;
	}

	@Override
	public String toString() {
		return "CheckboxResult{" + "selectedIds=" + selectedIds + '}';
	}

}
