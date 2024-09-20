package com.vincecommero.toolman.commandlineinterface;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.ExitCodeGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(2)
public class ToolmanCommandLine implements CommandLineRunner {
	
	private final CheckoutCommands checkoutCommands;
	
	private final ApplicationContext applicationContext;
	
	private final String banner = 
			"\n" +
			"===============================================\n" +
			"\n" +
			"              Welcome to Toolman!             \n" +
			"\n" +
			"===============================================";
	
	private final List<String> commandsList = new ArrayList<>();
	
	public ToolmanCommandLine(CheckoutCommands checkoutCommands, ApplicationContext applicationContext) {
		this.checkoutCommands = checkoutCommands;
		this.applicationContext = applicationContext;
		
		commandsList.add("checkout - Checks out a tool for rental and prints out a rental agreement.");
		commandsList.add("exit - Exits the application.");
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println(banner);
		
		try (Scanner scanner = new Scanner(System.in)) {
			boolean running = true;
			while (running) {
				// Prompt user for input
				System.out.println("\nPlease select one of the following options:");
				for (String command : commandsList) {
					System.out.println(command);
				}
				System.out.println();
				
				// Read user input
				String input = scanner.nextLine().trim().toLowerCase();
				
				// Process user input
				String outputMessage = switch (input) {
					case "checkout": {
						yield checkoutCommands.checkout(scanner);
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
				System.out.println(outputMessage);
			}
		}
		
		// Exits the application
		SpringApplication.exit(applicationContext, () -> 0);
	}
}
