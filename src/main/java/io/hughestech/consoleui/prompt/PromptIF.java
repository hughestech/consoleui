package io.hughestech.consoleui.prompt;

import java.io.IOException;
import java.util.HashMap;

import io.hughestech.consoleui.elements.PromptableElementIF;
import io.hughestech.consoleui.prompt.answer.Answer;

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
