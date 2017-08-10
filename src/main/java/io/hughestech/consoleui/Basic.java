package io.hughestech.consoleui;

import static org.fusesource.jansi.Ansi.ansi;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;import java.util.Map;
import java.util.function.Function;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.fusesource.jansi.AnsiConsole;

import io.hughestech.consoleui.elements.ConfirmChoice;
import io.hughestech.consoleui.exception.InvalidInputException;
import io.hughestech.consoleui.prompt.ConsolePrompt;
import io.hughestech.consoleui.prompt.answer.Answer;
import io.hughestech.consoleui.prompt.answer.ConfirmAnswer;
import io.hughestech.consoleui.prompt.builder.PromptBuilder;
import jline.TerminalFactory;
import jline.console.completer.StringsCompleter;

/**
 * User: Andreas Wegmann Date: 29.11.15
 */
@ReadEventAnnotation
@ApplicationScoped
public class Basic implements IExample  {
	
	@Inject	static PromptBuilder promptBuilder;

	/* (non-Javadoc)
	 * @see io.hughestech.consoleui.IExample#run(java.lang.String[])
	 */
	@Override
	@ReadEventAnnotation
	public void run(String[] args) throws InterruptedException {
		
		//AnsiConsole.systemInstall();
		
	
		
		System.out.println(ansi().eraseScreen().render("@|red,italic Hello|@ @|green World|@\n@|reset "
				+ "This is a demonstration of ConsoleUI java library. It provides a simple console interface\n"
				+ "for querying information from the user. ConsoleUI is inspired by Inquirer.js which is written\n" + "in JavaScrpt.|@"));

		try {
			ConsolePrompt prompt = new ConsolePrompt();
			promptBuilder = prompt.getPromptBuilder();

			//// @formatter:off
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
			// @formatter:on

			//// @formatter:off
		    promptBuilder.listPrompt("pizzatype")
	              .message(anwers -> "Select a pizza "+anwers.get("name").value())
	              .choices("Margherita", "Veneziana", "Hawai", "Quattro Stagioni")
	              .when(anwserFunc())
	              .build();
		    // @formatter:on

			//// @formatter:off
            promptBuilder.checkboxPrompt("topping")
	              .message("Please select additional toppings:")
	              .choices(answers -> new HashSet<>(Arrays.asList("A", "B", "C")))
	              //.newSeparator("standard toppings").add()
	
	              //.newSeparator("and our speciality...").add()
	
	              .build();
	         // @formatter:on

			//// @formatter:off
            /*
		    promptBuilder.createChoicePrompt()
		              .name("payment")
		              .message("How do you want to pay?")
		
		              .newItem().name("cash").message("Cash").key('c').asDefault().add()
		              .newItem("visa").message("Visa Card").key('v').add()
		              .newItem("master").message("Master Card").key('m').add()
		              .newSeparator("online payment").add()
		              .newItem("paypal").message("Paypal").key('p').add()
		              .build();
		             */
		    // @formatter:on

			//// @formatter:off
		    promptBuilder.confirmPrompt("delivery")
		          .message("Is this pizza for delivery?")
		          .defaultValue(ConfirmChoice.ConfirmationValue.YES)
		          .build();
		    // @formatter:on

			HashMap<String, ? extends Answer> result = prompt.prompt(promptBuilder.build());
			System.out.println("result = " + result);

			ConfirmAnswer delivery = (ConfirmAnswer) result.get("delivery");
			if (delivery.getConfirmed() == ConfirmChoice.ConfirmationValue.YES) {
				System.out.println("We will deliver the pizza in 5 minutes");
			}else {
				System.out.println("You will need to pick up your pizza");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				TerminalFactory.get().restore();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	private Function<Map<String, Answer>, Boolean> anwserFunc() {
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
