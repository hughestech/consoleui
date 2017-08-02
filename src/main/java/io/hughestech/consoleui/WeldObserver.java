package io.hughestech.consoleui;

import java.util.List;
import javax.enterprise.event.Observes;


import org.jboss.weld.environment.se.bindings.Parameters;
import org.jboss.weld.environment.se.events.ContainerInitialized;

public class WeldObserver{
	
	public static void main(String... args) {
		//SeContainerInitializer containerInit = SeContainerInitializer.newInstance();
		//SeContainer container = containerInit.initialize();

		// Fire synchronous event that triggers the code in App class.
		//container.getBeanManager().fireEvent(new SimpleEvent());

		//container.close();
	}
	
	public void containerInitialized(@Observes ContainerInitialized event, @Parameters List<String> parameters) {

	       if (parameters.size() > 1) {
			System.out.println("Hello " + parameters.get(0));
		}else {
			System.out.println("Hello");
		}
	}
}
