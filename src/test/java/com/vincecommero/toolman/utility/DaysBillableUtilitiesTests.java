package com.vincecommero.toolman.utility;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class DaysBillableUtilitiesTests {
	
	@Test
	void shouldDetermineIfADateIsAnIndependenceDayHoliday() {
		assertFalse(DaysBillableUtilities.isIndependenceDayHoliday(LocalDate.of(2024, 7, 5)));
		assertTrue(DaysBillableUtilities.isIndependenceDayHoliday(LocalDate.of(2024, 7, 4)));
		
		assertFalse(DaysBillableUtilities.isIndependenceDayHoliday(LocalDate.of(2021, 7, 4)));
		assertTrue(DaysBillableUtilities.isIndependenceDayHoliday(LocalDate.of(2021, 7, 5)));
		
		assertFalse(DaysBillableUtilities.isIndependenceDayHoliday(LocalDate.of(2020, 7, 4)));
		assertTrue(DaysBillableUtilities.isIndependenceDayHoliday(LocalDate.of(2020, 7, 3)));
	}
	
	@Test
	void shouldDetermineIfADateIsALaborDayHoliday() {
		assertFalse(DaysBillableUtilities.isLaborDayHoliday(LocalDate.of(2014, 8, 31)));
		assertTrue(DaysBillableUtilities.isLaborDayHoliday(LocalDate.of(2014, 9, 1)));
		assertFalse(DaysBillableUtilities.isLaborDayHoliday(LocalDate.of(2015, 9, 1)));
		assertTrue(DaysBillableUtilities.isLaborDayHoliday(LocalDate.of(2015, 9, 7)));
	}

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
		long output = DaysBillableUtilities.getBillableDays(LocalDate.of(2024, 9, 1), LocalDate.of(2024, 9, 7), true, false, false);
		assertEquals(4, output);
	}
}
