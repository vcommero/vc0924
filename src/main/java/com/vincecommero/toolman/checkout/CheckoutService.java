package com.vincecommero.toolman.checkout;

import java.time.LocalDate;

public class CheckoutService {

	public void checkout(String toolCode, int rentalDays, LocalDate checkoutDate, int discountPercentage) {
		
		// Check if rental days and the discount percentage parameters are valid values
		if (rentalDays <= 0)
			throw new IllegalArgumentException(rentalDays + "days is not a valid rental duration. " + 
					"The duration of the rental must be greater than 1 day.");
		if (discountPercentage < 0 || discountPercentage > 100) {
			throw new IllegalArgumentException(discountPercentage + " is not a valid discount percentage. " + 
					"The discount percentage can't be less than 0% or greater than 100%.");
		}
		
		
	}
}