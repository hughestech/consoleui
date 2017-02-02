package de.codeshelf.consoleui.prompt;

import static org.fusesource.jansi.Ansi.ansi;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.swing.plaf.InputMapUIResource;

import org.fusesource.jansi.Ansi.Color;
import org.fusesource.jansi.Ansi.Erase;

import de.codeshelf.consoleui.elements.InputValue;
import de.codeshelf.consoleui.prompt.reader.ConsoleReaderImpl;
import de.codeshelf.consoleui.prompt.reader.ReaderIF;
import de.codeshelf.consoleui.prompt.reader.ReaderIF.SpecialKey;
import de.codeshelf.consoleui.prompt.renderer.CUIRenderer;
import de.codeshelf.consoleui.util.Validator;
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

	public InputPrompt() throws IOException {
	}

	public InputAnswer prompt(InputValue inputElement, HashMap<String, Answer> answers) throws IOException {
		this.inputElement = inputElement;

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

		renderMessagePromptAndResult(inputElement.getMessage(), result);

		return new InputAnswer(lineInput);
	}

	private void prompt() {
		String optionalDefaultValue = itemRenderer.renderOptionalDefaultValue(this.inputElement);
		this.prompt = renderMessagePrompt(this.inputElement.getMessage(), invalidInput) + optionalDefaultValue;
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
		Validator validator = inputElement.getValidator();
		if (validator != null) {
			invalidInput = false;
			Object validationResult = validator.test(lineInput);
			if (validationResult instanceof Boolean) {
				boolean booleanValidationResult = Boolean.valueOf(validationResult.toString());
				if (!booleanValidationResult) {
					invalidInput = true;
					System.out.println(ansi().cursorUp(renderHeight));
				}
			} else if (validationResult instanceof String) {
				String stringValidationResult = (String) validationResult;
				invalidInput = true;
				System.out.print(ansi().cursorDown(renderHeight).fg(Color.RED).a(">> ").reset().a(stringValidationResult));
				System.out.println(ansi().cursorUp(renderHeight).eraseLine());
			}
		}
	}

	private void sleep() {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
