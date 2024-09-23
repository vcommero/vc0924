package com.vincecommero.toolman.checkout.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.vincecommero.toolman.commandlineinterface.utility.ConsoleIO;

public class RentalAgreementTests {
	
	private PrintStream mockPrintStream = mock(PrintStream.class);
	
	private RentalAgreement testAgreement = new RentalAgreement(
			"CHNS", 
			"Chainsaw", 
			"Stihl", 
			5, 
			LocalDate.of(2015, 7, 2), 
			LocalDate.of(2015, 7, 7), 
			1490000,
			3, 
			4470000,
			25, 
			1117500,
			3352500);

	@Test
	void shouldBeAbleToCompareEquality() {
		Object nonAgreementObject = new Object();
		
		assertFalse(testAgreement.equals(nonAgreementObject));
		assertTrue(testAgreement.equals(testAgreement));
	}
	
	@Test
	void shouldPrintFormattedAgreementToConsole() {
		String expectedOutput = 
				"Tool code: CHNS\n" + 
				"Tool type: Chainsaw\n" +
				"Tool brand: Stihl\n" +
				"Rental days: 5\n" +
				"Check out date: 07/02/2015\n" +
				"Due date: 07/07/2015\n" +
				"Daily rental charge: $1.49\n" +
				"Charge days: 3\n" +
				"Pre-discount charge: $4.47\n" +
				"Discount percent: 25%\n" +
				"Discount amount: $1.12\n" +
				"Final charge: $3.35";
		
		//ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
		//doNothing().when(mockPrintStream).println(captor.capture());
		
		testAgreement.printAgreement(mockPrintStream);
		
		verify(mockPrintStream).println(expectedOutput);
	}
	
	@Test
	void shouldUseDefaultSystemOutIfPrintAgreementCalledWithNoArguments() {
		String expectedOutput = 
				"Tool code: CHNS\n" + 
				"Tool type: Chainsaw\n" +
				"Tool brand: Stihl\n" +
				"Rental days: 5\n" +
				"Check out date: 07/02/2015\n" +
				"Due date: 07/07/2015\n" +
				"Daily rental charge: $1.49\n" +
				"Charge days: 3\n" +
				"Pre-discount charge: $4.47\n" +
				"Discount percent: 25%\n" +
				"Discount amount: $1.12\n" +
				"Final charge: $3.35";
		
		PrintStream standardOut = System.out;
		System.setOut(mockPrintStream);
		
		testAgreement.printAgreementToConsole();
		verify(mockPrintStream).println(expectedOutput);
		
		System.setOut(standardOut);
	}
}
