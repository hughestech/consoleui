package io.hughestech.consoleui.elements;

import java.util.Map;
import java.util.function.Function;

import io.hughestech.consoleui.prompt.answer.Answer;

/**
 * User: Andreas Wegmann Date: 06.01.16
 */
public class AbstractPromptableElement implements PromptableElementIF {

	protected String message;
	protected String name;
	protected Function<Map<String, Answer>, String> fnMessage;

	public AbstractPromptableElement(String message, String name) {
		this.message = message;
		this.name = name;
	}

	public AbstractPromptableElement(Function<Map<String, Answer>, String> fnMessage, String name) {
		super();
		this.name = name;
		this.fnMessage = fnMessage;
	}

	public String getMessage() {
		return message;
	}

	public String getName() {
		return name;
	}

	public Function<Map<String, Answer>, String> getFnMessage() {
		return fnMessage;
	}

	public void setFnMessage(Function<Map<String, Answer>, String> fnMessage) {
		this.fnMessage = fnMessage;
	}

}
