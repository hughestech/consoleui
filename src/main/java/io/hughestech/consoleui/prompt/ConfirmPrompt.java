package io.hughestech.consoleui.prompt;

import java.io.IOException;
import java.util.HashMap;

import io.hughestech.consoleui.elements.ConfirmChoice;
import io.hughestech.consoleui.prompt.answer.Answer;
import io.hughestech.consoleui.prompt.answer.ConfirmAnswer;
import io.hughestech.consoleui.prompt.reader.ConsoleReaderImpl;
import io.hughestech.consoleui.prompt.reader.ReaderIF;
import io.hughestech.consoleui.prompt.renderer.CUIRenderer;

import static org.fusesource.jansi.Ansi.ansi;

/**
 * Implementation of the confirm choice. The user will be asked for a yes/no
 * questions. both of the answers can be the default choice.
 * <p>
 * User: Andreas Wegmann Date: 06.01.16
 */
public class ConfirmPrompt extends AbstractPrompt implements PromptIF<ConfirmChoice, ConfirmAnswer> {

	private ReaderIF reader;
	CUIRenderer itemRenderer = CUIRenderer.getRenderer();
	private ConfirmChoice confirmChoice;
	char yes_key;
	char no_key;
	String yes_answer;
	String no_answer;
	ConfirmChoice.ConfirmationValue givenAnswer;
	private String message;

	/**
	 * Default Constructor. Initializes the localized strings and keys from
	 * resourceBundle.
	 *
	 * @throws IOException
	 *             can be thrown by base class construction.
	 */
	public ConfirmPrompt() throws IOException {
		super();
		yes_key = resourceBundle.getString("confirmation_yes_key").trim().charAt(0);
		no_key = resourceBundle.getString("confirmation_no_key").trim().charAt(0);
		yes_answer = resourceBundle.getString("confirmation_yes_answer");
		no_answer = resourceBundle.getString("confirmation_no_answer");
	}

	/**
	 * Prompt the user for a question which can be answered with yes or no.
	 *
	 * @param confirmChoice
	 *            the question for the user.
	 * @return {@link ConfirmAnswer} object with answer.
	 * @throws IOException
	 *             can be thrown by the console reader.
	 */
	public ConfirmAnswer prompt(ConfirmChoice confirmChoice, HashMap<String, Answer> answers) throws IOException {
		givenAnswer = null;
		this.confirmChoice = confirmChoice;

		this.message = this.confirmChoice.getFnMessage() != null ? this.confirmChoice.getFnMessage().apply(answers)
				: this.confirmChoice.getMessage();

		if (reader == null) {
			reader = new ConsoleReaderImpl();
		}

		if (renderHeight == 0) {
			renderHeight = 1;
		} else {
			System.out.println(ansi().cursorUp(renderHeight));
		}

		this.reader.addAllowedPrintableKey(no_key);
		this.reader.addAllowedPrintableKey(yes_key);
		this.reader.addAllowedSpecialKey(ReaderIF.SpecialKey.ENTER);
		this.reader.addAllowedSpecialKey(ReaderIF.SpecialKey.BACKSPACE);

		render();
		ReaderIF.ReaderInput readerInput = this.reader.read();
		while (true) {
			if (readerInput.getSpecialKey() == ReaderIF.SpecialKey.ENTER) {
				if (givenAnswer != null) {
					break;
				} else if (confirmChoice.getDefaultConfirmation() != null) {
					givenAnswer = confirmChoice.getDefaultConfirmation();
					break;
				}
			}
			if (readerInput.getSpecialKey() == ReaderIF.SpecialKey.PRINTABLE_KEY) {
				if (readerInput.getPrintableKey().equals(yes_key)) {
					givenAnswer = ConfirmChoice.ConfirmationValue.YES;
				} else if (readerInput.getPrintableKey().equals(no_key)) {
					givenAnswer = ConfirmChoice.ConfirmationValue.NO;
				}
			} else if (readerInput.getSpecialKey() == ReaderIF.SpecialKey.BACKSPACE) {
				givenAnswer = null;
			}
			render();
			readerInput = this.reader.read();
		}
		String resultValue = calcResultValue();

		System.out.println();
		renderMessagePromptAndResult(this.message, resultValue);

		return new ConfirmAnswer(givenAnswer);
	}

	/**
	 * Renders the confirmation message on the screen.
	 */
	private void render() {
		System.out.println("");
		System.out.println(ansi().eraseLine().cursorUp(2));
		System.out.print(renderMessagePrompt(this.message) + itemRenderer.renderConfirmChoiceOptions(this.confirmChoice) + " "
				+ ansi().reset().a(calcResultValue() + " ").eraseLine());
		System.out.flush();
		renderHeight = 1;
	}

	/**
	 * Returns the localized string representation of 'yes' or 'no' depending on
	 * the given answer.
	 *
	 * @return localized answer string.
	 */
	private String calcResultValue() {
		if (givenAnswer == ConfirmChoice.ConfirmationValue.YES) {
			return yes_answer;
		} else if (givenAnswer == ConfirmChoice.ConfirmationValue.NO) {
			return no_answer;
		}
		return "";
	}
}
