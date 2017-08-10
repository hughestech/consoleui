/**
 * 
 */
package io.hughestech.consoleui;

import java.util.HashSet;

import javax.decorator.Decorator;
import javax.decorator.Delegate;
import javax.enterprise.inject.Any;
import javax.inject.Inject;

import io.hughestech.consoleui.elements.ConfirmChoice.ConfirmationValue;
import io.hughestech.consoleui.prompt.answer.Answer;

@Decorator
/**
 * @author anton
 *
 */
public abstract class AnswerDecorator implements Answer {

	/**
	 * 
	 */
	@Inject
	@Delegate
	@Any
	private Answer answer;

	/**
	 * 
	 */
	public AnswerDecorator() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see io.hughestech.consoleui.prompt.answer.Answer#value()
	 */
	@Override
	public String value() {
		System.out.println("anwser decorator value()");
		return answer.value();
	}

	/* (non-Javadoc)
	 * @see io.hughestech.consoleui.prompt.answer.Answer#list()
	 */
	@Override
	public HashSet<String> list() {
		System.out.println("anwser decorator list()");
		return answer.list();
	}

	/* (non-Javadoc)
	 * @see io.hughestech.consoleui.prompt.answer.Answer#confirmation()
	 */
	@Override
	public ConfirmationValue confirmation() {
		System.out.println("anwser decorator conf()");
		return answer.confirmation();
	}

}
