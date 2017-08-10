/**
 * 
 */
package io.hughestech.consoleui;

import javax.decorator.Decorator;
import javax.decorator.Delegate;
import javax.enterprise.inject.Any;
import javax.inject.Inject;

import org.fusesource.jansi.AnsiConsole;

@Decorator
/**
 * @author anton
 *
 */
public abstract class IExampleDecorator implements IExample {

	/**
	 * 
	 */
	@Inject
	@Delegate
	@Any
	private IExample iExample;

	/**
	 * 
	 */
	public IExampleDecorator() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see io.hughestech.consoleui.IExample#run(java.lang.String[])
	 */
	@Override
	public void run(String[] args) throws InterruptedException {
		System.out.println("hello from IExampleDecorate");
		AnsiConsole.systemInstall();
		iExample.run(args);
	}

}
