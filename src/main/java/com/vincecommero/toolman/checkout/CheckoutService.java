package com.vincecommero.toolman.checkout;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;

import com.vincecommero.toolman.checkout.model.RentalAgreement;
import com.vincecommero.toolman.tools.ToolService;
import com.vincecommero.toolman.tools.model.Tool;
import com.vincecommero.toolman.tools.model.ToolType;
import com.vincecommero.toolman.utility.DaysBillableUtilities;

@Service
public class CheckoutService {
	
	private final ToolService toolService;
	
	public CheckoutService(ToolService toolService) {
		this.toolService = toolService;
	}

	/**
	 * Performs operations to checkout a tool for rental and return a rental agreement with the details of the rental.
	 * @param toolCode - Code of the tool being checked out for rental.
	 * @param rentalDuration - Duration of the rental in days.
	 * @param checkoutDate - Calendar date of the rental checkout.
	 * @param discountPercentage - Discount as an integer ranging from 0 - 100.
	 * @return Returns a RentalAgreement object with the details of the rental.
	 */
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
		} catch (NoSuchElementException e) {
			throw new IllegalArgumentException("A tool with the tool code: '" + toolCode + "' was not found.");
		}
		
		// Compute calculated values for rental agreement
		ToolType toolType = tool.getToolType();
		LocalDate dueDate = DaysBillableUtilities.getDueDate(checkoutDate, rentalDuration);
		int chargeDays = (int) DaysBillableUtilities.getBillableDays(checkoutDate, dueDate, 
				toolType.getWeekdayCharge(), 
				toolType.getWeekendCharge(), 
				toolType.getHolidayCharge());
		long preDiscountCharge = (long) chargeDays * (long) toolType.getDailyCharge();
		BigDecimal discountPercentDecimal = BigDecimal.valueOf(discountPercentage).divide(BigDecimal.valueOf(100));
		BigDecimal discountAmount = BigDecimal
				.valueOf(preDiscountCharge)
				.multiply(discountPercentDecimal) // Note: Operations in BigDecimal to handle potential large decimal values here.
				.setScale(0, RoundingMode.HALF_UP);
		long finalCharge = preDiscountCharge - discountAmount.longValue();
		
		// Generate rental agreement
		RentalAgreement agreement = new RentalAgreement(
				tool.getToolCode(), 
				toolType.getTypeName(), 
				tool.getToolBrand(), 
				rentalDuration, 
				checkoutDate, 
				dueDate,
				toolType.getDailyCharge(), 
				chargeDays,
				preDiscountCharge,
				discountPercentage, 
				discountAmount.longValue(),
				finalCharge);
		
		return agreement;
	}
	
}