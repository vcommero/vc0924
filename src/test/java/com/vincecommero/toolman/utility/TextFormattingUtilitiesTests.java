package com.vincecommero.toolman.utility;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class TextFormattingUtilitiesTests {

	@Test
	void shouldProperlyFormatCurrencyValues() {
		assertEquals(TextFormattingUtilities.formatCurrency(1000000000), "$1,000.00");
		assertEquals(TextFormattingUtilities.formatCurrency(-1000000000), "-$1,000.00");
		assertEquals(TextFormattingUtilities.formatCurrency(0), "$0.00");
		assertEquals(TextFormattingUtilities.formatCurrency(10), "$0.00");
		assertEquals(TextFormattingUtilities.formatCurrency(Long.MAX_VALUE), "$9,223,372,036,854.78");
	}
	
	@Test
	void shouldProperlyFormatLocalDateValues() {
		assertEquals(TextFormattingUtilities.formatDate(LocalDate.of(2020, 10, 5)), "10/05/2020");
		assertEquals(TextFormattingUtilities.formatDate(LocalDate.of(2020, 10, 15)), "10/15/2020");
		assertEquals(TextFormattingUtilities.formatDate(LocalDate.of(2020, 5, 5)), "05/05/2020");
	}
}
