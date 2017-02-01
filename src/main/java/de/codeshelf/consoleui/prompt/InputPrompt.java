package de.codeshelf.consoleui.prompt;

import static org.fusesource.jansi.Ansi.ansi;

import java.io.IOException;
import java.util.List;

import org.fusesource.jansi.Ansi.Color;

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
public class InputPrompt extends AbstractPrompt implements PromptIF<InputValue, InputResult> {

	private InputValue inputElement;
	private ReaderIF reader;
	CUIRenderer itemRenderer = CUIRenderer.getRenderer();
	private boolean invalidInput = false;

	public InputPrompt() throws IOException {
	}

	public InputResult prompt(InputValue inputElement) throws IOException {
		this.inputElement = inputElement;

		if (reader == null) {
			reader = new ConsoleReaderImpl();
		}

		if (renderHeight == 0) {
			renderHeight = 1;
		} else {
			System.out.println(ansi().cursorUp(renderHeight));
		}
		
		String prompt = renderMessagePrompt(this.inputElement.getMessage(), invalidInput)
				+ itemRenderer.renderOptionalDefaultValue(this.inputElement);
		List<Completer> completer = inputElement.getCompleter();
		Character mask = inputElement.getMask();
		ReaderIF.ReaderInput readerInput = reader.readLine(completer, prompt, inputElement.getValue(), mask);
		String lineInput = readerInput.getLineInput();

		if (lineInput == null || lineInput.trim().length() == 0) {
			lineInput = inputElement.getDefaultValue();
		}
		
		/*
		if (validateInput(inputElement, lineInput, readerInput)) {
			this.reader = null;
			prompt(inputElement);
		}*/
		

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

		return new InputResult(lineInput);
	}

	private boolean validateInput(InputValue inputElement, String lineInput, ReaderIF.ReaderInput readerInput) throws IOException {
		Validator validator = inputElement.getValidator();
		invalidInput = false;
		if (validator != null) {
			Object validationResult = validator.test(lineInput);
			if (validationResult instanceof Boolean) {
				boolean booleanValidationResult = Boolean.valueOf(validationResult.toString());
				if (!booleanValidationResult) {
					invalidInput = true;
				}
			} else if (validationResult instanceof String) {
				String stringValidationResult = (String) validationResult;
				invalidInput = true;
				System.out.print(ansi().cursorDown(renderHeight).fg(Color.RED).a(">> ").reset().a(stringValidationResult));
			}
		}
		return invalidInput;
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
