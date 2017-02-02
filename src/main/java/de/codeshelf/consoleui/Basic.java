package de.codeshelf.consoleui;

import static org.fusesource.jansi.Ansi.ansi;

import java.io.IOException;
import java.util.HashMap;

import org.fusesource.jansi.AnsiConsole;

import de.codeshelf.consoleui.elements.ConfirmChoice;
import de.codeshelf.consoleui.prompt.Answer;
import de.codeshelf.consoleui.prompt.ConfirmAnswer;
import de.codeshelf.consoleui.prompt.ConsolePrompt;
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
			promptBuilder.createInputPrompt()
	              .name("name")
	              .message(anwer -> "Digite o nome")
	              //.mask('*')
	              .addCompleter(new StringsCompleter("Jim", "Jack", "John"))
	              .addPrompt();
			// @formatter:on

			//// @formatter:off
		    promptBuilder.createListPrompt()
	              .name("pizzatype")
	              .message(anwers -> "Escolha a pizza "+anwers.get("name").value())
	              .newItem().text("Margherita").add()  // without name (name defaults to text)
	              .newItem("veneziana").text("Veneziana").add()
	              .newItem("hawai").text("Hawai").add()
	              .newItem("quattro").text("Quattro Stagioni").add()
	              .addPrompt();
		    // @formatter:on

		    //// @formatter:off
            promptBuilder.createCheckboxPrompt()
	              .name("topping")
	              .message("Please select additional toppings:")
	
	              .newSeparator("standard toppings")
	              .add()
	
	              .newItem().name("cheese").text("Cheese").add()
	              .newItem("bacon").text("Bacon").add()
	              .newItem("onions").text("Onions").disabledText("Sorry. Out of stock.").add()
	
	              .newSeparator().text("special toppings").add()
	
	              .newItem("salami").text("Very hot salami").check().add()
	              .newItem("salmon").text("Smoked Salmon").add()
	
	              .newSeparator("and our speciality...").add()
	
	              .newItem("special").text("Anchovies, and olives").checked(true).add()
	              .addPrompt();
	         // @formatter:on

            //// @formatter:off
		    promptBuilder.createChoicePrompt()
		              .name("payment")
		              .message("How do you want to pay?")
		
		              .newItem().name("cash").message("Cash").key('c').asDefault().add()
		              .newItem("visa").message("Visa Card").key('v').add()
		              .newItem("master").message("Master Card").key('m').add()
		              .newSeparator("online payment").add()
		              .newItem("paypal").message("Paypal").key('p').add()
		              .addPrompt();
		    // @formatter:on

			//// @formatter:off
		    promptBuilder.createConfirmPromp()
		          .name("delivery")
		          .message("Is this pizza for delivery?")
		          .defaultValue(ConfirmChoice.ConfirmationValue.YES)
		          .addPrompt();
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
