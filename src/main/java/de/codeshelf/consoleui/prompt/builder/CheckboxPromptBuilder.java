package de.codeshelf.consoleui.prompt.builder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import de.codeshelf.consoleui.elements.Checkbox;
import de.codeshelf.consoleui.elements.items.CheckboxItemIF;
import de.codeshelf.consoleui.elements.items.impl.CheckboxItem;

/**
 * Created by andy on 22.01.16.
 */
public class CheckboxPromptBuilder {
	private final PromptBuilder promptBuilder;
	private String name;
	private String message;
	private List<CheckboxItemIF> itemList;

	public CheckboxPromptBuilder(PromptBuilder promptBuilder) {
		this.promptBuilder = promptBuilder;
		itemList = new ArrayList<CheckboxItemIF>();
	}

	void addItem(CheckboxItemIF checkboxItem) {
		itemList.add(checkboxItem);
	}

	public CheckboxPromptBuilder name(String name) {
		this.name = name;
		if (message == null) {
			message = name;
		}
		return this;
	}

	public CheckboxPromptBuilder message(String message) {
		this.message = message;
		if (name == null) {
			name = message;
		}
		return this;
	}

	public CheckboxItemBuilder newItem() {
		return new CheckboxItemBuilder(this);
	}

	public CheckboxItemBuilder newItem(String name) {
		CheckboxItemBuilder checkboxItemBuilder = new CheckboxItemBuilder(this);
		return checkboxItemBuilder.name(name);
	}
	
	public CheckboxPromptBuilder choices(String...choices) {
	//// @formatter:off
			Arrays.asList(choices).stream()
				.filter(StringUtils::isNotBlank)
				.forEach(choice -> itemList.add(new CheckboxItem(choice)));
			// @formatter:on
			return this;
	}

	public PromptBuilder build() {
		Checkbox checkbox = new Checkbox(message, name, itemList);
		promptBuilder.addPrompt(checkbox);
		return promptBuilder;
	}

	public CheckboxSeperatorBuilder newSeparator() {
		return new CheckboxSeperatorBuilder(this);
	}

	public CheckboxSeperatorBuilder newSeparator(String text) {
		CheckboxSeperatorBuilder checkboxSeperatorBuilder = new CheckboxSeperatorBuilder(this);
		return checkboxSeperatorBuilder.text(text);
	}

}
