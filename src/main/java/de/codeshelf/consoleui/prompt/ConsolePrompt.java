package de.codeshelf.consoleui.prompt;

import de.codeshelf.consoleui.elements.*;
import de.codeshelf.consoleui.prompt.builder.PromptBuilder;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

/**
 * ConsolePrompt encapsulates the prompting of a list of input questions for the
 * user.
 * <p>
 * Created by Andreas Wegmann on 20.01.16.
 */
public class ConsolePrompt {
	// input prompt implementation
	private InputPrompt inputPrompt;

	// expandable choice prompt implementation
	private ExpandableChoicePrompt expandableChoicePrompt;

	// checkbox prompt implementation
	private CheckboxPrompt checkboxPrompt;

	// list box prompt implementation
	private ListPrompt listPrompt;

	// confirmation prompt implementation
	private ConfirmPrompt confirmPrompt;

	/* Lazy getter for input prompt */
	private InputPrompt getInputPrompt() throws IOException {
		if (inputPrompt == null) {
			inputPrompt = new InputPrompt();
		}
		return inputPrompt;
	}

	/* Lazy getter for expandable choice prompt */
	private ExpandableChoicePrompt getExpandableChoicePrompt() throws IOException {
		if (expandableChoicePrompt == null) {
			expandableChoicePrompt = new ExpandableChoicePrompt();
		}
		return expandableChoicePrompt;
	}

	/* Lazy getter for checkbox prompt */
	private CheckboxPrompt getCheckboxPrompt() throws IOException {
		if (checkboxPrompt == null) {
			checkboxPrompt = new CheckboxPrompt();
		}
		return checkboxPrompt;
	}

	/* Lazy getter for list prompt */
	private ListPrompt getListPrompt() throws IOException {
		if (listPrompt == null) {
			listPrompt = new ListPrompt();
		}
		return listPrompt;
	}

	/* Lazy getter for confirm prompt */
	private ConfirmPrompt getConfirmPrompt() throws IOException {
		if (confirmPrompt == null) {
			confirmPrompt = new ConfirmPrompt();
		}
		return confirmPrompt;
	}

	/**
	 * Default constructor for this class.
	 */
	public ConsolePrompt() {
	}

	/**
	 * Prompt a list of choices (questions). This method takes a list of
	 * promptable elements, typically created with {@link PromptBuilder}. Each
	 * of the elements is processed and the user entries and answers are filled
	 * in to the result map. The result map contains the key of each promtable
	 * element and the user entry as an object implementing
	 * {@link Answer}.
	 *
	 * @param promptableElementList
	 *            the list of questions / promts to ask the user for.
	 * @return a map containing a result for each element of
	 *         promptableElementList
	 * @throws IOException
	 *             may be thrown by console reader
	 */
	public HashMap<String, ? extends Answer> prompt(List<PromptableElementIF> promptableElementList) throws IOException {
		HashMap<String, Answer> answers = new HashMap<String, Answer>();

		for (int i = 0; i < promptableElementList.size(); i++) {
			PromptableElementIF promptableElement = promptableElementList.get(i);
			if (promptableElement instanceof ListChoice) {
				ListAnswer result = doPrompt((ListChoice) promptableElement, answers);
				answers.put(promptableElement.getName(), result);
			} else if (promptableElement instanceof InputValue) {
				InputAnswer result = doPrompt((InputValue) promptableElement, answers);
				answers.put(promptableElement.getName(), result);
			} else if (promptableElement instanceof ExpandableChoice) {
				ExpandableChoiceAnswer result = doPrompt((ExpandableChoice) promptableElement, answers);
				answers.put(promptableElement.getName(), result);
			} else if (promptableElement instanceof Checkbox) {
				CheckboxAnswer result = doPrompt((Checkbox) promptableElement, answers);
				answers.put(promptableElement.getName(), result);
			} else if (promptableElement instanceof ConfirmChoice) {
				ConfirmAnswer result = doPrompt((ConfirmChoice) promptableElement, answers);
				answers.put(promptableElement.getName(), result);
			} else {
				throw new IllegalArgumentException("wrong type of promptable element");
			}
		}
		return answers;
	}

	/**
	 * Process a {@link ConfirmChoice}.
	 *
	 * @param confirmChoice
	 *            the confirmation to ask the user for.
	 * @return Object of type {@link ConfirmAnswer} holding the users answer
	 * @throws IOException
	 *             may be thrown by console reader
	 */
	private ConfirmAnswer doPrompt(ConfirmChoice confirmChoice, HashMap<String, Answer> answers) throws IOException {
		return getConfirmPrompt().prompt(confirmChoice, answers);
	}

	/**
	 * Process a {@link ListChoice}.
	 *
	 * @param listChoice
	 *            the list to let the user choose an item from.
	 * @return Object of type {@link ListAnswer} holding the uses choice.
	 * @throws IOException
	 *             may be thrown by console reader
	 */
	private ListAnswer doPrompt(ListChoice listChoice, HashMap<String, Answer> answers) throws IOException {
		return getListPrompt().prompt(listChoice, answers);
	}

	/**
	 * Process a {@link InputValue}.
	 *
	 * @param inputValue
	 *            the input value to ask the user for.
	 * @return Object of type {@link InputAnswer} holding the uses input.
	 * @throws IOException
	 *             may be thrown by console reader
	 */
	private InputAnswer doPrompt(InputValue inputValue, HashMap<String, Answer> answers) throws IOException {
		return getInputPrompt().prompt(inputValue, answers);
	}

	/**
	 * Process a {@link Checkbox}.
	 *
	 * @param checkbox
	 *            the checkbox displayed where the user can check values.
	 * @return Object of type {@link CheckboxAnswer} holding the uses choice.
	 * @throws IOException
	 *             may be thrown by console reader
	 */
	private CheckboxAnswer doPrompt(Checkbox checkbox, HashMap<String, Answer> answers) throws IOException {
		return getCheckboxPrompt().prompt(checkbox, answers);
	}

	/**
	 * Process a {@link ExpandableChoice}.
	 *
	 * @param expandableChoice
	 *            the expandable choice displayed where the user can select a
	 *            value from.
	 * @return Object of type {@link ExpandableChoiceAnswer} holding the uses
	 *         choice.
	 * @throws IOException
	 *             may be thrown by console reader
	 */
	private ExpandableChoiceAnswer doPrompt(ExpandableChoice expandableChoice, HashMap<String, Answer> answers) throws IOException {
		return getExpandableChoicePrompt().prompt(expandableChoice, answers);
	}

	/**
	 * Creates a {@link PromptBuilder}.
	 *
	 * @return a new prompt builder object.
	 */
	public PromptBuilder getPromptBuilder() {
		return new PromptBuilder();
	}
}
