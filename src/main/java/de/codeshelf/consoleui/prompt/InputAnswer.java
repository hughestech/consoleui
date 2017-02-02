package de.codeshelf.consoleui.prompt;

/**
 *
 * User: Andreas Wegmann
 * Date: 03.02.16
 */
public class InputAnswer implements Answer {
  private String input;

  public InputAnswer(String input) {
    this.input = input;
  }

  public String getInput() {
    return input;
  }

  @Override
  public String toString() {
    return "InputResult{" +
            "input='" + input + '\'' +
            '}';
  }
}
