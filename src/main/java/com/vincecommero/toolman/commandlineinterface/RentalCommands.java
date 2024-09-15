package com.vincecommero.toolman.commandlineinterface;

import org.springframework.shell.standard.ShellCommandGroup;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
@ShellCommandGroup(value = "Rental Operations")
public class RentalCommands {

	@ShellMethod(value = "Prints a greeting.", key = "greet")
	public String greeting() {
		return "Hello! Welcome to Rental Operations!";
	}
}
