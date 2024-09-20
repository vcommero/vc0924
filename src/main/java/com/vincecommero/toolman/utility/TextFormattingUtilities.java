package com.vincecommero.toolman.utility;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class TextFormattingUtilities {

	/**
	 * Formats a currency integer value in microdollars as a print friendly format (e.g. "$X,XXX.XX").
	 * @param currencyValue - Value is an integer in microdollars. Rounding is down using "half-even" which is a standard used in finance.
	 * @return Formatted string of the currency value. (e.g. "$X,XXX.XX")
	 */
	public static String formatCurrency(long currencyValue) {
		BigDecimal currencyDecimal = new BigDecimal(currencyValue / 1000000f);
		currencyDecimal.setScale(2, RoundingMode.HALF_EVEN);
		NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(Locale.US);
		return currencyFormat.format(currencyDecimal);
	}
	
	/**
	 * Formats a LocalDate object as a string in mm/dd/yyyy format.
	 * @param date - Date as a LocalDate object
	 * @return Returns a date string in mm/dd/yyyy format.
	 */
	public static String formatDate(LocalDate date) {
		DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("mm/dd/yyyy");
		return dateFormatter.format(date);
	}
}
