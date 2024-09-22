package com.vincecommero.toolman.commandlineinterface;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.vincecommero.toolman.checkout.CheckoutService;
import com.vincecommero.toolman.checkout.model.RentalAgreement;
import com.vincecommero.toolman.commandlineinterface.utility.ConsoleIO;

@Component
public class CheckoutCommands {
	
	private CheckoutService checkoutService;
	private ConsoleIO consoleIO;
	
	public CheckoutCommands(CheckoutService checkoutService, ConsoleIO consoleIO) {
		this.checkoutService = checkoutService;
		this.consoleIO = consoleIO;
	}
	
	private Scanner scanner;
	
	public String checkout() {
		
		// Asks user for tool code
		String toolCode = "";
		while (toolCode.isBlank()) {
			toolCode = consoleIO.prompt("Please enter the code of the tool you wish to checkout: ");
			if (toolCode.isBlank())
				consoleIO.println("Invalid input: Please enter a tool code.");
		}
		
		
		// Asks user for rental duration
		String durationInput = "";
		Integer duration = null;
		while (duration == null) {
			durationInput = consoleIO.prompt("Please enter the rental duration in days: ");
			try {
				duration = Integer.parseInt(durationInput);
				if (duration < 1)
					throw new IllegalArgumentException();
			} catch (NumberFormatException nfe) {
				consoleIO.println("Invalid input: Please enter a valid integer number of days.");
			} catch (IllegalArgumentException ile) {
				consoleIO.println("Invalid input: Rental duration must be 1 day or greater.");
				duration = null;
			}
		}
		
		// Ask the user for a discount percentage
		String discountInput = "";
		Integer discountPercentage = null;
		while (discountPercentage == null) {
			discountInput = consoleIO.prompt("Please enter the rental price discount percentage (from 0 to 100) or press Enter for 0: ");
			if (discountInput.isBlank())
				discountPercentage = 0;
			else {
				try {
					discountPercentage = Integer.parseInt(discountInput);
					if (discountPercentage < 0 || discountPercentage > 100)
						throw new IllegalArgumentException();
				} catch (NumberFormatException nfe) {
					consoleIO.println("Invalid input: Please enter a valid integer number for the discount.");
				} catch (IllegalArgumentException ile) {
					consoleIO.println("Invalid input: Discount percentage can't be greater than 100% or less than 0%.");
					discountPercentage = null;
				}
			}
		}
		
		// Ask the user for a checkout date
		String checkoutDateInput = "";
		LocalDate checkoutDate = null;
		while (checkoutDate == null) {
			checkoutDateInput = consoleIO.prompt("Please enter the checkout date (in YYYY-MM-DD) or press Enter for today: ");
			if (checkoutDateInput.isBlank())
				checkoutDate = LocalDate.now();
			else {
				try {
					checkoutDate = LocalDate.parse(checkoutDateInput);
					if (checkoutDate.isBefore(LocalDate.now()))
						throw new IllegalArgumentException();
				} catch (DateTimeParseException dpe) {
					consoleIO.println("Invalid input: Please enter a valid date");
				} catch (IllegalArgumentException ile) {
					consoleIO.println("Invalid input: Please enter a current or future date.");
					checkoutDate = null;
				}
			}
		}
		
		RentalAgreement agreement;
		try {
			agreement = checkoutService.checkout(toolCode, duration, checkoutDate, discountPercentage);
			agreement.printAgreement(consoleIO.getOutputPrintStream());
			consoleIO.println(); // Add newline after agreement print
			return "Checkout was successful!";
		} catch (IllegalArgumentException ile) {
			return ile.getLocalizedMessage();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
}
