package io.hughestech.consoleui;

import java.util.List;
import javax.enterprise.event.Observes;
import javax.enterprise.inject.Any;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;

import org.fusesource.jansi.AnsiConsole;
import org.jboss.weld.environment.se.bindings.Parameters;
import org.jboss.weld.environment.se.events.ContainerInitialized;

public class WeldObserver{
	
	@Inject @Any
	 Instance<Basic> basicInstance;
	
	@Inject Example02 example;
	
	@Inject PromptBuilderProducer pbp;
	
	public static void main(String... args) {
		
		
		
		//SeContainerInitializer containerInit = SeContainerInitializer.newInstance();
		//SeContainer container = containerInit.initialize();

		// Fire synchronous event that triggers the code in App class.
		//container.getBeanManager().fireEvent(new SimpleEvent());

		//container.close();
		
		
		
		System.console().readLine();
	}
	
	public void containerInitialized(@Observes ContainerInitialized event, @Parameters List<String> parameters) {

		this.pbp.getPromptBuilder();
		
		this.example.runCmdPrompt();
	}
}
