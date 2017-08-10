package io.hughestech.consoleui;

import java.util.Map;
import java.util.function.Function;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.hughestech.consoleui.exception.InvalidInputException;
import io.hughestech.consoleui.prompt.ConsolePrompt;
import io.hughestech.consoleui.prompt.answer.Answer;
import io.hughestech.consoleui.prompt.builder.PromptBuilder;

@Dependent
public class Example02 {
	
	@Inject ConsolePrompt prompt;
	@Inject	static PromptBuilder promptBuilder;
	//@Inject	static Logger log;
	private static Logger log = LoggerFactory.getLogger(Example02.class);


	public static void main(String[] args) {
		System.out.println("log!");
		runCmdPrompt();

	}

	public static void runCmdPrompt() {
		String msg = "starting example";
		log.info(msg);
		log.info(msg ); // print
		promptBuilder.inputPrompt("name")
        .message(anwer -> "Please enter your name")
        .validate(answer -> {
      	  if(answer == null) {
      		  throw new InvalidInputException("What? You dont have a name?");
      	  }
        })
        //.mask('*')
        .choices("Jim", "Jack", "John").defaultValue("Anton")
        .when(anwserFunc())
        .build();
	}
	
	private static Function<Map<String, Answer>, Boolean> anwserFunc() {
		return new Function<Map<String,Answer>, Boolean>() {
			
			@Override
			public Boolean apply(Map<String, Answer> t) {
				System.out.println("applying...");
				if (!t.isEmpty()) {
					for (String a : t.keySet()) {
						System.out.println(a);
					}
				}
				return true;
			}
		};
	}

}
