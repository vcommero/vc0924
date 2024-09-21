package com.vincecommero.toolman.checkout.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RentalAgreementTests {
	
	private final ByteArrayOutputStream streamCaptor = new ByteArrayOutputStream();
	private final PrintStream originalOutput = System.out;
	
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
	
	@BeforeEach
	void setup() {
		System.setOut(new PrintStream(streamCaptor));
	}
	
	@AfterEach
	void cleanup() {
		System.setOut(originalOutput);
	}

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

		testAgreement.printAgreementToConsole();
		assertEquals(expectedOutput, streamCaptor.toString().trim());
	}
}
