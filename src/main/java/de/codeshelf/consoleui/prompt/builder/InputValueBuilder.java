package de.codeshelf.consoleui.prompt.builder;

import java.util.ArrayList;
import java.util.Map;
import java.util.function.Function;

import de.codeshelf.consoleui.elements.InputValue;
import de.codeshelf.consoleui.prompt.Answer;
import de.codeshelf.consoleui.util.Validator;
import jline.console.completer.Completer;

/**
 * Created by andy on 22.01.16.
 */
public class InputValueBuilder {
	private final PromptBuilder promptBuilder;
	private String name;
	private String defaultValue;
	private String message;
	private Character mask;
	private ArrayList<Completer> completers;
	private Validator validator;
	private Function<Map<String, Answer>, String> fnMessage;

	public InputValueBuilder(PromptBuilder promptBuilder) {
		this.promptBuilder = promptBuilder;
	}

	public InputValueBuilder name(String name) {
		this.name = name;
		return this;
	}

	public InputValueBuilder defaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
		return this;
	}

	public InputValueBuilder message(String message) {
		this.message = message;
		return this;
	}

	public InputValueBuilder message(Function<Map<String, Answer>, String> message) {
		this.fnMessage = message;
		return this;
	}

	public InputValueBuilder addCompleter(Completer completer) {
		if (completers == null) {
			completers = new ArrayList<Completer>();
		}
		this.completers.add(completer);
		return this;
	}

	public InputValueBuilder mask(char mask) {
		this.mask = mask;
		return this;
	}

	public InputValueBuilder validate(Validator validator) {
		this.validator = validator;
		return this;
	}

	public PromptBuilder addPrompt() {
		InputValue inputValue = new InputValue(name, message, null, defaultValue);
		if (fnMessage != null) {
			inputValue.setFnMessage(fnMessage);
		}
		if (completers != null) {
			inputValue.setCompleter(completers);
		}
		if (mask != null) {
			inputValue.setMask(mask);
		}
		if (validator != null) {
			inputValue.setValidator(validator);
		}
		promptBuilder.addPrompt(inputValue);
		return promptBuilder;
	}

}
