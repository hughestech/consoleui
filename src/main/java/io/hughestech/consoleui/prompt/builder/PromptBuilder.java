package io.hughestech.consoleui.prompt.builder;

import java.util.ArrayList;
import java.util.List;

import io.hughestech.consoleui.elements.*;

/**
 * PromptBuilder is the builder class which creates
 *
 * Created by Andreas Wegmann on 20.01.16.
 */
public class PromptBuilder {
	List<PromptableElementIF> promptList = new ArrayList<PromptableElementIF>();

	public List<PromptableElementIF> build() {
		return promptList;
	}

	public void addPrompt(PromptableElementIF promptableElement) {
		promptList.add(promptableElement);
	}

	public InputPromptBuilder inputPrompt(String name) {
		return new InputPromptBuilder(this, name);
	}

	public ListPromptBuilder listPrompt(String name) {
		return new ListPromptBuilder(this, name);
	}
	
	/*
	public ExpandableChoicePromptBuilder createChoicePrompt() {
		return new ExpandableChoicePromptBuilder(this);
	}*/

	public CheckboxPromptBuilder checkboxPrompt(String name) {
		return new CheckboxPromptBuilder(this, name);
	}

	public ConfirmPromptBuilder confirmPrompt(String name) {
		return new ConfirmPromptBuilder(this, name);
	}
}
