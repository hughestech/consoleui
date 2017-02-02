package de.codeshelf.consoleui.prompt;

import java.util.HashSet;

/**
 * Result of a an expandable choice. ExpandableChoiceResult contains a String
 * with the IDs of the selected item.
 * <p>
 * User: Andreas Wegmann
 * <p>
 * Date: 03.02.16
 */
public class ExpandableChoiceAnswer implements Answer {
	String selectedId;

	/**
	 * Default constructor.
	 *
	 * @param selectedId
	 *            the selected id
	 */
	public ExpandableChoiceAnswer(String selectedId) {
		this.selectedId = selectedId;
	}

	/**
	 * Returns the selected id.
	 *
	 * @return selected id.
	 */
	public String getSelectedId() {
		return selectedId;
	}

	@Override
	public String getValue() {
		return this.selectedId;
	}

	@Override
	public HashSet<String> getList() {
		return null;
	}

	@Override
	public String toString() {
		return "ExpandableChoiceResult{" + "selectedId='" + selectedId + '\'' + '}';
	}

}
