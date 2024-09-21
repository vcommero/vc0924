package com.vincecommero.toolman;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.vincecommero.toolman.checkout.CheckoutService;
import com.vincecommero.toolman.checkout.model.RentalAgreement;
import com.vincecommero.toolman.commandlineinterface.ToolmanCommandLine;

import jakarta.transaction.Transactional;

@SpringBootTest
@Transactional
public class IntegrationTests {
	
	private final List<CheckoutTestCase> testCases = new ArrayList<CheckoutTestCase>();
	
	@MockBean
	private ToolmanCommandLine toolmanCommandLine;
	
	@Autowired
	private CheckoutService checkoutService;
	
	@BeforeEach
	void testSetup() {
		if (testCases.isEmpty()) {
			testCases.add(new CheckoutTestCase("JAKR", LocalDate.of(2015, 9, 3), 5, 101));
			testCases.add(new CheckoutTestCase("LADW", LocalDate.of(2020, 7, 2), 3, 10));
			testCases.add(new CheckoutTestCase("CHNS", LocalDate.of(2015, 7, 2), 5, 25));
			testCases.add(new CheckoutTestCase("JAKD", LocalDate.of(2015, 9, 3), 6, 0));
			testCases.add(new CheckoutTestCase("JAKR", LocalDate.of(2015, 7, 2), 9, 0));
			testCases.add(new CheckoutTestCase("JAKR", LocalDate.of(2020, 7, 2), 4, 50));
		}
	}
	
	@Test
	void shouldPassIntegrationTestCase_1() {
		CheckoutTestCase testCase = this.testCases.get(0);
		
		Exception exception = assertThrows(IllegalArgumentException.class,
				() -> checkoutService.checkout(testCase.toolCode, testCase.rentalDays, testCase.checkoutDate, testCase.discount));
		
		String expectedMessage = testCase.discount + " is not a valid discount percentage. " + 
				"The discount percentage can't be less than 0% or greater than 100%.";
		assertTrue(exception.getLocalizedMessage().equals(expectedMessage));
	}
	
	@Test
	void shouldPassIntegrationTestCase_2() {
		CheckoutTestCase testCase = this.testCases.get(1);
		RentalAgreement expectedAgreement = new RentalAgreement(
				"LADW", 
				"Ladder", 
				"Werner", 
				3, 
				LocalDate.of(2020, 7, 2), 
				LocalDate.of(2020, 7, 5), 
				1990000, // $1.99
				2, 
				3980000, // $3.98
				10, 
				398000, // $0.40
				3582000); // $3.58
		
		RentalAgreement receivedAgreement = assertDoesNotThrow(() -> 
				checkoutService.checkout(testCase.toolCode, testCase.rentalDays, testCase.checkoutDate, testCase.discount));
		assertTrue(receivedAgreement.equals(expectedAgreement));
	}
	
	@Test
	void shouldPassIntegrationTestCase_3() {
		CheckoutTestCase testCase = this.testCases.get(2);
		RentalAgreement expectedAgreement = new RentalAgreement(
				"CHNS", 
				"Chainsaw", 
				"Stihl", 
				5, 
				LocalDate.of(2015, 7, 2), 
				LocalDate.of(2015, 7, 7), 
				1490000, // $1.49
				3, 
				4470000, // $4.47
				25, 
				1117500, // $1.12
				3352500); // $3.35
		
		RentalAgreement receivedAgreement = assertDoesNotThrow(() -> 
				checkoutService.checkout(testCase.toolCode, testCase.rentalDays, testCase.checkoutDate, testCase.discount));
		assertTrue(receivedAgreement.equals(expectedAgreement));
	}
	
	@Test
	void shouldPassIntegrationTestCase_4() {
		CheckoutTestCase testCase = this.testCases.get(3);
		RentalAgreement expectedAgreement = new RentalAgreement(
				"JAKD", 
				"Jackhammer", 
				"DeWalt", 
				6, 
				LocalDate.of(2015, 9, 3), 
				LocalDate.of(2015, 9, 9), 
				2990000, // $2.99
				3, 
				8970000, // $8.97
				0, 
				0, // $0.00
				8970000); // $8.97
		
		RentalAgreement receivedAgreement = assertDoesNotThrow(() -> 
				checkoutService.checkout(testCase.toolCode, testCase.rentalDays, testCase.checkoutDate, testCase.discount));
		receivedAgreement.printAgreementToConsole();
		assertTrue(receivedAgreement.equals(expectedAgreement));
	}
	
	@Test
	void shouldPassIntegrationTestCase_5() {
		CheckoutTestCase testCase = this.testCases.get(4);
		RentalAgreement expectedAgreement = new RentalAgreement(
				"JAKR", 
				"Jackhammer", 
				"Ridgid", 
				9, 
				LocalDate.of(2015, 7, 2), 
				LocalDate.of(2015, 7, 11), 
				2990000, // $2.99
				5, 
				14950000, // $14.95
				0, 
				0, // $0.00
				14950000); // $14.95
		
		RentalAgreement receivedAgreement = assertDoesNotThrow(() -> 
				checkoutService.checkout(testCase.toolCode, testCase.rentalDays, testCase.checkoutDate, testCase.discount));
		assertTrue(receivedAgreement.equals(expectedAgreement));
	}
	
	@Test
	void shouldPassIntegrationTestCase_6() {
		CheckoutTestCase testCase = this.testCases.get(5);
		RentalAgreement expectedAgreement = new RentalAgreement(
				"JAKR", 
				"Jackhammer", 
				"Ridgid", 
				4, 
				LocalDate.of(2020, 7, 2), 
				LocalDate.of(2020, 7, 6), 
				2990000, // $2.99
				1, 
				2990000, // $2.99
				50, 
				1495000, // $1.50
				1495000); // $1.50
		
		RentalAgreement receivedAgreement = assertDoesNotThrow(() -> 
				checkoutService.checkout(testCase.toolCode, testCase.rentalDays, testCase.checkoutDate, testCase.discount));
		assertTrue(receivedAgreement.equals(expectedAgreement));
	}
	
	
	
	private class CheckoutTestCase {
		public String toolCode;
		public LocalDate checkoutDate;
		public int rentalDays;
		public int discount;
		
		public CheckoutTestCase(String toolCode, LocalDate checkoutDate, int rentalDays, int discount) {
			super();
			this.toolCode = toolCode;
			this.checkoutDate = checkoutDate;
			this.rentalDays = rentalDays;
			this.discount = discount;
		}
	}
}
