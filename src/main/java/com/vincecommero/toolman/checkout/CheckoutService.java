package com.vincecommero.toolman.checkout;

import java.time.LocalDate;
import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;

import com.vincecommero.toolman.checkout.model.RentalAgreement;
import com.vincecommero.toolman.tools.ToolService;
import com.vincecommero.toolman.tools.model.Tool;

import jakarta.persistence.EntityNotFoundException;

@Service
public class CheckoutService {
	
	private final ToolService toolService;
	
	public CheckoutService(ToolService toolService) {
		this.toolService = toolService;
	}

	public RentalAgreement checkout(String toolCode, int rentalDuration, LocalDate checkoutDate, int discountPercentage) {
		
		// Check if rental days and the discount percentage parameters are valid values
		if (rentalDuration <= 0)
			throw new IllegalArgumentException(rentalDuration + "days is not a valid rental duration. " + 
					"The duration of the rental must be greater than 1 day.");
		if (discountPercentage < 0 || discountPercentage > 100) {
			throw new IllegalArgumentException(discountPercentage + " is not a valid discount percentage. " + 
					"The discount percentage can't be less than 0% or greater than 100%.");
		}
		
		// Get tool by tool code
		Tool tool;
		try {
			tool = toolService.getToolByToolCode(toolCode);
			System.out.println(tool.toString());
		} catch (EntityNotFoundException | NoSuchElementException e) {
			throw new IllegalArgumentException("A tool with the tool code: '" + toolCode + "' was not found.");
		}
		
		// Generate rental agreement
		RentalAgreement agreement = new RentalAgreement();
		
		return agreement;
	}
}