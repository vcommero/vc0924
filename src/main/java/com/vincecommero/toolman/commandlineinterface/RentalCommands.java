package com.vincecommero.toolman.commandlineinterface;

import org.jline.reader.LineReader;
import org.springframework.shell.standard.ShellCommandGroup;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
@ShellCommandGroup(value = "Rental Operations")
public class RentalCommands {
	
	/*
	private final LineReader lineReader;
	
	public RentalCommands(LineReader lineReader) {
		this.lineReader = lineReader;
	}
	*/
	
	@ShellMethod(value = "Checks out a tool from the inventory for rental.", key = "checkout")
	public String checkout() {
		// Ask for rental duration
		
		return "Finished!";
	}
	
	/* Helper methods */
	private void prompt() {
	}
}
