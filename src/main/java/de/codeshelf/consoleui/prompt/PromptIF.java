package de.codeshelf.consoleui.prompt;

import de.codeshelf.consoleui.elements.PromptableElementIF;
import de.codeshelf.consoleui.prompt.answer.Answer;

import java.io.IOException;
import java.util.HashMap;

/**
 * Interface for all prompt implementation.
 *
 * User: Andreas Wegmann
 * Date: 01.01.16
 */
public interface PromptIF<T extends PromptableElementIF, R extends Answer> {
  /**
   * Prompt the user for an imput.
   *
   * @param promptableElement prompt definition
   * @return the prompt result
   * @throws IOException may be thrown by getting the users input.
   */
  R prompt(T promptableElement, HashMap<String, Answer> answers) throws IOException;
}
