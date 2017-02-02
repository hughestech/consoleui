package de.codeshelf.consoleui;

import static org.fusesource.jansi.Ansi.ansi;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

import org.fusesource.jansi.AnsiConsole;

import de.codeshelf.consoleui.elements.ConfirmChoice;
import de.codeshelf.consoleui.prompt.ConsolePrompt;
import de.codeshelf.consoleui.prompt.answer.Answer;
import de.codeshelf.consoleui.prompt.answer.ConfirmAnswer;
import de.codeshelf.consoleui.prompt.builder.PromptBuilder;
import jline.TerminalFactory;
import jline.console.completer.StringsCompleter;

/**
 * User: Andreas Wegmann Date: 29.11.15
 */
public class Basic {

	public static void main(String[] args) throws InterruptedException {
		AnsiConsole.systemInstall();
		System.out.println(ansi().eraseScreen().render("@|red,italic Hello|@ @|green World|@\n@|reset "
				+ "This is a demonstration of ConsoleUI java library. It provides a simple console interface\n"
				+ "for querying information from the user. ConsoleUI is inspired by Inquirer.js which is written\n" + "in JavaScrpt.|@"));

		try {
			ConsolePrompt prompt = new ConsolePrompt();
			PromptBuilder promptBuilder = prompt.getPromptBuilder();

			//// @formatter:off
			promptBuilder.inputPrompt("name")
	              .message(anwer -> "Digite o nome")
	              .validate(answer -> {
	            	  if(answer == null) {
	            		  return "Campo obrigatório";
	            	  }
	            	  return true;
	              })
	              //.mask('*')
	              .addCompleter(new StringsCompleter("Jim", "Jack", "John"))
	              .build();
			// @formatter:on

			//// @formatter:off
		    promptBuilder.listPrompt("pizzatype")
	              .message(anwers -> "Escolha a pizza "+anwers.get("name").value())
	              .choices("Margherita", "Veneziana", "Hawai", "Quattro Stagioni")
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
		    promptBuilder.confirmPrompt()
		          .name("delivery")
		          .message("Is this pizza for delivery?")
		          .defaultValue(ConfirmChoice.ConfirmationValue.YES)
		          .build();
		    // @formatter:on

			HashMap<String, ? extends Answer> result = prompt.prompt(promptBuilder.build());
			System.out.println("result = " + result);

			ConfirmAnswer delivery = (ConfirmAnswer) result.get("delivery");
			if (delivery.getConfirmed() == ConfirmChoice.ConfirmationValue.YES) {
				System.out.println("We will deliver the pizza in 5 minutes");
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
}
