package de.codeshelf.consoleui.prompt;

import java.util.HashSet;

/**
 *
 * User: Andreas Wegmann Date: 03.02.16
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
	public String getValue() {
		return this.input;
	}

	@Override
	public HashSet<String> getList() {
		return null;
	}

	@Override
	public String toString() {
		return "InputResult{" + "input='" + input + '\'' + '}';
	}

}
