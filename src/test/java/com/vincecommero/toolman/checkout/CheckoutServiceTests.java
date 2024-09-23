package com.vincecommero.toolman.checkout;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.vincecommero.toolman.tools.ToolService;
import com.vincecommero.toolman.tools.model.Tool;
import com.vincecommero.toolman.tools.model.ToolType;

@ExtendWith(MockitoExtension.class)
class CheckoutServiceTests {
	
	Tool testTool1 = new Tool();
	
	ToolType testType1 = new ToolType();
	
	@BeforeEach
	public void initialSetup() {
		testType1.setTypeName("Type1");
		testType1.setDailyCharge(1000000);
		testType1.setWeekdayCharge(true);
		testType1.setWeekendCharge(false);
		testType1.setHolidayCharge(true);
		
		testTool1.setToolCode("ABCD");
		testTool1.setToolType(testType1);
		testTool1.setToolBrand("Test Brand");
	}
	
	@Mock
	private ToolService toolService;

	@InjectMocks
	private CheckoutService checkoutService;
	
	@Test
	void shouldThrowExceptionIfRentalDurationLessThanZero() {
		int rentalDuration = -1;
		Exception exception = assertThrows(IllegalArgumentException.class, 
				() -> checkoutService.checkout("ABCD", rentalDuration, LocalDate.now(), 0));
		
		String expectedMessage = rentalDuration + "days is not a valid rental duration. " + 
				"The duration of the rental must be greater than 1 day.";
		assertTrue(exception.getLocalizedMessage().equals(expectedMessage));
	}
	
	@Test
	void shouldThrowExceptionIfDiscountPercentageOutsideOfRange() {
		int[] discountValues = {-1, 101};
		for (int discountPercent : discountValues) {
			Exception exception = assertThrows(IllegalArgumentException.class, 
					() -> checkoutService.checkout("ABCD", 1, LocalDate.now(), discountPercent));
			
			String expectedMessage = discountPercent + " is not a valid discount percentage. " + 
					"The discount percentage can't be less than 0% or greater than 100%.";
			assertTrue(exception.getLocalizedMessage().equals(expectedMessage));
		}
	}
	
	@Test
	void shouldThrowExceptionIfToolNotFound() {
		String toolCode = "ABCD";
		
		when(toolService.getToolByToolCode(anyString())).thenThrow(new NoSuchElementException());
		
		Exception exception = assertThrows(IllegalArgumentException.class,
				() -> checkoutService.checkout(toolCode, 1, LocalDate.now(), 0));
		
		String expectedMessage = "A tool with the tool code: '" + toolCode + "' was not found.";
		assertTrue(exception.getLocalizedMessage().equals(expectedMessage));
	}
	
	
	
}
