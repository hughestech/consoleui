package io.hughestech.consoleui;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.hughestech.consoleui.prompt.ConsolePrompt;
import io.hughestech.consoleui.prompt.builder.PromptBuilder;

@ApplicationScoped
public class PromptBuilderProducer {
	
	
	@Inject ConsolePrompt prompt;
	//@Inject Logger log;
	private  Logger log = LoggerFactory.getLogger(PromptBuilderProducer.class);
	
/*	@Produces
	public ConsolePrompt getConsolePrompt() {
		log.info("producing console prompt");
		return new ConsolePrompt();
	}*/
	
	
	@Produces
	public PromptBuilder getPromptBuilder() {
		log.info("producing PromptBuilder");
		ConsolePrompt prompt = new ConsolePrompt();
		return prompt.getPromptBuilder();
	}

}
