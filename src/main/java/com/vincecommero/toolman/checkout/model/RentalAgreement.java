package com.vincecommero.toolman.checkout.model;

import java.io.PrintStream;
import java.time.LocalDate;

import com.vincecommero.toolman.utility.TextFormattingUtilities;

public class RentalAgreement {
	
	// Note: Currency fields are in microdollars stored in integer values to avoid precision errors in computation.

	private String toolCode;
	private String toolType;
	private String toolBrand;
	private int rentalDays;
	private LocalDate checkoutDate;
	private LocalDate dueDate;
	private int dailyRentalCharge;
	private int chargeDays;
	private long prediscountCharge;
	private int discountPercent;
	private long discountAmount;
	private long finalCharge;
	
	public RentalAgreement(String toolCode, String toolType, String toolBrand, int rentalDays, LocalDate checkoutDate,
			LocalDate dueDate, int dailyRentalCharge, int chargeDays, long prediscountCharge, int discountPercent,
			long discountAmount, long finalCharge) {
		this.toolCode = toolCode;
		this.toolType = toolType;
		this.toolBrand = toolBrand;
		this.rentalDays = rentalDays;
		this.checkoutDate = checkoutDate;
		this.dueDate = dueDate;
		this.dailyRentalCharge = dailyRentalCharge;
		this.chargeDays = chargeDays;
		this.prediscountCharge = prediscountCharge;
		this.discountPercent = discountPercent;
		this.discountAmount = discountAmount;
		this.finalCharge = finalCharge;
	}

	// Getters and setters
	public String getToolCode() {
		return toolCode;
	}

	public String getToolType() {
		return toolType;
	}

	public String getToolBrand() {
		return toolBrand;
	}

	public int getRentalDays() {
		return rentalDays;
	}

	public LocalDate getCheckoutDate() {
		return checkoutDate;
	}

	public LocalDate getDueDate() {
		return dueDate;
	}

	public int getDailyRentalCharge() {
		return dailyRentalCharge;
	}

	public int getChargeDays() {
		return chargeDays;
	}

	public long getPrediscountCharge() {
		return prediscountCharge;
	}

	public int getDiscountPercent() {
		return discountPercent;
	}

	public long getDiscountAmount() {
		return discountAmount;
	}

	public long getFinalCharge() {
		return finalCharge;
	}
	
	// Public methods
	
	/**
	 * Prints a formatted document of the rental agreement to the desired print stream.
	 */
	public void printAgreement(PrintStream printStream) {
		StringBuilder builder = new StringBuilder();
		builder.append("Tool code: ").append(toolCode).append("\n");
		builder.append("Tool type: ").append(toolType).append("\n");
		builder.append("Tool brand: ").append(toolBrand).append("\n");
		builder.append("Rental days: ").append(rentalDays).append("\n");
		builder.append("Check out date: ").append(TextFormattingUtilities.formatDate(checkoutDate)).append("\n");
		builder.append("Due date: ").append(TextFormattingUtilities.formatDate(dueDate)).append("\n");
		builder.append("Daily rental charge: ").append(TextFormattingUtilities.formatCurrency(dailyRentalCharge)).append("\n");
		builder.append("Charge days: ").append(chargeDays).append("\n");
		builder.append("Pre-discount charge: ").append(TextFormattingUtilities.formatCurrency(prediscountCharge)).append("\n");
		builder.append("Discount percent: ").append(discountPercent).append("%\n");
		builder.append("Discount amount: ").append(TextFormattingUtilities.formatCurrency(discountAmount)).append("\n");
		builder.append("Final charge: ").append(TextFormattingUtilities.formatCurrency(finalCharge));
		
		printStream.println(builder.toString());
	}
	
	/**
	 * Prints a formatted document of the rental agreement to the console.
	 */
	public void printAgreementToConsole() {
		printAgreement(System.out);
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof RentalAgreement)) return false;
		RentalAgreement comparedAgreement = (RentalAgreement) obj;
	
		return this.getToolCode().equals(comparedAgreement.getToolCode()) &&
				this.getToolType().equals(comparedAgreement.getToolType()) &&
				this.getToolBrand().equals(comparedAgreement.getToolBrand()) &&
				this.getRentalDays() == comparedAgreement.getRentalDays() &&
				this.getCheckoutDate().isEqual(comparedAgreement.getCheckoutDate()) &&
				this.getDueDate().isEqual(comparedAgreement.getDueDate()) &&
				this.getDailyRentalCharge() == comparedAgreement.getDailyRentalCharge() &&
				this.getChargeDays() == comparedAgreement.getChargeDays() &&
				this.getPrediscountCharge() == comparedAgreement.getPrediscountCharge() &&
				this.getDiscountPercent() == comparedAgreement.getDiscountPercent() &&
				this.getDiscountAmount() == comparedAgreement.getDiscountAmount() &&
				this.getFinalCharge() == comparedAgreement.getFinalCharge();
	}
	
}
