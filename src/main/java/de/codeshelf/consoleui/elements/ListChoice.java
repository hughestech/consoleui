package de.codeshelf.consoleui.elements;

import de.codeshelf.consoleui.elements.items.ConsoleUIItemIF;
import de.codeshelf.consoleui.elements.items.ListItemIF;
import de.codeshelf.consoleui.prompt.answer.Answer;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;

/**
 * User: Andreas Wegmann Date: 04.01.16
 */
public class ListChoice extends AbstractPromptableElement {

	private List<ListItemIF> listItemList;

	Function<Map<String, Answer>, Set<String>> fnChoices;

	public ListChoice(String message, String name, List<ListItemIF> listItemList) {
		super(message, name);
		this.listItemList = listItemList;
	}

	public Function<Map<String, Answer>, Set<String>> getFnChoices() {
		return fnChoices;
	}

	public void setFnChoices(Function<Map<String, Answer>, Set<String>> fnChoices) {
		this.fnChoices = fnChoices;
	}

	public String getMessage() {
		return message;
	}

	public ArrayList<ConsoleUIItemIF> getListItemList() {
		return new ArrayList<ConsoleUIItemIF>(listItemList);
	}
}
