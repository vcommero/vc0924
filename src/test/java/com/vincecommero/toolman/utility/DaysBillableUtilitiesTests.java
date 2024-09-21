package com.vincecommero.toolman.utility;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class DaysBillableUtilitiesTests {

	@Test
	void shouldCalculateDueDateFromACheckoutDateAndRentalDuration() {
		assertThrows(IllegalArgumentException.class, () -> DaysBillableUtilities.getDueDate(LocalDate.of(2020, 10, 10), -1));
		assertEquals(LocalDate.of(2020, 3, 1), DaysBillableUtilities.getDueDate(LocalDate.of(2020, 2, 25), 5));
		assertEquals(LocalDate.of(2021, 3, 1), DaysBillableUtilities.getDueDate(LocalDate.of(2021, 2, 25), 4));
	}
	
	@Test
	void shouldCalculateBillableDaysGivenProperParameters() {
		assertThrows(IllegalArgumentException.class, () -> 
			DaysBillableUtilities.getBillableDays(LocalDate.of(2020, 10, 5), LocalDate.of(2020, 10, 4), false, false, false));
	}
}
