package com.vincecommero.toolman.commandlineinterface.utility;

import java.io.PrintStream;
import java.util.Scanner;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.stereotype.Component;

/**
 * Class for abstracting console input and output. This makes input and output operations more controllable, and allows for easier testing.
 * It has an instance of Scanner that will be shared and reused across the application.
 */
@Component
public class ConsoleIO implements DisposableBean {
	
	private final Scanner scanner = new Scanner(System.in);

	public void println(String input) {
		System.out.println(input);
	}
	
	public void println() {
		System.out.println();
	}
	
	public String prompt(String message) {
        println(message);
        if (scanner.hasNextLine()) {
            String input = scanner.nextLine();
            return input;
        } else {
            return "";
        }
	}
	
	public Scanner getScanner() {
		return this.scanner;
	}
	
	public PrintStream getOutputPrintStream() {
		return System.out;
	}

	/**
	 * Closes the Scanner at end of bean lifecycle
	 */
	@Override
	public void destroy() throws Exception {
		if (scanner != null) scanner.close();
	}
}
