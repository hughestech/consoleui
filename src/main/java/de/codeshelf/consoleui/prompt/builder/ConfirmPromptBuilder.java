package de.codeshelf.consoleui.prompt.builder;

import java.util.Map;
import java.util.function.Function;

import de.codeshelf.consoleui.elements.ConfirmChoice;
import de.codeshelf.consoleui.prompt.answer.Answer;

/**
 * User: Andreas Wegmann Date: 24.01.16
 */
public class ConfirmPromptBuilder {
	private final PromptBuilder promptBuilder;
	private String name;
	private String message;
	private ConfirmChoice.ConfirmationValue defaultConfirmationValue;
	private Function<Map<String, Answer>, String> fnMessage;

	public ConfirmPromptBuilder(PromptBuilder promptBuilder, String name) {
		this.promptBuilder = promptBuilder;
		this.name = name;
	}

	public ConfirmPromptBuilder message(String message) {
		this.message = message;
		if (name == null) {
			name = message;
		}
		return this;
	}

	public ConfirmPromptBuilder message(Function<Map<String, Answer>, String> fnMessage) {
		this.fnMessage = fnMessage;
		if (name == null) {
			name = message;
		}
		return this;
	}

	public ConfirmPromptBuilder defaultValue(ConfirmChoice.ConfirmationValue confirmationValue) {
		this.defaultConfirmationValue = confirmationValue;
		return this;
	}

	public PromptBuilder build() {
		ConfirmChoice confirmChoice = new ConfirmChoice(message, name, defaultConfirmationValue);
		if (fnMessage != null) {
			confirmChoice.setFnMessage(fnMessage);
		}
		promptBuilder.addPrompt(confirmChoice);
		return promptBuilder;
	}
}
