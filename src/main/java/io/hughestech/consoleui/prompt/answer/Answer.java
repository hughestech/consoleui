package io.hughestech.consoleui.prompt.answer;

import java.util.HashSet;

import io.hughestech.consoleui.ReadEventAnnotation;
import io.hughestech.consoleui.elements.ConfirmChoice.ConfirmationValue;

/**
 * Created by Andreas Wegmann on 03.02.16.
 */
@ReadEventAnnotation
public interface Answer {

	public String value();

	public HashSet<String> list();
	
	ConfirmationValue confirmation();
}
