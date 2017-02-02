package de.codeshelf.consoleui.prompt;

import de.codeshelf.consoleui.elements.ConfirmChoice;

/**
 * Result of a confirmation choice. Holds a single value of 'yes' or 'no'
 * from enum {@link ConfirmChoice.ConfirmationValue}.
 * <p>
 * User: Andreas Wegmann
 * Date: 03.02.16
 */
public class ConfirmAnswer implements Answer {
  ConfirmChoice.ConfirmationValue confirmed;

  /**
   * Default constructor.
   *
   * @param confirm the result value to hold.
   */
  public ConfirmAnswer(ConfirmChoice.ConfirmationValue confirm) {
    this.confirmed = confirm;
  }

  /**
   * Returns the confirmation value.
   * @return confirmation value.
   */
  public ConfirmChoice.ConfirmationValue getConfirmed() {
    return confirmed;
  }

  @Override
  public String toString() {
    return "ConfirmResult{" +
            "confirmed=" + confirmed +
            '}';
  }
}
