package de.codeshelf.consoleui.elements;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;

import de.codeshelf.consoleui.elements.items.CheckboxItemIF;
import de.codeshelf.consoleui.prompt.Answer;

/**
 * User: Andreas Wegmann Date: 01.01.16
 */
public class Checkbox extends AbstractPromptableElement {

	private List<CheckboxItemIF> checkboxItemList;
	private Function<Map<String, Answer>, Set<String>> fnChoices;

	public Checkbox(String message, String name, List<CheckboxItemIF> checkboxItemList) {
		super(message, name);
		this.checkboxItemList = checkboxItemList;
	}

	public String getMessage() {
		return message;
	}

	public Function<Map<String, Answer>, Set<String>> getFnChoices() {
		return fnChoices;
	}

	public void setFnChoices(Function<Map<String, Answer>, Set<String>> fnChoices) {
		this.fnChoices = fnChoices;
	}

	public ArrayList<CheckboxItemIF> getCheckboxItemList() {
		return new ArrayList<CheckboxItemIF>(checkboxItemList);
	}
}
