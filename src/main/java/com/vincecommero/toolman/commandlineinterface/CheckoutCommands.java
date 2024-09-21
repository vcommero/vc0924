package com.vincecommero.toolman.commandlineinterface;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.vincecommero.toolman.checkout.CheckoutService;
import com.vincecommero.toolman.checkout.model.RentalAgreement;

@Component
public class CheckoutCommands {
	
	@Autowired
	private CheckoutService checkoutService;
	
	private Scanner scanner;
	
	public String checkout(Scanner scanner) {
		if (scanner != null)
			this.scanner = scanner;
		else
			this.scanner = new Scanner(System.in);
		
		// Asks user for tool code
		String toolCode = "";
		while (toolCode.isBlank()) {
			toolCode = prompt("Please enter the code of the tool you wish to checkout: ");
			if (toolCode.isBlank())
				System.out.println("Invalid input: Please enter a tool code.");
		}
		
		
		// Asks user for rental duration
		String durationInput = "";
		Integer duration = null;
		while (duration == null) {
			durationInput = prompt("Please enter the rental duration in days: ");
			try {
				duration = Integer.parseInt(durationInput);
				if (duration < 1)
					throw new IllegalArgumentException();
			} catch (NumberFormatException nfe) {
				System.out.println("Invalid input: Please enter a valid integer number of days.");
			} catch (IllegalArgumentException ile) {
				System.out.println("Invalid input: Rental duration must be 1 day or greater.");
				duration = null;
			}
		}
		
		// Ask the user for a discount percentage
		String discountInput = "";
		Integer discountPercentage = null;
		while (discountPercentage == null) {
			discountInput = prompt("Please enter the rental price discount percentage (from 0 to 100): ");
			if (discountInput.isBlank())
				discountPercentage = 0;
			else {
				try {
					discountPercentage = Integer.parseInt(discountInput);
					if (discountPercentage < 0 || discountPercentage > 100)
						throw new IllegalArgumentException();
				} catch (NumberFormatException nfe) {
					System.out.println("Invalid input: Please enter a valid integer number for the discount.");
				} catch (IllegalArgumentException ile) {
					System.out.println("Invalid input: Discount percentage can't be greater than 100% or less than 0%.");
					discountPercentage = null;
				}
			}
		}
		
		// Ask the user for a checkout date
		String checkoutDateInput = "";
		LocalDate checkoutDate = null;
		while (checkoutDate == null) {
			checkoutDateInput = prompt("Please enter the checkout date (in YYYY-MM-DD) or press Enter for today: ");
			if (checkoutDateInput.isBlank())
				checkoutDate = LocalDate.now();
			else {
				try {
					checkoutDate = LocalDate.parse(checkoutDateInput);
					if (checkoutDate.isBefore(LocalDate.now()))
						throw new IllegalArgumentException();
				} catch (DateTimeParseException dpe) {
					System.out.println("Invalid input: Please enter a valid date");
				} catch (IllegalArgumentException ile) {
					System.out.println("Invalid input: Please enter a current or future date.");
					checkoutDate = null;
				}
			}
		}
		
		RentalAgreement agreement;
		try {
			agreement = checkoutService.checkout(toolCode, duration, checkoutDate, discountPercentage);
			agreement.printAgreementToConsole();
			return "\nCheckout was successful!";
		} catch (IllegalArgumentException ile) {
			return ile.getLocalizedMessage();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	private String prompt(String message) {
        System.out.print(message);
        if (scanner.hasNextLine()) {
            String input = scanner.nextLine();
            return input;
        } else {
            return "";
        }
	}
}
