package io.hughestech.consoleui.prompt.builder;

import java.util.ArrayList;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;

import io.hughestech.consoleui.ReadEventAnnotation;
import io.hughestech.consoleui.elements.InputValue;
import io.hughestech.consoleui.prompt.answer.Answer;
import jline.console.completer.Completer;
import jline.console.completer.StringsCompleter;

/**
 * Created by andy on 22.01.16.
 */
public class InputPromptBuilder {
	private final PromptBuilder promptBuilder;
	private String name;
	private String defaultValue;
	private String message;
	private Character mask;
	private ArrayList<Completer> completers;
	private Consumer<String> validator;
	private Function<Map<String, Answer>, String> fnMessage;
	private Boolean required;
	private Function<Map<String, Answer>, Boolean> fnWhen;

	public InputPromptBuilder(PromptBuilder promptBuilder, String name) {
		this.promptBuilder = promptBuilder;
		this.name = name;
	}

	public InputPromptBuilder defaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
		return this;
	}

	public InputPromptBuilder message(String message) {
		this.message = message;
		return this;
	}

	public InputPromptBuilder message(Function<Map<String, Answer>, String> message) {
		this.fnMessage = message;
		return this;
	}

	public InputPromptBuilder when(Function<Map<String, Answer>, Boolean> when) {
		this.fnWhen = when;
		return this;
	}

	@ReadEventAnnotation
	public InputPromptBuilder choices(String... completer) {
		if (completers == null) {
			completers = new ArrayList<Completer>();
		}
		this.completers.add(new StringsCompleter(completer));
		return this;
	}

	public InputPromptBuilder mask(char mask) {
		this.mask = mask;
		return this;
	}

	@ReadEventAnnotation
	public InputPromptBuilder required() {
		this.required = true;
		return this;
	}

	@ReadEventAnnotation
	public InputPromptBuilder validate(Consumer<String> validator) {
		this.validator = validator;
		return this;
	}

	@ReadEventAnnotation
	public PromptBuilder build() {
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
		if (required != null) {
			inputValue.setRequired(required);
		}
		if (fnWhen != null) {
			inputValue.setFnWhen(fnWhen);
		}
		promptBuilder.addPrompt(inputValue);
		return promptBuilder;
	}

}
