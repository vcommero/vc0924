package com.vincecommero.toolman.commandlineinterface;

import org.springframework.shell.Input;
import org.springframework.shell.InputProvider;
import org.springframework.shell.standard.ShellCommandGroup;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
@ShellCommandGroup(value = "Rental Operations")
public class RentalCommands {
	
	private final InputProvider inputProvider;
	
	public RentalCommands(InputProvider inputProvider) {
		this.inputProvider = inputProvider;
	}

	@ShellMethod(value = "Prints a greeting.", key = "greet")
	public String greeting() {
		return "Hello! Welcome to Rental Operations!";
	}
	
	@ShellMethod(value = "Checks out a tool from the inventory for rental.", key = "Checkout")
	public String checkout() {
		// Ask for rental duration
        Input name = inputProvider.readInput();
		
		return "Finished!";
	}
}
