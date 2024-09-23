package com.vincecommero.toolman.commandlineinterface;

import static org.junit.jupiter.api.Assertions.assertTimeout;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.contains;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.Duration;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;
import org.springframework.context.ApplicationContext;

import com.vincecommero.toolman.commandlineinterface.utility.ConsoleIO;

@ExtendWith(MockitoExtension.class)
public class ToolmanCommandLineTests {
	
	@Mock
	private CheckoutCommands checkoutCommands;
	
	@Mock
	private ApplicationContext applicationContext;
	
	@Mock
	private ConsoleIO consoleIO;

	@InjectMocks
	private ToolmanCommandLine toolmanCommandLine;
	
	@Test
	void shouldExitCommandLineWhenExitCommandIsEntered() {
		when(consoleIO.prompt(anyString())).thenReturn("exit");
		assertTimeout(Duration.ofSeconds(2), () -> {
			toolmanCommandLine.run();
		});
	}
	
	@Test
	void shouldPrintCorrectBannerAndPromptForUser() {
		when(consoleIO.prompt(anyString())).thenReturn("exit");
		assertTimeout(Duration.ofSeconds(2), () -> {
			toolmanCommandLine.run();
		});
		verify(consoleIO).println(contains("Welcome to Toolman!"));
		verify(consoleIO).prompt(contains("checkout"));
		verify(consoleIO).prompt(contains("exit"));
	}
	
	@Test
	void shouldPromptToSelectAnOptionIfNoInput() {
		when(consoleIO.prompt(anyString())).then(new Answer<String>() {
			private int count = 0;
			
			@Override
			public String answer(InvocationOnMock invocation) throws Throwable {
				count++;
				if (count == 1) {
					return "";
				}
				else {
					return "exit";
				}
			}
		});
		assertTimeout(Duration.ofSeconds(2), () -> {
			toolmanCommandLine.run();
		});
		verify(consoleIO).println("Please select an option.");
	}
	
	@Test
	void shouldAlertAndRepromptIfUnrecognizedCommand() {
		when(consoleIO.prompt(anyString())).then(new Answer<String>() {
			private int count = 0;
			
			@Override
			public String answer(InvocationOnMock invocation) throws Throwable {
				count++;
				if (count == 1) {
					return "asdf";
				}
				else {
					return "exit";
				}
			}
		});
		assertTimeout(Duration.ofSeconds(2), () -> {
			toolmanCommandLine.run();
		});
		verify(consoleIO).println("Unrecognized command: asdf");
	}
	
	@Test
	void shouldRunCheckoutCommandIfSelectedByUser() {
		when(checkoutCommands.checkout()).thenReturn("TEST SUCCESS!");
		when(consoleIO.prompt(anyString())).then(new Answer<String>() {
			private int callCount = 0;
			
			@Override
			public String answer(InvocationOnMock invocation) throws Throwable {
				callCount++;
				if (callCount == 1) {
					return "checkout";
				}
				else {
					return "exit";
				}
			}
		});
		assertTimeout(Duration.ofSeconds(2), () -> {
			toolmanCommandLine.run();
		});
		verify(consoleIO).println("TEST SUCCESS!");
	}
}
