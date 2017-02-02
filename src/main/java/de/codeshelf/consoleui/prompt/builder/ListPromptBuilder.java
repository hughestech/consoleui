package de.codeshelf.consoleui.prompt.builder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;

import org.apache.commons.lang3.StringUtils;

import de.codeshelf.consoleui.elements.ListChoice;
import de.codeshelf.consoleui.elements.items.ListItemIF;
import de.codeshelf.consoleui.elements.items.impl.ListItem;
import de.codeshelf.consoleui.prompt.answer.Answer;

/**
 * Created by andy on 22.01.16.
 */
public class ListPromptBuilder {
	private final PromptBuilder promptBuilder;
	private String name;
	private String message;
	private List<ListItemIF> itemList = new ArrayList<ListItemIF>();
	private Function<Map<String, Answer>, String> fnMessage;
	private Function<Map<String, Answer>, Set<String>> fnChoices;

	public ListPromptBuilder(PromptBuilder promptBuilder) {
		this.promptBuilder = promptBuilder;
	}

	public ListPromptBuilder name(String name) {
		this.name = name;
		if (message != null) {
			this.message = name;
		}
		return this;
	}

	public ListPromptBuilder message(String message) {
		this.message = message;
		if (name == null) {
			name = message;
		}
		return this;
	}

	public ListPromptBuilder message(Function<Map<String, Answer>, String> fnMessage) {
		this.fnMessage = fnMessage;
		if (name == null) {
			name = message;
		}
		return this;
	}

	public ListPromptBuilder choices(String... choices) {
		//// @formatter:off
		Arrays.asList(choices).stream()
			.filter(StringUtils::isNotBlank)
			.forEach(choice -> itemList.add(new ListItem(choice)));
		// @formatter:on
		return this;
	}

	public ListPromptBuilder choices(Function<Map<String, Answer>, Set<String>> fnChoices) {
		this.fnChoices = fnChoices;
		return this;
	}

	public PromptBuilder build() {
		ListChoice listChoice = new ListChoice(message, name, itemList);
		if (fnMessage != null) {
			listChoice.setFnMessage(fnMessage);
		}
		if (fnChoices != null) {
			listChoice.setFnChoices(fnChoices);
		}
		promptBuilder.addPrompt(listChoice);
		return promptBuilder;
	}

	void addItem(ListItem listItem) {
		this.itemList.add(listItem);
	}

}
