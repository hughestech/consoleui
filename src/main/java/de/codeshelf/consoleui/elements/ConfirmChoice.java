package de.codeshelf.consoleui.elements;

import java.util.Map;
import java.util.function.Function;

import de.codeshelf.consoleui.prompt.answer.Answer;

/**
 * User: Andreas Wegmann Date: 07.01.16
 */
public class ConfirmChoice extends AbstractPromptableElement {

	public enum ConfirmationValue {
		YES, NO
	}

	private ConfirmationValue defaultConfirmation = null;

	private Function<Map<String, Answer>, String> fnMessage;

	public ConfirmChoice(String message, String name) {
		super(message, name);
	}

	public ConfirmChoice(String message, String name, ConfirmationValue defaultConfirmation) {
		super(message, name);
		this.defaultConfirmation = defaultConfirmation;
	}

	public ConfirmationValue getDefaultConfirmation() {
		return defaultConfirmation;
	}

	public Function<Map<String, Answer>, String> getFnMessage() {
		return fnMessage;
	}

	public void setFnMessage(Function<Map<String, Answer>, String> fnMessage) {
		this.fnMessage = fnMessage;
	}

}
