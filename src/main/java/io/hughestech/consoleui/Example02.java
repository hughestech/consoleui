package io.hughestech.consoleui;

import java.util.Map;
import java.util.function.Function;

import javax.inject.Inject;

import org.slf4j.Logger;

import io.hughestech.consoleui.exception.InvalidInputException;
import io.hughestech.consoleui.prompt.ConsolePrompt;
import io.hughestech.consoleui.prompt.answer.Answer;
import io.hughestech.consoleui.prompt.builder.PromptBuilder;

public class Example02 {
	
	@Inject ConsolePrompt prompt;
	@Inject	static PromptBuilder promptBuilder;
	@Inject	static Logger log;

	public static void main(String[] args) {
		System.out.println("log!");
		log.info("starting example");
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
