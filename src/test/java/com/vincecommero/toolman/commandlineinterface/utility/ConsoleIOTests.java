package com.vincecommero.toolman.commandlineinterface.utility;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ConsoleIOTests {
	
	private final PrintStream standardOut = System.out;
	private final InputStream standardIn = System.in;
	private final ByteArrayOutputStream outputCaptor = new ByteArrayOutputStream();
	private final PrintStream captorPrintStream = new PrintStream(outputCaptor);
	
	@InjectMocks
	private ConsoleIO consoleIO;

	@BeforeEach
	public void setUp() {
	    System.setOut(captorPrintStream);
	}
	
	@AfterEach
	public void tearDown() {
	    System.setOut(standardOut);
	    System.setIn(standardIn);
	}
	
	@Test
	void shouldPrintToSystemOutWhenPrintIsCalled() {
		String testString = "TESTING!123";
		
		consoleIO.println(testString);
		assertEquals(testString + "\n", outputCaptor.toString());
	}
	
	@Test
	void shouldCallSystemOutPrintlnWhenNoArgumentPrintlnIsCalled() {
		consoleIO.println();
		assertTrue(outputCaptor.toString().contains("\n"));
	}
	
	@Test
	void shouldReturnTheScannerInstance() {
		assertNotNull(consoleIO.getScanner());
	}
	
	@Test
	void shouldReturnTheOutputPrintStream() {
		assertEquals(captorPrintStream, consoleIO.getOutputPrintStream());
	}
	
	@Test
	void shouldPromptUserForInputAndHandleInput() {
		String testString = "TEST123!@";
		ByteArrayInputStream inputCaptor = new ByteArrayInputStream(testString.getBytes());
		System.setIn(inputCaptor);
		ConsoleIO testConsole = new ConsoleIO();
		
		String output = testConsole.prompt("Enter input:");
		assertEquals(testString, output);
		
		inputCaptor = new ByteArrayInputStream(new byte[0]);
		System.setIn(inputCaptor);
		testConsole = new ConsoleIO();
		
		output = testConsole.prompt("Enter input:");
		assertEquals("", output);
	}
	
	@Test
	void shouldCloseScannerWhenDestroyIsCalled() {
		Scanner scanner = consoleIO.getScanner();
		consoleIO.destroy();
		
		assertThrows(IllegalStateException.class, () -> scanner.hasNext());
	}
}
