package com.vincecommero.toolman.commandlineinterface;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.vincecommero.toolman.commandlineinterface.utility.ConsoleIO;

@Component
@Order(2)
public class ToolmanCommandLine implements CommandLineRunner {
	
	private final CheckoutCommands checkoutCommands;
	private final ApplicationContext applicationContext;
	private final ConsoleIO consoleIO;
	
	private final String banner = 
			"\n" +
			"===============================================\n" +
			"\n" +
			"              Welcome to Toolman!             \n" +
			"\n" +
			"===============================================";
	
	private final List<String> commandsList = new ArrayList<>();
	
	public ToolmanCommandLine(CheckoutCommands checkoutCommands, ApplicationContext applicationContext, ConsoleIO consoleIO) {
		this.checkoutCommands = checkoutCommands;
		this.applicationContext = applicationContext;
		this.consoleIO = consoleIO;
		
		commandsList.add("checkout - Checks out a tool for rental and prints out a rental agreement.");
		commandsList.add("exit - Exits the application.");
	}

	@Override
	public void run(String... args) throws Exception {
		consoleIO.println(banner);
		
		boolean running = true;
		while (running) {
			// Prompt user for input
			StringBuilder builder = new StringBuilder("\nPlease select one of the following options:\n");
			for (String command : commandsList) {
				builder.append(command).append("\n");
			}
			String input = consoleIO.prompt(builder.toString())
					.trim().toLowerCase();
			
			// Process user input
			String outputMessage = switch (input) {
				case "": {
					yield "Please select an option.";
				}
				case "checkout": {
					yield checkoutCommands.checkout();
				}
				case "exit": {
					running = false;
					yield "Exiting application...";
				}
				default: {
					yield "Unrecognized command: " + input;
				}
			};
			
			// Print output of command
			consoleIO.println(outputMessage);
		}
		
		// Exits the application
		SpringApplication.exit(applicationContext, () -> 0);
	}
}
