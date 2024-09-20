package com.vincecommero.toolman.utility;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;

/**
 * Methods for calculating billable days and rental date operations.
 */
public class DaysBillableUtilities {

	/**
	 * Gets the due date of a rental given an initial checkout date and rental duration in days.
	 * @param checkoutDate - Initial checkout date.
	 * @param rentalDuration - Rental duration in days.
	 * @return
	 */
	public static LocalDate getDueDate(LocalDate checkoutDate, int rentalDuration) {
		return checkoutDate.plusDays(rentalDuration);
	}
	
	/**
	 * Gets the number of billable days in a rental from initial checkout date to (and including) the due date.
	 * @param checkoutDate - Initial checkout date.
	 * @param dueDate - Due date of the rental.
	 * @param weekdayCharge - Indicates if a weekday is billable or not.
	 * @param weekendCharge - Indicates if a weekend is billable or not.
	 * @param holidayCharge - Indicates if a holiday is billable or not.
	 * @return Returns the number of billable days in the rental.
	 */
	public static long getBillableDays(LocalDate checkoutDate, LocalDate dueDate, 
			boolean weekdayCharge, boolean weekendCharge, boolean holidayCharge) {
		return checkoutDate.datesUntil(dueDate.plusDays(1))
			.filter((cursorDate) -> checkIfDayIsBillable(cursorDate, weekdayCharge, weekendCharge, holidayCharge))
			.count();
	}
	
	/**
	 * Checks if a particular date is a billable day for rental purposes. In this case, holidays have priority in determining billability.
	 * @param date - The date to check.
	 * @param weekdayCharge - Indicates if a weekday is billable or not.
	 * @param weekendCharge - Indicates if a weekend is billable or not.
	 * @param holidayCharge - Indicates if a holiday is billable or not.
	 * @return Returns true if the day is billable. Returns false if not.
	 */
	public static boolean checkIfDayIsBillable(LocalDate date, boolean weekdayCharge, boolean weekendCharge, boolean holidayCharge) {
		if (isIndependenceDayHoliday(date) || isLaborDayHoliday(date)) {
			return holidayCharge;
		}
		else if (date.getDayOfWeek() == DayOfWeek.SATURDAY || date.getDayOfWeek() == DayOfWeek.SUNDAY) {
			return weekendCharge;
		}
		else {
			return weekdayCharge;
		}
	}
	
	
	// Holiday utilities
	
	/**
	 * Checks if the date falls on Independence Day. Note if July 4th is on a weekend day, Independence Day holiday will be on the closest weekday.
	 * @param date - The date to check.
	 * @return Returns true if the date falls on the calculated Independence Day for that year, else returns false.
	 */
	public static boolean isIndependenceDayHoliday(LocalDate date) {
		if (date.getMonth() == Month.JULY) {
			LocalDate independenceDay = LocalDate.of(date.getYear(), 7, 4);
			if (independenceDay.getDayOfWeek() == DayOfWeek.SATURDAY) {
				independenceDay = independenceDay.minusDays(1);
			}
			else if (independenceDay.getDayOfWeek() == DayOfWeek.SUNDAY) {
				independenceDay = independenceDay.plusDays(1);
			}
			
			return date.isEqual(independenceDay);
		}
		
		return false;
	}
	
	/**
	 * Checks if the date falls on Labor Day. Labor Day is defined as the first Monday in September.
	 * @param date - The date to check.
	 * @return Returns true if the date falls on the calculated Labor Day holiday, otherwise returns false.
	 */
	public static boolean isLaborDayHoliday(LocalDate date) {
		if (date.getMonth() == Month.SEPTEMBER) {
			LocalDate laborDay = LocalDate.of(date.getYear(), 9, 1);
			laborDay = laborDay.datesUntil(LocalDate.of(laborDay.getYear(), 10, 1))
					.filter((cursorDate) -> cursorDate.getDayOfWeek() == DayOfWeek.MONDAY)
					.findFirst()
					.orElseThrow();
			
			return date.isEqual(laborDay);
		}
		
		return false;
	}
}
