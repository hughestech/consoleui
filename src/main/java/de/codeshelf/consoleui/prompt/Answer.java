package de.codeshelf.consoleui.prompt;

import java.util.HashSet;

/**
 * Created by Andreas Wegmann on 03.02.16.
 */
public interface Answer {

	public String value();

	public HashSet<String> list();
}
