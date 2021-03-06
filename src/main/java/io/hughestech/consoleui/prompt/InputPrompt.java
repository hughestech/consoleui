package io.hughestech.consoleui.prompt;

import static org.fusesource.jansi.Ansi.ansi;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

import org.apache.commons.lang3.StringUtils;
import org.fusesource.jansi.Ansi.Color;

import io.hughestech.consoleui.elements.InputValue;
import io.hughestech.consoleui.exception.InvalidInputException;
import io.hughestech.consoleui.prompt.answer.Answer;
import io.hughestech.consoleui.prompt.answer.InputAnswer;
import io.hughestech.consoleui.prompt.reader.ConsoleReaderImpl;
import io.hughestech.consoleui.prompt.reader.ReaderIF;
import io.hughestech.consoleui.prompt.renderer.CUIRenderer;
import jline.console.completer.Completer;

/**
 * Implementation of the input choice prompt. The user will be asked for a
 * string input value. With support of completers an automatic expansion of
 * strings and filenames can be configured. Defining a mask character, a
 * password like input is possible.
 * <p>
 * User: Andreas Wegmann
 * <p>
 * Date: 06.01.16
 */
public class InputPrompt extends AbstractPrompt implements PromptIF<InputValue, InputAnswer> {

	private InputValue inputElement;
	private ReaderIF reader;
	CUIRenderer itemRenderer = CUIRenderer.getRenderer();
	private boolean invalidInput = false;
	ReaderIF.ReaderInput readerInput;
	private String lineInput;
	Character mask;
	List<Completer> completer;
	private String prompt;
	private String message;

	public InputPrompt() throws IOException {
	}

	public InputAnswer prompt(InputValue inputElement, HashMap<String, Answer> answers) throws IOException {
		this.inputElement = inputElement;
		this.message = this.inputElement.getFnMessage() != null ? this.inputElement.getFnMessage().apply(answers)
				: this.inputElement.getMessage();

		/*
		 * if (reader == null) { reader = new ConsoleReaderImpl(); }
		 */

		if (renderHeight == 0) {
			renderHeight = 1;
		} else {
			System.out.println(ansi().cursorUp(renderHeight));
		}

		this.completer = inputElement.getCompleter();
		this.mask = inputElement.getMask();

		do {
			prompt();
			read(inputElement, prompt, completer, mask);
			validateInput(inputElement, lineInput, readerInput);
		} while (this.invalidInput);

		String result;
		if (mask == null) {
			result = lineInput;
		} else {
			result = "";
			if (lineInput != null) {
				for (int i = 0; i < lineInput.length(); i++) {
					result += mask;
				}
			}
		}

		renderMessagePromptAndResult(this.message, result);

		return new InputAnswer(lineInput);
	}

	private void prompt() {
		String optionalDefaultValue = itemRenderer.renderOptionalDefaultValue(this.inputElement);
		this.prompt = renderMessagePrompt(this.message, invalidInput) + optionalDefaultValue;
	}

	private void read(InputValue inputElement, String prompt, List<Completer> completer, Character mask) throws IOException {
		this.reader = new ConsoleReaderImpl();
		this.readerInput = reader.readLine(completer, prompt, inputElement.getValue(), mask);
		this.lineInput = readerInput.getLineInput();
		setupDefaultValue(inputElement);
	}

	private void setupDefaultValue(InputValue inputElement) {
		if (lineInput == null || lineInput.trim().length() == 0) {
			lineInput = inputElement.getDefaultValue();
		}
	}

	private void validateInput(InputValue inputElement, String lineInput, ReaderIF.ReaderInput readerInput) throws IOException {
		Consumer<String> validator = inputElement.getValidator();
		invalidInput = false;
		if (validator != null) {
			try {
				validator.accept(lineInput);
			} catch (InvalidInputException e) {
				invalidInput = true;
				System.out.print(ansi().cursorDown(renderHeight).fg(Color.RED).a(">> ").reset().a(e.getMessage()));
				System.out.println(ansi().cursorUp(renderHeight).eraseLine());
			}
		}
		if (inputElement.getRequired() != null && StringUtils.isEmpty(lineInput)) {
			invalidInput = true;
			System.out.print(ansi().cursorDown(renderHeight).fg(Color.RED).a(">> ").reset().a("Preenchimento obrigatório"));
			System.out.println(ansi().cursorUp(renderHeight).eraseLine());
		}
	}
}
