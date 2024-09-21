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
	 * @param currencyValue - Value is an integer in microdollars. Rounding is "half-up".
	 * @return Formatted string of the currency value. (e.g. "$X,XXX.XX")
	 */
	public static String formatCurrency(long currencyValue) {
		BigDecimal currencyDecimal = BigDecimal.valueOf(currencyValue).divide(BigDecimal.valueOf(1000000));
		currencyDecimal.setScale(2, RoundingMode.HALF_UP);
		NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(Locale.US);
		return currencyFormat.format(currencyDecimal);
	}
	
	/**
	 * Formats a LocalDate object as a string in MM/dd/yyyy format.
	 * @param date - Date as a LocalDate object
	 * @return Returns a date string in MM/dd/yyyy format.
	 */
	public static String formatDate(LocalDate date) {
		DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
		return dateFormatter.format(date);
	}
}
