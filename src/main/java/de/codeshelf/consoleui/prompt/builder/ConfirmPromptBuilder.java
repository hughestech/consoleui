package de.codeshelf.consoleui.prompt.builder;

import de.codeshelf.consoleui.elements.ConfirmChoice;

/**
 * User: Andreas Wegmann Date: 24.01.16
 */
public class ConfirmPromptBuilder {
	private final PromptBuilder promptBuilder;
	private String name;
	private String message;
	private ConfirmChoice.ConfirmationValue defaultConfirmationValue;

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

	public ConfirmPromptBuilder defaultValue(ConfirmChoice.ConfirmationValue confirmationValue) {
		this.defaultConfirmationValue = confirmationValue;
		return this;
	}

	public PromptBuilder build() {
		promptBuilder.addPrompt(new ConfirmChoice(message, name, defaultConfirmationValue));
		return promptBuilder;
	}
}
